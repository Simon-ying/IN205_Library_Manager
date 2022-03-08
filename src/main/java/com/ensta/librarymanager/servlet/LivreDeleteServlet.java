package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.LivreService;

public class LivreDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 51855013953355365L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/livre_delete")) {
			int id = -1;
			if (req.getParameter("id") != null) {
				id = Integer.parseInt(req.getParameter("id"));
			}
			
			final LivreService lInstance = LivreService.getInstance();
			try {
				if (id != -1) {
					req.setAttribute("book", lInstance.getById(id));
					req.setAttribute("id", id);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(req, resp);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final LivreService lInstance = LivreService.getInstance();
		try {
			lInstance.delete(Integer.parseInt(req.getParameter("id")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/LibraryManager/livre_list");
	}
	

}
