package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.JsonData;
import com.example.demo.common.PageInfo;
import com.example.demo.entity.User;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserRoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linziyu on 2019/2/12.
 * <p>
 * 用户角色处理视图
 */

@Controller
@Slf4j
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    //设置为管理员
    @RequestMapping(value = "/setUserAdmin")
    @ResponseBody
    public JsonData changeUserRole(Long id) {
        userRoleService.setUserAdmin(id);
        return new JsonData(200, "ok");

    }

    //设置为普通用户
    @RequestMapping(value = "/setUser")
    @ResponseBody
    public JsonData setUser(Long id) {
        return userRoleService.setUser(id);
    }

    @Autowired
    private UserService userService;


}
