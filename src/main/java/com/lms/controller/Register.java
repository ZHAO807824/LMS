package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lms.entity.Admin;
import com.lms.entity.user.User;
import com.lms.service.RegisterService;
import com.lms.util.ValidatorUtil;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String tell=request.getParameter("tell");
		String idcard=request.getParameter("idcard");
		String address=request.getParameter("address");
		String genderStr=request.getParameter("gender");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if (StringUtils.isNoneEmpty(name)&&StringUtils.isNotEmpty(tell)&&StringUtils.isNotEmpty(address)&&StringUtils.isNotEmpty(genderStr)&&StringUtils.isNotEmpty(password)&&ValidatorUtil.isIDCard(idcard)&&ValidatorUtil.isEmail(email)) {
			Admin admin=new Admin(email, password);
			User user=new User(name, email, tell, idcard, address, Integer.valueOf(genderStr));
		
			RegisterService service=new RegisterService();
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(service.register(admin, user)));
		}
	}

}
