package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.dao.*;

public class EmpruntService implements IEmpruntService {
	
	private static EmpruntService instance;
	public static EmpruntService getInstance() {
		if (instance == null) {
			instance = new EmpruntService();
		}
		return instance;
	}

	@Override
	public List<Emprunt> getList() throws ServiceException {
		List<Emprunt> borrowList = new ArrayList<>();
		try {
			borrowList = EmpruntDao.getInstance().getList();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the borrowing list");
		}
		return borrowList;
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		List<Emprunt> borrowListCurrent = new ArrayList<>();
		try {
			borrowListCurrent = EmpruntDao.getInstance().getListCurrent();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the current borrowing list");
		}
		return borrowListCurrent;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		List<Emprunt> borrowListCurrent = new ArrayList<>();
		try {
			borrowListCurrent = EmpruntDao.getInstance().getListCurrentByMembre(idMembre);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the current borrowing list by member");
		}
		return borrowListCurrent;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		List<Emprunt> borrowListCurrent = new ArrayList<>();
		try {
			borrowListCurrent = EmpruntDao.getInstance().getListCurrentByLivre(idLivre);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the current borrowing list by book");
		}
		return borrowListCurrent;
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		Emprunt Current = null;
		try {
			Current = EmpruntDao.getInstance().getById(id);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the current borrowing list by ID");
		}
		return Current;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		try {
			EmpruntDao.getInstance().create(idMembre, idLivre, dateEmprunt);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when creating the borrowing reccord");
		}

	}

	@Override
	public void returnBook(int id) throws ServiceException {
		Emprunt temp = this.getById(id);
		temp.setDateRetour(LocalDate.now());
		try {
			EmpruntDao.getInstance().update(temp);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when returning a book");
		}

	}

	@Override
	public int count() throws ServiceException {
		int count = 0;
		try {
			count = EmpruntDao.getInstance().count();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when counting borrowing list");
		}
		return count;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		List<Emprunt> temp = this.getListCurrentByLivre(idLivre);
		if (temp.size() > 0) return false;
		else return true;

		
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		List<Emprunt> temp = this.getListCurrentByMembre(membre.getId());
		if (temp.size() < membre.getAbon().getValue()) {
			return true;
		}
		else return false;
	}

}
