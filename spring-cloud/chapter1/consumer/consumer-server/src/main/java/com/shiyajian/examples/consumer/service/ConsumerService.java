package com.shiyajian.examples.consumer.service;

import java.io.File;

/**
 * @author shiyajian
 * create: 2018-11-25
 */
public interface ConsumerService {

    /**
     * 上传文件，测试方法
     */
    void uploadFile(File file);

    /**
     * 上传多个文件，测试方法
     */
    void uploadFiles(File[] files);
}
