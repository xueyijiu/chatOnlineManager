package com.example.demo.controller;

import com.example.demo.common.JsonData;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAndRole;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by linziyu on 2019/2/12.
 * <p>
 * 注册视图
 */

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private final static BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @RequestMapping(value = "/register")
    @ResponseBody
    public JsonData register(User user) {
        String rawPasswod = user.getPassword();
        String encodePassword = ENCODER.encode(rawPasswod);
        user.setPassword(encodePassword);
        userService.saveUser(user);
        Long userID = userService.getUserId(user.getUsername());
        Long roleID = 2l;//每个新注册用户默认设置角色为普通用户
        UserAndRole userAndRole = new UserAndRole(userID, roleID);
        userRoleService.save1(userAndRole);
        return new JsonData(200, "注册成功");
    }

}
