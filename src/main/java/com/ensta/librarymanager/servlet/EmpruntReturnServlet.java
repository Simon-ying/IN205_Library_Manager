package com.ensta.librarymanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.EmpruntService;

public class EmpruntReturnServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4817537809978612323L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		int id = -1;
		if (action.equals("/emprunt_return")) {
			final EmpruntService eInstance = EmpruntService.getInstance();
			if (req.getParameter("id") != null) {
				id = Integer.parseInt(req.getParameter("id"));
			}
			try {
				if (id != -1) {
					req.setAttribute("borrow", eInstance.getById(id));
				}
				req.setAttribute("currBorrow", eInstance.getListCurrent());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final EmpruntService eInstance = EmpruntService.getInstance();
		try {
			if (req.getParameter("id") == null) {
				throw new ServletException("borrow record does not exist");
			}
			else {
				eInstance.returnBook(Integer.parseInt(req.getParameter("id")));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			req.setAttribute("currBorrow", eInstance.getListCurrent());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/LibraryManager/emprunt_list");
	}
	
	

}
