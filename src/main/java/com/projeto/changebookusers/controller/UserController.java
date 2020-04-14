package com.projeto.changebookusers.controller;

import com.projeto.changebookusers.domain.JwtResponse;
import com.projeto.changebookusers.domain.User;
import com.projeto.changebookusers.service.ChangeBookDetailsService;
import com.projeto.changebookusers.service.UserService;
import com.projeto.changebookusers.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/change-book/v1/users")
@CrossOrigin
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private ChangeBookDetailsService changeBookDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService, ChangeBookDetailsService changeBookDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.changeBookDetailsService = changeBookDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user){
        userService.saveUser(user);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/token/valid")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
        if (token == null || token.isEmpty() || token.isBlank())
            return ResponseEntity.status(401).body("Token not found.");
        token = token.substring(7);
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        if (!jwtTokenUtil.isTokenExpired(token) && userService.existsUserById(userName))
            return ResponseEntity.ok().body("");
        else
            return ResponseEntity.status(401).body("Token is not valid.");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user){
        userService.saveUser(user);
        return ResponseEntity.ok().body("");
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = changeBookDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
