package com.laowei.dao.impl;

import com.laowei.dao.AccountDao;
import com.laowei.domain.Account;
import com.laowei.util.DBCPUtil;
import com.laowei.util.TransationManager;
import org.apache.commons.dbutils.QueryRunner;

import javax.management.Query;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {

    private QueryRunner queryRunner = new QueryRunner();
    // 查找用户信息
    public Account findAccountByName(String accountName) {
        try {
            return queryRunner.query(TransationManager.getConnection(), "select * from account where name=?", new org.apache.commons.dbutils.handlers.BeanHandler<Account>(Account.class),accountName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 更新用户金额
    public void updateAccountMoney(Account account) {
        try {
            queryRunner.update(TransationManager.getConnection(), "update account set money=? where id=?", account.getMoney(), account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
