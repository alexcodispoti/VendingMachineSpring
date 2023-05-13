package com.distributore.distributore.controller.apicontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distributore.distributore.BevandaNonTrovataException;
import com.distributore.distributore.Response;
import com.distributore.distributore.model.Bevanda;
import com.distributore.distributore.model.Colonna;
import com.distributore.distributore.model.Tessera;
import com.distributore.distributore.repository.BevandaRepo;
import com.distributore.distributore.repository.ColonnaRepo;
import com.distributore.distributore.repository.TesseraRepo;

@RestController
public class BevandaApiController {
    
    @Autowired
    public BevandaRepo bevandaRepo;
    @Autowired
    public ColonnaRepo colonnaRepo;
    @Autowired
    public TesseraRepo tesseraRepo;

    @Value("${application.name}")
    String appName;

    @Value("${application.version}")
    String appVersion;

    @GetMapping("/api/v1/version")
    public String versione(){
        return "{\"application \" : \"" +  appName + " " + appVersion + "\"}";
    }

    @GetMapping("/api/v1/bevande")
    public ArrayList<Bevanda> bevande(){
       return (ArrayList<Bevanda>) bevandaRepo.findAll();
    }


    //Aggiungi Bevanda
    @PostMapping("/api/v1/bevande")
    public ResponseEntity<Bevanda> addBevanda(@RequestBody Bevanda bevanda){
        bevandaRepo.save(bevanda);
        return new ResponseEntity<>(bevanda,HttpStatus.OK);
    }

    //Elimina Bevanda
    @DeleteMapping("/api/v1/bevande/{id}")
    public ResponseEntity<Void> removeBevanda(@PathVariable Long id) throws BevandaNonTrovataException {
        if(bevandaRepo.existsById(id)){
            bevandaRepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new BevandaNonTrovataException();
    }
    
    //Metodo Eroga
    @GetMapping("/api/v1/eroga")
    public ResponseEntity<Response> eroga(@RequestParam(value = "key", defaultValue = "")String key , @RequestParam (value = "key2", defaultValue = "") String key2){


        // Response response=new Response("L'erogazione effettuate con successo",200);
       
        // return new ResponseEntity<Response>(response,HttpStatus.OK);
        if (!key.equals("") || !key2.equals("")){
            // if(bevandaRepo.findByCodice(key2)==null || tesseraRepo.findByCodice(key)==null ){
        Bevanda bevanda = bevandaRepo.findByCodice(key);
        Tessera tessera = tesseraRepo.findByCodice(key2);
        Float creditotessera= tessera.getCredito();
        Float prezzobevanda=bevanda.getPrezzo();
        String nomebevanda=bevanda.getNome();
        Integer lattine=0;
        ArrayList<Colonna> colonne = colonnaRepo.findByNome(nomebevanda);
        for(Colonna colonna : colonne){
            Integer lattinerimaste=colonna.getQuantita();
            lattine = lattine+lattinerimaste;
        }
        if(lattine==0){
            Response response=new Response("Lattine FINITE",404);
       
            new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
        }
        if(prezzobevanda<=creditotessera){
            creditotessera=creditotessera-prezzobevanda;
            tessera.setCredito(creditotessera);
            tesseraRepo.save(tessera);
        }
        else{
            Response response=new Response("Credito Insufficiente",400);
            return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
        }
        for (Colonna colonna : colonne) {
            if (colonna.getQuantita() > 0) {
                colonna.setQuantita(colonna.getQuantita() - 1);
                colonnaRepo.save(colonna);
                break;
            }
        }
        Response response=new Response("L'erogazione effettuate con successo",200);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    else{

                 Response response=new Response("Non hai passato i parametri",400);
                 return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);

    }
}

    @ExceptionHandler(BevandaNonTrovataException.class)
    public ResponseEntity<Response> sugo(BevandaNonTrovataException exception) {
        Response resp = new Response(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
    }
}