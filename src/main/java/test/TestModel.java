package test;

import java.time.LocalDate;

import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.utils.Abonnement;

public class TestModel {
	public static void main(String arvs[]) {
		Livre book1 = new Livre(1, "Les oiseaux migrateurs", "Patrick FICHTER", "978-2817704876");
		Membre member1 = new Membre(1, "CHERIF", "Kader", "2 rue Sadi Carnot", "kcherif@email.com", "0243585672", Abonnement.PREMIUM);
		Emprunt borrow1 = new Emprunt(1, book1, member1, LocalDate.now(), LocalDate.now());
		
		System.out.println(book1);
		System.out.println(member1);
		System.out.println(borrow1);
	}
}
