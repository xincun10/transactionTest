package com.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import com.utils.DBUtil;

public class AccountService {
	//ת�˲���
	public void zhuanzhang(String from, String to, double money)
	{
		//������Ĳ�������ʹ��Connection����
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			//��������
			conn.setAutoCommit(false);
			//ִ������
			AccountDao dao = new AccountDao();
			dao.updateBalance(conn, from, money);
			dao.updateBalance(conn, to, money);
			//�ύ����
			conn.commit();
			conn.close();
		}catch(Exception e)
		{
			//�ع�����
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
