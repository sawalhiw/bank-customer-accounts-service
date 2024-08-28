package bank.mapper;

import bank.dto.AccountDto;
import bank.entity.Account;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@AnnotateWith(GeneratedMapper.class)
public abstract class AccountMapper extends BaseMapper<AccountDto, Account> {
}
