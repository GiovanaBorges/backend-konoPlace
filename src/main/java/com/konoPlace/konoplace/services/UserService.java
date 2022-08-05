package com.konoPlace.konoplace.services;


import com.konoPlace.konoplace.controllers.MesaController;
import com.konoPlace.konoplace.controllers.UserController;
import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.rest.core.event.ExceptionEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CookieService cookieService;

    public String encryptPass(String pass){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String encoder = encrypt.encode(pass);
        return encoder;
    }

    private boolean comparePass(String newpass,String pass){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        return encrypt.matches(newpass,pass);
    }

    public void registerUser(UserModel newUser , HttpServletResponse res){
                newUser.setSenha(encryptPass(newUser.getSenha()));
                newUser.setRole("USER");
                userRepo.save(newUser);
                UserModel user = userRepo.findByEmail(newUser.getEmail());

                String userId = String.valueOf(user.getId());
                cookieService.setCookie(res,userId);
                MesaController controller = new MesaController();
                controller.getMesa();
    }

    public void loginUser(){

    }

}