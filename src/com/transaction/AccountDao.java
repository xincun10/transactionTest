package com.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {

	//�������
	public void updateBalance(Connection conn, String name, double balance)
	{
		try
		{
			//�õ����Ӷ���
//			Connection conn = DBUtil.getConnection();
			//����������뼶��
//			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			//����ps����
			String sql = "update account set balance=? where name=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, balance);
			pstmt.setString(2, name);
			//ִ��
			pstmt.executeUpdate();			
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
}
