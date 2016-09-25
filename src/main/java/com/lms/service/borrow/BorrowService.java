package com.lms.service.borrow;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.lms.bean.Borrow;
import com.lms.dao.book.BookDao;
import com.lms.dao.user.UserDao;
import com.lms.entity.book.Book;
import com.lms.entity.user.User;
import com.lms.service.LoginService;

public class BorrowService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	/**
	 * 图书用户列表
	 * 
	 * @return
	 */
	public List<Borrow<List<User>>> bookUserList() {
		List<Borrow<List<User>>> list = Lists.newArrayList();
		BookDao bookDao = new BookDao();
		UserDao userDao = new UserDao();
		try {
			List<Map<String, Object>> books = bookDao.findAllId();
			if (books != null && books.size() > 0) {
				for (Map<String, Object> map : books) {
					List<User> value = userDao.findAll((Integer) map.get("id"));
					if (value != null && value.size() > 0) {
						list.add(new Borrow<List<User>>(String.valueOf(map.get("name")), value));
					}
				}
			}
		} catch (SQLException e) {
			LOGGER.info("BorrowService bookUserList()," + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 用户图书列表
	 * 
	 * @return
	 */
	public List<Borrow<List<Book>>> userBookList() {
		List<Borrow<List<Book>>> list = Lists.newArrayList();
		BookDao bookDao = new BookDao();
		UserDao userDao = new UserDao();
		try {
			List<Map<String, Object>> users = userDao.findAllId();
			if (users != null && users.size() > 0) {
				for (Map<String, Object> map : users) {
					List<Book> value = bookDao.findAll((Integer) map.get("id"));
					if (value != null && value.size() > 0) {
						list.add(new Borrow<List<Book>>(String.valueOf(map.get("name")), value));
					}
				}
			}
		} catch (SQLException e) {
			LOGGER.info("BorrowService userBookList()," + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
}
