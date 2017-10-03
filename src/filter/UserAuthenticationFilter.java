package filter;

import java.io.IOException;


import java.util.Arrays;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

//@WebFilter(filterName="UserAuthenticationFilter",urlPatterns={"/*"})
public class UserAuthenticationFilter {
	private String [] pages;
	private String [] acceptableSuffix;
	
	public void destroy(){
		
	}
	
	public void doFilter(ServletRequest req,ServletResponse res)throws ServletException,IOException{
		HttpServletRequest request=(HttpServletRequest)req;
		User user=(User)(request.getSession().getAttribute("user"));
		
		/*
		boolean matchPages=Arrays.stream(pages)
				.map(page->request.getRequestURI().endsWith(page))
				.reduce(false, (acc, e) -> acc || e);
		
		boolean matchSuffix = Arrays.stream(acceptableSuffix)
                .map(suffix -> request.getRequestURI().endsWith(suffix))
                .reduce(false, (acc, e) -> acc || e);
		
		if (!matchSuffix && !matchPages && user == null) {
            request.getContextPath();
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendRedirect("jumpToIndex.jsp");
            return;
        }
        chain.doFilter(req, res);
        */
	}
	
	
	public void init(FilterConfig config) throws ServletException {
        pages = new String[]{
                "index.jsp", "login.jsp", "/login"
                , "register.jsp", "/register"
                , "jumpToIndex.jsp", "/404", "about.jsp"};

        acceptableSuffix = new String[]{"css", "js", "png"};
    }
}
