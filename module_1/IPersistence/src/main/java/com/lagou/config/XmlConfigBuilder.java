package com.lagou.config;

import com.lagou.io.Resources;
import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 解析sqlMapConfig.xml配置文件类
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:52
 */
public class XmlConfigBuilder {
    
    private Configuration configuration;
    
    public XmlConfigBuilder(){
        this.configuration = new Configuration();
    }

    /**
     * 解析SqlMapConfig.xml文件
     * @param rootElement 根标签
     * @return
     * @throws PropertyVetoException
     */
    private DataSource parseDataSource(Element rootElement) throws PropertyVetoException {
        List<Element> selectNodes = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : selectNodes) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        return comboPooledDataSource;
    }
    
    /**
     * 查询-使用dom4j解析配置文件,封装Configuration对象
     * @param inputStream
     * @return
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.解析sqlMapConfig.xml */
        configuration.setDataSource(parseDataSource(rootElement));
        /** 3.解析UserMapper.xml */
        List<Element> selectNodes = rootElement.selectNodes("//mapper");
        for (Element element : selectNodes) {
            String mapperFilePath = "mapper/" + element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperFilePath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }

    /**
     * 新增-使用dom4j解析配置文件,封装Configuration对象
     * @param inputStream
     * @return
     */
    public Configuration parseInsertConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.解析sqlMapConfig.xml */
        configuration.setDataSource(parseDataSource(rootElement));
        /** 3.解析UserMapper.xml */
        List<Element> selectNodes = rootElement.selectNodes("//mapper");
        for (Element element : selectNodes) {
            String mapperFilePath = "mapper/" + element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperFilePath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parseInsert(resourceAsStream);
        }
        return configuration;
    }

    /**
     * 删除-使用dom4j解析配置文件,封装Configuration对象
     * @param inputStream
     * @return
     */
    public Configuration parseDeleteConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.解析sqlMapConfig.xml */
        configuration.setDataSource(parseDataSource(rootElement));
        /** 3.解析UserMapper.xml */
        List<Element> selectNodes = rootElement.selectNodes("//mapper");
        for (Element element : selectNodes) {
            String mapperFilePath = "mapper/" + element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperFilePath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parseDelete(resourceAsStream);
        }
        return configuration;
    }

    /**
     * 修改-使用dom4j解析配置文件,封装Configuration对象
     * @param inputStream
     * @return
     * @throws DocumentException
     * @throws PropertyVetoException
     */
    public Configuration parseUpdateConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.解析sqlMapConfig.xml */
        configuration.setDataSource(parseDataSource(rootElement));
        /** 3.解析UserMapper.xml */
        List<Element> selectNodes = rootElement.selectNodes("//mapper");
        for (Element element : selectNodes) {
            String mapperFilePath = "mapper/" + element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperFilePath);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parseUpdate(resourceAsStream);
        }
        return configuration;
    }
}
