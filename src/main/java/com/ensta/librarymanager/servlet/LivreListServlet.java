package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.LivreService;

public class LivreListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 931088923883654318L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		
		if (action.equals("/livre_list")) {
			final LivreService lInstance = LivreService.getInstance();
			try {
				req.setAttribute("bookList", lInstance.getList());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/livre_list.jsp").forward(req, resp);
			
		}
	}
	

}
