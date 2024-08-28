package bank.feign;

import lombok.Generated;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "bank-auth-service", url = "localhost:9090/api/validate-token")
@Generated
public interface BankAuthServiceClient {
    @PostMapping
    ResponseEntity<?> validateToken(@RequestHeader("Authorization") final String authorization);
}
