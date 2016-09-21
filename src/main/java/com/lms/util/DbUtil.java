package com.lms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @Date 2016年9月20日
 *
 */
public class DbUtil {
	static {
		try {
			//加载驱动
			String jdbcDriver = PropertiesUtil.getValue("jdbcDriver");
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 建立连接
	 * @return
	 */
	public static Connection getConn() {
		Connection connection = null;
		String url = PropertiesUtil.getValue("url");
		String uname = PropertiesUtil.getValue("uname");
		String password = PropertiesUtil.getValue("password");
		try {
			connection = DriverManager.getConnection(url, uname, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 关闭连接
	 * @param connection
	 */
	public static void closeConn(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取处理对象
	 * @param connection
	 * @return
	 */
	public static Statement getStmt(Connection connection) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * 关闭Statement
	 * @param statement
	 */
	public static void closeStmt(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取PreparedStatement
	 * @param connection
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPstmt(Connection connection, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	/**
	 * 获取PreparedStatement
	 * @param connection
	 * @param sql
	 * @param statement
	 * @return
	 */
	public static PreparedStatement getPstmt(Connection connection, String sql,int statement) {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql,statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * 关闭PreparedStatement
	 * @param pstmt
	 */
	public static void closePstmt(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭结果集
	 * @param rs
	 */
	public static void closeRs(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询的时候关闭
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeConnStatRs(Connection connection, Statement statement, ResultSet resultSet) {
		closeRs(resultSet);
		closeStmt(statement);
		closeConn(connection);
	}

	/**
	 * 查询的时候关闭
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeConnPstatRs(Connection connection, PreparedStatement pstmt, ResultSet resultSet) {
		closeRs(resultSet);
		closePstmt(pstmt);
		closeConn(connection);
	}
}
