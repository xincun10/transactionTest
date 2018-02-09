package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 具体的增删改的操作
 * DBUtils类的实现逻辑
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
			//得到连接
			conn = dataSource.getConnection();
			//获取preparedStatement
			pst = conn.prepareStatement(sql);
			//给参数赋值
			initParams(pst, params);
			
			//执行sql语句
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
	//给参数赋值
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
			//得到连接
			conn = dataSource.getConnection();
			//获取preparedStatement
			pst = conn.prepareStatement(sql);
			//给参数赋值
			initParams(pst, params);
			
			//执行sql语句
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

//定义一个接口，用来把结果集转换成需要的类型
interface RsHandler<T>
{
	//将结果集转换为一个对象
	public T handle(ResultSet rs) throws SQLException;
}
