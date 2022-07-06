package com.konoPlace.konoplace.controllers;


import com.konoPlace.konoplace.models.MesaModel;
import com.konoPlace.konoplace.models.ReservaModel;
import com.konoPlace.konoplace.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reserva")
public class ReservaController {

    //injeção de dependencias
    @Autowired
    private ReservaRepository repository;

    @GetMapping
    public ResponseEntity<List<ReservaModel>> getReserva(){
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> getReservaById(@PathVariable Long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaModel> createReserva(@RequestBody ReservaModel reservation){
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
