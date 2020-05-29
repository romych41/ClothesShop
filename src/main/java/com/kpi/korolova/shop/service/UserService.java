package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.entities.User;
import com.kpi.korolova.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addNewAdmin(User user) {
        userRepository.save(user);
    }

    public void editAdmin(User user) {
        if(user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(userRepository.getOne(user.getId()).getPassword());
        }
        userRepository.save(user);
    }

    public void removeAdmin(int id) {
        userRepository.deleteById(id);
    }

    public List<User> getAdmins() {
        List<User> result = userRepository.findAllByRole("ROLE_ADMIN");
        for(User user : result) {
            user.setPassword("");
        }
        return result;
    }
}
