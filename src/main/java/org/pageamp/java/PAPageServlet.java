/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pageamp.java;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pageamp.Server;
import pageamp.core.Page;

/**
 *
 * @author fabrizio
 */
@WebServlet(name = "PAPageServlet", urlPatterns = {"*.html"})
public class PAPageServlet extends HttpServlet {
	protected String docroot;

	@Override
	public void init() {
		docroot = getServletContext().getRealPath("/");
	}

	protected void processRequest(HttpServletRequest req,
			HttpServletResponse res)
			throws ServletException, IOException {
		String docroot = req.getServletContext().getRealPath("/");
		String path = req.getServletPath();
		String domain = req.getServerName();
		domain = "127.0.0.1".equals(domain) ? "localhost" : domain;
		res.setContentType("text/html;charset=UTF-8");
		try (PrintWriter wr = res.getWriter()) {
			String contextPath = getServletContext().getContextPath();
			Page page = Server.load(docroot, path, domain, true, contextPath);
			String html = page.doc.toString();
			res.setContentType("text/html; charset=UTF-8");
			wr.print(html);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="Boilerplate code">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
