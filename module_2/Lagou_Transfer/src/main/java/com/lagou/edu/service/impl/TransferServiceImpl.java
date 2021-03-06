package com.lagou.edu.service.impl;

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.dao.impl.JdbcAccountDaoImpl;
import com.lagou.edu.factory.BeanFactory;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import com.lagou.edu.utils.ConnectionUtils;
import com.lagou.edu.utils.TransactionManager;

/**
 * 转账服务实现类
 * @author zhouchaowei 
 */
public class TransferServiceImpl implements TransferService {

    /** 方式一:通过new对象的方式 */
//    private AccountDao accountDao = new JdbcAccountDaoImpl();

    /** 方式二:通过Bean工厂创建的方式替换new对象 */
//    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    /** 方式三:通过Bean对象的set方法 */
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 转账方法
     * @param fromCardNo 来源卡号
     * @param toCardNo 目标卡号
     * @param money 转账金额
     * @throws Exception
     */
    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
//        try{
//            /** 在try块中开启事务(开启事务的本质是获取JDBC数据库连接) */
//            TransactionManager.getInstance().openTransaction();
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setBalance(from.getBalance()-money);
            to.setBalance(to.getBalance()+money);

            accountDao.updateAccountByCardNo(to);
            accountDao.updateAccountByCardNo(from);
//            TransactionManager.getInstance().commitTransaction();
//            /** 提交事务 */
//        }catch (Exception e){
//            e.printStackTrace();
//            /** 在异常中回滚事务 */
//            TransactionManager.getInstance().rollbackTransaction();
//            throw e;
//        }
    }
}
