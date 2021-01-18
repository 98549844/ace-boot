package com.aceboot.controller;

import com.aceboot.entity.vo.SwaggerVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/swagger")
public class SwaggerController {
    private final Log log = LogFactory.getLog(this.getClass());

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getSwagger() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");


        String get = "Hello World ! GET";
        return get;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public String getSwagger(@PathVariable String userName) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");


        String get = "Hello World !" + userName + " GET";
        return get;
    }

    @ApiOperation(value = "swagger post body", notes = "{\n" + "  \"swaggerContent\": \"内容\",\n" + "  \"swaggerDescription\": \"Json描述\",\n" + "  \"swaggerId\": 0,\n" + "  \"swaggerTitle\": \"Json标题\"\n" + "}")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public SwaggerVO postSwagger(@RequestBody SwaggerVO swaggerVO) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        String post = "Hello World ! POST";
        swaggerVO.setSwaggerDescription(post);
        return swaggerVO;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public String putSwagger() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");


        String put = "Hello World ! PUT";
        return put;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteSwagger() {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");


        String delete = "Hello World ! DELETE";
        return delete;
    }
}
