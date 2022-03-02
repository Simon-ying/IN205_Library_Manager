package com.ensta.librarymanager.model;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private String isbn;
	
	public Livre(int id, String titre, String auteur, String isbn) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", isbn=" + isbn + ", getId()=" + getId()
				+ ", getTitre()=" + getTitre() + ", getAuteur()=" + getAuteur() + ", getIsbn()=" + getIsbn()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public int getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
