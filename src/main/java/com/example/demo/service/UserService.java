package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.JsonData;
import com.example.demo.common.PageInfo;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    public User findByUserName(String userName);

    public void saveUser(User user);

    public IPage<User> getAllUsers(PageInfo pageInfo);

    public Long getUserId(String username);

    public JsonData deleteUserById(Long id, String delete_user_role);


}
