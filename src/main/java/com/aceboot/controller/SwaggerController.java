package com.aceboot.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/swagger")
public class SwaggerController {
    private final Log log = LogFactory.getLog(this.getClass());

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getSwagger() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+": ");


        String hello = "Hello World !";
        return hello;
    }
}
