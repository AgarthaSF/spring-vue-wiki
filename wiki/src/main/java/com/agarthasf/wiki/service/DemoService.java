package com.agarthasf.wiki.service;

import com.agarthasf.wiki.domain.Demo;
import com.agarthasf.wiki.domain.Test;
import com.agarthasf.wiki.mapper.DemoMapper;
import com.agarthasf.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;


    public List<Demo> list(){
        return demoMapper.selectByExample(null);
    }



}
