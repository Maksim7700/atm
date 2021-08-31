package com.bank.atm.service;

import com.bank.atm.model.User;
import com.bank.atm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    
    public User getCurrentUser() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByCardNumber(authentication.getName());
    }
    
    public void saveOrUpdateUser(User updateUser) {
        
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userRepository.save(updateUser);
    }
    
    public void deleteUser(User user) {
        
        userRepository.delete(user);
    }
    
    public User findUserById(Long id) {
        
        return userRepository.findUserById(id);
    }
    
    public List<User> findAll() {
        
        return userRepository.findAll();
    }
}
