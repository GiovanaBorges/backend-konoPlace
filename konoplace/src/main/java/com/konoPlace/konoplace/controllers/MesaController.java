package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.MesaModel;
import com.konoPlace.konoplace.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesa")
@CrossOrigin("*")
public class MesaController {

    //injeção de dependencias
    @Autowired
    private MesaRepository repository;

    @GetMapping
    public ResponseEntity<List<MesaModel>> getMesa(){

        return ResponseEntity.ok(repository.findAll());

    }

    @PostMapping
    public ResponseEntity<MesaModel> createMesa(@RequestBody MesaModel mesa){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(mesa));
    }

    @PutMapping
    public ResponseEntity<MesaModel> EditMesa(@RequestBody MesaModel mesa){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(mesa));
    }

    @DeleteMapping("{id}")
    public void DeleteMesa(@PathVariable Long id){
        repository.deleteById(id);
    }
}
