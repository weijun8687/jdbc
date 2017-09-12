package com.laowei.util;

import java.sql.Connection;
import java.sql.SQLException;

// 事务管理器
public class TransationManager {

    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static Connection getConnection(){
        Connection connection = tl.get();
        if (connection == null){
            connection = DBCPUtil.getConnection();
            tl.set(connection);     // 将连接绑定的当前线程上
        }
        return connection;
    }

    // 开始事务
    public static void startTransition(){
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 提交事务
    public static void commit(){
        Connection connection = getConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 回滚事务
    public static void rollback(){
        Connection connection = getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭事务, 释放连接
    public static void release(){
        Connection connection = getConnection();
        try {
            connection.close();
            tl.remove();    // 与线程池有关, 特别重要
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
