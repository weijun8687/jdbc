package com.laowei.util;


import com.laowei.service.BusinessService;
import com.laowei.service.impl.BusinessSeviceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    /**
     * 产生BusinessService 的实体类
     * @param isProxy true 返回代理类  false: 原来的类
     * @return
     */
    public static BusinessService getBussinessService(boolean isProxy){
        final BusinessService service = new BusinessSeviceImpl();
        if (isProxy){
            // 返回实现的代理类
            BusinessService proxyS = (BusinessService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object rtValue = null;

                    // 使用代理 添加事务 和 时间打印
                    // 以后的log日志的打印也放到该方法中
                    try {
                        long time = System.currentTimeMillis();

                        TransationManager.startTransition();
                        // 执行原来的方法
                        rtValue = method.invoke(service, args);
                        TransationManager.commit();

                        System.out.println(method.getName() + "cost time" + (System.currentTimeMillis()-time) + "millis second");

                    } catch (Exception e) {
                        TransationManager.rollback();
                        throw new RuntimeException(e);
                    }finally {
                        TransationManager.release();
                    }

                    return rtValue;
                }
            });
            return proxyS;
        }else {
            return service;
        }
    }
}
