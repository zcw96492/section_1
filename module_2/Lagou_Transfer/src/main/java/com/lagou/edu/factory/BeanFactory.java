package com.lagou.edu.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

/**
 * 生产对象的工厂类-使用反射技术生产对象
 * 任务一:读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
 * 任务二:对外提供获取实例对象的接口（根据id获取）
 * @author zhouchaowei 
 */
public class BeanFactory {

    /** 用于存储实例化对象的Map集合 */
    private static Map<String,Object> map = new HashMap<>();

    /**
     * 静态代码块的作用:读取解析XML,通过反射技术实例化对象并且存储待用
     */
    static {
        /** 1.加载XML */
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        /** 2.解析XML */
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                /** 2.1.遍历每个bean元素,获取到该元素的id属性和class属性 */
                Element element = beanList.get(i);
                String id = element.attributeValue("id");
                String classPath = element.attributeValue("class");
                /** 2.2.通过反射技术,实例化对象 */
                Class<?> classObject = Class.forName(classPath);
                Object objectInstance = classObject.newInstance();
                map.put(id,objectInstance);
            }
        } catch (DocumentException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对外提供获取实例的方法
     * @param id Bean实例Id
     * @return
     */
    public static Object getBean(String id){
        return map.get(id);
    }
}
