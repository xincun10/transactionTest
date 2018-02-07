package com.jdbcPools;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * C3P0���ӳ�
 */
public class C3P0Test {
	@Test
	public void fun1() throws PropertyVetoException, SQLException
	{
		//�������ӳض���
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		//�Գؽ����Ĵ����������
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		//������
		dataSource.setAcquireIncrement(5);
		dataSource.setInitialPoolSize(20);
		dataSource.setMinPoolSize(2);
		dataSource.setMaxPoolSize(50);
		
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
		conn.close();
	}
	
}
