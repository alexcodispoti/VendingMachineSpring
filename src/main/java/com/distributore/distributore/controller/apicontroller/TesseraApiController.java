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

import com.distributore.distributore.model.Tessera;
import com.distributore.distributore.repository.TesseraRepo;

@RestController
public class TesseraApiController {
    
    @Autowired
    TesseraRepo tesserarepo;

    @GetMapping("/api/v1/tessere")
    public ArrayList<Tessera> tessera(){
       return (ArrayList<Tessera>) tesserarepo.findAll();
    }

    //Aggiungi Tessera
    @PostMapping("/api/v1/tessere")
    public ResponseEntity<Tessera> addTessera(@RequestBody Tessera tessera){
        tesserarepo.save(tessera);
        return new ResponseEntity<Tessera>(tessera,HttpStatus.OK);
    }

    //Elimina Tessera
    @DeleteMapping("/api/v1/tessere/{id}")
    public ResponseEntity<Void> removeBevanda(@PathVariable Long id){
        if(tesserarepo.existsById(id)){
            tesserarepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    

}
