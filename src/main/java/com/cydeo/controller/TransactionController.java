package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model) {
  model.addAttribute("transaction", Transaction.builder().build());
  model.addAttribute("accounts", accountService.listAllAccounts());
  model.addAttribute("lastTransactions", transactionService.last10Transactions());


        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@ModelAttribute("transaction") Transaction transaction){

        Account sender = accountService.findById(transaction.getSender());
        Account receiver = accountService.findById(transaction.getReceiver());
        transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }

    @GetMapping("/transaction/{id}")
    public String TransactionList(@PathVariable("id") UUID id, Model model){

        System.out.println(id);
        model.addAttribute("transactions",transactionService.findTransactionByID(id));

        return "transaction/transactions";
    }
    }

