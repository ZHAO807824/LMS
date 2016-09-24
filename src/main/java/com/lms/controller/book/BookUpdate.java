package com.lms.controller.book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lms.entity.book.Book;
import com.lms.service.book.BookService;

/**
 * Servlet implementation class BookUpdate
 */
public class BookUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			BookService service = new BookService();
			request.setAttribute("book", service.query(id));
		}
		request.getRequestDispatcher("book_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String auth = request.getParameter("auth");
		String press = request.getParameter("press");
		String totalNumber = request.getParameter("totalNumber");
		String remark = request.getParameter("remark");
		BookService service = new BookService();
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(service.update(new Book(Integer.valueOf(id), name, auth, press,
				Integer.valueOf(totalNumber), 0, Integer.valueOf(totalNumber), 1, remark))));
	}

}
