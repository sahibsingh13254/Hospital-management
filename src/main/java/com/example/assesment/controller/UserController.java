package com.example.assesment.controller;


import com.example.assesment.entity.UserEntity;
import com.example.assesment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public UserEntity createuser(@RequestBody UserEntity userEntity)
    {
        return userService.createUser(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
         userService.deleteUser(id);
    }


}
