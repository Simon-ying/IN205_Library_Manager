package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ensta.librarymanager.service.*;
public class DashboardServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -963094406558960533L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
//		System.out.println(action);
		if (action.equals("/dashboard")) {
			final MembreService mInstance = MembreService.getInstance();
			final LivreService lInstance = LivreService.getInstance();
			final EmpruntService eInstance = EmpruntService.getInstance();
			
			try {
				req.setAttribute("mSum", mInstance.count());
				req.setAttribute("lSum", lInstance.count());
				req.setAttribute("eSum", eInstance.count());
				req.setAttribute("currBorrow", eInstance.getListCurrent());
			}
			catch (final Exception e) {
				e.printStackTrace();
			}
			final RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
