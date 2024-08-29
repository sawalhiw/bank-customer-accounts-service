package bank.service.implementation;


import bank.dto.AccountDto;
import bank.dto.CustomerDto;
import bank.entity.Account;
import bank.exception.NotFoundException;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import bank.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Before
    public void setup() {
        setup(mapper, repository, new AccountServiceImpl(repository, mapper));
    }

    @Override
    protected Account createEntity() {
        return Account
                .builder()
                .id("1")
                .build();
    }

    @Override
    protected AccountDto createDto() {
        return AccountDto
                .builder()
                .id("1")
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

}