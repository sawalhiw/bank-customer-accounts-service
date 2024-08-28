package bank.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponseDto {
    private String message;
    private Integer statusCode;
}
