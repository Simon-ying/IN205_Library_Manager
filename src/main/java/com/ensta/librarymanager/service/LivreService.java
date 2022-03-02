package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;

public class LivreService implements ILivreService {

	@Override
	public List<Livre> getList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

}
