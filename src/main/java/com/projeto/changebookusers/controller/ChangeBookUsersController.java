package com.projeto.changebookusers.controller;

import com.projeto.changebookusers.domain.ChangeBookUser;
import com.projeto.changebookusers.service.ChangeBookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/change-book/v1/users")
public class ChangeBookUsersController {

    private ChangeBookUserService changeBookUserService;

    @Autowired
    public ChangeBookUsersController(ChangeBookUserService changeBookUserService) {
        this.changeBookUserService = changeBookUserService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> saveUser(@RequestBody @Valid ChangeBookUser user){
        changeBookUserService.saveUser(user);
        return ResponseEntity.accepted().body("");
    }
}
