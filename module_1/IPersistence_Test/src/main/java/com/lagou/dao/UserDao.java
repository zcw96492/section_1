package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月08日 星期三 16:04
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<User> findAll() throws Exception;

    /**
     * 根据条件查询用户信息
     * @param user 测试用-用户实体类
     * @return
     * @throws Exception
     */
    User findByCondition(User user) throws Exception;

    /**
     * 插入用户信息
     * @param user 测试用-用户实体类
     * @return
     */
    int insertUser(User user);

    /**
     * 删除用户信息
     * @param user 测试用-用户实体类
     * @return
     */
    int deleteUser(User user);

    /**
     * 修改用户信息
     * @param user 测试用-用户实体类
     * @return
     */
    int updateUser(User user);
}
