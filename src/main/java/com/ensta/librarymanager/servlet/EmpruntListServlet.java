package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.EmpruntService;

public class EmpruntListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7608980966202657645L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final EmpruntService eInstance = EmpruntService.getInstance();
		String action = req.getServletPath();
		String show = "";
		if (req.getParameter("show") != null) {
			show = req.getParameter("show");
		}
		if (action.equals("/emprunt_list")) {
			try {
				if (show.equals("all"))
					req.setAttribute("allList", eInstance.getList());
				else
					req.setAttribute("allList", eInstance.getListCurrent());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(req, resp);
		
	}
	
}
