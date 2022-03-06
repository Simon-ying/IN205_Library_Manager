package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.dao.*;

public class LivreService implements ILivreService {
	
	private static LivreService instance;
	public static LivreService getInstance() {
		if (instance == null) {
			instance = new LivreService();
		}
		return instance;
	}

	@Override
	public List<Livre> getList() throws ServiceException {
		List<Livre> bookList = new ArrayList<>();
		try {
			bookList = LivreDao.getInstance().getList();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the book list");
		}
		return bookList;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		List<Livre> bookList = new ArrayList<>();
		bookList = getList();
		Iterator<Livre> iter = bookList.iterator();
		while (iter.hasNext()) {
			Livre next = iter.next();
			if (!EmpruntService.getInstance().isLivreDispo(next.getId())) {
				iter.remove();
			}
		}
		return bookList;
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		Livre book = null;
		try {
			book = LivreDao.getInstance().getById(id);
		}
		catch(DaoException e) {
			throw new ServiceException("Problem occured when searching the book");
		}
		return book;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		int id = -1;
		if (titre == null) {
			throw new ServiceException("Title of book can't be empty");
		}
		try {
			id = LivreDao.getInstance().create(titre, auteur, isbn);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when adding new book");
		}
		return id;
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		if (livre.getTitre() == null)
			throw new ServiceException("Title of book can't be empty");
		try {
			LivreDao.getInstance().update(livre);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when updating the book list");
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			LivreDao.getInstance().delete(id);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when deleting a book");
		}
	}

	@Override
	public int count() throws ServiceException {
		int count = 0;
		try {
			count = LivreDao.getInstance().count();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when counting the book list");
		}
		return count;
	}

}
