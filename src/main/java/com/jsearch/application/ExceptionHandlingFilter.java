package com.jsearch.application;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandlingFilter implements Filter {

	private final Logger LOG = Logger.getLogger(ExceptionHandlingFilter.class.getName());

	@Override
	public void destroy() {
		// no-op
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// no-op
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Exception ex) {
			LOG.warning("Unhandled Request Exception: ");
			ex.printStackTrace();
			/** Send Generic 500 response without any revealing information **/
			((HttpServletResponse) response).sendError(500, "Unhandled exception resulted from this request.");
		}

	}

}