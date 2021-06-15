package com.ubimate.pageamp;

import java.io.File;
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
@WebServlet("/.pageamp-page")
public class PAPageServlet extends HttpServlet {
	public static final String ROOTPATH = "/.pageamp-page";
	protected String docroot;

	@Override
	public void init() {
		docroot = getServletContext().getRealPath("/");
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			String domain = req.getAttribute("domain").toString();
			String path = req.getAttribute("path").toString();
			String ext = req.getAttribute("ext").toString();
			if (ext.length() == 0) {
				File f = new File(docroot, path);
				if (f.isDirectory()) {
					path += "/index";
				} else {
					path += ".html";
				}
			}
			String contextPath = getServletContext().getContextPath();
			Page page = Server.load(docroot, path, domain, true, contextPath);
			String html = page.doc.toString();
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter wr = res.getWriter();
			wr.print(html);
		} catch (Exception ex) {
			//TODO: better error presentation
			res.setContentType("text/text; charset=UTF-8");
			PrintWriter wr = res.getWriter();
			ex.printStackTrace(wr);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "PAPageServlet";
	}

}
