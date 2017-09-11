package com.laowei.dbassist;

import com.laowei.util.DBCPUtil;

import javax.sql.DataSource;
import java.sql.*;

public class DBAssist {
    private DataSource dataSource;

    public DBAssist(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);

            // 设置参数
            // 获取元数据对象
            ParameterMetaData pmd = statement.getParameterMetaData();
            // 获取元数据对象 参数的个数
            int num = pmd.getParameterCount();

            if (num > 0) {
                if (params == null) {
                    throw new IllegalArgumentException("有占位符, 但没有传参数");
                }
                if (params.length != num) {
                    throw new IllegalArgumentException("参数个数不匹配");
                }
                for (int i = 0; i < num; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }

            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(resultSet, statement, connection);
        }
    }

    // 查询
    public Object query(String sql, ResultSetHandler handler, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            ParameterMetaData pmd = statement.getParameterMetaData();
            int num = pmd.getParameterCount();
            if (num>0){
                if (params==null){
                    throw new IllegalArgumentException("有占位符, 没有参数");
                }
                if (params.length != num){
                    throw new IllegalArgumentException("参数个数不匹配");
                }

                for(int i=0; i < num; i++){
                    statement.setObject(i+1, params[i]);
                }
            }

            resultSet = statement.executeQuery();
            return handler.handle(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release(resultSet, statement, connection);
        }
        return null;
    }


    private void release(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
