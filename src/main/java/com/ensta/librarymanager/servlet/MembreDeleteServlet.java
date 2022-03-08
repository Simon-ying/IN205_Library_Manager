package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.MembreService;

public class MembreDeleteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2842177992023688907L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String action = req.getServletPath();
		if (action.equals("/membre_delete")) {
			int id = -1;
			if (req.getParameter("id") != null) {
				id = Integer.parseInt(req.getParameter("id"));
			}
			
			final MembreService mInstance = MembreService.getInstance();
			try {
				if (id != -1) {
					req.setAttribute("membre", mInstance.getById(id));
					req.setAttribute("id", id);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp").forward(req, resp);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final MembreService mInstance = MembreService.getInstance();
		try {
			
			mInstance.delete(Integer.parseInt(req.getParameter("id")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("/LibraryManager/membre_list");
	}

}
