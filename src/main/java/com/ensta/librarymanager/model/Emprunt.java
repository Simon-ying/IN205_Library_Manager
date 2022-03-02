package com.ensta.librarymanager.model;

import java.time.LocalDate;
public class Emprunt {
	private int id;
	private Livre livre;
	private Membre member;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	
	
	public Emprunt(int id, Livre livre, Membre member, LocalDate dateEmprunt, LocalDate dateRetour) {
		super();
		this.id = id;
		this.livre = livre;
		this.member = member;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	public int getId() {
		return id;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public Membre getMember() {
		return member;
	}
	public void setMember(Membre member) {
		this.member = member;
	}
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	@Override
	public String toString() {
		return "Emprunt [id=" + id + ", livre=" + livre + ", member=" + member + ", dateEmprunt=" + dateEmprunt
				+ ", dateRetour=" + dateRetour + "]";
	}
	
	
	

}
