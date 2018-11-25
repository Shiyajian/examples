package com.shiyajian.examples.provider.client;

import com.shiyajian.examples.provider.client.config.FeignMultipartSupportConfig;
import entity.response.ProviderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author shiyajian
 * create: 2018-11-25
 * attention:
 * 1、@RequestMapping("/client") 此处不能加，不然启动会注册两次mapping地址，导致报错
 * 2、需要增加配置类
 * 3、FeignClient中的value必须和spring.application.name一致
 */
@FeignClient(value = "provider-server", configuration = FeignMultipartSupportConfig.class)
//@RequestMapping("/client") 不可以加
public interface ProviderClient {

    /**
     * 单个文件上传
     *
     * @param file 文件
     * @param id   文件id
     * @return 响应值
     * <p>
     * attention ：
     * 1、server中实现了此接口，那么@PostMapping可以不写
     * 2、server中实现了此接口，@RequestPart("file")、@PathVariable等注解必须写
     * 3、@RequestPart括号中的值，也就是value值必须写。比如"@PathVariable String id"就会报错
     * 4、@RequestPart和@RequestParam不是等价的，必须使用@ReuqestPart
     * 5、必须增加"consumes= multipart/form-data"，此处使用了静态导包
     * 6、参数中不支持@RequestBody注解
     */
    @PostMapping(value = "client/upload/{id}", consumes = MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file,
                      @PathVariable("id") String id,
                      @RequestParam("name") String name);


    @PostMapping(value = "client/uploads", consumes = MULTIPART_FORM_DATA_VALUE)
    List<ProviderResponse> uploadFiles(@RequestPart("files") MultipartFile[] files, @RequestParam("author") String author);

}
