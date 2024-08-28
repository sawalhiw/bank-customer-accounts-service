package bank.feign;

import bank.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bank-customer-service", url = "localhost:9091/api/customers")
public interface BankCustomerClient {
    @GetMapping("{id}")
    CustomerDto getCustomer(@PathVariable final String id);
}
