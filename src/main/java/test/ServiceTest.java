package test;

import java.time.LocalDate;

import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.utils.Abonnement;
import com.ensta.librarymanager.utils.FillDatabase;

public class ServiceTest {
	public static String clines(int n) {
		String res = "";
		int number = Integer.max(n, 0);
		for (int i=0; i<number; i++) {
			res += "-";
		}
		return res;
	}
	public static void main(String args[]) throws Exception {
		final int LINE_SIZE = 40;
		FillDatabase.main(args);
		LivreService bookInstance = LivreService.getInstance();
		MembreService memberInstance = MembreService.getInstance();
		EmpruntService borrowInstance = EmpruntService.getInstance();
		
		/*
		 * Test LivreService
		 */
		System.out.println(clines(LINE_SIZE));
		System.out.println("Count: " + bookInstance.count());
		System.out.println(clines(LINE_SIZE));
		
		for (Livre ibook : bookInstance.getList()) {
			System.out.println(ibook);
		}
		System.out.println(clines(LINE_SIZE));
		System.out.println(bookInstance.getById(1));
		System.out.println(clines(LINE_SIZE));
		
		int id = bookInstance.create("Le Petit Prince", "De Saint-Exupéry", "978-1406331981");
		Livre book = bookInstance.getById(id);
		System.out.println("Id created : " + id + " : " + book);
		System.out.println(clines(LINE_SIZE));
		
		book.setIsbn("140-6331988 ");
		bookInstance.update(book);
		for (Livre ibook : bookInstance.getList()) {
			System.out.println(ibook);
		}
		System.out.println(clines(LINE_SIZE));
		
		for (Livre ibook : bookInstance.getListDispo()) {
			System.out.println(ibook);
		}
		System.out.println(clines(LINE_SIZE));
		
//		bookInstance.delete(id);
//		for (Livre ibook : bookInstance.getList()) {
//			System.out.println(ibook);
//		}
//		System.out.println(clines(LINE_SIZE));
//		id = bookInstance.create(null, "De Saint-Exupéry", "978-1406331981");
//		book.setTitre(null);
//		bookInstance.update(book);
		
		
		/*
		 * Test MembreService
		 */
		System.out.println(clines(LINE_SIZE));
		System.out.println("Count : " + memberInstance.count());
		System.out.println(clines(LINE_SIZE));
		for (Membre imember : memberInstance.getList()) {
			System.out.println(imember);
		}
		System.out.println(clines(LINE_SIZE));
		System.out.println(memberInstance.getById(1));
		System.out.println(clines(LINE_SIZE));
		id = memberInstance.create("Simon", "YING", "Paris", "566@example.com", "11111");
		Membre member = memberInstance.getById(id);
		System.out.println("Id created : " + id + " : " + member);
		System.out.println(clines(LINE_SIZE));
		
		member.setAbon(Abonnement.VIP);
		memberInstance.update(member);
		for (Membre imember : memberInstance.getList()) {
			System.out.println(imember);
		}
		System.out.println(clines(LINE_SIZE));
		
		for (Membre imember : memberInstance.getListMembreEmpruntPossible()) {
			System.out.println(imember);
		}
		System.out.println(clines(LINE_SIZE));
		
//		memberInstance.delete(id);
//		for (Membre imember : memberInstance.getList()) {
//			System.out.println(imember);
//		}
//		System.out.println(clines(LINE_SIZE));
		
		/*
		 * Test Emprunt
		 */
		System.out.println(clines(LINE_SIZE));
		System.out.println("Count : " + borrowInstance.count());
		System.out.println(clines(LINE_SIZE));
		for (Emprunt iborrow : borrowInstance.getList()) {
			System.out.println(iborrow);
		}
		System.out.println(clines(LINE_SIZE));
		
		for (Emprunt iborrow : borrowInstance.getListCurrent()) {
			System.out.println(iborrow);
		}
		System.out.println(clines(LINE_SIZE));
		System.out.println(memberInstance.getById(4).getAbon());
		for (Emprunt iborrow : borrowInstance.getListCurrentByMembre(4)) {
			System.out.println(iborrow);
		}
		System.out.println(clines(LINE_SIZE));
		
		for (Emprunt iborrow : borrowInstance.getListCurrentByLivre(2)) {
			System.out.println(iborrow);
		}
		System.out.println(clines(LINE_SIZE));
		System.out.println(borrowInstance.getById(1));
		System.out.println(clines(LINE_SIZE));
		
		if (borrowInstance.isLivreDispo(11)) {
			if (borrowInstance.isEmpruntPossible(member)) {
				System.out.println(member);
				borrowInstance.create(member.getId(), 11, LocalDate.now());
			}
		}
		
		for (Emprunt iborrow : borrowInstance.getListCurrentByMembre(member.getId())) {
			System.out.println(iborrow);
		}
		System.out.println(clines(LINE_SIZE));
		borrowInstance.returnBook(7);
		for (Emprunt iborrow : borrowInstance.getListCurrentByMembre(member.getId())) {
			System.out.println(iborrow);
		}
		System.out.println(borrowInstance.getById(7));
		
	}
}
