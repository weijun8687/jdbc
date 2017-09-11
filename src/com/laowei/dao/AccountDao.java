package com.laowei.dao;

import com.laowei.domain.Account;

public interface AccountDao {

    /**
     * 根据账户名查找用户信息
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);

    /**
     * 更新账户数据信息
     * @param account
     */
    void updateAccountMoney(Account account);
}
