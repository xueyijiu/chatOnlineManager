package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.JsonData;
import com.example.demo.common.PageInfo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linziyu on 2019/2/9.
 * <p>
 * 处理用户
 */

@Service("UserService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    public void saveUser(User user) {
        userMapper.insert(user);
    }

    public IPage<User> getAllUsers(PageInfo pageInfo) {
        Page page = new Page();
        page.setCurrent(pageInfo.getPageIndex());
        page.setSize(pageInfo.getPageSize());
        return userMapper.getAllUsers(page);
    }

    public Long getUserId(String username) {
        return userMapper.getUserId(username);
    }

    public JsonData deleteUserById(Long id, String delete_user_role) {
        if (delete_user_role.equals("ROLE_ADMIN")) {
            return new JsonData(501, "NO");
        }
        UpdateWrapper<User> wrapper=new UpdateWrapper<>();
        wrapper.eq("id",id);
        return new JsonData(200, "OK");


    }

}
