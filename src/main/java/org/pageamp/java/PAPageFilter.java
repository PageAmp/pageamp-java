/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pageamp.java;

import java.io.File;
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

/**
 *
 * @author fabrizio
 */
@WebFilter(filterName = "PAPageFilter", urlPatterns = {"/*"})
public class PAPageFilter implements Filter {
	private static final boolean DEBUG = false;
	private FilterConfig filterConfig = null;
	private String docroot;
	
	@Override
	public void init(FilterConfig filterConfig) {		
		this.filterConfig = filterConfig;
		filterConfig.getServletContext().getRealPath("/");
	}

	private void actualFilter(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain)
			throws Throwable {
		String path = req.getServletPath();
		int i1 = path.lastIndexOf('.');
		int i2 = path.lastIndexOf('/');
		String ext = (i1 > 0 && i2 < i1 ? path.substring(i1 + 1) : null);
		if (ext == null
				&& !path.endsWith("/")
				&& new File(docroot, path).isDirectory()) {
			// if path is a directory without a final "/", add one
			req.getRequestDispatcher(path + "/").forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}
	
	// <editor-fold defaultstate="collapsed" desc="Boilerplate code">
	
	public PAPageFilter() {
	}	
	
	private void doBeforeProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (DEBUG) {
			log("PAPageFilter:DoBeforeProcessing");
		}

		// Write code here to process the request and/or response before
		// the rest of the filter chain is invoked.
		// For example, a logging filter might log items on the request object,
		// such as the parameters.
		/*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
		 */
	}	
	
	private void doAfterProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (DEBUG) {
			log("PAPageFilter:DoAfterProcessing");
		}

		// Write code here to process the request and/or response after
		// the rest of the filter chain is invoked.
		// For example, a logging filter might log the attributes on the
		// request object after the request has been processed. 
		/*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
		 */
		// For example, a filter might append something to the response.
		/*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
		 */
	}

	/**
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
		if (DEBUG) {
			log("PAPageFilter:doFilter()");
		}
		
		doBeforeProcessing(request, response);
		
		Throwable problem = null;
		try {
			actualFilter((HttpServletRequest) request,
					(HttpServletResponse) response,
					chain);
		} catch (Throwable t) {
			// If an exception is thrown somewhere down the filter chain,
			// we still want to execute our after processing, and then
			// rethrow the problem after that.
			problem = t;
			t.printStackTrace();
		}
		
		doAfterProcessing(request, response);

		// If there was a problem, we want to rethrow it if it is
		// a known type, otherwise log it.
		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {		
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("PAPageFilter()");
		}
		StringBuffer sb = new StringBuffer("PAPageFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}
	
	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);		
		
		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);				
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

				// PENDING! Localize this for next official release
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");				
				pw.print(stackTrace);				
				pw.print("</pre></body>\n</html>"); //NOI18N
				pw.close();
				ps.close();
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
	
	// </editor-fold>

}
