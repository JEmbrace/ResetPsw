package com.hjj.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;

@WebServlet(name = "myFirstServlet", urlPatterns = "/resetCode")
public class GetImagServlet extends HttpServlet {
	// 学校教务处登陆页面
	private final String LOGIN_URL = "教务登陆页面URL";
	// 验证码页面
	private final String CAPTCHA_URL = "教务登陆页面上验证码图片的URL";
	// 保存会话cookie
	public static String cookieValue = null;
	// 模拟登录设置服务器代理
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	
	final HashMap<String, String> map = new HashMap<String, String>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String schoolnum = request.getParameter("schoolnum");
		String path=request.getRequestURI()+"../"+"WebContent/image/vcode.png";
		System.out.println("获取的路径："+path);
		Dao dao = new Dao();
		cookieValue = dao.getPng(LOGIN_URL, CAPTCHA_URL, USER_AGENT);
		HttpSession session=request.getSession();
		session.setAttribute("userCookie", cookieValue);
		session.setAttribute("userAccout", schoolnum);
		map.put(schoolnum, cookieValue);
		request.getRequestDispatcher("/validate.jsp").forward(request,response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ClientProtocolException, IOException, ServletException {
		HttpSession session=request.getSession();
		String schoolnum = request.getParameter("schoolnum");
		String userPass = request.getParameter("password");
		Dao dao = new Dao();
		boolean info = dao.postForm(schoolnum, request.getParameter("password"), request.getParameter("validate"), userPass,
				(String)session.getAttribute("userCookie"), USER_AGENT);
		
		session.setAttribute("userCookie", null);
		session.setAttribute("userAccout", null);
		if(info==true){
			System.out.println("可以跳转修改密码");
			session.setAttribute("modify", "OK");
		}
		System.out.println("验证结果"+info);
		PrintWriter out = response.getWriter();
		if (info == true) {
			//out.println(1);
			out.flush();
		} else {
			//out.println(0);
			out.flush();
		}
	}
}
