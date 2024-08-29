package bank.service.implementation;


import bank.dto.AccountDto;
import bank.dto.CustomerDto;
import bank.entity.Account;
import bank.exception.NotFoundException;
import bank.exception.ValidationException;
import bank.feign.BankCustomerClient;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import bank.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"accountConfig"})
public class AccountServiceImplTest extends BaseServiceTest<Account, AccountDto> {
    @MockBean
    private AccountMapper mapper;
    @MockBean
    private AccountRepository repository;
    @Mock
    private BankCustomerClient client;

    @Before
    public void setup() {
        setup(mapper, repository, new AccountServiceImpl(repository, mapper, client));
        when(client.getCustomer(any())).thenReturn(createCustomer());
    }

    @Override
    protected Account createEntity() {
        return Account
                .builder()
                .id("1234567899")
                .customerId("1234567")
                .build();
    }

    @Override
    protected AccountDto createDto() {
        return AccountDto
                .builder()
                .id("1234567899")
                .customerId("1234567")
                .build();
    }

    @Test
    public void testFindAll() {
        super.testFindAll();
    }

    @Test
    public void testDeleteById() {
        super.testDeleteById();
    }

    @Test
    public void testFindById() {
        super.testFindById();
    }

    @Test
    public void testCreate() {
        super.testCreate();
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateByIdShouldThrowNotFoundException() {
        super.testUpdate();
    }

    @Test
    public void testUpdateByIdShouldSuccess() {
        when(repository.existsById(any())).thenReturn(true);
        super.testUpdate();
    }

    @Test(expected = ValidationException.class)
    public void testUpdateByIdShouldFailBecauseAccountDidNotStartWithCustomerId() {
        when(repository.existsById(any())).thenReturn(true);
        CustomerDto customerDto = createCustomer();
        customerDto.setAssociatedLegalId("9771324");
        when(client.getCustomer(any())).thenReturn(customerDto);
        super.testUpdate();
    }

    @Test(expected = ValidationException.class)
    public void testUpdateByIdShouldFailBecauseCustomerHasMoreThanTenAccounts() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.hasMoreThanTenAccounts(any())).thenReturn(true);
        super.testUpdate();
    }

    @Test(expected = ValidationException.class)
    public void testUpdateByIdShouldFailBecauseCustomerHasSalaryAccount() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.existsAccountByCustomerIdAndType(any(), any())).thenReturn(true);
        super.testUpdate();
    }

    private CustomerDto createCustomer() {
        return CustomerDto.builder().associatedLegalId("1234567").build();
    }

}