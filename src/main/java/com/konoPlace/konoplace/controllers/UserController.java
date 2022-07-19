package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import com.konoPlace.konoplace.services.UserService;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    //injeção de dependencias
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public ResponseEntity<List<UserModel>> getUsers(){
      return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/register")
    public ModelAndView  registerScreen(){
        ModelAndView model = new ModelAndView("index.html");
        UserModel user = new UserModel();
        model.addObject("userModel",user);
        return model;
    }

    @GetMapping("/login")
    public ModelAndView loginScreen()
    {
        ModelAndView model = new ModelAndView(); 
        model.setViewName("login.html");
        UserLogin user = new UserLogin();
        model.addObject("userLogin" , user);
        return model;
    }

    @GetMapping("/forget")
    public ModelAndView forgetScreen()
    {
        ModelAndView model = new ModelAndView(); 
        model.setViewName("forget.html");
        return model;
    }
    
    @GetMapping("/perfil")
    public ModelAndView perfilScreen()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("perfil.html");
        return model;
    }


    @PostMapping("/register")
    public void createUser(@ModelAttribute UserModel user , HttpServletResponse response) {
            userService.registerUser(user,response);
    }

    @PostMapping("/login")
    public void login(@ModelAttribute UserLogin user , HttpServletResponse response)
    {
        userService.loginUser(user, response);
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
