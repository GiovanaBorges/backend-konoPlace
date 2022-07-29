package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import com.konoPlace.konoplace.services.CookieService;
import com.konoPlace.konoplace.services.UserService;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class UserController {

    //injeção de dependencias
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private CookieService cookieService;

    @GetMapping("/list")
    public ResponseEntity<List<UserModel>> getUsers(){
      return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/register")
    public ModelAndView  registerScreen(){
        ModelAndView model = new ModelAndView("index");
        UserModel user = new UserModel();
        model.addObject("userModel",user);
        return model;
    }

    @GetMapping("/login")
    public ModelAndView loginScreen(HttpServletRequest http)
    {
        ModelAndView model = new ModelAndView("login");
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

//        Optional<Cookie> result =  Arrays.stream(cookie).findFirst();
        String cookieUserID = cookieService.readCookie(request);
        Long id = Long.parseLong(cookieUserID);
        Optional<UserModel> user = repository.findById(id);

        UserModel userModel = new UserModel();
        userModel.setEmail(user.get().getEmail());
        userModel.setSenha("sua senha");
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

    private UserModel getPrincipal(){
        UserModel user = null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserModel ){
            user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

    @PostMapping("/register")
    public void createUser(@ModelAttribute UserModel user , HttpServletResponse response) {
            userService.registerUser(user,response);
    }

    @PostMapping("/login")
    public void login(@ModelAttribute UserLogin user , HttpServletResponse response)
    {
        cookieService.setCookie(response,user.getEmail());
    }

    @PutMapping("/update")
    public void EditUser(@ModelAttribute UserModel user ){
        ModelAndView mv = new ModelAndView();

        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
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
    }

    @DeleteMapping("/delete/{id}")
    public void DeleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }
}
