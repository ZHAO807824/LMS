package com.lms.controller.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.entity.Admin;
import com.lms.service.book.BookService;

/**
 * Servlet implementation class BookList
 */
public class BookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService service=new BookService();
		HttpSession session=request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		request.setAttribute("books", service.list(admin.getRole(),null));
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookName=request.getParameter("bookName");
		BookService service=new BookService();
		HttpSession session=request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		request.setAttribute("books", service.list(admin.getRole(),bookName));
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
	}

}
