package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.*;

public class EmpruntAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1998662569720788024L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final String action = req.getServletPath();
		if (action.equals("/emprunt_add")) {
			final LivreService lInstance = LivreService.getInstance();
			final MembreService mInstance = MembreService.getInstance();
			try {
				req.setAttribute("avBook", lInstance.getListDispo());
				req.setAttribute("avMember", mInstance.getListMembreEmpruntPossible());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final EmpruntService eInstance = EmpruntService.getInstance();
		
		final LivreService lInstance = LivreService.getInstance();
		final MembreService mInstance = MembreService.getInstance();
		try {
			eInstance.create(Integer.parseInt(req.getParameter("idMembre")), Integer.parseInt(req.getParameter("idLivre")), LocalDate.now());
			System.out.println(lInstance.getById(Integer.parseInt(req.getParameter("idLivre"))) + " : " + mInstance.getById(Integer.parseInt(req.getParameter("idMembre"))));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			req.setAttribute("avBook", lInstance.getListDispo());
			req.setAttribute("avMember", mInstance.getListMembreEmpruntPossible());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/LibraryManager/emprunt_list");
	}

}
