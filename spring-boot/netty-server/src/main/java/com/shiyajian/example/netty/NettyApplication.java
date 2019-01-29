package com.shiyajian.example.netty;

import com.shiyajian.example.netty.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author shiyajian
 * create: 2018-11-28
 */
@SpringBootApplication
public class NettyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NettyApplication.class);
        NettyServer nettyServer = context.getBean(NettyServer.class);
        nettyServer.run();
    }
}
