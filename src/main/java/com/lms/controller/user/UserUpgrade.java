package com.lms.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lms.service.user.UserService;

/**
 * Servlet implementation class UserUpgrade
 */
public class UserUpgrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UserUpgrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		UserService service=new UserService();
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(service.upgrade(id)));
	}

}
