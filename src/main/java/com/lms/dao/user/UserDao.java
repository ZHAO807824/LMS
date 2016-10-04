package com.lms.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
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
					resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getInt(9), resultSet.getInt(10));
		}
		DbUtil.closeConnStatRs(connection, statement, resultSet);
		return user;
	}

	/**
	 * 根据ID查询单个用户记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User findOne(Integer id) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select * from user where id='" + id + "'";
		resultSet = statement.executeQuery(sql);
		User user = null;
		if (resultSet != null && resultSet.first()) {
			user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getInt(9), resultSet.getInt(10));
		}
		DbUtil.closeConnStatRs(connection, statement, resultSet);
		return user;
	}

	/**
	 * 插入数据
	 * 
	 * @param user
	 * @param admin
	 *            true 管理员操作;false 普通用户操作
	 * @return
	 * @throws SQLException
	 */
	public Integer insert(User user, boolean admin) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "";
		if (admin)
			sql = "insert into user(name,email,tell,idcard,address,gender,role,status) values(?,?,?,?,?,?,?,?)";
		else
			sql = "insert into user(name,email,tell,idcard,address,gender) values(?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getTell());
		preparedStatement.setString(4, user.getIdcard());
		preparedStatement.setString(5, user.getAddress());
		preparedStatement.setInt(6, user.getGender());
		if (admin) {
			preparedStatement.setInt(7, user.getRole());
			preparedStatement.setInt(8, user.getStatus());
		}
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

	/**
	 * 遍历用户列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> findAll() throws SQLException {
		List<User> users = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select * from user";
		resultSet = statement.executeQuery(sql);

		User user;
		while (resultSet.next()) {
			user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getInt(9), resultSet.getInt(10));
			users.add(user);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return users;
	}

	/**
	 * 遍历用户ID列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAllId() throws SQLException {
		List<Map<String, Object>> lists = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select id,name from user where status=1";
		resultSet = statement.executeQuery(sql);

		Integer id;
		String name;
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			id = resultSet.getInt(1);
			name = resultSet.getString(2);
			map.put("id", id);
			map.put("name", name);
			lists.add(map);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return lists;
	}

	/**
	 * 根据用户ID获取部分用户信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findAllId(Integer userId) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select id,name from user where status=1 and id=" + userId;
		resultSet = statement.executeQuery(sql);

		Integer id;
		String name;
		Map<String, Object> map = new HashMap<String, Object>();
		while (resultSet.next()) {

			id = resultSet.getInt(1);
			name = resultSet.getString(2);
			map.put("id", id);
			map.put("name", name);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return map;
	}

	/**
	 * 根据adminId获取userId
	 * 
	 * @param adminId
	 * @return
	 * @throws SQLException
	 */
	public Integer findUserId(Integer adminId) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select user_id from admin_user  where admin_id=" + adminId;
		resultSet = statement.executeQuery(sql);

		Integer id = null;
		while (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return id;
	}

	/**
	 * 根据所借图书ID遍历用户列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> findAll(Integer id) throws SQLException {
		List<User> users = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select u.*,bu.lend_time as lend_time,bu.return_time as return_time from book_user as bu LEFT JOIN book as b on bu.book_id=b.id LEFT JOIN  `user` as u on bu.user_id=u.id where b.id="
				+ id;
		resultSet = statement.executeQuery(sql);

		User user;
		while (resultSet.next()) {
			user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getInt(9), resultSet.getInt(10), resultSet.getDate(11), resultSet.getDate(12));
			users.add(user);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return users;
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer delete(Integer id) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "delete from user where id=" + id;
		Integer delete = statement.executeUpdate(sql);
		DbUtil.closeStmt(statement);
		DbUtil.closeConn(connection);
		return delete;
	}

	/**
	 * 修改status
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer status(Integer id, Integer status) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "update user set status=" + status + " where id=" + id;
		Integer update = statement.executeUpdate(sql);
		DbUtil.closeStmt(statement);
		DbUtil.closeConn(connection);
		return update;
	}

	/**
	 * 修改status
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer role(Integer id, Integer role) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "update user set role=" + role + " where id=" + id;
		Integer update = statement.executeUpdate(sql);
		DbUtil.closeStmt(statement);
		DbUtil.closeConn(connection);
		return update;
	}

	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Integer update(User user) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "update user set name=?,email=?,tell=?,idcard=?,address=?,gender=? where id=?";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getTell());
		preparedStatement.setString(4, user.getIdcard());
		preparedStatement.setString(5, user.getAddress());
		preparedStatement.setInt(6, user.getGender());
		preparedStatement.setInt(7, user.getId());
		Integer update = preparedStatement.executeUpdate();
		DbUtil.closePstmt(preparedStatement);
		DbUtil.closeConn(connection);
		return update;
	}
	
	/**
	 * 插入数据book_user
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Integer borrow(Integer userId, Integer bookId) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "insert into book_user(book_id,user_id) values(?,?)";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setInt(1, bookId);
		preparedStatement.setInt(2, userId);
		Integer result = null;
		result = preparedStatement.executeUpdate();
		DbUtil.closeConnPstatRs(connection, preparedStatement, resultSet);
		return result;
	}
	
	/**
	 * 获取指定用户借阅图书数量
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Integer count(Integer userId) throws SQLException{
		connection = (Connection) DbUtil.getConn();
		String sql = "select count(id) from book_user where user_id=?";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setInt(1, userId);
		Integer result = 0;
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
			result=resultSet.getInt(1);
		DbUtil.closeConnPstatRs(connection, preparedStatement, resultSet);
		return result;
	}
}
