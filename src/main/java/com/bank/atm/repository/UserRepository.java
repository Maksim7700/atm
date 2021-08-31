package com.bank.atm.repository;

import com.bank.atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByCardNumber(String card);
    User findUserById(Long id);
}
