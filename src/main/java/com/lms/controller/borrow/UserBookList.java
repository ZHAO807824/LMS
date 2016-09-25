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
 * Servlet implementation class UserBookList
 */
public class UserBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserBookList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BorrowService service = new BorrowService();
		List<Borrow<List<Book>>> list = service.userBookList();
		if (list != null && list.size() > 0) {
			for (Borrow<List<Book>> borrow : list) {
				System.out.println(borrow.getKey());
				List<Book> values = borrow.getValue();
				for (Book book : values) {
					System.out.println(book);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
