package com.lms.controller.borrow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.bean.Borrow;
import com.lms.entity.user.User;
import com.lms.service.borrow.BorrowService;

/**
 * Servlet implementation class BookUserList
 */
public class BookUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookUserList() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BorrowService service = new BorrowService();
		List<Borrow<List<User>>> lists = service.bookUserList();
		if (lists != null && lists.size() > 0) {
			request.setAttribute("lists",lists);
		}
		request.getRequestDispatcher("book_user_list.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
