package com.projeto.changebookusers.service;

import com.projeto.changebookusers.domain.User;
import com.projeto.changebookusers.domain.data.UserResponse;
import com.projeto.changebookusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        if (user != null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        }else
            throw new RuntimeException("invalid data.");
    }
    public void updateUser(User user){
        if (user != null && existsUserById(user.getCpf())){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
        }else
            throw new RuntimeException("invalid data.");
    }

    public UserResponse getUserById(String cpf){
        User user = userRepository.findByCpf(cpf);
        return UserResponse.builder()
                .userName(user.getUserName())
                .cpf(user.getCpf())
                .city(user.getCity())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    public boolean existsUserById(String cpf){
        return userRepository.existsById(cpf);
    }

}
