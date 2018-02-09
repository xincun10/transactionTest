package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * �������ɾ�ĵĲ���
 * DBUtils���ʵ���߼�
 *
 */
public class JDBCUtils2 {
	
	private DataSource dataSource;

	public JDBCUtils2() {
		super();
	}
	
	public JDBCUtils2(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * insert delete update
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		try
		{
			//�õ�����
			conn = dataSource.getConnection();
			//��ȡpreparedStatement
			pst = conn.prepareStatement(sql);
			//��������ֵ
			initParams(pst, params);
			
			//ִ��sql���
			return pst.executeUpdate();
		} catch(Exception e)
		{
			throw new RuntimeException(e);
		} finally
		{
			try
			{
				if(pst!=null) pst.close();
				if(conn!=null)	conn.close();
			}
			catch(SQLException e1){}
		}
	}
	//��������ֵ
	private void initParams(PreparedStatement pst, Object[] params) 
			throws SQLException {
		for(int i=0; i<params.length; i++)
		{
			pst.setObject(i+1, params[i]);
		}
	}

	public <T> T query(String sql, RsHandler<T> rh, Object... params)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try
		{
			//�õ�����
			conn = dataSource.getConnection();
			//��ȡpreparedStatement
			pst = conn.prepareStatement(sql);
			//��������ֵ
			initParams(pst, params);
			
			//ִ��sql���
			rs = pst.executeQuery();
			return rh.handle(rs);
		} catch(Exception e)
		{
			throw new RuntimeException(e);
		} finally
		{
			try
			{
				if(rs!=null) rs.close();
				if(pst!=null) pst.close();
				if(conn!=null)	conn.close();
			}
			catch(SQLException e1){}
		}
	}
}

//����һ���ӿڣ������ѽ����ת������Ҫ������
interface RsHandler<T>
{
	//�������ת��Ϊһ������
	public T handle(ResultSet rs) throws SQLException;
}
