package com.projeto.changebookusers.service;

import com.projeto.changebookusers.domain.ChangeBookUser;
import com.projeto.changebookusers.repository.ChangeBookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChangeBookDetailsService implements UserDetailsService {

    private final ChangeBookUserRepository changeBookUserRepository;

    @Autowired
    public ChangeBookDetailsService(ChangeBookUserRepository changeBookUserRepository) {
        this.changeBookUserRepository = changeBookUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ChangeBookUser changeBookUser = Optional.ofNullable(changeBookUserRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        List<GrantedAuthority> role_user = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new User(changeBookUser.getEmail(), changeBookUser.getPassword(), role_user);
    }
}
