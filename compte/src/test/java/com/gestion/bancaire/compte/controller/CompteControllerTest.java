package com.gestion.bancaire.compte.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class CompteControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

   

    @Test
    void testCreerCompte() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/comptes/creer")
                .param("id", "1")
                .param("solde", "10.0")
                .param("nomClient", "Saad"))
                .andExpect(status().isOk()); 
    }

    @Test
    void testCrediterCompte() throws Exception {
        //Créer le compte
        mockMvc.perform(MockMvcRequestBuilders.post("/comptes/creer")
        		.param("id", "2")
                .param("solde", "10.0")
                .param("nomClient", "Saad"))
                .andExpect(status().isOk());

        //créditer le compte
        mockMvc.perform(MockMvcRequestBuilders.post("/comptes/crediter")
                .param("id", "2")
                .param("montant", "30.0"))
                .andExpect(status().isOk()); 
    }

    @Test
    void testDebiterCompte() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.post("/comptes/creer")
        		.param("id", "3")
                .param("solde", "10.0")
                .param("nomClient", "Saad"))
                .andExpect(status().isOk());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/comptes/debiter")
        		.param("id", "3")
                .param("montant", "3.0"))
                .andExpect(status().isOk());
    }

    @Test
    void testDebiterSoldeInsuffisant() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.post("/comptes/creer")
        		.param("id", "4")
                .param("solde", "10.0")
                .param("nomClient", "Saad"))
                .andExpect(status().isOk());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/comptes/debiter")
        		.param("id", "4")
                .param("montant", "30.0"))
                .andExpect(status().isInternalServerError()); 
    }
	
	

}
