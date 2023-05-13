package com.distributore.distributore.controller.apicontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.distributore.distributore.model.Colonna;
import com.distributore.distributore.repository.ColonnaRepo;

@RestController
public class ColonnaApiController {
    
    @Autowired
    public ColonnaRepo colonnaRepo;

    @GetMapping("/api/v1/colonne")
    public ArrayList<Colonna> colonna(){
       return (ArrayList<Colonna>) colonnaRepo.findAll();
    }

    //Aggiungi Colonna
    @PostMapping("/api/v1/colonne")
    public ResponseEntity<Colonna> addColonna(@RequestBody Colonna colonna){
        colonnaRepo.save(colonna);
        return new ResponseEntity<Colonna>(colonna,HttpStatus.OK);
    }

    //Elimina Colonna
    @DeleteMapping("/api/v1/colonne/{id}")
    public ResponseEntity<Void> removeColonna(@PathVariable Long id){
        if(colonnaRepo.existsById(id)){
            colonnaRepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }


}
