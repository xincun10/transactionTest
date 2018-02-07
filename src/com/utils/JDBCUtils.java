package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//ʹ���ļ���Ĭ�����ã�Ҫ��������c3p0-config.xml
	private static ComboPooledDataSource dataSource = 
			new ComboPooledDataSource();
	//ʹ��oracle�����ݿ����ӳ�
//	private static ComboPooledDataSource dataSource = 
//			new ComboPooledDataSource("oracle-config");
	//ʹ�����ӳط���һ�����Ӷ���
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	//�������ӳض���
	public static DataSource getDataSource()
	{
		return dataSource;
	}
}
