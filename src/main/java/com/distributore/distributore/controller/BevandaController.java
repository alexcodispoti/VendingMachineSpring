package com.distributore.distributore.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.distributore.distributore.model.Bevanda;
import com.distributore.distributore.model.Colonna;
import com.distributore.distributore.model.Tessera;
import com.distributore.distributore.repository.BevandaRepo;
import com.distributore.distributore.repository.ColonnaRepo;
import com.distributore.distributore.repository.TesseraRepo;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class BevandaController implements WebMvcConfigurer {

    // Dependency Injection
    @Autowired
    TesseraRepo tesseraRepo;
    @Autowired
    ColonnaRepo colonnaRepo;
    @Autowired
    BevandaRepo bevandaRepo;

    @GetMapping("/bevande")
    public String bevande(Model model) {
        ArrayList<Bevanda> bevande = (ArrayList<Bevanda>) bevandaRepo.findAll();
        model.addAttribute("bevande", bevande);
        return "bevande";
    }

    @GetMapping("/bevande/add")
    public String aggiungiBevanda(Bevanda bevanda, Model model) {
        model.addAttribute("bevanda", bevanda);
        return "aggiungibevanda";
    }

    @PostMapping("/bevande/add")
    public String aggiungiBevandapost(Bevanda bevanda) {
        bevandaRepo.save(bevanda);
        return "redirect:/bevande";
    }

    @GetMapping("/bevande/eroga")
    public String erogazione(@RequestParam(value = "key", defaultValue = "") String key,
            @RequestParam(value = "key2", defaultValue = "") String key2, Model model) {

        ArrayList<Bevanda> bevande = (ArrayList<Bevanda>) bevandaRepo.findAll();
        model.addAttribute("bevande", bevande);
        ArrayList<Tessera> tessere = (ArrayList<Tessera>) tesseraRepo.findAll();
        model.addAttribute("tessere", tessere);

        if (!key.equals("") || !key2.equals("")) {
            
            Bevanda bevanda = bevandaRepo.findByCodice(key2);
            Tessera tessera = tesseraRepo.findByCodice(key);
            Float credito = tessera.getCredito();
            Float prezzobevanda = bevanda.getPrezzo();
            String nomebevanda = bevanda.getNome();
            Integer lattine = 0;
            ArrayList<Colonna> colonne = colonnaRepo.findByNome(nomebevanda);
            for (Colonna colonna : colonne) {
                Integer lattinerimaste = colonna.getQuantita();
                lattine = lattinerimaste + lattine;
            }
            if (credito >= prezzobevanda && lattine > 0) {
                credito = credito - prezzobevanda;
                tessera.setCredito(credito);
                tesseraRepo.save(tessera);
                lattine--;
            } else {
                return "eroga";
            }
            for (Colonna colonna : colonne) {
                if (colonna.getQuantita() > 0) {
                    colonna.setQuantita(colonna.getQuantita() - 1);
                    colonnaRepo.save(colonna);
                    break;
                }
            }

        }
        return "eroga";
    }

    @GetMapping("bevande/{id}/delete")
    public String deletebevanda(@PathVariable Long id) {

        bevandaRepo.deleteById(id);
        return "redirect:/bevande";
    }

    // @GetMapping("/bevande/eroga")
    // public String eroga(@RequestParam(value = "key", defaultValue = "") String
    // key,
    // @RequestParam(value = "key2", defaultValue = "") String key2, Model model) {

    // if (!key.equals("") && !key2.equals("")) {
    // String nomebevanda = bevande.get(key2).getNome();
    // Float creditotessera = tessere.get(key).getCredito();
    // Float prezzobevanda = bevande.get(key2).getPrezzo();
    // int lattine;
    // int numerobevanda;

    // if (this.bevande.containsKey(key2) && this.tessere.containsKey(key)) {

    // for (String i : colonne.keySet()) {
    // if (colonne.get(i).getNome() == nomebevanda) {
    // lattine = colonne.get(i).getNumero();
    // numerobevanda = colonne.get(i).getNumero();
    // if (lattine >= 0 && creditotessera >= prezzobevanda) {

    // creditotessera = creditotessera - prezzobevanda;
    // tessere.get(key).setCredito(creditotessera);
    // lattine--;
    // colonne.get(i).setNumero(lattine);

    // }
    // }
    // }
    // }
    // return "redirect:/bevande";
    // }
    // return "eroga";
    // }
}
