package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;

public class LivreDao implements ILivreDao {

	@Override
	public List<Livre> getList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Livre getById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Livre livre) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public int count() throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
