package com.laowei.view;

import com.laowei.service.BusinessService;
import com.laowei.service.impl.BusinessSeviceImpl;
import com.laowei.util.BeanFactory;

public class Main {

    public static void main(String[] args){
//        BusinessService service = new BusinessSeviceImpl();

        BusinessService service = BeanFactory.getBussinessService(true);
        service.transfer("老王", "老张",100);
    }

}
