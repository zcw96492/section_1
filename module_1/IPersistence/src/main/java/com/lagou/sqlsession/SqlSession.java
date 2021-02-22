package com.lagou.sqlsession;

import java.util.List;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 12:06
 */
public interface SqlSession {

    /**
     * 查询所有记录
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> selectList(String statementId, Object ... params) throws Exception;

    /**
     * 查询一条记录
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> E selectOne(String statementId, Object ... params) throws Exception;
    
    /**
     * 插入一条记录
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    int insert(String statementId,  Object ... params) throws Exception;

    /**
     * 删除一条记录
     * @param statementId
     * @param params
     * @return
     */
    int delete(String statementId, Object ... params) throws Exception;
    
    /**
     * 更新一条记录
     * @param statementId
     * @param params
     * @return
     */
    int update(String statementId, Object params);

    /**
     * 查询-为Dao接口生成代理类
     * @param mapperClassType
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> mapperClassType);

    /**
     * 新增-为Dao接口生成代理类
     * @param mapperClassType
     * @param <T>
     * @return
     */
    <T> T getInsertMapper(Class<?> mapperClassType);

    /**
     * 删除-为Dao接口生成代理类
     * @param mapperClassType
     * @param <T>
     * @return
     */
    <T> T getDeleteMapper(Class<?> mapperClassType);
}
