package com.gestion.bancaire.compte.model;

import java.math.BigDecimal;

public class Compte {
	
	
	private int id; 
	
	
	private BigDecimal solde;
	
	private String nomClient;
	
	

	public Compte(int id, BigDecimal solde, String nomClient) {
		this.id = id;
		this.solde = solde;
		this.nomClient = nomClient;
	}
	
	public Compte(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getSolde() {
		return solde;
	}

	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	

}
