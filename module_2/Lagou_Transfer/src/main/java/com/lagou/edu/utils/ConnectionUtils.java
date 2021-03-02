package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC连接工具类
 * (为实现事务控制,将当前线程与当前数据库操作所用连接进行绑定,使得当前线程获取绑定的Connection连接)
 * @author zhouchaowei
 */
public class ConnectionUtils {

    /** 用于存储当前线程的Connection连接 */
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * 以下代码用于保证Connection单例
     */
    private static ConnectionUtils connectionUtils = new ConnectionUtils();

    private ConnectionUtils() { }

    public static ConnectionUtils getInstance(){
        return connectionUtils;
    }

    /**
     * 从当前线程获取连接
     * (判断当前线程中是否已经绑定Connection连接,如果没有绑定,需要从Druid连接池中获取一个连接并绑定到当前线程中)
     * @return
     */
    public Connection getConnectionFromCurrentThread() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        /** 如果当前线程中没有绑定Connection连接,则获取连接并进行绑定 */
        if(connection == null){
            connection = DruidUtils.getInstance().getConnection();
            connectionThreadLocal.set(connection);
        }
        return connection;
    }
}
