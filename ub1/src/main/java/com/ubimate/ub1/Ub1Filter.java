package com.ubimate.ub1;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ub1.Ub1Server;

/**
 *
 * @author fabrizio
 */
@WebFilter("/*")
public class Ub1Filter implements Filter {
	private FilterConfig filterConfig = null;

	public Ub1Filter() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain)
			throws IOException, ServletException {
		try {
			String path = ((HttpServletRequest) req).getServletPath();
			path = (path.endsWith("/") ? path.substring(0, path.length() - 1) : path);
			
			int i1 = path.lastIndexOf('.');
			int i2 = path.lastIndexOf('/');
			String ext = (i1 > 0 && i2 < i1 ? path.substring(i1 + 1) : "");
			
			if (ext.length() == 0 || "html".equalsIgnoreCase(ext)) {
				// requests of files with either .html or no suffix are
				// considered page requests (this includes directories, whose
				// index.html will be served)
				String domain = req.getServerName();
				domain = "127.0.0.1".equals(domain) ? "localhost" : domain;
				req.setAttribute("domain", domain);
				req.setAttribute("path", path);
				req.setAttribute("ext", ext);
				req.getRequestDispatcher(Ub1Servlet.ROOTPATH).forward(req, res);
			} else if (Ub1Server.CLIENT_JS_PATHNAME.equals(path)) {
				req.getRequestDispatcher(Ub1Client.ROOTPATH).forward(req, res);
			} else if (path.endsWith(".htm")) {
				// *.htm are considered page fragments meant for inclusion
				// and are not directly served
				((HttpServletResponse) res).setStatus(404);
			} else {
				chain.doFilter(req, res);
			}
		} catch (Throwable problem) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, res);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("Ub1Filter()");
		}
		StringBuilder sb = new StringBuilder("Ub1Filter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);

		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				try (PrintStream ps = new PrintStream(response.getOutputStream());
						PrintWriter pw = new PrintWriter(ps)) {
					pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

					// PENDING! Localize this for next official release
					pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
					pw.print(stackTrace);
					pw.print("</pre></body>\n</html>"); //NOI18N
				}
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		}
	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception ex) {
		}
		return stackTrace;
	}

	public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

}
