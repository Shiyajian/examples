package com.shiyajian.examples.logs.service.impl;

import com.shiyajian.examples.logs.model.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author shiyajian
 * create: 2018-12-06
 */
@Slf4j
@Component
public class Log {


    public void record(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            if (log.isDebugEnabled()) {
                log.debug("id:{},name:{},ip:{},url:{},params:{}",
                        logEntity.getId(),
                        logEntity.getName(),
                        logEntity.getId(),
                        logEntity.getUrl(),
                        logEntity.getParams());
            }
            log.info("id:{},name:{},ip:{},url:{},params:{}",
                    logEntity.getId(),
                    logEntity.getName(),
                    logEntity.getId(),
                    logEntity.getUrl(),
                    logEntity.getParams());
        }
    }

    public void record1(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            log.debug("id:{},name:{},ip:{},url:{},params:{}",
                    logEntity.getId(),
                    logEntity.getName(),
                    logEntity.getId(),
                    logEntity.getUrl(),
                    logEntity.getParams());
            log.info("id:{},name:{},ip:{},url:{},params:{}",
                    logEntity.getId(),
                    logEntity.getName(),
                    logEntity.getId(),
                    logEntity.getUrl(),
                    logEntity.getParams());
        }
    }

    public void record2(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
            if (log.isDebugEnabled()) {
                log.debug("id:" + logEntity.getId() +
                        ",name:" + logEntity.getName() +
                        ",ip:" + logEntity.getId() +
                        ",url:" + logEntity.getUrl() +
                        ",params:" + logEntity.getParams());
            }
            log.info("id:" + logEntity.getId() +
                    ",name:" + logEntity.getName() +
                    ",ip:" + logEntity.getId() +
                    ",url:" + logEntity.getUrl() +
                    ",params:" + logEntity.getParams());

        }
    }

    public void record3(LogEntity logEntity) {
        for (int i = 0; i < 1000; i++) {
                log.debug("id:" + logEntity.getId() +
                        ",name:" + logEntity.getName() +
                        ",ip:" + logEntity.getId() +
                        ",url:" + logEntity.getUrl() +
                        ",params:" + logEntity.getParams());
            log.info("id:" + logEntity.getId() +
                    ",name:" + logEntity.getName() +
                    ",ip:" + logEntity.getId() +
                    ",url:" + logEntity.getUrl() +
                    ",params:" + logEntity.getParams());

        }
    }
}
