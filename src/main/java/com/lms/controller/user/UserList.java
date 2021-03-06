package com.lms.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.service.user.UserService;

/**
 * Servlet implementation class UserList
 */
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service=new UserService();
		request.setAttribute("users", service.list());
		request.getRequestDispatcher("user_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
