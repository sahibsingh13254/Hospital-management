package com.example.assesment.security;

import com.example.assesment.entity.UserEntity;
import com.example.assesment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService { //  implementing Spring's interface,   Spring will call OUR implementation!

    private final UserRepository userRepository;    // we need this to find user in DB!

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" +name)); // if user not found → throw exception


        // Step 2: convert UserEntity → UserDetails
        return User.builder()
                .username(user.getName())    // from OUR UserEntity
                .password(user.getPassword())  //encrypted password from DB
                .authorities(user.getRole())  //ROLE_ADMIN or ROLE_USER
                .build();
        // now Spring understands this user!
    }
}

