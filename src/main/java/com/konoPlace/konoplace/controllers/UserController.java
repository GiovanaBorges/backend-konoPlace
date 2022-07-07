package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import com.konoPlace.konoplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    //injeção de dependencias
    @Autowired
    private UserRepository repository;

    private UserService userService;

    @GetMapping("/register")
    public ModelAndView  registerScreen(){
        ModelAndView model = new ModelAndView(); 
        model.setViewName("index.html");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView loginScreen()
    {
        ModelAndView model = new ModelAndView(); 
        model.setViewName("login.html");
        return model;
    }


    /*
    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){
      return userService.getUsers();
    }
     */

    @PostMapping("/register")
    public ResponseEntity<UserModel> createUser(UserModel user){
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
