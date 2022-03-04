package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDao implements ILivreDao {
	
	private static LivreDao instance;
	public static LivreDao getInstance() {
		if (instance == null) {
			instance = new LivreDao();
		}
		return instance;
	}
	@Override
	public List<Livre> getList() throws DaoException {
		List<Livre> bookList = new ArrayList<>();
		Connection connection = null;
		Statement createStatement = null;
		ResultSet res = null;
		
		try {
			connection = ConnectionManager.getConnection();
			String LIST_ALL_BOOK = "SELECT id, titre, auteur, isbn FROM livre;";
			createStatement = connection.createStatement();
			res = createStatement.executeQuery(LIST_ALL_BOOK);
			while(res.next()) {
				bookList.add(new Livre(res.getInt("id"), 
						res.getString("titre"),
						res.getString("auteur"),
						res.getString("isbn")));
			}
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when fetching the book list.");
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
		
		return bookList;
		
	}

	@Override
	public Livre getById(int id) throws DaoException {
		Livre book = null;
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		
		try {
			connection = ConnectionManager.getConnection();
			String LIST_A_BOOK = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(LIST_A_BOOK);
			createPreparedStatement.setInt(1, id);
			res = createPreparedStatement.executeQuery();
			while(res.next()) {
				book = new Livre(res.getInt("id"), 
						res.getString("titre"),
						res.getString("auteur"),
						res.getString("isbn"));
			}
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when searching the book.");
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
		
		return book;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		String CREATE_BOOK = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		ResultSet res = null;
		int id = -1;
		
		try {
			connection = ConnectionManager.getConnection();
			createPreparedStatement = connection.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);
			createPreparedStatement.setString(1, titre);
			createPreparedStatement.setString(2, auteur);
			createPreparedStatement.setString(3, isbn);
			createPreparedStatement.execute();
			res = createPreparedStatement.getGeneratedKeys();
			
			if (res.next()) {
				id = res.getInt(1);
			}
		}
		catch(SQLException e) {
			throw new DaoException("Problem occured when adding new book.");
		}
		finally {
			try {
				connection.close();
				createPreparedStatement.close();
				res.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
		
	}

	@Override
	public void update(Livre livre) throws DaoException {
		Connection connection = null;
		PreparedStatement createPreparedStatement = null;
		
		try { 
			connection = ConnectionManager.getConnection();
			String SQL = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setString(1,livre.getTitre());
			createPreparedStatement.setString(2,livre.getAuteur());
			createPreparedStatement.setString(3, livre.getIsbn());
			createPreparedStatement.setInt(4, livre.getId());
			createPreparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when updating the book list.");
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
			String SQL = "DELETE FROM livre WHERE id = ?;";
			createPreparedStatement = connection.prepareStatement(SQL);
			createPreparedStatement.setInt(1,id);
			createPreparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when deleting a book.");
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
			String SQL = "SELECT COUNT(id) AS count FROM livre;";
			createPreparedStatement = connection.prepareStatement(SQL);
			res = createPreparedStatement.executeQuery();
			if (res.next()) {
				count = res.getInt(1);
			}
		}
		catch (SQLException e) {
			throw new DaoException("Problem occured when counting the book list.");
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
