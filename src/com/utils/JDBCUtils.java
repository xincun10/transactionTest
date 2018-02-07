package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//使用文件的默认配置！要求必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = 
			new ComboPooledDataSource();
	//使用oracle的数据库连接池
//	private static ComboPooledDataSource dataSource = 
//			new ComboPooledDataSource("oracle-config");
	//使用连接池返回一个连接对象
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	//返回连接池对象！
	public static DataSource getDataSource()
	{
		return dataSource;
	}
}
