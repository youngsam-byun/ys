package com.ys.app.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter {

	private static final String TNC_PATH = "/tnc";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		 String url = ((HttpServletRequest) request).getPathTranslated();
	    if(url!=null&&url.isEmpty()==false &&url.contains(TNC_PATH)==false)    
	    	chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request),response);
	    else 
	    	chain.doFilter(new XSSRequestWrapperForWebEditor((HttpServletRequest) request),response);
	}

	@Override
	public void destroy() {

	}

}
