package com.konoPlace.konoplace.services;


import com.konoPlace.konoplace.models.UserLogin;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private String encryptPass(String pass){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        return encrypt.encode(pass);
    }

    private boolean comparePass(String newpass,String pass){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        return encrypt.matches(newpass,pass);
    }

    private String generateBasicToken(String user , String senha){
        String structure = user + ":" + senha;
        byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(structureBase64);
    }

    public ModelAndView loginUser(UserLogin userlogin){
        Optional<UserModel> userCompare = userRepo.findByEmail(userlogin.getEmail());
        ModelAndView model = new ModelAndView(); 
        
        
        if(userCompare.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST , "This user does not exists!");
        }else{
            if(comparePass(userlogin.getPass(), userCompare.get().getSenha())){
                userlogin.setId(userlogin.getId());
                userlogin.setEmail(userlogin.getEmail());
                userlogin.setPass(userlogin.getPass());
                userlogin.setToken(generateBasicToken(userlogin.getEmail(), userlogin.getPass()));
                model.setViewName("home.html");
                return model;
            }
        }
        model.setViewName("index.html");
        return model;

    }


    public ResponseEntity registerUser(UserModel newUser){
        Optional<UserModel> optUser = userRepo.findByEmail(newUser.getEmail());

        if(optUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "This user already exist");
        }else{
            UserModel user = new UserModel();
            user.setNome(newUser.getNome());
            user.setCargo(newUser.getCargo());
            user.setDepartamento(newUser.getDepartamento());
            user.setTelefone(newUser.getTelefone());
            user.setSenha(encryptPass(newUser.getSenha()));

            return ResponseEntity.status(200).body(userRepo.save(user));
        }
    }

}