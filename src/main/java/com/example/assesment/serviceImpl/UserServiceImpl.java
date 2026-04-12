package com.example.assesment.serviceImpl;

import java.util.List;

import com.example.assesment.entity.UserEntity;
import org.springframework.stereotype.Service;

import com.example.assesment.repository.UserRepository;
import com.example.assesment.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserEntity createUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found at Id :" +id));
    }
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
 
}
