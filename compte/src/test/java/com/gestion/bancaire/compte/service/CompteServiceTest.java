package com.gestion.bancaire.compte.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.gestion.bancaire.compte.model.Compte;

public class CompteServiceTest {
	
	
	private CompteService compteService;
    

    @BeforeEach
    void setUp() {
    	
    	compteService = new CompteService();
    	compteService.creerCompte(21, new BigDecimal(10.0), "Saad FAREH");
    }

    @Test
    void testCreerCompte() {
     
    	  
        List<Compte> comptes = compteService.getComptes();
        assertEquals(1, comptes.size());
        assertEquals(21, comptes.get(0).getId());
        assertEquals("Saad FAREH", comptes.get(0).getNomClient());
        assertEquals(0, comptes.get(0).getSolde().compareTo(new BigDecimal(10)));
    }

    @Test
    void testCrediterCompte() {
        
        boolean isCrediter = compteService.crediterCompte(21, new BigDecimal(9.0));
        
        assertTrue(isCrediter); 
        Compte compte = compteService.getComptes().get(0);
        assertEquals(0, compte.getSolde().compareTo(new BigDecimal(19))); 
    }

    @Test
    void testDebiterCompte() {
        
    	  boolean isDebiter = compteService.debiterCompte(21, new BigDecimal(9.0));
        
        assertTrue(isDebiter); 
        Compte compte = compteService.getComptes().get(0);
        assertEquals(0, compte.getSolde().compareTo(new BigDecimal(1))); 
    }

    @Test
    void testDebiterCompteSoldeInsuffisant() {
        
        boolean isSuffisant = compteService.debiterCompte(21, new BigDecimal(30.0));
        
        assertFalse(isSuffisant); 
        Compte compte = compteService.getComptes().get(0);
        assertEquals(0, compte.getSolde().compareTo(new BigDecimal(10))); 
    }
}
	


