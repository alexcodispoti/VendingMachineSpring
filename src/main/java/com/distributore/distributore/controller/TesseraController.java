package com.distributore.distributore.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.PostMapping;

import com.distributore.distributore.model.Tessera;
import com.distributore.distributore.repository.TesseraRepo;


   

@Controller
public class TesseraController  {

    @Autowired
     TesseraRepo tesseraRepo;

    
    
  

    //fare get mapping su template distributore html oppure fare un template per ogni Classe

    @GetMapping("/tessere")
    public String tessere ( Model model) {
       ArrayList<Tessera> tessere= (ArrayList<Tessera>) tesseraRepo.findAll();
       model.addAttribute("tessere", tessere);
       return "tessere";
    }
    
    @GetMapping("/tessere/add")
    public String aggiungiTessera(Tessera tessera, Model model) {
        model.addAttribute("colonna", tessera);
        return "aggiungitessera";
    }

    @PostMapping("/tessere/add")
    public String aggiungiTesserapost(Tessera tessera) {
        tesseraRepo.save(tessera);
        return "redirect:/tessere";
    }

    @GetMapping("/tessere/{id}/addcredit")
    public String caricaTessera(@PathVariable Long id, Model model) {
        Tessera tessera = tesseraRepo.findById(id).get();
        model.addAttribute("tessera", tessera);
        return "aggiungicredito";
    }

    @PostMapping("/tessere/{id}/addcredit")
    public String caricaTesserapost(@PathVariable Long id,Tessera tessera) {
        tessera.setId(id);
        tesseraRepo.save(tessera);
        return "redirect:/tessere";
    }


    


    
}
    

