package com.lagou.io;

import java.io.InputStream;

/**
 * 加载配置文件方法
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:23
 */
public class Resources {

    /**
     * 根据配置文件的路径,将配置文件加载成字节输入流,存储在内存中
     * @param path 配置文件的路径
     * @return
     */
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
