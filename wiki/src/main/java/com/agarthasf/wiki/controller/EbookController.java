package com.agarthasf.wiki.controller;

import com.agarthasf.wiki.req.EbookQueryReq;
import com.agarthasf.wiki.req.EbookSaveReq;
import com.agarthasf.wiki.resp.CommonResp;
import com.agarthasf.wiki.resp.EbookQueryResp;
import com.agarthasf.wiki.resp.PageResp;
import com.agarthasf.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

// RestController is used to return a String, Controller is used to return a page
// ResponseBody is used to return a json
@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    // it will map the parameter to the request
    @GetMapping("list")
    public CommonResp list(@Valid EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("save")
    public CommonResp save(@RequestBody @Valid EbookSaveReq req){
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }


}
