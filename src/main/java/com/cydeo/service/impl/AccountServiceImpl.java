package com.cydeo.service.impl;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {

        // create account object
        Account account = Account.builder()
                .id(UUID.randomUUID())
                .balance(balance)
                .accountType(accountType)
                .userId(userId)
                .creationDate(createDate)
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        // save into db
        Account saved = accountRepository.save(account);

        //return onj
        return saved;
    }
    @Override
    public List<Account> listAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    @Override
    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id);
        account.setAccountStatus(AccountStatus.DELETED);

    }

    @Override
    public void activateAccount(UUID id) {
        Account account = accountRepository.findById(id);
        account.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id);
    }


}
