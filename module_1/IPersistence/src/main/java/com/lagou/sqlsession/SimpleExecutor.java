package com.lagou.sqlsession;

import com.lagou.config.BoundSql;
import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import com.lagou.utils.GenericTokenParser;
import com.lagou.utils.ParameterMapping;
import com.lagou.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 核心执行JDBC方法
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 20:30
 */
public class SimpleExecutor implements Executor {
    
    /**
     * 底层JDBC查询方法
     * @param configuration 【容器对象】sqlMapConfig.xml解析类
     * @param mappedStatement 【容器对象】自定义Mapper.xml解析类
     * @param params 查询参数
     * @return
     */
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        /** 1.注册驱动,获取连接 */
        Connection connection = configuration.getDataSource().getConnection();
        
        /** 2.获取并转换SQL语句 */
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        
        /** 3.获取预处理对象 PreparedStatement */
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getParseSql());
        
        /** 4.获取参数的全路径,并设置查询参数 */
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            
            /** 
             * 4.1. 反射
             * 根据参数名称来获取实体对象中的属性值
             * 再根据属性值获取当前具体对象中(例如:User实体对象)中具体的属性值
             */
            /** 4.1.1. 获取属性对象 */
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            /** 4.1.2. 暴力访问 */
            declaredField.setAccessible(true);
            /** 4.1.3. 获取属性对象的值 */
            Object object = declaredField.get(params[0]);
            /** 4.1.4. 为预编译对象进行设值【设置参数下标应从1开始】 */
            preparedStatement.setObject(i + 1, object);
        }

        /** 5.执行SQL语句 */
        ResultSet resultSet = preparedStatement.executeQuery();
        
        /** 6.获取返回参数的全路径,并封装返回结果集 */
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        List<Object> objectList = new ArrayList<>();
        
        while (resultSet.next()){
            Object resultTypeInstance = resultTypeClass.newInstance();
            /** 6.1. 获取元数据 */
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            /** [注]:resultSetMetaData.getColumnCount()是指查询结果中列的个数,即表中有多少字段 */
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                /** 6.1.1. 获取该列字段名 */
                /** TODO [注]:getColumnName()方法括号中的i要从1开始,这也是循环为什么要从1开始循环的原因 */
                String columnName = resultSetMetaData.getColumnName(i);
                /** 6.1.2. 根据该列的字段名获取该列的字段值 */
                Object object = resultSet.getObject(columnName);
                /** 6.1.3. 使用反射或内省,根据数据库表和实体的对应关系,完成封装 */
                /** TODO [注]: PropertyDescriptor在创建类对象的过程中会对resultTypeClass类对象中的columnName属性来生成读写方法 */
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                /** 6.1.4. 获取对应的属性写方法 */
                Method writeMethod = propertyDescriptor.getWriteMethod();
                /** 6.1.5. 调用写方法的invoke方法,将对应的字段值object写入到当前的类对象中对应的属性上 */
                writeMethod.invoke(resultTypeInstance,object);
            }
            objectList.add(resultTypeInstance);
        }
        return (List<E>) objectList;
    }

    /**
     * 底层JDBC插入方法(包含增加删,删除,修改功能)
     * @param mappedStatement
     * @param params
     * @return
     */
    @Override
    public int insert(Configuration configuration, MappedStatement mappedStatement, Object ... params) throws Exception {
        /** 1.注册驱动,获取连接 */
        Connection connection = configuration.getDataSource().getConnection();

        /** 2.获取并转换SQL语句 */
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        /** 3.获取预处理对象 PreparedStatement */
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getParseSql());

        /** 4.获取参数的全路径,并设置查询参数 */
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            /**
             * 4.1. 反射
             * 根据参数名称来获取实体对象中的属性值
             * 再根据属性值获取当前具体对象中(例如:User实体对象)中具体的属性值
             */
            /** 4.1.1. 获取属性对象 */
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            /** 4.1.2. 暴力访问 */
            declaredField.setAccessible(true);
            /** 4.1.3. 获取属性对象的值 */
            Object o = declaredField.get(params[0]);
            /** 4.1.4. 为预编译对象进行设值【设置参数下标应从1开始】 */
            preparedStatement.setObject(i + 1, o);
        }

        /** 5.执行SQL语句 */
        return preparedStatement.executeUpdate();
    }
    
    /**
     * 转换SQL核心方法【完成对#{}的解析工作,将#{}使用?进行代替,并对#{}里面的值进行存储】
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        /** 1.标记处理类,配置标记解析器来完成对占位符的解析处理工作 */
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        
        /** 2.解析SQL,parseSql为解析后的SQL(带?号) */
        String parseSql = genericTokenParser.parse(sql);

        /** 3.#{}里面解析出来的参数名称列表 */
        List<ParameterMapping> parameterMappingList = parameterMappingTokenHandler.getParameterMappings();
        
        /** 4.返回封装过后的BoundSql */
        return new BoundSql(parseSql,parameterMappingList);
    }

    /**
     * 根据全路径获取类对象
     * @param classType 参数实体全路径
     * @return
     */
    private Class<?> getClassType(String classType) throws ClassNotFoundException {
        if(classType != null){
            Class<?> parameterTypeClass = Class.forName(classType);
            return parameterTypeClass;
        }
        return null;
    }
}
