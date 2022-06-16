package com.agarthasf.wiki.service;

import com.agarthasf.wiki.domain.Test;
import com.agarthasf.wiki.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;


    public List<Test> list(){
        return testMapper.list();
    }



}
