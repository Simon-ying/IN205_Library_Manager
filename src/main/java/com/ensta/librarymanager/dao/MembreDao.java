package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.utils.Abonnement;

public class MembreDao implements IMembreDao {
	private static MembreDao instance;
	public static MembreDao getInstance() {
		if (instance == null) {
			instance = new MembreDao();
		}
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws DaoException {
		List<Membre> memberList = new ArrayList<>();
		Connection connection = null;
		Statement createStatement = null;
		ResultSet res = null;
		
		try {
			connection = ConnectionManager.getConnection();
			String SQL = "SELECT id, nom, prenom, adresse, email, telephone, abonnement "
					+ "FROM membre "
					+ "ORDER BY nom, prenom;";
			createStatement = connection.createStatement();
			res = createStatement.executeQuery(SQL);
			while(res.next()) {
				memberList.add(new Membre(res.getInt("id"), 
						res.getString("nom"),
						res.getString("prenom"),
						res.getString("adresse"),
						res.getString("email"),
						res.getString("telephone"),
						Abonnement.getFromString(res.getString("abonnement"))));
			}
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when fetching the member list.");
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
		
		return memberList;
	}

	@Override
	public Membre getById(int id) throws DaoException {
		Membre member = null;
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		
		try {
			connection = ConnectionManager.getConnection();
			String SQL = "SELECT id, nom, prenom, adresse, email, telephone, abonnement "
					+ "FROM membre "
					+ "WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setInt(1, id);
			res = createPreparedStatement.executeQuery();
			while(res.next()) {
				member = new Membre(res.getInt("id"), 
						res.getString("nom"),
						res.getString("prenom"),
						res.getString("adresse"),
						res.getString("email"),
						res.getString("telephone"),
						Abonnement.getFromString(res.getString("abonnement")));
			}
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when searching the member.");
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
		
		return member;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
		String SQL = "INSERT INTO membre(nom, prenom, adresse, email, telephone, "
				+ "abonnement) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		int id = -1;
		
		try {
			connection = ConnectionManager.getConnection();
			createPreparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			createPreparedStatement.setString(1, nom);
			createPreparedStatement.setString(2, prenom);
			createPreparedStatement.setString(3, adresse);
			createPreparedStatement.setString(4, email);
			createPreparedStatement.setString(5, telephone);
			createPreparedStatement.setString(6, "BASIC");
			
			createPreparedStatement.execute();
			res = createPreparedStatement.getGeneratedKeys();
			
			if (res.next()) {
				id = res.getInt(1);
			}
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when adding new member.");
		}
		finally {
			try {
				res.close();
				createPreparedStatement.close();
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public void update(Membre membre) throws DaoException {
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "UPDATE membre "
					+ "SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, "
					+ "abonnement = ? "
					+ "WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setString(1,membre.getNom());
			createPreparedStatement.setString(2,membre.getPrenom());
			createPreparedStatement.setString(3, membre.getAdresse());
			createPreparedStatement.setString(4, membre.getEmail());
			createPreparedStatement.setString(5, membre.getTelephone());
			createPreparedStatement.setString(6, membre.getAbon());
			createPreparedStatement.setInt(7, membre.getId());
			createPreparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when updating the member list");
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
	public void delete(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "DELETE FROM membre WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setInt(1,id);
			createPreparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when deleting the member list");
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
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		int count = -1;
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "SELECT COUNT(id) AS count FROM membre;";
			createPreparedStatement = connection.prepareStatement(SQL);
			res = createPreparedStatement.executeQuery();
			if (res.next()) {
				count = res.getInt(1);
			}
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when counting the member list.");
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
		return count;
	}

}
