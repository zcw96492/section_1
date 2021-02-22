package com.lagou.sqlsession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * 
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 12:08
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 查询所有记录
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        /** 将要完成对simplExecutor里的核心query方法的调用 */
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> objectList = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) objectList;
    }

    /**
     * 查询一条记录
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public <E> E selectOne(String statementId, Object... params) throws Exception {
        List<Object> list = selectList(statementId,params);
        if(list.size() == 1){
            return (E) list.get(0);
        }else{
            throw new RuntimeException("返回结果过多");
        }
    }

    /**
     * 插入一条记录(带参数)
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public int insert(String statementId, Object ... params) throws Exception {
        /** 将要完成对simplExecutor里的核心query方法的调用 */
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        int insertResult = simpleExecutor.insert(configuration, mappedStatement, params);
        return insertResult;
    }

    /**
     * 删除一条记录
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public int delete(String statementId, Object ... params) throws Exception {
        /** 将要完成对simplExecutor里的核心query方法的调用 */
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        int insertResult = simpleExecutor.insert(configuration, mappedStatement, params);
        return insertResult;
    }
    
    /**
     * 更新一条记录
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public int update(String statementId, Object params) {
        return 0;
    }

    /**
     * 查询-为Dao接口生成代理类
     * @param mapperClassType
     * @return
     */
    @Override
    public <T> T getMapper(Class<?> mapperClassType) {
        /** 使用JDK动态代理来为Dao接口生成代理对象并返回 */
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClassType}, new InvocationHandler() {

            /**
             * 在这个接口中
             * @param proxy 指当前代理对象的引用
             * @param method 指当前被调用方法的引用
             * @param args 指方法中传递的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /** TODO 底层都还是去执行JDBC,但是会根据不同情况来调用selectList或者是selectOne方法 */
                /** 
                 * 1.准备参数 statementId SQL语句的唯一标识namespace.id
                 * TODO [注意]:这里会有一个问题,很显然,在当前的这个invoke方法中,我们是不能直接拿到UserMapper.xml文件中mapper标签的namespace属性的值和select标签中id属性的值,
                 * TODO 因此MyBatis要求我们在定义这两个属性值的时候,就不能随便乱写了.
                 * [namespace]:需要与接口的全限定性类名(比如:UserDao接口的全限定性类名为com.lagou.dao.UserDao)保持一致
                 * [id]:需要与当前接口中对应的方法名保持一致
                 */

                /** 1.获取方法名 */
                String methodName = method.getName();
                /** 2.获取接口全限定性类名 */
                String className = method.getDeclaringClass().getName();
                /** 3.拼接statementId */
                String statementId = className + "." + methodName;
                /** 
                 * 4.获取被调用方法的返回值类型
                 * TODO [注意]:我们需要根据被调用方法的返回值类型,来决定调用什么样的方法,是selectOne还是selectList,而不是单一的都去调用selectList方法
                 */
                Type returnType = method.getGenericReturnType();
                /** 
                 * 5.判断当前被调用方法的返回值类型是否进行了泛型类型参数化
                 * TODO 说白了就是当前被调用方法的返回值类型是否包含泛型.如果包含,则说明当前返回值类型为集合,反之为实体对象.
                 */
                if(returnType instanceof ParameterizedType){
                    return selectList(statementId,args);
                }else{
                    return selectOne(statementId,args);
                }
            }
        });
        return (T) proxyInstance;
    }

    /**
     * 新增-为Dao接口生成代理类
     * @param mapperClassType
     * @return
     */
    @Override
    public <T> T getInsertMapper(Class<?> mapperClassType) {
        /** 使用JDK动态代理来为Dao接口生成代理对象并返回 */
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClassType}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /** 1.获取方法名 */
                String methodName = method.getName();
                /** 2.获取接口全限定性类名 */
                String className = method.getDeclaringClass().getName();
                /** 3.拼接statementId */
                String statementId = className + "." + methodName;
                return insert(statementId,args);
            }
        });
        return (T) proxyInstance;
    }

    /**
     * 删除-为Dao接口生成代理类
     * @param mapperClassType
     * @return
     */
    @Override
    public <T> T getDeleteMapper(Class<?> mapperClassType) {
        /** 使用JDK动态代理来为Dao接口生成代理对象并返回 */
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClassType}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /** 1.获取方法名 */
                String methodName = method.getName();
                /** 2.获取接口全限定性类名 */
                String className = method.getDeclaringClass().getName();
                /** 3.拼接statementId */
                String statementId = className + "." + methodName;
                return insert(statementId,args);
            }
        });
        return (T) proxyInstance;
    }
}
