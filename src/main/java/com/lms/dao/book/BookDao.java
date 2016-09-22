package com.lms.dao.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public List<Book> findAll() throws SQLException {
		List<Book> books = Lists.newArrayList();

		connection = (Connection) DbUtil.getConn();
		statement = (Statement) DbUtil.getStmt(connection);
		String sql = "select * from book";
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
}
