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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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
    public ModelAndView perfilScreen(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView();

        Cookie[] cookie = null;
        cookie = request.getCookies();

        Optional<Cookie> result =  Arrays.stream(cookie).findFirst();
        Long id = Long.parseLong(result.get().getValue());
        Optional<UserModel> user = repository.findById(id);

        UserModel userModel = new UserModel();
        userModel.setEmail(user.get().getEmail());
        userModel.setSenha(user.get().getSenha());
        userModel.setId(user.get().getId());
        userModel.setReserva(user.get().getReserva());
        userModel.setCargo(user.get().getCargo());
        userModel.setDepartamento(user.get().getDepartamento());
        userModel.setFoto(user.get().getFoto());
        userModel.setTelefone(user.get().getTelefone());
        userModel.setNome(user.get().getNome());

        model.addObject("user", userModel);
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

    @PutMapping("/update/{id}")
    public ModelAndView EditUser(@PathVariable Long id,@ModelAttribute UserModel user ,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        Cookie[] cookie = null;
        cookie = request.getCookies();

        //Optional<Cookie> result =  Arrays.stream(cookie).findFirst();
        //Long idUser = Long.parseLong(result.get().getValue());

        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setEmail(user.getEmail());
        userModel.setSenha(user.getSenha());
        userModel.setId(user.getId());
        userModel.setReserva(user.getReserva());
        userModel.setCargo(user.getCargo());
        userModel.setDepartamento(user.getDepartamento());
        userModel.setFoto(user.getFoto());
        userModel.setTelefone(user.getTelefone());
        userModel.setNome(user.getNome());

        repository.save(user);
        mv.setViewName("home.html");
        return mv;

    }

    @DeleteMapping("/delete/{id}")
    public void DeleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }
}
