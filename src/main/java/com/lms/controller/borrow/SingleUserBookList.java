package com.lms.controller.borrow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.bean.Borrow;
import com.lms.entity.book.Book;
import com.lms.service.borrow.BorrowService;

/**
 * Servlet implementation class SingleUserBookList
 */
public class SingleUserBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SingleUserBookList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		BorrowService service = new BorrowService();
		List<Borrow<List<Book>>> lists = service.userBookList(idStr);
		if (lists != null && lists.size() > 0) {
			request.setAttribute("lists", lists);
		}
		request.getRequestDispatcher("user_book_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
