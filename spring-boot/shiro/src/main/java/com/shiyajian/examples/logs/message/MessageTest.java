package com.shiyajian.examples.logs.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author shiyajian
 * create: 2019-01-02
 */
@Component
@Slf4j
public class MessageTest {

    @Autowired
    MessageSource messageSource;

    public void print(){

        log.info(messageSource.getMessage("hello.test",null,"", Locale.CHINA));
    }
}
