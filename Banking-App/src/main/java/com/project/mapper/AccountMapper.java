package com.project.mapper;

import com.project.dto.Accountdto;
import com.project.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(Accountdto accountdto) {

        Account account = new Account(
                accountdto.getId(),
                accountdto.getAccountHolderName(),
                accountdto.getBalance()


        );

        return account;

    }

    public static Accountdto mapToAccountDto(Account account) {
        Accountdto accountdto = new Accountdto(
                account.getId(),
                account.getAccount_Holder(),
                account.getBalance()

        );
        return accountdto;
    }
}
