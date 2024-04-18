package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Date;
import java.util.UUID;

@Controller
public class AccountController {

    /*
        write a method to return index.html including account list information
        endpoint: index
     */
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model){

        model.addAttribute("accountList",accountService.listAllAccounts());

        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateFormPage(Model model){

        //we need to provide empty account object
        model.addAttribute("account", Account.builder().build());
        //we need to provide accountType enum info for filling the dropdown options
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    @PostMapping("/create")
    public String captureInformation(@ModelAttribute("account") Account account){
        Account newAccount = accountService.createNewAccount(account.getBalance(),new Date(),account.getAccountType(),account.getUserId());
        System.out.println(newAccount);
        return "redirect:/index";
}

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id) {
        System.out.println(id);
        accountService.deleteAccount(id);
        return "redirect:/index";
    }
    @GetMapping("/activate/{id}")
    public String activeAccount(@PathVariable("id") UUID id) {
        System.out.println(id);
        accountService.activateAccount(id);
        return "redirect:/index";
    }
}