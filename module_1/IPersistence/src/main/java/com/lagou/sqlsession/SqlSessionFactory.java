package com.lagou.sqlsession;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:51
 */
public interface SqlSessionFactory {

    /**
     * 开启sqlSession会话
     * @return
     */
    SqlSession openSession();
}
