package com.agarthasf.wiki.service;

import com.agarthasf.wiki.domain.Category;
import com.agarthasf.wiki.domain.CategoryExample;
import com.agarthasf.wiki.mapper.CategoryMapper;
import com.agarthasf.wiki.req.CategoryQueryReq;
import com.agarthasf.wiki.req.CategorySaveReq;
import com.agarthasf.wiki.resp.CategoryQueryResp;
import com.agarthasf.wiki.resp.PageResp;
import com.agarthasf.wiki.util.CopyUtil;
import com.agarthasf.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;


    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        List<CategoryQueryResp> respList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        pageResp.setList(respList);
        pageResp.setTotal((int) pageInfo.getTotal());

        return pageResp;
    }

    public void save(CategorySaveReq req){

        Category category = CopyUtil.copy(req, Category.class);

        // 没有id则新增，有id则更新
        if(ObjectUtils.isEmpty(category.getId())){
            category.setId(snowFlake.nextId() / 1000);
            categoryMapper.insert(category);
        }else{
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }

    public List<CategoryQueryResp> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> respList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        return respList;
    }

}
