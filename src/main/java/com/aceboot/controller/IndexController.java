package com.aceboot.controller;

import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/index")
@Api(tags = "---Index---")
public class IndexController {
    private final Log log = LogFactory.getLog(this.getClass());

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");


        String get = "Welcome Index ! GET";
        return get;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public String getIndex(@PathVariable String userName) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        log.info(userName);

        String get = "Welcome Index !" + userName + " GET";
        return get;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postIndex(@RequestBody String IndexVO) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        String post = "Welcome Index ! POST";
        System.out.println(post);
        IndexVO = post;
        return IndexVO;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public String putIndex() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        String put = "Welcome Index ! PUT";
        return put;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteIndex() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");


        String delete = "Hello World ! DELETE";
        return delete;
    }
}
