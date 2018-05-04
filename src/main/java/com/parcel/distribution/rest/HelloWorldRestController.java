package com.parcel.distribution.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloWorldRestController {

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public HelloWorldMsg helloWorldMsg(){
        HelloWorldMsg helloWorldMsg = new HelloWorldMsg();
        helloWorldMsg.setMsg("siema");
        return helloWorldMsg;
    }
}
