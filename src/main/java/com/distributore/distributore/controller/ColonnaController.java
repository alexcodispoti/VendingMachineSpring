package com.distributore.distributore.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.distributore.distributore.model.Colonna;
import com.distributore.distributore.repository.ColonnaRepo;

@Controller
public class ColonnaController implements WebMvcConfigurer {

    @Autowired
    ColonnaRepo colonnaRepo;

    @GetMapping("/colonne")
    public String colonne ( Model model) {
       ArrayList<Colonna> colonne = (ArrayList<Colonna>) colonnaRepo.findAll();
       model.addAttribute("colonne", colonne);
       return "colonne";
    }

    @GetMapping("/colonne/add")
    public String aggiungiColonna(Colonna colonna, Model model) {
        model.addAttribute("colonna", colonna);
        return "aggiungicolonna";
    }

    @PostMapping("/colonne/add")
    public String aggiungiColonnapost(Colonna colonna) {
        colonnaRepo.save(colonna);
        return "redirect:/colonne";
    }
    
    


    
}
