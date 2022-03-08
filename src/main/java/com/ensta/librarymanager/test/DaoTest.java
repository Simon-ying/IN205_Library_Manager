package com.ensta.librarymanager.test;

import java.time.LocalDate;
import com.ensta.librarymanager.utils.*;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.utils.Abonnement;
public class DaoTest {
	public static void print(String s) {
		System.out.println(s);
	}
	public static void main(String args[]) throws Exception {
		FillDatabase.main(args);
		
		LivreDao bookInstance = LivreDao.getInstance();
		MembreDao memberInstance = MembreDao.getInstance();
		EmpruntDao borrowInstance = EmpruntDao.getInstance();
		
		/*
		 * Test LivreDao
		 */
		System.out.println(bookInstance.count());
		System.out.println("--------------------");
		for (Livre ibook : bookInstance.getList()) {
			System.out.println(ibook);
		}
		System.out.println("--------------------");
		System.out.println(bookInstance.getById(1));
		System.out.println("--------------------");
//		bookInstance.delete(12);
		int id = bookInstance.create("Le Petit Prince", "De Saint-Exupéry", "978-1406331981");
		System.out.println("Id created : " + id);
		Livre book = bookInstance.getById(id);
		book.setIsbn("140-6331988 ");
		bookInstance.update(book);
		for (Livre ibook : bookInstance.getList()) {
			System.out.println(ibook);
		}
		
		
		
		/*
		 * Test MembreDao
		 */
		System.out.println(memberInstance.count());
		System.out.println("--------------------");
		for (Membre imember : memberInstance.getList()) {
			System.out.println(imember);
		}
		System.out.println("--------------------");
		System.out.println(memberInstance.getById(1));
		System.out.println("--------------------");
		id = memberInstance.create("Simon", "YING", "Paris", "566@example.com", "11111");
		System.out.println("Id created : " + id);
		Membre member = memberInstance.getById(id);
		member.setAbon(Abonnement.VIP);
		memberInstance.update(member);
		memberInstance.delete(id);
		for (Membre imember : memberInstance.getList()) {
			System.out.println(imember);
		}
		
		
		
		/*
		 * Test EmpruntDao
		 */
		System.out.println(borrowInstance.count());
		System.out.println("--------------------");
		for (Emprunt iborrow : borrowInstance.getList()) {
			System.out.println(iborrow);
		}
		System.out.println("--------------------");
		
		for (Emprunt iborrow : borrowInstance.getListCurrent()) {
			System.out.println(iborrow);
		}
		System.out.println("--------------------");
		
		for (Emprunt iborrow : borrowInstance.getListCurrentByMembre(4)) {
			System.out.println(iborrow);
		}
		System.out.println("--------------------");
		
		for (Emprunt iborrow : borrowInstance.getListCurrentByLivre(2)) {
			System.out.println(iborrow);
		}
		System.out.println("--------------------");
		System.out.println(borrowInstance.getById(1));
		System.out.println("--------------------");
		
//		borrowInstance.create(2, 2, LocalDate.now());
		Emprunt borrow = borrowInstance.getById(6);
		borrow.setDateRetour(LocalDate.now());
		borrowInstance.update(borrow);
		for (Emprunt iborrow : borrowInstance.getList()) {
			System.out.println(iborrow);
		}
		
	}
}
