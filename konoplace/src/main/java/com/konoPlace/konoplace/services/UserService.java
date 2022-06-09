package com.konoPlace.konoplace.services;

import com.konoPlace.konoplace.dto.UserRegisterDTO;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    public ResponseEntity<UserModel> registerUser(@Valid UserRegisterDTO newUser){
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
