package bank.service.implementation;

import bank.dto.*;
import bank.entity.Account;
import bank.exception.ValidationException;
import bank.feign.BankCustomerClient;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;



@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountDto> implements AccountService {
    private final AccountRepository repository;
    private final BankCustomerClient client;

    public AccountServiceImpl(AccountRepository repository,
                              AccountMapper mapper,
                              BankCustomerClient client) {
        super(repository, mapper);
        this.repository = repository;
        this.client = client;
    }

    @Override
    protected FeatureInfoDto featureInfo() {
        return FeatureInfoDto
                .builder()
                .single("Account")
                .plural("Accounts")
                .build();
    }

    @Override
    protected void validate(AccountDto dto) {
        CustomerDto customer = client.getCustomer(dto.getCustomerId());

        final String customerAssociatedLegalId = customer.getAssociatedLegalId();

        if (Strings.isBlank(dto.getId()) || !dto.getId().startsWith(customerAssociatedLegalId)) {
            throw new ValidationException("Account should be 10 digits starting with the customerId as the starting 7 digits.");
        }

        if (repository.hasMoreThanTenAccounts(dto.getCustomerId())) {
            throw new ValidationException("Customer should have less than or equals 10 accounts.");
        }

        if (repository.existsAccountByCustomerIdAndType(dto.getCustomerId(), AccountType.SALARY)) {
            throw new ValidationException("Customer should have only one SALARY account.");
        }
    }
}
