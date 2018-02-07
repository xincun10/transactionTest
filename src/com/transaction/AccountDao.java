package com.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {

	//更新余额
	public void updateBalance(Connection conn, String name, double balance)
	{
		try
		{
			//得到连接对象
//			Connection conn = DBUtil.getConnection();
			//设置事务隔离级别
//			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			//创建ps对象
			String sql = "update account set balance=? where name=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, balance);
			pstmt.setString(2, name);
			//执行
			pstmt.executeUpdate();			
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
}
