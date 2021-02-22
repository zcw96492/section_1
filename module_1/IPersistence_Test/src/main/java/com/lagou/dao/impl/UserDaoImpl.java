package com.lagou.dao.impl;

import com.lagou.dao.UserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlsession.SqlSession;
import com.lagou.sqlsession.SqlSessionFactory;
import com.lagou.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * 用户Dao实现类
 * @author zhouchaowei
 * @date 2020年07月08日 星期三 16:11
 */
public class UserDaoImpl implements UserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectList("user.selectList");
    }

    /**
     * 根据条件查询用户信息
     * @param user
     * @return
     */
    @Override
    public User findByCondition(User user) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("user.selectOne", user);
    }

    /**
     * 插入用户信息
     * @param user 测试用-用户实体类
     * @return
     */
    @Override
    public int insertUser(User user) {
        return 0;
    }

    /**
     * 删除用户信息
     * @param user 测试用-用户实体类
     * @return
     */
    @Override
    public int deleteUser(User user) {
        return 0;
    }

    /**
     * 修改用户信息
     * @param user 测试用-用户实体类
     * @return
     */
    @Override
    public int updateUser(User user) {
        return 0;
    }
}
