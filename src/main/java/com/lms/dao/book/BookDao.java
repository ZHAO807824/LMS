package com.lms.dao.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.lms.entity.book.Book;
import com.lms.util.DbUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BookDao {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	/**
	 * 遍历图书列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Book> findAll(boolean status) throws SQLException {
		List<Book> books = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select * from book";
		if(!status)
			sql=sql+" where status=1";
		resultSet = statement.executeQuery(sql);

		Book book;
		while (resultSet.next()) {
			book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getString(9));
			books.add(book);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return books;
	}
	

	/**
	 * 根据借阅者ID遍历用户列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Book> findAll(Integer id) throws SQLException {
		List<Book> books = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select b.*,bu.lend_time as lend_time,bu.return_time as return_time from book_user as bu LEFT JOIN book as b on bu.book_id=b.id LEFT JOIN  `user` as u on bu.user_id=u.id where u.id="+id;
		resultSet = statement.executeQuery(sql);

		Book book;
		while (resultSet.next()) {
			book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getString(9),resultSet.getDate(10),resultSet.getDate(11));
			books.add(book);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return books;
	}
	
	
	/**
	 * 遍历图书ID列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAllId() throws SQLException {
		List<Map<String, Object>> lists = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select id,name from book where status=1";
		resultSet = statement.executeQuery(sql);

		Integer id;
		String name;
		while (resultSet.next()) {
			Map<String, Object> map=new HashMap<String, Object>();
			id=resultSet.getInt(1);
			name=resultSet.getString(2);
			map.put("id", id);
			map.put("name", name);
			lists.add(map);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return lists;
	}
	
	/**
	 * 根据图书id获取图书部分信息
	 * @param bookId
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findAllId(Integer bookId) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select id,name from book where status=1 and id="+bookId;
		resultSet = statement.executeQuery(sql);

		Integer id;
		String name;
		Map<String, Object> map=new HashMap<String, Object>();
		while (resultSet.next()) {
			id=resultSet.getInt(1);
			name=resultSet.getString(2);
			map.put("id", id);
			map.put("name", name);
		}
		DbUtil.closeConnStatRs(connection, preparedStatement, resultSet);
		return map;
	}

	/**
	 * 修改status
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer update(Integer id, Integer status) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "update book set status=" + status + " where id=" + id;
		Integer update = statement.executeUpdate(sql);
		DbUtil.closeStmt(statement);
		DbUtil.closeConn(connection);
		return update;
	}

	/**
	 * 删除图书
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer delete(Integer id) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "delete from book where id=" + id;
		Integer delete = statement.executeUpdate(sql);
		DbUtil.closeStmt(statement);
		DbUtil.closeConn(connection);
		return delete;
	}

	/**
	 * 插入
	 * 
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public Integer insert(Book book) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "insert into book(name,auth,press,total_number,lend_number,inventory_number,status,remark) values(?,?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setString(1, book.getName());
		preparedStatement.setString(2, book.getAuth());
		preparedStatement.setString(3, book.getPress());
		preparedStatement.setInt(4, book.getTotalNumber());
		preparedStatement.setInt(5, book.getLendNumber());
		preparedStatement.setInt(6, book.getInventoryNumber());
		preparedStatement.setInt(7, book.getStatus());
		preparedStatement.setString(8, book.getRemark());
		Integer insert = preparedStatement.executeUpdate();
		DbUtil.closePstmt(preparedStatement);
		DbUtil.closeConn(connection);
		return insert;
	}

	/**
	 * 通过ID获取Book
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Book findOne(Integer id) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "select * from book where id=?";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		Book book = null;
		if (resultSet.next()) {
			book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
					resultSet.getString(9));
		}
		DbUtil.closeConnPstatRs(connection, preparedStatement, resultSet);
		return book;
	}

	/**
	 * 修改
	 * 
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	public Integer update(Book book) throws SQLException {
		connection = (Connection) DbUtil.getConn();
		String sql = "update book set name=?,auth=?,press=?,total_number=?,lend_number=?,inventory_number=?,remark=? where id=?";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setString(1, book.getName());
		preparedStatement.setString(2, book.getAuth());
		preparedStatement.setString(3, book.getPress());
		preparedStatement.setInt(4, book.getTotalNumber());
		preparedStatement.setInt(5, book.getLendNumber());
		preparedStatement.setInt(6, book.getInventoryNumber());
		preparedStatement.setString(7, book.getRemark());
		preparedStatement.setInt(8, book.getId());
		Integer update = preparedStatement.executeUpdate();
		DbUtil.closePstmt(preparedStatement);
		DbUtil.closeConn(connection);
		return update;
	}
	
	/**
	 * 借出图书
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Integer lend(Integer id) throws SQLException{
		connection = (Connection) DbUtil.getConn();
		String sql = "update book set lend_number=lend_number+1 where id=?";
		preparedStatement = (PreparedStatement) DbUtil.getPstmt(connection, sql);
		preparedStatement.setInt(1, id);
		Integer update = preparedStatement.executeUpdate();
		DbUtil.closePstmt(preparedStatement);
		DbUtil.closeConn(connection);
		return update;
	}

}
