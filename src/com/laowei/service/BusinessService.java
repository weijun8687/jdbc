package com.laowei.service;

public interface BusinessService {
    /**
     * 转账
     * @param 转出账户
     * @param 转入账户
     * @param 涉及金额
     */
    void transfer(String sourceAccountName, String tagetAccountName, float money);

}
