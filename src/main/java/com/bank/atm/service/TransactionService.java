package com.bank.atm.service;

import com.bank.atm.model.Transaction;
import com.bank.atm.model.User;
import com.bank.atm.repository.TransactionRepository;
import com.bank.atm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private UserService userService;
    
    public List<Transaction> getAllTransactions() {
        
        return transactionRepository.findAll();
    }
    
    public List<Transaction> getUserTransactionsByIdUser(Long id) {
        
        return transactionRepository.findTransactionsByUserFromId(id);
    }
    
    public void saveTransaction(Transaction transaction) {
        
        transactionRepository.save(transaction);
    }
    
    public User getUserWithdraw(Transaction transaction) {
        
        User user = userService.getCurrentUser();
        int currentSum = Integer.parseInt(user.getCash());
        int withdraw = Integer.parseInt(transaction.getSumTransaction());
        int sum;
        if (withdraw > currentSum) {
            return null;
        } else {
            sum = currentSum - withdraw;
        }
        user.setCash(String.valueOf(sum));
        transaction.setUserFromId(user.getId());
        transaction.setSumTransaction("-"+ transaction.getSumTransaction());
        return user;
    }
    
    
    public Boolean isNegative(Transaction transaction) {
        
        if (transaction.getSumTransaction().contains("-")) {
            return true;
        }
        return false;
    }
    
    public void setTransaction(Transaction transaction) {
        
        User user = setNewSum(transaction);
        
        userService.saveOrUpdateUser(user);
        transactionRepository.save(transaction);
    }
    
    private User setNewSum(Transaction transaction) {
        
        User user = userService.findUserById(transaction.getUserToId());
        User currentUser = userService.getCurrentUser();
        transaction.setUserFromId(currentUser.getId());
        int currentSum = Integer.parseInt(user.getCash());
        int additionalSum = Integer.parseInt(transaction.getSumTransaction());
        int sum = currentSum + additionalSum;
        user.setCash(String.valueOf(sum));
        return user;
    }
}
