package bank.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseDto {
    @Size(min = 10, max = 11, message = "Id should be 10 digits")
    private String id;
}
