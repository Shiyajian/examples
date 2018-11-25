package com.shiyajian.examples.consumer.service.impl;

import com.shiyajian.examples.consumer.service.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsumerServiceImplTest {

    @Autowired
    ConsumerService consumerService;

    @Test
    public void uploadFile() {
        // FIXME: 2018/11/25  正式测试时候，请将此处文件路径换成你本地的配置
        File file = new File("/Users/shiyajian/hello.txt");
        consumerService.uploadFile(file);
    }

    @Test
    public void uploadFiles() {
        // FIXME: 2018/11/25  正式测试时候，请将此处文件路径换成你本地的配置
        File file =  new File("/Users/shiyajian/hello.txt");
        File file1 = new File("/Users/shiyajian/jmeter.log");
        File[] files = {file,file1};
        consumerService.uploadFiles(files);
    }
}