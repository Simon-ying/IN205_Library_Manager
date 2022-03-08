package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.utils.Abonnement;

public class MembreDetailsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8188707111739130363L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/membre_details")) {
			final MembreService mInstance = MembreService.getInstance();
			final EmpruntService eInstance = EmpruntService.getInstance();
			try {
				req.setAttribute("membre", mInstance.getById(Integer.parseInt(req.getParameter("id"))));
				req.setAttribute("emprunts", eInstance.getListCurrentByMembre(Integer.parseInt(req.getParameter("id"))));
			}
			catch (Exception e) {
				new ServletException("can not find the book");
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(req, resp);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final MembreService mInstance = MembreService.getInstance();
		final EmpruntService eInstance = EmpruntService.getInstance();
		if (req.getParameter("nom").equals("") || req.getParameter("prenom").equals("")) {
			throw new ServletException("Name is empty");
		}
		try {
			Membre umembre = mInstance.getById(Integer.parseInt(req.getParameter("id")));
			umembre.setNom(req.getParameter("nom"));
			umembre.setPrenom(req.getParameter("prenom"));
			umembre.setAdresse(req.getParameter("adresse"));
			umembre.setEmail(req.getParameter("email"));
			umembre.setTelephone(req.getParameter("telephone"));
			umembre.setAbon(Abonnement.getFromString(req.getParameter("abonnement")));

			mInstance.update(umembre);
			req.setAttribute("membre", mInstance.getById(umembre.getId()));
			req.setAttribute("emprunts", eInstance.getListCurrentByMembre(umembre.getId()));
			resp.sendRedirect("/LibraryManager/membre_details?id=" + umembre.getId());
		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
