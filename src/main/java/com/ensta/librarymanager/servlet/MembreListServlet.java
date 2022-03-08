package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.MembreService;

public class MembreListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657993820018299539L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		
		if (action.equals("/membre_list")) {
			final MembreService mInstance = MembreService.getInstance();
			try {
				req.setAttribute("membreList", mInstance.getList());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_list.jsp").forward(req, resp);
			
		}
	}

}
