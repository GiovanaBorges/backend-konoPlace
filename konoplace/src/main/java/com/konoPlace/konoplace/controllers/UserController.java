package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    //injeção de dependencias
    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){
        return ResponseEntity.ok(repository.findAll());
    }


}
