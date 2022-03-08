package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

public class MembreAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6906402111635806390L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/membre_add")) {
			final LivreService lInstance = LivreService.getInstance();
			final MembreService mInstance = MembreService.getInstance();
			try {
				req.setAttribute("currBook", lInstance.getListDispo());
				req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final MembreService mInstance = MembreService.getInstance();
		int id = -1;
		if (req.getParameter("nom").equals("") || req.getParameter("prenom").equals("")) {
			throw new ServletException("Name is empty");
		}
		try {
			
			id = mInstance.create(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("adresse"), req.getParameter("email"), req.getParameter("telephone"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final LivreService lInstance = LivreService.getInstance();
		try {
			req.setAttribute("currBook", lInstance.getListDispo());
			req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/LibraryManager/membre_details?id=" + id);
	}

}
