package com.lagou.test;

import com.lagou.dao.UserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlsession.SqlSession;
import com.lagou.sqlsession.SqlSessionFactory;
import com.lagou.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 自定义持久层框架测试类
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:26
 */
public class IPersistenceTest {

    /**
     * 查询-传统方式查询
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /** 1.查询单个对象 */
        User userReq = new User();
        userReq.setId(1);
        userReq.setUsername("lucy");
        User user = sqlSession.selectOne("com.lagou.dao.UserDao.findByCondition", userReq);
        System.out.println("============== 查询单个记录 ==============");
        System.out.println(user);
        System.out.println("============== 查询所有记录 ==============");
        
        /** 2.查询所有对象 */
        List<User> userList = sqlSession.selectList("com.lagou.dao.UserDao.findAll");
        for (User userItem : userList) {
            System.out.println(userItem.toString());
        }
    }

    /**
     * 查询-getMapper方式进行查询
     * @throws Exception
     */
    @Test
    public void testGetMapper() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("================查询全部记录结果====================");
        /** userDao就是通过getMapper获取的一个代理对象 */
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.findAll();
        for (User user: userList) {
            System.out.println(user.toString());
        }
       
        System.out.println("================查询单个记录结果====================");
        User userReq = new User();
        userReq.setId(1);
        userReq.setUsername("lucy");
        User userRes = userDao.findByCondition(userReq);
        System.out.println(userRes.toString());
    }

    /**
     * 插入-getMapper方式进行插入
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().buildInsert(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getInsertMapper(UserDao.class);

        User userReq = new User();
        userReq.setId(11);
        userReq.setUsername("tiantian");
        int i = userDao.insertUser(userReq);
        System.out.println(i == 1 ? "新增成功" : "新增失败");
    }

    /**
     * 删除-getMapper方式进行删除
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().buildDelete(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getDeleteMapper(UserDao.class);

        User userReq = new User();
        userReq.setId(11);
        int i = userDao.deleteUser(userReq);
        System.out.println(i == 1 ? "删除成功" : "删除失败");
    }

    /**
     * 修改-getMapper方式进行修改
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().buildUpdate(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getInsertMapper(UserDao.class);

        User userReq = new User();
        userReq.setId(10);
        userReq.setUsername("hanmeimei");
        int i = userDao.updateUser(userReq);
        System.out.println(i == 1 ? "修改成功" : "修改失败");
    }
}
