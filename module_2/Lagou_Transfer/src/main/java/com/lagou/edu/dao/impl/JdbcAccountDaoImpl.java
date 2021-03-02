package com.lagou.edu.dao.impl;

import com.lagou.edu.pojo.Account;
import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.utils.ConnectionUtils;
import com.lagou.edu.utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 持久层
 * @author zhouchaowei 
 */
public class JdbcAccountDaoImpl implements AccountDao {

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        /** 传统方式获取数据库连接 */
//        Connection con = DruidUtils.getInstance().getConnection();
        /** 从当前线程中获取绑定的数据库连接(不能关闭该连接) */
        Connection connection = ConnectionUtils.getInstance().getConnectionFromCurrentThread();
        String sql = "select * from ACCOUNT where account_card_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while(resultSet.next()) {
            account.setAccountCardNo(resultSet.getString("account_card_no"));
            account.setUserName(resultSet.getString("user_name"));
            account.setBalance(resultSet.getInt("balance"));
        }

        resultSet.close();
        preparedStatement.close();
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        /** 传统方式获取数据库连接 */
//        Connection con = DruidUtils.getInstance().getConnection();
        /** 从当前线程中获取绑定的数据库连接(不能关闭该连接) */
        Connection connection = ConnectionUtils.getInstance().getConnectionFromCurrentThread();
        String sql = "update ACCOUNT set balance = ? where account_card_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,account.getBalance());
        preparedStatement.setString(2,account.getAccountCardNo());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        return i;
    }
}
