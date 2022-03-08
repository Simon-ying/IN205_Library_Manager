package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;

public class LivreDetailsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7028244142910870818L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/livre_details")) {
			final LivreService lInstance = LivreService.getInstance();
			final EmpruntService eInstance = EmpruntService.getInstance();
			try {
				req.setAttribute("book", lInstance.getById(Integer.parseInt(req.getParameter("id"))));
				req.setAttribute("borrowList", eInstance.getListCurrentByLivre(Integer.parseInt(req.getParameter("id"))));
			}
			catch (Exception e) {
				new ServletException("can not find the book");
			}
			req.getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(req, resp);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final LivreService lInstance = LivreService.getInstance();
		final EmpruntService eInstance = EmpruntService.getInstance();
		if (req.getParameter("titre") == null || req.getParameter("titre")=="") {
			throw new ServletException("Title is empty");
		}
		try {
			Livre ubook = lInstance.getById(Integer.parseInt(req.getParameter("id")));
			ubook.setAuteur(req.getParameter("auteur"));
			ubook.setIsbn(req.getParameter("isbn"));
			ubook.setTitre(req.getParameter("titre"));
			lInstance.update(ubook);
			req.setAttribute("book", lInstance.getById(Integer.parseInt(req.getParameter("id"))));
			req.setAttribute("borrowList", eInstance.getListCurrentByLivre(Integer.parseInt(req.getParameter("id"))));
			resp.sendRedirect("/LibraryManager/livre_details?id=" + ubook.getId());
		} catch (NumberFormatException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	

}
