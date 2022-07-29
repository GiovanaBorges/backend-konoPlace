package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.MesaModel;
import com.konoPlace.konoplace.models.ReservaModel;
import com.konoPlace.konoplace.models.UserModel;
import com.konoPlace.konoplace.repositories.ReservaRepository;
import com.konoPlace.konoplace.repositories.UserRepository;
import com.konoPlace.konoplace.services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reserva")
public class ReservaController {

    //injeção de dependencias
    @Autowired
    private ReservaRepository repository;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private CookieService cookieService;

    @GetMapping
    public ResponseEntity<List<ReservaModel>> getReserva(){
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/{id}")
    public Optional<ReservaModel> getReservaById(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("reserva.html");
        return repository.findById(id);
    }

    @GetMapping("/reservas")
    public ModelAndView profileScreen(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("reserve.html");

        Long id = Long.valueOf(cookieService.readCookie(request));
        Optional<UserModel> user = repoUser.findById(id);

        UserModel userModel = new UserModel();
        userModel.setEmail(user.get().getEmail());
        userModel.setSenha(user.get().getSenha());
        userModel.setId(user.get().getId());
        userModel.setReserva(user.get().getReserva().size() > 0? user.get().getReserva() : null);
        userModel.setCargo(user.get().getCargo());
        userModel.setDepartamento(user.get().getDepartamento());
        userModel.setFoto(user.get().getFoto());
        userModel.setTelefone(user.get().getTelefone());

        model.addObject("reservable", userModel.getReserva());
        return model;
    }


    @GetMapping("/perfil")
    public ModelAndView  perfil(){
        ModelAndView model = new ModelAndView(); 
        model.setViewName("perfil.html");
        return model;
    }

    @PostMapping
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reservation){
        //DateTime dateTime = new DateTime(reservation.getDate());
        //reservation.setDate(dateTime.plusDays(1).toDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(reservation));
    }

    @PutMapping
    public ResponseEntity<ReservaModel> EditReserva(@RequestBody ReservaModel reservation){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(reservation));
    }

    @DeleteMapping("{id}")
    public void DeleteReserva(@PathVariable Long id){
        repository.deleteById(id);
    }
}
