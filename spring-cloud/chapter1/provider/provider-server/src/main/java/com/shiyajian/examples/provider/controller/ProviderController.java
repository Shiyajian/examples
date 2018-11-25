package com.shiyajian.examples.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外暴露服务的controller
 *
 * @author shiyajian
 * create: 2018-11-25
 */
@RestController
@RequestMapping("provider")
public class ProviderController {

    @GetMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }
}
