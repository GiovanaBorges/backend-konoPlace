package com.konoPlace.konoplace.controllers;

import com.konoPlace.konoplace.models.MesaModel;
import com.konoPlace.konoplace.repositories.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("{id}")
    public ResponseEntity<MesaModel> getMesaById(@PathVariable Long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MesaModel> getMesaByName(@PathVariable String name){
        MesaModel mesa =  repository.findByName(name);
        if(mesa != null){
            return ResponseEntity.ok(repository.findByName(name));
        }else{
            return (ResponseEntity<MesaModel>) ResponseEntity.notFound();
        }


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
