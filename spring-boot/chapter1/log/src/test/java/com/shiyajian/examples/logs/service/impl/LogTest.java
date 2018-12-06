package com.shiyajian.examples.logs.service.impl;
import com.google.common.collect.Maps;

import com.shiyajian.examples.logs.model.LogEntity;
import com.shiyajian.examples.logs.service.impl.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author shiyajian
 * create: 2018-12-06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTest {

    @Autowired
    Log log;

    private LogEntity logEntity = new LogEntity();

    @Before
    public void upset(){

        logEntity.setId("123456");
        logEntity.setName("test");
        logEntity.setIp("localhost");
        logEntity.setUrl("www.baidu.com");
        HashMap<String, String> params = Maps.newHashMap();
        params.put("ref","www.baidu.com");
        logEntity.setParams(params);

    }

    @Test
    public void test0(){
        log.record(logEntity);
    }
    @Test
    public void test1(){
        log.record1(logEntity);
    }
    @Test
    public void test2(){
        log.record2(logEntity);
    }
    @Test
    public void test3(){
        log.record3(logEntity);
    }
}
