package com.example.assesment.service;

import com.example.assesment.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
    User getUserById (Long id);
    void deleteUser(Long id);

}
