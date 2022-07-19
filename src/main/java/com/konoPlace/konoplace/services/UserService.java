package com.konoPlace.konoplace.services;


import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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

    private String encryptPass(String pass){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String encoder = encrypt.encode(pass);
        return encoder;
    }

    private boolean comparePass(String newpass,String pass){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        return encrypt.matches(newpass,pass);
    }

    public ModelAndView loginUser(UserLogin userlogin , HttpServletResponse res){
        Optional<UserModel> userCompare = userRepo.findByEmail(userlogin.getEmail());

        ModelAndView model = new ModelAndView();

        if(userCompare.isEmpty()){
            model.setViewName("index.html");
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST , "This user does not exists!");
        }else{
            if(comparePass(userlogin.getPass(), userCompare.get().getSenha())){
                Optional<UserModel> user = userRepo.findByEmail(userCompare.get().getEmail());

                String userId = String.valueOf(user.get().getId());

                Cookie cookie = new Cookie("userID", userId);
                cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
                res.addCookie(cookie);

                model.setViewName("home.html");
            }
        }

        return model;

    }


    public ModelAndView registerUser(UserModel newUser , HttpServletResponse res){
                ModelAndView mv = new ModelAndView();
                userRepo.save(newUser);
                Optional<UserModel> user = userRepo.findByEmail(newUser.getEmail());

                String userId = String.valueOf(user.get().getId());
                cookieService.setCookie(res,userId);
                return mv;

    }

}