package com.example.assesment.service;

import com.example.assesment.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);
    List<UserEntity> getAllUsers();
    UserEntity getUserById (Long id);
    void deleteUser(Long id);

}
