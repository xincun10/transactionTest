package com.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import com.utils.DBUtil;

public class AccountService {
	//转账操作
	public void zhuanzhang(String from, String to, double money)
	{
		//对事务的操作必须使用Connection对象
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//执行事务
			AccountDao dao = new AccountDao();
			dao.updateBalance(conn, from, money);
			dao.updateBalance(conn, to, money);
			//提交事务
			conn.commit();
			conn.close();
		}catch(Exception e)
		{
			//回滚事务
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
