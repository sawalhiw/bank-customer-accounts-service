package bank.repository;

import bank.dto.AccountType;
import bank.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends BaseRepository<Account> {
    Boolean existsAccountByCustomerIdAndType(final String customerId,
                                             final AccountType type);

    @Query("SELECT CASE WHEN COUNT(a) > 10 THEN TRUE ELSE FALSE END " +
            "FROM Account a WHERE a.customerId = :customerId")
    boolean hasMoreThanTenAccounts(@Param("customerId") String customerId);

}
