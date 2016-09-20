package com.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.entity.Admin;
import com.lms.util.DbUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * 
 * @Date 2016年9月20日
 *
 */
public class LoginDao {
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	/**
	 * 根据Email和password查询单条记录
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Admin findOne(String email,String password) throws SQLException{
		connection=(Connection) DbUtil.getConn();
		statement=(Statement) DbUtil.getStmt(connection);
		String sql="select * from admin where email='"+email+"' and password='"+password+"'";
		resultSet=statement.executeQuery(sql);
		if(resultSet!=null&&resultSet.first()){
			return new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
		}
		return null;
	}
}
