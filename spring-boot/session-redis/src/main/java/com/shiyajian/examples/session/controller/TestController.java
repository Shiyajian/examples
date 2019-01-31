package com.shiyajian.examples.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shiyajian
 * create: 2019-01-31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public String getToken(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
