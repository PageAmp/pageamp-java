package com.ubimate.ub1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ub1.TestAll;

@WebServlet("/.ub1-test")
public class Ub1TestServlet extends HttpServlet {
	public static final String ROOTPATH = "/.ub1-test";
	
	@Override
	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/plain;charset=UTF-8");
		res.getWriter().println(TestAll.test(null));
	}

}
