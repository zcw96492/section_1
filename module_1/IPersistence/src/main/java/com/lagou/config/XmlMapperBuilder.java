package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 解析Mapper.xml配置文件类
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 11:30
 */
public class XmlMapperBuilder {

    private Configuration configuration;
    
    public XmlMapperBuilder(Configuration configuration){
        this.configuration = configuration;
    }
    
    /**
     * 查询-解析方法
     * @param inputStream
     */
    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.获取所有select标签的集合 */
        List<Element> selectNodes = rootElement.selectNodes("//select");
        /** 3.遍历select标签集合 */
        for (Element element : selectNodes) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatementMap().put(
                    rootElement.attributeValue("namespace") + "." + id,
                    mappedStatement
            );
        }
    }

    /**
     * 新增-解析方法
     * @param inputStream
     * @throws DocumentException
     */
    public void parseInsert(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.获取所有select标签的集合 */
        List<Element> selectNodes = rootElement.selectNodes("//insert");
        /** 3.遍历select标签集合 */
        for (Element element : selectNodes) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatementMap().put(
                    rootElement.attributeValue("namespace") + "." + id,
                    mappedStatement
            );
        }
    }

    /**
     * 删除-解析方法
     * @param inputStream
     */
    public void parseDelete(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.获取所有select标签的集合 */
        List<Element> selectNodes = rootElement.selectNodes("//delete");
        /** 3.遍历select标签集合 */
        for (Element element : selectNodes) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatementMap().put(
                    rootElement.attributeValue("namespace") + "." + id,
                    mappedStatement
            );
        }
    }

    /**
     * 修改-解析方法
     * @param inputStream
     */
    public void parseUpdate(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        /** 1.获取根标签 */
        Element rootElement = document.getRootElement();
        /** 2.获取所有select标签的集合 */
        List<Element> selectNodes = rootElement.selectNodes("//update");
        /** 3.遍历select标签集合 */
        for (Element element : selectNodes) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatementMap().put(
                    rootElement.attributeValue("namespace") + "." + id,
                    mappedStatement
            );
        }
    }
}
