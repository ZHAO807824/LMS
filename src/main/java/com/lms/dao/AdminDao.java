package com.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.entity.Admin;
import com.lms.util.DbUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


/**
 * 
 * @Date 2016年9月20日
 *
 */
public class AdminDao {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	/**
	 * 根据Email和password查询单条记录
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Admin findOne(String email, String password) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select * from admin where email='" + email + "' and password='" + password + "'";
		resultSet = statement.executeQuery(sql);
		Admin admin = null;
		if (resultSet != null && resultSet.first()) {
			admin = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),
					resultSet.getInt(5), resultSet.getInt(6));
		}
		DbUtil.closeConnStatRs(connection, statement, resultSet);
		return admin;
	}

	/**
	 * 根据Email查询单条记录
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public Admin findOne(String email) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select id from admin where email='" + email + "'";
		resultSet = statement.executeQuery(sql);
		Admin admin = null;
		if (resultSet != null && resultSet.first()) {
			admin = new Admin(resultSet.getInt(1));
		}
		DbUtil.closeConnStatRs(connection, statement, resultSet);
		return admin;
	}

	/**
	 * 插入数据返回主键
	 * 
	 * @param admin
	 * @return
	 * @throws SQLException 
	 */
	public Integer insert(Admin admin) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql="insert into admin(email,password) values(?,?)";
		preparedStatement=(PreparedStatement) DbUtil.getPstmt(connection, sql,  Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, admin.getEmail());
		preparedStatement.setString(2, admin.getPassword());
		preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
        Integer result = null;
        if(resultSet.next())
        {
            result = resultSet.getInt(1);
        }
       
		DbUtil.closeConnPstatRs(connection, preparedStatement, resultSet);
		return result;
	}

}
