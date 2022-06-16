package com.agarthasf.wiki.controller;

import com.agarthasf.wiki.req.UserQueryReq;
import com.agarthasf.wiki.req.UserSaveReq;
import com.agarthasf.wiki.resp.CommonResp;
import com.agarthasf.wiki.resp.UserQueryResp;
import com.agarthasf.wiki.resp.PageResp;
import com.agarthasf.wiki.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

// RestController is used to return a String, Controller is used to return a page
// ResponseBody is used to return a json
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // it will map the parameter to the request
    @GetMapping("list")
    public CommonResp list(@Valid UserQueryReq req){
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("save")
    public CommonResp save(@RequestBody @Valid UserSaveReq req){
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }


}
