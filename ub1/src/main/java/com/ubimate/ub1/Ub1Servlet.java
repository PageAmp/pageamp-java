package com.ubimate.ub1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ub1.Ub1Server;
import ub1.core.Page;

/**
 *
 * @author fabrizio
 */
@WebServlet("/ub1-servlet")
public class Ub1Servlet extends HttpServlet {

	public static final String ROOTPATH = "/ub1-servlet";
	protected String rootPath;

	@Override
	public void init() {
		rootPath = getServletContext().getRealPath("/");
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter wr = res.getWriter();
		try {
			String domain = req.getAttribute("domain").toString();
			String path = req.getAttribute("path").toString();
			String ext = req.getAttribute("ext").toString();
			if (ext.length() == 0) {
				File f = new File(rootPath, path);
				if (f.isDirectory()) {
					path += "/index";
				} else {
					path += ".html";
				}
			}
			Page page = Ub1Server.load(rootPath, path, domain, false);
			res.setContentType("text/html;charset=UTF-8");
			wr.print(page.doc.toString());
		} catch (Exception ex) {
			res.setContentType("text/text;charset=UTF-8");
			ex.printStackTrace(wr);
		} finally {
			wr.close();
			
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
		return "Ub1 Servlet";
	}

}
