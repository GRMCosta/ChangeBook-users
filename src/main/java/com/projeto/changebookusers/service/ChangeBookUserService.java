package com.projeto.changebookusers.service;

import com.projeto.changebookusers.domain.ChangeBookUser;
import com.projeto.changebookusers.repository.ChangeBookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ChangeBookUserService {

    private ChangeBookUserRepository changeBookUserRepository;

    @Autowired
    public ChangeBookUserService(ChangeBookUserRepository changeBookUserRepository) {
        this.changeBookUserRepository = changeBookUserRepository;
    }

    public void saveUser(ChangeBookUser user){
        if (user != null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            changeBookUserRepository.save(user);
        }else
            throw new RuntimeException("invalid data.");
    }

}
