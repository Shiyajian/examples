package com.shiyajian.examples.logs.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author shiyajian
 * create: 2018-12-06
 */
@Data
@Builder
public class LogEntity {

    private String id;

    private String name;

    private String ip;

    private String url;

    private Map<String,String> params;
}
