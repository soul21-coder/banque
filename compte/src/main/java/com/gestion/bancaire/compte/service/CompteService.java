package com.gestion.bancaire.compte.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.bancaire.compte.model.Compte;

@Service
public class CompteService {
	
	//List et fichier pour sauvegarder les comptes bancaires
	private static final String COMPTES = "comptes.json";
	private List<Compte> comptes = new ArrayList<>();
	
	
	 public void creerCompte(int id, BigDecimal solde, String nomClient) {
		 
		 // Vérification de l'existence du compte avant sa création
	        if (comptes.stream().anyMatch(compte -> compte.getId()==id)) {
	            throw new IllegalArgumentException("Le id existe déjà.");
	        }

	        Compte compte = new Compte(id, solde, nomClient);
	        comptes.add(compte);
	        sauvegarderComptes();
	        
	    }
	
	 
	 
	 public List<Compte> getComptes() {
	        chargerComptes();
	        return comptes;
	    }
	 
	 //Sérialisation de l'objet java pour le transformer en un element Json
	 private void sauvegarderComptes() {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(new File(COMPTES), comptes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	
	    public boolean crediterCompte(int id, BigDecimal montant) {
	    	
	    	//montant negatif
	    	if(montant.doubleValue() <0) {
	    		return false;
	    	}
	       
	        //Controler l'existence du compte à créditer
	        boolean credit = comptes.stream()
	        	    .filter(compte -> compte.getId() == id)
	        	    .findFirst()
	        	    .map(compte -> {
	        	        compte.setSolde(compte.getSolde().add(montant));
	        	        sauvegarderComptes();
	        	        return true;
	        	    })
	        	    .orElse(false);//Compte non existant

	        return credit; 
	    }
	    
	    
	    public boolean debiterCompte(int id, BigDecimal montant) {
	    	
	    	//montant negatif
	    	if(montant.doubleValue() <0) {
	    		return false;
	    	}
	       
	       

	        boolean debit = comptes.stream()
	        	    .filter(compte -> compte.getId() == id)
	        	    .filter(compte -> compte.getSolde().compareTo(montant) >= 0)
	        	    .findFirst()
	        	    .map(compte -> {
	        	        compte.setSolde(compte.getSolde().subtract(montant));
	        	        sauvegarderComptes();
	        	        return true;
	        	    })
	        	    .orElse(false);//Compte non existant

	        return debit;
	    }
	 
	  //Désérialisation de l' element Json en un objet java 
	 private void chargerComptes() {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            File fichier = new File(COMPTES);
	            if (fichier.exists()) {
	                comptes = objectMapper.readValue(fichier, objectMapper.getTypeFactory().constructCollectionType(List.class, Compte.class));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
