package bank.entity;

import bank.dto.AccountStatus;
import bank.dto.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "account")
@SuperBuilder
public class Account extends BaseEntity {
    @JoinColumn(name = "customer_id")
    private String customerId;
    private Integer balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
