package com.gestion.bancaire.compte.controller;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.bancaire.compte.model.Compte;
import com.gestion.bancaire.compte.service.CompteService;

@RestController
@RequestMapping("/comptes")
public class CompteController {
	
	
	@Autowired
    private CompteService compteService;
	
	
    //Créer un compte bancaire
    @PostMapping("/creer")
    public void creerCompte(@RequestParam int id, @RequestParam BigDecimal solde, @RequestParam String nomClient) {
    	compteService.creerCompte(id, solde, nomClient);
    }

    
    //Créditer un compte bancaire
    @PostMapping("/crediter")
    public ResponseEntity<String> crediterCompte(@RequestParam int id, @RequestParam BigDecimal montant) {
    
        boolean credit = compteService.crediterCompte(id, montant);
        return credit? new ResponseEntity<>("succès", HttpStatus.OK):new ResponseEntity<>("echec", HttpStatus.INTERNAL_SERVER_ERROR);
     
        
       
    }
    
 //Débiter un compte bancaire
    @PostMapping("/debiter")
    public ResponseEntity<String> debiterCompte(@RequestParam int id, @RequestParam BigDecimal montant) {
        boolean debit = compteService.debiterCompte(id, montant);
        return debit?new ResponseEntity<>("succès", HttpStatus.OK):new ResponseEntity<>("echec", HttpStatus.INTERNAL_SERVER_ERROR);	
    }
    
    //Lister les comptes bancaires
    @GetMapping("/list")
    public List<Compte> getComptes() {
        return compteService.getComptes();
    }
    
    
  

}
