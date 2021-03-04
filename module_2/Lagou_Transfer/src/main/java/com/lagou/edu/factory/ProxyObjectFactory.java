package com.lagou.edu.factory;

import com.lagou.edu.pojo.Account;
import com.lagou.edu.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象工厂类,生成代理对象的
 * @author zhouchaowei 
 * @since 2021.1.13
 */
public class ProxyObjectFactory {
    
    private static ProxyObjectFactory proxyFactory = new ProxyObjectFactory();
    private ProxyObjectFactory(){ }

    public static ProxyObjectFactory getInstance() {
        return proxyFactory;
    }

    /**
     * 通过JDK动态代理来获取代理对象
     * @param object 委托对象
     * @return 代理对象
     */
    public Object getJdkProxy(Object object) {
        /** 获取代理对象 */
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result;
                    try{
                        /** 在try块中开启事务(开启事务的本质是获取JDBC数据库连接) */
                        TransactionManager.getInstance().openTransaction();
                        result = method.invoke(object,args);
                        /** 提交事务 */
                        TransactionManager.getInstance().commitTransaction();
                    }catch (Exception e){
                        e.printStackTrace();
                        /** 在异常中回滚事务 */
                        TransactionManager.getInstance().rollbackTransaction();
                        throw e;
                    }
                    return result;
                }
            });
    }

    /**
     * 通过CGLIB动态代理来获取代理对象
     * @param object 委托对象
     * @return 代理对象
     */
    public Object getCglibProxy(Object object) {
        return Enhancer.create(object.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                try{
                    /** 在try块中开启事务(开启事务的本质是获取JDBC数据库连接) */
                    TransactionManager.getInstance().openTransaction();
                    result = method.invoke(object,objects);
                    /** 提交事务 */
                    TransactionManager.getInstance().commitTransaction();
                }catch (Exception e){
                    e.printStackTrace();
                    /** 在异常中回滚事务 */
                    TransactionManager.getInstance().rollbackTransaction();
                    throw e;
                }
                return result;
            }
        });
    }
}
