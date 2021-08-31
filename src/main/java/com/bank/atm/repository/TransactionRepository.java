package com.bank.atm.repository;

import com.bank.atm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsByUserFromId(Long id);
}
