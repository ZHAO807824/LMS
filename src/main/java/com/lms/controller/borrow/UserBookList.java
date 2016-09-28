package com.lms.controller.borrow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Lists;
import com.lms.bean.Borrow;
import com.lms.entity.Admin;
import com.lms.entity.book.Book;
import com.lms.service.borrow.BorrowService;

/**
 * Servlet implementation class UserBookList
 */
public class UserBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserBookList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BorrowService service = new BorrowService();
		HttpSession session=request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		List<Borrow<List<Book>>> lists =Lists.newArrayList();
		if(admin.getRole()==1)
			lists=service.userBookList();
		else
			lists=service.userBookList(admin.getId());
		if (lists != null && lists.size() > 0) {
			request.setAttribute("lists", lists);
		}
		request.getRequestDispatcher("user_book_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
