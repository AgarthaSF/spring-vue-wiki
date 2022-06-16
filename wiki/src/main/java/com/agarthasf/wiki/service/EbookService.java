package com.agarthasf.wiki.service;

import com.agarthasf.wiki.domain.Ebook;
import com.agarthasf.wiki.domain.EbookExample;
import com.agarthasf.wiki.mapper.EbookMapper;
import com.agarthasf.wiki.req.EbookQueryReq;
import com.agarthasf.wiki.req.EbookSaveReq;
import com.agarthasf.wiki.resp.EbookQueryResp;
import com.agarthasf.wiki.resp.PageResp;
import com.agarthasf.wiki.util.CopyUtil;
import com.agarthasf.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;


    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        if(!ObjectUtils.isEmpty(req.getCategoryId2())){
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        }

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);

        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        List<EbookQueryResp> respList = CopyUtil.copyList(ebooksList, EbookQueryResp.class);
        pageResp.setList(respList);
        pageResp.setTotal((int) pageInfo.getTotal());

        return pageResp;
    }

    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        // 没有id则新增，有id则更新
        if(ObjectUtils.isEmpty(ebook.getId())){
            ebook.setId(snowFlake.nextId() / 1000);
            ebookMapper.insert(ebook);
        }else{
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }

}
