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
	//����ר������
//	private static Connection conn = null;
	/*
	 * Ӧ���ڶ�����ÿһ���߳���һ���Լ�ר�õ�connection
	 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//ʹ�����ӳط���һ�����Ӷ���
	public static Connection getConnection() throws SQLException
	{
		/*
		 * ��ȡ�Լ��̵߳�connection
		 */
		Connection conn = tl.get();
		//��conn������null��˵���Ѿ����ù�beginTransaction(),��ʾ�Ѿ�����������
		if(conn!=null) return conn;
		
		return dataSource.getConnection();
	}
	//�������ӳض���
	public static DataSource getDataSource()
	{
		return dataSource;
	}
	/*
	 * ��ʼ����
	 * 1.��ȡһ��connection����������setAutoCommit(false)
	 * 2.��Ҫ��֤dao��ʹ�õ����������Ǹոմ�����
	 */
	public static void beginTransaction() throws SQLException
	{
		Connection conn = tl.get();
		
		if(conn!=null) throw new RuntimeException("�Ѿ����������񣬲�Ҫ�ظ�������");
		conn = getConnection();//��conn��ֵ����ʾ�����Ѿ���ʼ��
		conn.setAutoCommit(false);
		//�ѵ�ǰ�̵߳����ӱ�������
		tl.set(conn);
	}
	/*
	 * �ύ����
	 * 1.��ȡbeginTransaction�ṩ��Connection,Ȼ�����commit����
	 */
	public static void commitTransaction() throws SQLException
	{
		Connection conn = tl.get();
		
		if(conn==null) throw new RuntimeException("��û�п������񣬲����ύ��");
		conn.commit();
		conn.close();
//		conn = null;//��������Ϊnull,��ʾ�����Ѿ������ˣ�
//		//�´���ȥ����getConnection()���صľͲ���conn�ˡ�
		tl.remove();//�����Ӵ�tl���Ƴ�
	}
	/*
	 * �ع�����
	 * 1.��ȡbeginTransaction�ṩ��Connection,Ȼ�����rollback����
	 */
	public static void rollbackTransaction() throws SQLException
	{
		Connection conn = tl.get();
		
		if(conn==null) throw new RuntimeException("��û�п������񣬲��ܻع���");
		conn.rollback();
		conn.close();
//		conn = null;
		tl.remove();
	}
	
	/*
	 * �ͷ�����
	 */
	public static void releaseConnection(Connection connection) throws SQLException
	{
		Connection conn = tl.get();
		/*
		 * �ж����ǲ�������ר�ã�����ǾͲ��ر�
		 * �����������ר�ã���Ҫ����ر�
		 */
		//���conn==null��˵������û��������ôconnectionһ����������ר�õ�
		if(conn==null) connection.close();
		//���conn!=null,˵��������������Ҫ�жϲ��������Ƿ���conn���
		//������ȣ�˵���������Ӳ�������ר�����ӣ�����ر�
		if(conn!=connection) connection.close();
	}
}
