package com.lagou.edu.dao;

import com.lagou.edu.pojo.Account;

/**
 * 持久层接口
 * @author zhouchaowei
 */
public interface AccountDao {

    /**
     * 根据银行账户号查询账户信息
     * @param cardNo
     * @return
     * @throws Exception
     */
    Account queryAccountByCardNo(String cardNo) throws Exception;

    /**
     * 根据银行账户号更新账户信息
     * @param account
     * @return
     * @throws Exception
     */
    int updateAccountByCardNo(Account account) throws Exception;
}
