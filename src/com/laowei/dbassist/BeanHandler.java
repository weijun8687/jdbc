package com.laowei.dbassist;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanHandler implements ResultSetHandler {

    private Class clazz;

    public BeanHandler(Class clazz){
        this.clazz = clazz;
    }

    /**
     * 将数据库中查询出的结果转换成 object
     * @param resultSet
     * @return
     */
    public Object handle(ResultSet resultSet) {

        try {
            if (resultSet.next()){
                Object bean = clazz.newInstance();

                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int num = resultSetMetaData.getColumnCount();
                for (int i = 0; i < num; i++){

                    // 利用反射机制 给对象赋值
                    String filedName = resultSetMetaData.getColumnName(i+1);
                    Object filedValue = resultSet.getObject(i+1);
                    // 获取字段
                    Field f = clazz.getDeclaredField(filedName);
                    f.setAccessible(true);  // 将该属性设置为可访问的
                    f.set(bean, filedValue);    // 在bean 中 给 f 字段 设置成 filedValue 的值
                }
                return bean;
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
