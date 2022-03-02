package com.ensta.librarymanager.model;

import com.ensta.librarymanager.utils.Abonnement;

public class Membre {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;
	private Abonnement abon;
	
	
	
	public Membre(int id, String nom, String prenom, String adresse, String email, String telephone, Abonnement abon) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.abon = abon;
	}

	@Override
	public String toString() {
		return "Membre [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email
				+ ", telephone=" + telephone + ", abon=" + abon + "]";
	}

	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getAbon() {
		return abon.getType();
	}
	
	public void setAbon(Abonnement abon) {
		this.abon = abon;
	}

}
