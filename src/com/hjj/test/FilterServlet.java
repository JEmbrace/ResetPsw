package com.hjj.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(urlPatterns={"/validate.jsp","/modify.jsp"})
public class FilterServlet implements Filter {

	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//转换成实例的请求和响应对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session=req.getSession();
		if(((String)session.getAttribute("modify")).equals("OK")){
			System.out.println("跳转修改密码");
			req.getRequestDispatcher("/modify.jsp").forward(req,res);
		}
			
		if(session.getAttribute("userAccout")==null){
			req.getRequestDispatcher("/schoolnum.jsp").forward(req,res);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
