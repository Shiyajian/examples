package com.shiyajian.examples.consumer.service.impl;

import com.shiyajian.examples.consumer.service.ConsumerService;
import com.shiyajian.examples.consumer.utils.MultipartFileUtil;
import com.shiyajian.examples.provider.client.ProviderClient;
import entity.response.ProviderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author shiyajian
 * create: 2018-11-25
 */
@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ProviderClient providerClient;


    @Override
    public void uploadFile(File file) {
        // 这个必须和接受方中 requestPart中的key对应一致
        MultipartFile multipartFile = MultipartFileUtil.from(file, "file");
        String id = UUID.randomUUID().toString();
        String name = file.getName();
        String result = providerClient.uploadFile(multipartFile, id, name);
        System.out.println(result);
    }

    @Override
    public void uploadFiles(File[] files) {

        MultipartFile[] multipartFiles = MultipartFileUtil.from(files, "files");
        String author = "shiyajian";
        List<ProviderResponse> responses = providerClient.uploadFiles(multipartFiles,author);
        System.out.println(responses);
    }

}
