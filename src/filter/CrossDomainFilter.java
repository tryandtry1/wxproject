package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(filterName="filter",urlPatterns="/servlet/*")
public class CrossDomainFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String originalURL = request.getHeader("Origin");
		String x="12";
		request.setAttribute("name", x);          
        response.addHeader("Access-Control-Allow-Origin", originalURL);
        response.addHeader("Access-Control-Allow-Credentials", "true");
        //response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.addHeader("Access-Control-Allow-Methods", "*");
      //告诉浏览器允许带有Content-Type,header1,header2头的请求访问
        arg2.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
