package com.bank.atm.controller;

import com.bank.atm.model.Transaction;
import com.bank.atm.model.User;
import com.bank.atm.service.TransactionService;
import com.bank.atm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class TransactionController {
    
    
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        
        return transactionService.getAllTransactions();
    }
    
    @GetMapping("/transaction/{id}")
    public List<Transaction> getTransactionsById(@PathVariable("id") Long id) {
        
        return transactionService.getUserTransactionsByIdUser(id);
    }
    
    
    @PostMapping("/transaction")
    public String addTransaction(@RequestBody Transaction transaction) {
        
        boolean isNegative = transactionService.isNegative(transaction);
        if (isNegative) return "Sum can not be negative!";
        
        transactionService.setTransaction(transaction);
        
        return "Transaction completed!";
    }
    
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Transaction transaction) {
        
        User user = transactionService.getUserWithdraw(transaction);
        if (isNull(user)) return "You don't have enough money";
        userService.saveOrUpdateUser(user);
        transactionService.saveTransaction(transaction);
        
        return "Withdraw:"+transaction.getSumTransaction();
    }
    
    
    
    
    
    
    
    
}
