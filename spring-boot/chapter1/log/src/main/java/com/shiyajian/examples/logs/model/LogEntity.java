package com.shiyajian.examples.logs.model;

import lombok.Data;

import java.util.Map;

/**
 * @author shiyajian
 * create: 2018-12-06
 */
@Data
public class LogEntity {

    private String id;

    private String name;

    private String ip;

    private String url;

    private Map<String,String> params;
}
