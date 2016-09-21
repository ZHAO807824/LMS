package com.lms.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lms.dao.AdminDao;
import com.lms.dao.user.UserDao;
import com.lms.entity.Admin;
import com.lms.entity.user.User;
import com.lms.util.MD5Util;
import com.lms.util.ValidatorUtil;

public class RegisterService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	/**
	 * 校验idcard是否存在
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean idcard(String idcard) {
		if (ValidatorUtil.isIDCard(idcard)) {
			UserDao userDao = new UserDao();
			try {
				User user = userDao.findOne(idcard);
				if (user != null)
					return true;
			} catch (SQLException e) {
				LOGGER.info("Register idcard(String);" + e.getMessage());
			}
		}
		return false;
	}

	/**
	 * 校验email是否存在
	 * 
	 * @param email
	 * @return
	 */
	public boolean email(String email) {
		if (ValidatorUtil.isEmail(email)) {
			AdminDao adminDao = new AdminDao();
			try {
				Admin admin = adminDao.findOne(email);
				if (admin != null)
					return true;
			} catch (SQLException e) {
				LOGGER.info("Register email(String);" + e.getMessage());
			}
		}
		return false;
	}

	/**
	 * 注册
	 * @param admin
	 * @param user
	 * @return
	 */
	public boolean register(Admin admin,User user){
		AdminDao adminDao=new AdminDao();
		UserDao userDao=new UserDao();
		try {
			Integer adminId=adminDao.insert(new Admin(admin.getEmail(), MD5Util.getMD5(admin.getPassword())));
			Integer userId=userDao.insert(user);
			if (adminId!=null&&userId!=null) {
				return userDao.insert(userId, adminId)!=null?true:false;
			}
		} catch (SQLException e) {
			LOGGER.info("Register register(Admin,User);" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
