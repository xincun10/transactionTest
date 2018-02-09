package com.daos;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.utils.JDBCUtils;
/*
 * ����������Dao
 */
public class AccountDao {
//	public static void update(String name, double money) throws SQLException
//	{
//		QueryRunner qr = new QueryRunner();
//		String sql = "update account set balance=balance+? where name=?";
//		Object[] params = {money, name};
//		//������Ҫ�Լ��ṩ��������֤ÿ��ʹ�õ���ͬһ������
//		Connection conn = JDBCUtils.getConnection();
//		qr.update(conn, sql, params);
//		//�ͷ�����
//		JDBCUtils.releaseConnection(conn);
//	}
	public static void update(String name, double money) throws SQLException
	{
		QueryRunner qr = new QueryRunner();
		String sql = "update account set balance=balance+? where name=?";
		Object[] params = {money, name};
		//������Ҫ�Լ��ṩ��������֤ÿ��ʹ�õ���ͬһ������
		qr.update(sql, params);
	}
}
