package com.agarthasf.wiki.controller;

import com.agarthasf.wiki.domain.Test;
import com.agarthasf.wiki.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

// RestController is used to return a String, Controller is used to return a page
// ResponseBody is used to return a json
@RestController
public class TestController {

    @Resource
    private TestService testService;



    //   GET       POST      PUT     DELETE
    // retrieve   create    update   delete


    // RequestMapping indicates that this interface supports
    // all the request method

    // GetMapping PostMapping PutMapping DeleteMapping

//    @RequestMapping(value = "/user/1", method = RequestMethod.GET);

    @GetMapping("/hello/post")
    public String hello(){
        return "Hello World!";
    }


    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
}
