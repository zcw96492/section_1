package com.lagou.sqlsession;

import com.lagou.config.XmlConfigBuilder;
import com.lagou.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:48
 */
public class SqlSessionFactoryBuilder {

    /**
     * 查询-创建SqlSessionFactory方法
     * @param inputStream
     * @return
     * @throws DocumentException
     * @throws PropertyVetoException
     */
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        /** 1.使用dom4j解析配置文件,将解析出来的内容封装到Configuration对象中 */
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);
        
        /** 2.创建SqlSessionFactory对象 */
        return new DefaultSqlSessionFactory(configuration);
    }

    /**
     * 新增-创建SqlSessionFactory方法
     * @param inputStream
     * @return
     * @throws PropertyVetoException
     * @throws DocumentException
     */
    public SqlSessionFactory buildInsert(InputStream inputStream) throws PropertyVetoException, DocumentException {
        /** 1.使用dom4j解析配置文件,将解析出来的内容封装到Configuration对象中 */
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseInsertConfig(inputStream);
        /** 2.创建SqlSessionFactory对象 */
        return new DefaultSqlSessionFactory(configuration);
    }

    /**
     * 删除-创建SqlSessionFactory方法
     * @param inputStream
     * @return
     * @throws PropertyVetoException
     * @throws DocumentException
     */
    public SqlSessionFactory buildDelete(InputStream inputStream) throws PropertyVetoException, DocumentException {
        /** 1.使用dom4j解析配置文件,将解析出来的内容封装到Configuration对象中 */
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseDeleteConfig(inputStream);
        /** 2.创建SqlSessionFactory对象 */
        return new DefaultSqlSessionFactory(configuration);
    }

    /**
     * 修改-创建SqlSessionFactory方法
     * @param inputStream
     * @return
     * @throws PropertyVetoException
     * @throws DocumentException
     */
    public SqlSessionFactory buildUpdate(InputStream inputStream) throws PropertyVetoException, DocumentException {
        /** 1.使用dom4j解析配置文件,将解析出来的内容封装到Configuration对象中 */
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseUpdateConfig(inputStream);
        /** 2.创建SqlSessionFactory对象 */
        return new DefaultSqlSessionFactory(configuration);
    }
}
