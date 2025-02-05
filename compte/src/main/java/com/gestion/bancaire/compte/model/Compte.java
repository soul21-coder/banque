package com.gestion.bancaire.compte.model;



public class Compte {
	
	
	private int id; 
	
	
	private double solde;
	
	private String nomClient;
	
	

	public Compte(int id, double solde, String nomClient) {
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

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	

}
