package com.lagou.edu.utils;

import java.sql.SQLException;

/**
 * 事务管理器
 * @author zhouchaowei
 * @since 2021.01.03  11:17
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /*private static TransactionManager transactionManager = new TransactionManager();
    
    private TransactionManager(){
        
    }

    public static TransactionManager getInstance() {
        return transactionManager;
    }*/

    /**
     * 开启事务
     */
    public void openTransaction() throws SQLException {
        connectionUtils.getConnectionFromCurrentThread().setAutoCommit(false);
    }

    /**
     * 提交事务
     */
    public void commitTransaction() throws SQLException {
        connectionUtils.getConnectionFromCurrentThread().commit();
    }

    /**
     * 回滚事务
     */
    public void rollbackTransaction() throws SQLException {
        connectionUtils.getConnectionFromCurrentThread().rollback();
    }
}
