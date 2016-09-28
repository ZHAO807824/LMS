package com.lms.controller.borrow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.lms.entity.Admin;
import com.lms.service.user.UserService;

/**
 * Servlet implementation class Borrow
 */
public class Borrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Borrow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId=request.getParameter("id");
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		UserService service=new UserService();
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(service.borrow(Integer.valueOf(bookId), admin.getId())));
	}

}
