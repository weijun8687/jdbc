package com.laowei.test;

import com.laowei.dbassist.DBAssist;
import com.laowei.util.DBCPUtil;
import org.junit.Test;

public class DBAssistTest {

    private DBAssist dbAssist = new DBAssist(DBCPUtil.getDataSource());
    @Test
    public void testAdd(){
        dbAssist.update("insert into user (id,name, sex) values(?,?,?)", 5, "haha", 1);
    }

}
