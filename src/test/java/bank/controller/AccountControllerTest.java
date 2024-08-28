package bank.controller;

import bank.config.BaseWebTest;
import bank.dto.*;
import bank.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        AccountController.class,
        AccountService.class
})
@RunWith(SpringRunner.class)
public class AccountControllerTest extends BaseWebTest<AccountDto> {
    @MockBean
    private AccountService service;
    private final String accountId = "1234567899";

    @Override
    protected String feature() {
        return "accounts";
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        testDelete(accountId);
    }

    @Test
    public void testFindById() throws Exception {
        testGetById(accountId);
    }

    @Test
    public void testFindAll() throws Exception {
        testList(10, 0);
    }

    @Test
    public void testUpdateCustomerShouldReturnBadRequest() throws Exception {
        final AccountDto dto = AccountDto
                .builder()
                .id(accountId)
                .balance(0)
                .build();
        testPut(accountId, dto, status().isBadRequest());
    }

    @Test
    public void testUpdateCustomerShouldReturnOk() throws Exception {
        final AccountDto dto = AccountDto
                .builder()
                .id(accountId)
                .type(AccountType.SALARY)
                .status(AccountStatus.ACTIVE)
                .customerId("1234566")
                .balance(1000)
                .build();
        testPut(accountId, dto, status().isOk());
    }

    @Test
    public void testCreateCustomerShouldReturnOk() throws Exception {
        final AccountDto dto = AccountDto
                .builder()
                .id(accountId)
                .type(AccountType.SALARY)
                .status(AccountStatus.ACTIVE)
                .customerId("1234566")
                .balance(1000)
                .build();
        testPost(dto, status().isOk());
    }

    @Test
    public void testCreateCustomerShouldReturnBadRequest() throws Exception {
        AccountDto dto = AccountDto
                .builder()
                .id(accountId)
                .balance(0)
                .build();
        testPost(dto, status().isBadRequest());
    }
}