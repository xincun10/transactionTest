package com.jdbcPools;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

/**
 * dbcp���ӳ�
 *
 */
public class DBCPTest {

	@Test
	public void fun1() throws SQLException
	{
		/*
		 * 1.�������ӳض���
		 * 2.�����Ĵ����
		 * 3.���óز���
		 * 4.�õ����Ӷ���
		 */
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		Connection conn = dataSource.getConnection();
		conn.close();
		/*
		 * ���ӳ��ڲ�ʹ���Ĵ�������������Ӷ��󣬼�mysql�����ṩ��Connection
		 * ���ӳ�ʹ��mysql�����Ӷ��������װ�Σ�ֻ��close()������������ǿ
		 * װ��֮���Connection��close()�����������ѵ�ǰ���ӹ黹����
		 */
	}
}
