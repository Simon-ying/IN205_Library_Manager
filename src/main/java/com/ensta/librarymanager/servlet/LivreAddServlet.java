package com.ensta.librarymanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

public class LivreAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6301273089225024638L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/livre_add")) {
			final LivreService lInstance = LivreService.getInstance();
			final MembreService mInstance = MembreService.getInstance();
			try {
				req.setAttribute("currBook", lInstance.getListDispo());
				req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final LivreService lInstance = LivreService.getInstance();
		int id = -1;
		if (req.getParameter("titre") == null || req.getParameter("titre")=="") {
			throw new ServletException("Title is empty");
		}
		try {
			
			id = lInstance.create(req.getParameter("titre"), req.getParameter("auteur"), req.getParameter("isbn"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final MembreService mInstance = MembreService.getInstance();
		try {
			req.setAttribute("currBook", lInstance.getListDispo());
			req.setAttribute("currMember", mInstance.getListMembreEmpruntPossible());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/LibraryManager/livre_details?id=" + id);
	}
	

}
