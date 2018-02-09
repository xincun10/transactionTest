package com.services;

import java.sql.SQLException;

import com.daos.AccountDao;
import com.utils.JDBCUtils;

/*
 * 添加了事务的service
 */
public class AccountService {

	private AccountDao dao = new AccountDao();
	public void serviceMethod()
	{
		try{
			JDBCUtils.beginTransaction();
			dao.update("name1", 1000);
			dao.update("name2", -1000);
			JDBCUtils.commitTransaction();
		}catch(SQLException e)
		{
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
