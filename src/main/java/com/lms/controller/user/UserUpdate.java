package com.lms.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lms.entity.user.User;
import com.lms.service.user.UserService;

/**
 * Servlet implementation class UserUpdate
 */
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			UserService service = new UserService();
			request.setAttribute("user", service.query(id));
		}
		request.getRequestDispatcher("user_add.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String tell = request.getParameter("tell");
		String idcard = request.getParameter("idcard");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");

		UserService service = new UserService();
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(service
				.update(new User(Integer.valueOf(id), name, email, tell, idcard, address, Integer.valueOf(gender)))));
	}

}
