package com.example.miniquest.service;

import com.example.miniquest.model.Role;
import com.example.miniquest.model.User;
import com.example.miniquest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public Model addUser(User user, Model model) {
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
        model.addAttribute("user", user);
        return model;
    }

    public User findUser(User user) {
        return userRepository.findUserByUsername(user.getUsername());
    }

    public Model addAdmin(User user, Model model) {
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActive(true);
        userRepository.save(user);
        model.addAttribute("user", user);
        return model;
    }

}
