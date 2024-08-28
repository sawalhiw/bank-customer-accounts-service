package bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class AddressDto extends BaseDto {
    @NotBlank(message = "Country shouldn't be null.")
    private String country;
    @NotBlank(message = "State shouldn't be null.")
    private String state;
    @NotBlank(message = "Street shouldn't be null.")
    private String street;
}
