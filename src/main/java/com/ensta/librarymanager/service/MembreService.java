package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.exception.*;

public class MembreService implements IMembreService {
	private static MembreService instance;
	public static MembreService getInstance() {
		if (instance == null) {
			instance = new MembreService();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws ServiceException {
		List<Membre> list = new ArrayList<>();
		try {
			list = MembreDao.getInstance().getList();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when fetching the member list");
		}
		return list;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		List<Membre> list = this.getList();
		Iterator<Membre> iter = list.iterator();
		while (iter.hasNext()) {
			Membre member = iter.next();
			if (!EmpruntService.getInstance().isEmpruntPossible(member)) {
				iter.remove();
			}
		}
		return list;
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		Membre member = null;
		try {
			member = MembreDao.getInstance().getById(id);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when searching the member");
		}
		return member;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
		if (nom == null & prenom == null) {
			throw new ServiceException("The name of a member can't be empty");
		}
		String nom_maj = nom.toUpperCase();
		int id = -1;
		try {
			id = MembreDao.getInstance().create(nom_maj, prenom, adresse, email, telephone);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when searching the member");
		}
		return id;
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		if (membre.getNom() == null & membre.getPrenom() == null) {
			throw new ServiceException("The name of a member can't be empty");
		}
		String nom = membre.getNom().toUpperCase();
		membre.setNom(nom);
		try {
			MembreDao.getInstance().update(membre);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when updating the member list");
		}

	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			MembreDao.getInstance().delete(id);
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when deleting a member");
		}

	}

	@Override
	public int count() throws ServiceException {
		int count = 0;
		try {
			count = MembreDao.getInstance().count();
		}
		catch (DaoException e) {
			throw new ServiceException("Problem occured when counting the member list");
		}
		return count;
	}

}
