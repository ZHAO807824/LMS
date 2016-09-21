package com.lms.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.entity.user.User;
import com.lms.util.DbUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserDao {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	/**
	 * 根据身份证号查询单条数据
	 * 
	 * @param idcard
	 * @return
	 * @throws SQLException
	 */
	public User findOne(String idcard) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select * from user where idcard='" + idcard + "'";
		resultSet = statement.executeQuery(sql);
		User user = null;
		if (resultSet != null && resultSet.first()) {
			user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
		}
		DbUtil.closeConnStatRs(connection, statement, resultSet);
		return user;
	}

	/**
	 * 插入数据user
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Integer insert(User user) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "insert into user(name,email,tell,idcard,address,gender) values(?,?,?,?,?,?)";
		preparedStatement=(PreparedStatement) DbUtil.getPstmt(connection, sql,  Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getTell());
		preparedStatement.setString(4, user.getIdcard());
		preparedStatement.setString(5, user.getAddress());
		preparedStatement.setInt(6, user.getGender());
		preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		Integer result = null;
		if (resultSet.next()) {
			result = resultSet.getInt(1);
		}
		DbUtil.closeConnPstatRs(connection, preparedStatement, resultSet);
		return result;
	}

	/**
	 * 插入数据user_admin
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Integer insert(Integer userId, Integer adminId) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "insert into admin_user(user_id,admin_id) values(?,?)";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, adminId);
		Integer result = null;
		result = preparedStatement.executeUpdate();
		DbUtil.closeConnPstatRs(connection, preparedStatement, resultSet);
		return result;
	}
}
