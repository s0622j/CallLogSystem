package com.cn.test;

import com.cn.calllog.consumer.HbaseDao;
import org.junit.Test;

public class TestHbaseDao {
    @Test
    public void test1(){
        HbaseDao dao = new HbaseDao();
        dao.put("18301589432,18620192711,20210224 17:50:47,505");
    }
}
