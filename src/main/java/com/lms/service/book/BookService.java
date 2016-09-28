package com.lms.service.book;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lms.dao.book.BookDao;
import com.lms.entity.book.Book;
import com.lms.service.LoginService;

/**
 * 
 * @Date 2016年9月22日
 *
 */
public class BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	/**
	 * 图书列表:管理员权限查看所有图书,普通用户查看启用图书
	 * @param role
	 * @return
	 */
	public String list(Integer role) {
		BookDao dao = new BookDao();
		try {
			List<Book> books = dao.findAll(role==1?true:false);
			if (books != null && books.size() > 0) {
				return JSON.toJSONString(books);
			}
		} catch (SQLException e) {
			LOGGER.info("BookService list();" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 启用状态图书的id集合
	 * @return
	 */
	public List<Integer> idList(){
		return null;
	}

	/**
	 * 禁用图书状态
	 * 
	 * @param id
	 * @return
	 */
	public boolean disabled(String idStr) {
		Integer id = Integer.valueOf(idStr);
		BookDao dao = new BookDao();
		try {
			return dao.update(id, 0) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("BookService disabled(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 启用图书状态
	 * 
	 * @param idStr
	 * @return
	 */
	public boolean start(String idStr) {
		Integer id = Integer.valueOf(idStr);
		BookDao dao = new BookDao();
		try {
			return dao.update(id, 1) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("BookService start(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除图书信息
	 * 
	 * @param idStr
	 * @return
	 */
	public boolean delete(String idStr) {
		Integer id = Integer.valueOf(idStr);
		BookDao dao = new BookDao();
		try {
			return dao.delete(id) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("BookService delete(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 保存图书信息
	 * @param book
	 * @return
	 */
	public boolean save(Book book) {
		BookDao dao = new BookDao();
		try {
			return dao.insert(book) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("BookService save(Book);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 查询单个图书信息
	 * @param idStr
	 * @return
	 */
	public Book query(String idStr){
		Integer id = Integer.valueOf(idStr);
		BookDao dao = new BookDao();
		try {
			return dao.findOne(id);
		} catch (SQLException e) {
			LOGGER.info("BookService Query(String);" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public boolean update(Book book){
		BookDao dao = new BookDao();
		try {
			return dao.update(book) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("BookService update(Book);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
