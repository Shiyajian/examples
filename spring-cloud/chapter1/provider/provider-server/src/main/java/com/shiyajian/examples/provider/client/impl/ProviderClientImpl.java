package com.shiyajian.examples.provider.client.impl;

import com.google.common.collect.Lists;
import com.shiyajian.examples.provider.client.ProviderClient;
import entity.response.ProviderResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * 微服务之间采用feign调用
 *
 * @author shiyajian
 * create: 2018-11-25
 */
@RestController// 这个不能少
public class ProviderClientImpl implements ProviderClient {

    @Override
    // postMapping 这一行注解可以删除，但是参数前面的注解不可以删除
    public String uploadFile(@RequestPart("file") MultipartFile file,
                             @PathVariable("id") String id,
                             @RequestParam("name") String name) {
        String responsePattern = "接受到文件[%s],id:[%s],大小为:[%s]";
        return String.format(responsePattern, name, id, file.getSize());
    }

    @Override
    public List<ProviderResponse> uploadFiles(@RequestPart("files") MultipartFile[] files,
                                              @RequestParam("author") String author) {
        List<ProviderResponse> responses = Lists.newArrayList();

        // if empty return;
        if (files.length == 0) {
            return responses;
        }

        // not empty iterate;
        ProviderResponse response;
        for (MultipartFile file : files) {
            response = new ProviderResponse();
            response.setFileId(UUID.randomUUID().toString());
            response.setFileName(file.getName());
            response.setResponseMsg(String.format("%s upload file by feign form!", author));
            response.setContentLength(file.getSize());
            response.setSucceed(true);
            responses.add(response);
        }
        return responses;
    }
}
