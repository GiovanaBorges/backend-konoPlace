package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.dto.UserRegisterDTO;
import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import com.konoPlace.konoplace.services.UserService;

import ch.qos.logback.core.status.Status;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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
        model.addObject("morte" , "aaaaaaaaaaaaa");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView loginScreen()
    {
        ModelAndView model = new ModelAndView(); 
        model.setViewName("login.html");
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
    public ModelAndView createUser(@RequestBody UserModel user) {
        /* 
        
        Optional<UserModel> userModel = repository.findByEmail(user.getEmail());
        if(userModel.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "This user already exists!");
        }else{
            
            return userService.registerUser(user);
        }
        
        */
        ModelAndView mv = new ModelAndView(); 
        mv.setViewName("home.html");
        userService.registerUser(user);
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestBody UserLogin user)
    {
        return userService.loginUser(user);
        
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
