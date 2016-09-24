package com.lms.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lms.entity.user.User;
import com.lms.service.user.UserService;

/**
 * Servlet implementation class UserAdd
 */
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("user_add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String tell = request.getParameter("tell");
		String idcard = request.getParameter("idcard");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String role = request.getParameter("role");

		UserService service = new UserService();
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(service.register(new User(name, email, tell, idcard, address,
				Integer.valueOf(gender), 0, Integer.valueOf(role), Integer.valueOf(status)))));
	}

}
