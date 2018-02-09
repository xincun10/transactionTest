package com.daos;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.utils.JDBCUtils;
/*
 * 添加了事务的Dao
 */
public class AccountDao {
//	public static void update(String name, double money) throws SQLException
//	{
//		QueryRunner qr = new QueryRunner();
//		String sql = "update account set balance=balance+? where name=?";
//		Object[] params = {money, name};
//		//我们需要自己提供连接来保证每次使用的是同一个连接
//		Connection conn = JDBCUtils.getConnection();
//		qr.update(conn, sql, params);
//		//释放连接
//		JDBCUtils.releaseConnection(conn);
//	}
	public static void update(String name, double money) throws SQLException
	{
		QueryRunner qr = new QueryRunner();
		String sql = "update account set balance=balance+? where name=?";
		Object[] params = {money, name};
		//我们需要自己提供连接来保证每次使用的是同一个连接
		qr.update(sql, params);
	}
}
