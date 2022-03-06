package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.utils.Abonnement;

public class EmpruntDao implements IEmpruntDao {
	private static EmpruntDao instance;
	
	public static EmpruntDao getInstance() {
		if (instance == null) {
			instance = new EmpruntDao();
		}
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws DaoException {
		List<Emprunt> borrowList = new ArrayList<>();
		Connection connection = null;
		Statement createStatement = null;
		ResultSet res = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String LIST_ALL_BORROW = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
					+ "FROM emprunt AS e "
					+ "INNER JOIN membre ON membre.id = e.idMembre "
					+ "INNER JOIN livre ON livre.id = e.idLivre "
					+ "ORDER BY dateRetour DESC;";
			createStatement = connection.createStatement();
			res = createStatement.executeQuery(LIST_ALL_BORROW);
			
			while (res.next()) {
				LocalDate returnDate = (res.getDate("dateRetour") == null) ? null : res.getDate("dateRetour").toLocalDate();
				borrowList.add(new Emprunt(res.getInt("id"),
						new Livre(res.getInt("idLivre"),
								res.getString("titre"),
								res.getString("auteur"),
								res.getString("isbn")),
						new Membre(res.getInt("idMembre"),
								res.getString("nom"),
								res.getString("prenom"),
								res.getString("adresse"),
								res.getString("email"),
								res.getString("telephone"),
								Abonnement.getFromString(res.getString("abonnement"))),
						res.getDate("dateEmprunt").toLocalDate(),
						returnDate
						));
			}
			
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when fetching the borrowing list");
		}
		finally{
			try {
				connection.close();
				createStatement.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return borrowList;
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		List<Emprunt> borrowListCurrent = new ArrayList<>();
		Connection connection = null;
		Statement createStatement = null;
		ResultSet res = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String LIST_ALL_BORROW_CURRENT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
					+ "FROM emprunt AS e "
					+ "INNER JOIN membre ON membre.id = e.idMembre "
					+ "INNER JOIN livre ON livre.id = e.idLivre "
					+ "WHERE dateRetour IS NULL;";
			createStatement = connection.createStatement();
			res = createStatement.executeQuery(LIST_ALL_BORROW_CURRENT);
			while (res.next()) {
				borrowListCurrent.add(new Emprunt(res.getInt("id"),
						new Livre(res.getInt("idLivre"),
								res.getString("titre"),
								res.getString("auteur"),
								res.getString("isbn")),
						new Membre(res.getInt("idMembre"),
								res.getString("nom"),
								res.getString("prenom"),
								res.getString("adresse"),
								res.getString("email"),
								res.getString("telephone"),
								Abonnement.getFromString(res.getString("abonnement"))),
						res.getDate("dateEmprunt").toLocalDate(),
						null
						));
			}
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when fetching the current borrowing list");
		}
		finally{
			try {
				connection.close();
				createStatement.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return borrowListCurrent;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		List<Emprunt> listCurrent = new ArrayList<>();
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String LIST_ALL_BORROW_CURRENT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
					+ "FROM emprunt AS e "
					+ "INNER JOIN membre ON membre.id = e.idMembre "
					+ "INNER JOIN livre ON livre.id = e.idLivre "
					+ "WHERE dateRetour IS NULL AND membre.id = ?;";
			createPreparedStatement = connection.prepareStatement(LIST_ALL_BORROW_CURRENT);
			createPreparedStatement.setInt(1,idMembre);
			
			res = createPreparedStatement.executeQuery();
			while (res.next()) {
				listCurrent.add(new Emprunt(res.getInt("id"),
						new Livre(res.getInt("idLivre"),
								res.getString("titre"),
								res.getString("auteur"),
								res.getString("isbn")),
						new Membre(res.getInt("idMembre"),
								res.getString("nom"),
								res.getString("prenom"),
								res.getString("adresse"),
								res.getString("email"),
								res.getString("telephone"),
								Abonnement.getFromString(res.getString("abonnement"))),
						res.getDate("dateEmprunt").toLocalDate(),
						null
						));
			}
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when fetching the current borrowing list by member");
		}
		finally{
			try {
				connection.close();
				createPreparedStatement.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return listCurrent;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		List<Emprunt> listCurrent = new ArrayList<>();
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String LIST_ALL_BORROW_CURRENT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
					+ "FROM emprunt AS e "
					+ "INNER JOIN membre ON membre.id = e.idMembre "
					+ "INNER JOIN livre ON livre.id = e.idLivre "
					+ "WHERE dateRetour IS NULL AND livre.id = ?;";
			createPreparedStatement = connection.prepareStatement(LIST_ALL_BORROW_CURRENT);
			createPreparedStatement.setInt(1,idLivre);
			
			res = createPreparedStatement.executeQuery();
			while (res.next()) {
				listCurrent.add(new Emprunt(res.getInt("id"),
						new Livre(res.getInt("idLivre"),
								res.getString("titre"),
								res.getString("auteur"),
								res.getString("isbn")),
						new Membre(res.getInt("idMembre"),
								res.getString("nom"),
								res.getString("prenom"),
								res.getString("adresse"),
								res.getString("email"),
								res.getString("telephone"),
								Abonnement.getFromString(res.getString("abonnement"))),
						res.getDate("dateEmprunt").toLocalDate(),
						null
						));
			}
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when fetching the borrowing list by book");
		}
		finally{
			try {
				connection.close();
				createPreparedStatement.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return listCurrent;
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		Emprunt Current = null;
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, "
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
					+ "FROM emprunt AS e "
					+ "INNER JOIN membre ON membre.id = e.idMembre "
					+ "INNER JOIN livre ON livre.id = e.idLivre "
					+ "WHERE e.id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setInt(1,id);
			
			res = createPreparedStatement.executeQuery();
			if (res.next()) {
				LocalDate returnDate = (res.getDate("dateRetour") == null) ? null : res.getDate("dateRetour").toLocalDate();
				Current = new Emprunt(res.getInt("idEmprunt"),
						new Livre(res.getInt("idLivre"),
								res.getString("titre"),
								res.getString("auteur"),
								res.getString("isbn")),
						new Membre(res.getInt("idMembre"),
								res.getString("nom"),
								res.getString("prenom"),
								res.getString("adresse"),
								res.getString("email"),
								res.getString("telephone"),
								Abonnement.getFromString(res.getString("abonnement"))),
						res.getDate("dateEmprunt").toLocalDate(),
						returnDate);
			}
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when fetching the borrowing list");
		}
		finally{
			try {
				connection.close();
				createPreparedStatement.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return Current;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setInt(1,idMembre);
			createPreparedStatement.setInt(2,idLivre);
			createPreparedStatement.setDate(3, Date.valueOf(dateEmprunt));
			createPreparedStatement.setDate(4, null);
			
			createPreparedStatement.execute();
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when creating the borrowing reccord");
		}
		finally{
			try {
				connection.close();
				createPreparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "UPDATE emprunt "
					+ "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? "
					+ "WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setInt(1,emprunt.getMember().getId());
			createPreparedStatement.setInt(2,emprunt.getLivre().getId());
			createPreparedStatement.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
			createPreparedStatement.setDate(4, Date.valueOf(emprunt.getDateRetour()));
			createPreparedStatement.setInt(5, emprunt.getId());
			createPreparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when updating the borrowing list");
		}
		finally{
			try {
				connection.close();
				createPreparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

	@Override
	public int count() throws DaoException {
		Connection connection = null;
		Statement createStatement = null;
		ResultSet res = null;
		int count = 0;
		String COUNT_BORROW = "SELECT COUNT(id) AS count FROM emprunt;";
		try {
			connection = ConnectionManager.getConnection();
			createStatement = connection.createStatement();
			res = createStatement.executeQuery(COUNT_BORROW);
			if (res.next()) {
				count = res.getInt(1);
			}
			
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when counting borrowing list");
		}
		finally {
			try {
				connection.close();
				createStatement.close();
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return count;
	}

}
