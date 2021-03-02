package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 应癫
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://cdb-19urcqui.cd.tencentcdb.com:10057/bank");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("ZQZNzcw19921115_");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
