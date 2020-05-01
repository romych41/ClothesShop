package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.model.User;
import com.kpi.korolova.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }
        return null;
    }

    public int addNewUser(User user) {
        return userRepository.save(user).getId();
    }
}
