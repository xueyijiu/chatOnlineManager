package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.JsonData;
import com.example.demo.common.PageInfo;
import com.example.demo.common.ResponseObject;
import com.example.demo.constant.StatusCode;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linziyu on 2019/2/13.
 * <p>
 * 用户处理视图
 */

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/deleteUserById")
    @ResponseBody
    public JsonData deleteUserById(Long id, String role) {
        if (role.equals("ROLE_ADMIN")) {
            return new JsonData(501, "NO");
        }
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("status", false);
        wrapper.eq("id", id);
        userService.update(wrapper);
        return new JsonData(200, "OK");
    }


    @RequestMapping("/login_page")
    public String login() {
        return "/login";
    }

    //用户资料分页
    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public Map<String, Object> page(int page, int limit) {
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
//        log.info("{}","page");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(page);
        pageInfo.setPageSize(limit);
        IPage<User> allUsers = userService.getAllUsers(pageInfo);
        for (User user : allUsers.getRecords()) {
            user.setRole(user.getRoles().get(0).getName());//用户角色包装 方便处理
        }

        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", allUsers.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        System.out.println(allUsers.getRecords());
        data.put("data", allUsers.getRecords());
        //返回给前端
        return data;

    }

    /**
     * 展示用户信息
     *
     * @return
     */
    @RequestMapping("/display_user_info")
    public String displayUserInfo() {
        return "displayuserinfo";
    }

    /**
     * 修改密码
     *
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResponseObject updatePassword(@RequestParam String name, @RequestParam String pwd) {
        User byUserName = userService.findByUserName(name);
        if (null == byUserName) {
            return new ResponseObject(StatusCode.FAILED.getCode(), "该用户不存在");
        }
        for (Role role : byUserName.getRoles()) {
            if (!role.getName().equals("ROLE_ADMIN")) {
                return new ResponseObject(StatusCode.FAILED.getCode(), "你不是管理员！");
            }
        }
        if (passwordEncoder.matches(pwd, byUserName.getPassword())) {
            return new ResponseObject(StatusCode.FAILED.getCode(), "和上次密码一致");
        }
        String encode = passwordEncoder.encode(pwd);
        if (!byUserName.getStatus()) {
            return new ResponseObject(StatusCode.FAILED.getCode(), "该用户不存在");
        }
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", byUserName.getId());
        wrapper.set("password", encode);
        userService.update(wrapper);
        return new ResponseObject(StatusCode.SUCCESS.getCode(), "成功");
    }
}
