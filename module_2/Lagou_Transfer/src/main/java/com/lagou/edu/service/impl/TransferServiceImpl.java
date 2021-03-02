package com.lagou.edu.service.impl;

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.factory.BeanFactory;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;

/**
 * 转账服务实现类
 * @author zhouchaowei 
 */
public class TransferServiceImpl implements TransferService {

    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setBalance(from.getBalance()-money);
            to.setBalance(to.getBalance()+money);

            accountDao.updateAccountByCardNo(to);
            accountDao.updateAccountByCardNo(from);
    }
}
