package com.agarthasf.wiki.controller;

import com.agarthasf.wiki.req.CategoryQueryReq;
import com.agarthasf.wiki.req.CategorySaveReq;
import com.agarthasf.wiki.resp.CommonResp;
import com.agarthasf.wiki.resp.CategoryQueryResp;
import com.agarthasf.wiki.resp.PageResp;
import com.agarthasf.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

// RestController is used to return a String, Controller is used to return a page
// ResponseBody is used to return a json
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    // it will map the parameter to the request
    @GetMapping("list")
    public CommonResp list(@Valid CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("save")
    public CommonResp save(@RequestBody @Valid CategorySaveReq req){
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }

    @GetMapping("all")
    public CommonResp all(){
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        resp.setContent(categoryService.all());
        return resp;
    }


}
