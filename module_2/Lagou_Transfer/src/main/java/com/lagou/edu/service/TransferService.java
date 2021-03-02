package com.lagou.edu.service;

/**
 * 转账服务接口
 * @author zhouchaowei
 */
public interface TransferService {

    /**
     * 转账方法
     * @param fromCardNo 来源卡号
     * @param toCardNo 目标卡号
     * @param money 转账金额
     * @throws Exception
     */
    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}
