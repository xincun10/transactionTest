package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils1 {

	//ʹ��xml�ļ���Ĭ�����ô������ӳ�
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//��������
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
