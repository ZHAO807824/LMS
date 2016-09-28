package com.lms.service.user;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.lms.dao.AdminDao;
import com.lms.dao.book.BookDao;
import com.lms.dao.user.UserDao;
import com.lms.entity.Admin;
import com.lms.entity.user.User;
import com.lms.service.LoginService;
import com.lms.util.MD5Util;
import com.lms.util.PropertiesUtil;

/**
 * 
 * @Date 2016年9月24日
 */
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String list() {
		UserDao dao=new UserDao();
		try {
			List<User> users=dao.findAll();
			if (users != null && users.size() > 0) {
				return JSON.toJSONString(users);
			}
		} catch (SQLException e) {
			LOGGER.info("UserService list();" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除用户信息
	 * 
	 * @param idStr
	 * @return
	 */
	public boolean delete(String idStr) {
		Integer id = Integer.valueOf(idStr);
		UserDao dao=new UserDao();
		try {
			return dao.delete(id) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("UserService delete(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 禁用用户
	 * @param idStr
	 * @return
	 */
	public boolean disabled(String idStr){
		Integer id = Integer.valueOf(idStr);
		UserDao dao=new UserDao();
		try {
			return dao.status(id, 0) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("UserService start(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 激活用户
	 * 
	 * @param idStr
	 * @return
	 */
	public boolean start(String idStr) {
		Integer id = Integer.valueOf(idStr);
		UserDao dao=new UserDao();
		try {
			return dao.status(id, 1) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("UserService disabled(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public boolean register(User user){
		AdminDao adminDao=new AdminDao();
		UserDao userDao=new UserDao();
		try {
			Integer adminId=adminDao.insert(new Admin(user.getEmail(), MD5Util.getMD5(PropertiesUtil.getValue("user.password"))));
			Integer userId=userDao.insert(user,true);
			if (adminId!=null&&userId!=null) {
				return userDao.insert(userId, adminId)!=null?true:false;
			}
		} catch (SQLException e) {
			LOGGER.info("Register register(User);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 升级用户:普通用户->VIP用户
	 * @param idStr
	 * @return
	 */
	public boolean upgrade(String idStr){
		Integer id = Integer.valueOf(idStr);
		UserDao dao=new UserDao();
		try {
			return dao.role(id, 1) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("UserService upgrade(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 降级用户:VIP用户->普通用户
	 * @param idStr
	 * @return
	 */
	public boolean demotion(String idStr){
		Integer id = Integer.valueOf(idStr);
		UserDao dao=new UserDao();
		try {
			return dao.role(id, 0) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("UserService demotion(String);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 查询单个用户信息
	 * @param idStr
	 * @return
	 */
	public User query(String idStr){
		Integer id = Integer.valueOf(idStr);
		UserDao dao=new UserDao();
		try {
			return dao.findOne(id);
		} catch (SQLException e) {
			LOGGER.info("BookService query(String);" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean update(User user){
		UserDao dao=new UserDao();
		try {
			return dao.update(user) != 0 ? true : false;
		} catch (SQLException e) {
			LOGGER.info("UserService update(User);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 借阅图书
	 * @param bookId
	 * @param adminId
	 * @return
	 */
	public boolean borrow(Integer bookId,Integer adminId){
		UserDao userDao=new UserDao();
		BookDao bookDao=new BookDao();
		try {
			if(bookDao.lend(bookId)>0){
				return userDao.borrow(userDao.findUserId(adminId), bookId)>0?true:false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
