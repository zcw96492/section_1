package com.lagou.pojo;

import java.io.Serializable;

/**
 * 测试用-用户实体类
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:52
 */
public class User implements Serializable {
    
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}