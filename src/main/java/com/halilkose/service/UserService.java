package com.halilkose.service;

import com.halilkose.model.Files;
import com.halilkose.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User findByEmail(String email);

    public List<User> getAllUsers();

}
