package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.dto.UserRegisterDTO;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import com.konoPlace.konoplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    //injeção de dependencias
    @Autowired
    private UserRepository repository;

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserRegisterDTO user){
        return userService.registerUser(user);
    }

    @PutMapping
    public ResponseEntity<UserModel> EditUser(@RequestBody UserModel user){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
    }

    @DeleteMapping("{id}")
    public void DeleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }
}
