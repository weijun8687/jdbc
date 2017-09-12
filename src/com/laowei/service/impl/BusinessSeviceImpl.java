package com.laowei.service.impl;

import com.laowei.dao.AccountDao;
import com.laowei.dao.impl.AccountDaoImpl;
import com.laowei.domain.Account;
import com.laowei.service.BusinessService;
import com.laowei.util.TransationManager;

public class BusinessSeviceImpl implements BusinessService{

    private AccountDao accountDao = new AccountDaoImpl();

    public void transfer(String sourceAccountName, String tagetAccountName, float money) {

        // 使用该方法时, AccountDaoImpl 获取连接时也要改成 TransationManager 的方法才可以
//        TransationManager.startTransition();
        Account sourceAccount = accountDao.findAccountByName(sourceAccountName);
        Account tagetAccount = accountDao.findAccountByName(tagetAccountName);

        sourceAccount.setMoney(sourceAccount.getMoney() - money);
        tagetAccount.setMoney(tagetAccount.getMoney() + money);

        // 更新改变的数据
        accountDao.updateAccountMoney(sourceAccount);
//        int i = 1/0;
        accountDao.updateAccountMoney(tagetAccount);


//        TransationManager.commit();
//        TransationManager.release();
    }
}
