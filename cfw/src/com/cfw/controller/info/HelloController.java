package com.cfw.controller.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 15-9-23
 * Time: 上午9:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/hello", method = { RequestMethod.POST, RequestMethod.GET })
    public String hello(){
        System.out.println("hello,............");
        return "hello";
    }
}
