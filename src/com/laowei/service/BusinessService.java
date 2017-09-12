package com.laowei.service;

public interface BusinessService {
    /**
     * 转账
     * @param sourceAccountName 转出账户
     * @param tagetAccountName 转入账户
     * @param money 涉及金额
     */
    void transfer(String sourceAccountName, String tagetAccountName, float money);

}
