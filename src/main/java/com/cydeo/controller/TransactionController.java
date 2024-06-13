package com.cydeo.controller;

import com.cydeo.dto.TransactionDTO;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model) {

        model.addAttribute("transaction", new TransactionDTO());
        model.addAttribute("accounts", accountService.listAllAccounts());
        model.addAttribute("lastTransactions", transactionService.last10Transactions());


        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transaction") TransactionDTO transactionDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("accounts", accountService.listAllActiveAccounts());
            model.addAttribute("lastTransactions", transactionService.last10Transactions());

            return "transaction/make-transfer";

        }
        return "redirect:/make-transfer";
    }

    @GetMapping("/transaction/{id}")
    public String getTransactionList(@PathVariable("id") Long id, Model model){
        System.out.println(id);
        //TASK
        //get the list of transactions based on the id and return as a model attribute
        //findTransactionListById
        List<TransactionDTO> transactionDTOListById = transactionService.findTransactionListById(id);
        model.addAttribute("transactions", transactionDTOListById);
        //(service and repository)
        return "transaction/transactions";
    }
    }

