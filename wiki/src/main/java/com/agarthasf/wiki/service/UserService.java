package com.agarthasf.wiki.service;

import com.agarthasf.wiki.domain.User;
import com.agarthasf.wiki.domain.UserExample;
import com.agarthasf.wiki.mapper.UserMapper;
import com.agarthasf.wiki.req.UserQueryReq;
import com.agarthasf.wiki.req.UserSaveReq;
import com.agarthasf.wiki.resp.UserQueryResp;
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
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;


    public PageResp<UserQueryResp> list(UserQueryReq req){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getLoginName())){
            criteria.andLoginNameEqualTo(req.getLoginName());
        }

        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> usersList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(usersList);
        PageResp<UserQueryResp> pageResp = new PageResp<>();
        List<UserQueryResp> respList = CopyUtil.copyList(usersList, UserQueryResp.class);
        pageResp.setList(respList);
        pageResp.setTotal((int) pageInfo.getTotal());

        return pageResp;
    }

    public void save(UserSaveReq req){
        User user = CopyUtil.copy(req, User.class);
        // 没有id则新增，有id则更新
        if(ObjectUtils.isEmpty(user.getId())){
            user.setId(snowFlake.nextId() / 1000);
            userMapper.insert(user);
        }else{
            userMapper.updateByPrimaryKey(user);
        }
    }

    public void delete(Long id){
        userMapper.deleteByPrimaryKey(id);
    }

}
