package com.lagou.sqlsession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 20:28
 */
public interface Executor {

    /**
     * 底层查询方法
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object ... params) throws Exception;

    /**
     * 底层插入方法
     * @param configuration
     * @param mappedStatement
     * @param params
     * @return
     * @throws Exception
     */
    int insert (Configuration configuration,MappedStatement mappedStatement, Object ... params) throws Exception;
}
