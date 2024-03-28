package com.cydeo.repository;

import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    // class will be representing db
    public static List<Account> accountList = new ArrayList<>();

    public Account save(Account account) {
        accountList.add(account);
        return account;
    }


    public List<Account> findAllAccounts() {
        return accountList;
    }

    public Account findById(UUID id) {
        return findAllAccounts().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("Account does not exist"));
    }
}