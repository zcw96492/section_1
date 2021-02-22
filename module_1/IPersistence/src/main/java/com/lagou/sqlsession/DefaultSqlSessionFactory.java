package com.lagou.sqlsession;

import com.lagou.pojo.Configuration;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 12:04
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;
    
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
