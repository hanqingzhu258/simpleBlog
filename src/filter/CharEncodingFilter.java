package filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebFilter(
		filterName="encodingFilter",
		urlPatterns={"/*"},
		initParams={@WebInitParam(name="encoding",value="UTF-8")}
)*/

public class CharEncodingFilter implements Filter{
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws  ServletException{
		encoding=filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,FilterChain filterChain) throws IOException,ServletException{
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		request.setCharacterEncoding(encoding);
		((ServletRequest) response).setCharacterEncoding(encoding);
		filterChain.doFilter(request,response);
	}
	
	@Override
	public void destroy(){
		
	}
	
}
