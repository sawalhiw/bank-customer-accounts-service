package bank.config;


import bank.controller.AccountController;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("accountConfig")
public class AccountConfiguration {

    @Bean
    public AccountController accountController() {
        return Mockito.mock(AccountController.class);
    }

    @Bean
    public AccountService accountService() {
        return Mockito.mock(AccountService.class);
    }

    @Bean
    public AccountRepository accountRepository() {
        return Mockito.mock(AccountRepository.class);
    }

    @Bean
    public AccountMapper customerMapper() {
        return Mockito.mock(AccountMapper.class);
    }
}
