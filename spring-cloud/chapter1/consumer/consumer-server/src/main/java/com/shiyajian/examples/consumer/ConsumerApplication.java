package com.shiyajian.examples.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shiyajian
 * create: 2018-11-25
 */
@SpringCloudApplication
// 这个注解非常重要，不然引用不到client中的方法
@EnableFeignClients("com.shiyajian.examples.provider")
public class ConsumerApplication {
    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class);
    }
}
