package com.cydeo;

import com.cydeo.enums.AccountType;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);

        //get account and transaction service beans
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);

        //create 2 accounts sender and receiver
//        AccountDTO sender = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING, 1L);
//        AccountDTO receiver = accountService.createNewAccount(BigDecimal.valueOf(30), new Date(), AccountType.SAVING, 1L);
//        Account receiver3 = accountService.createNewAccount(BigDecimal.valueOf(2300), new Date(), AccountType.CHECKING, 123L);
//        Account receiver4 = accountService.createNewAccount(BigDecimal.valueOf(4322), new Date(), AccountType.SAVING, 143L);
//        Account sender2 = null;
//        accountService.listAllAccounts().forEach(System.out::println);
//
//        transactionService.makeTransfer(sender,receiver,new BigDecimal(70),new Date(),"Transaction 1");
//
//        System.out.println(transactionService.findAllTransaction().get(0));
//
//        accountService.listAllAccounts().forEach(System.out::println);


    }

    @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }

        @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }
    }


