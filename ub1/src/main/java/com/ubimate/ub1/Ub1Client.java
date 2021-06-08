package com.ubimate.ub1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/.ub1-client")
public class Ub1Client extends HttpServlet {
	public static final String ROOTPATH = "/.ub1-client";
	protected static final String CLIENT_JS_PATH = "ub1.js";
	protected static final int BUFFER_SIZE = 4 * 1024;
	
	@Override
	public void init() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter wr = res.getWriter();
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream(CLIENT_JS_PATH)))) {
			res.setContentType("application/javascript");
			char buff[] = new char[BUFFER_SIZE];
			for (int n; (n = rd.read(buff)) >= 0;) {
				wr.write(buff, 0, n);
			}
		} catch (Exception ex) {
			//TODO: better error presentation
			res.setContentType("text/text;charset=UTF-8");
			ex.printStackTrace(wr);
		}
	}

}
