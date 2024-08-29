package bank.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmailNotificationDto {
    private String customerId;
    private String subject;
    private String body;
}
