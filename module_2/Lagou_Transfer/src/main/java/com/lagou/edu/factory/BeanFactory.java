package com.lagou.edu.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 生产对象的工厂类-使用反射技术生产对象
 * 任务一:读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
 * 任务二:对外提供获取实例对象的接口（根据id获取）
 * @author zhouchaowei
 * @since 2021.01.13
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

            /** TODO 实例化完成之后维护对象的依赖关系,检查哪些对象需要传值进入;根据它的配置,我们传入相应的值 */
            /** 3.有property子元素的Bean就是有传值需求的对象 */
            List<Element> list = rootElement.selectNodes("//property");
            for (int i = 0; i < list.size(); i++) {
                Element element = list.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                /** 3.1.找到当前被需要处理依赖关系的Bean(即当前property子元素的父元素) */
                Element parentElement = element.getParent();
                /** 3.2.调用父元素对象的反射功能 */
                String parentElementId = parentElement.attributeValue("id");
                /** 3.3.根据Id从map集合中拿到父元素对象 */
                Object parentElementObject = map.get(parentElementId);
                /** 3.4.遍历父对象中的所有方法,找到"set + name"方法 */
                Method[] methods = parentElementObject.getClass().getMethods();
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if(method.getName().equalsIgnoreCase("set" + name)){
                        method.invoke(parentElementObject,map.get(ref));
                    }
                }
                /** 3.5.把处理之后的parentElementObject重新放到map中 */
                map.put(parentElementId,parentElementObject);
            }
        } catch (DocumentException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
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
