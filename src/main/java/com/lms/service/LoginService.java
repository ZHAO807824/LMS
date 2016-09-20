package com.lms.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lms.dao.LoginDao;
import com.lms.entity.Admin;
import com.lms.util.MD5Util;

/**
 * 
 * @Date 2016年9月20日
 *
 */
public class LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	/**
	 * 登录
	 * 
	 * @param admin
	 * @return
	 */
	public boolean login(Admin admin) {
		LoginDao loginDao = new LoginDao();
		try {
			Admin user = loginDao.findOne(admin.getEmail(), MD5Util.getMD5(admin.getPassword()));
			if (user != null)
				return true;
		} catch (SQLException e) {
			LOGGER.info("LoginService login(Admin);"+e.getMessage());
		}
		return false;
	}
}
