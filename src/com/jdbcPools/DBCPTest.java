package com.jdbcPools;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

/**
 * dbcp连接池
 *
 */
public class DBCPTest {

	@Test
	public void fun1() throws SQLException
	{
		/*
		 * 1.创建连接池对象
		 * 2.配置四大参数
		 * 3.配置池参数
		 * 4.得到连接对象
		 */
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		Connection conn = dataSource.getConnection();
		conn.close();
		/*
		 * 连接池内部使用四大参数创建了连接对象，即mysql驱动提供的Connection
		 * 连接池使用mysql的连接对象进行了装饰，只对close()方法进行了增强
		 * 装饰之后的Connection的close()方法，用来把当前连接归还给池
		 */
	}
}
