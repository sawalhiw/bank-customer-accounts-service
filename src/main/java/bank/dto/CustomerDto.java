package bank.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CustomerDto extends BaseDto {
    @NotBlank
    @Size(min = 7, max = 8)
    private String associatedLegalId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name should contain only letters and spaces.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^(male|female)$", message = "Gender should be either 'male' or 'female'.")
    private String gender;

    @NotNull(message = "Type shouldn't be null.")
    private Type type;

    @NotNull
    private AddressDto address;

    @NotBlank
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Phone number should be valid and can contain country code with a total length between 10 and 15 digits.")
    private String phoneNumber;

    @NotBlank
    @Email(message = "Email should be valid.")
    private String email;

    @Past(message = "Date of birth should be in the past.")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
}
