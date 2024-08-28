package bank.service.implementation;

import bank.dto.AccountDto;
import bank.dto.FeatureInfoDto;
import bank.entity.Account;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountDto> implements AccountService {
    public AccountServiceImpl(AccountRepository repository,
                              AccountMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected FeatureInfoDto featureInfo() {
        return FeatureInfoDto
                .builder()
                .single("Account")
                .plural("Accounts")
                .build();
    }
}
