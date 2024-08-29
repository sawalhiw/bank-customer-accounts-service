package bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class AccountDto extends BaseDto {
    @NotNull(message = "Account shouldn't be null.")
    private AccountStatus status;
    @Min(value = 1)
    private Integer balance;
    @NotBlank(message = "CustomerId shouldn't be null or empty.")
    private String customerId;
    @NotNull(message = "AccountType shouldn't be null.")
    private AccountType type;
}
