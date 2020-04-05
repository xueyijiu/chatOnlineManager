package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.PageInfo;
import com.example.demo.common.ResponseObject;
import com.example.demo.constant.StatusCode;
import com.example.demo.dto.UserDynamicDto;
import com.example.demo.entity.DynamicFile;
import com.example.demo.entity.UserDynamic;
import com.example.demo.service.IDynamicFileService;
import com.example.demo.service.IUserDynamicService;
import com.example.demo.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户动态 前端控制器
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/user-dynamic")
public class UserDynamicController {

    @Autowired
    private IUserDynamicService userDynamicService;

    @Autowired
    private IDynamicFileService dynamicFileService;

    @Autowired
    private UserService userService;

    @Value("${com.zjc.friend.image-path}")
    private String imagePath;

    @Value("${com.zjc.friend.web-root}")
    private String webRoot;


    @RequestMapping("/dynamicList")
    public Map<String, Object> dynamicList(UserDynamic dynamic, @RequestParam(required = false) Boolean status, @RequestParam int page, @RequestParam int limit) {
        QueryWrapper<UserDynamic> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time"); //按创建时间降序排列
        if (null != status) {
            wrapper.eq("delete_status", status);
        }
        System.out.println(page + "==" + limit);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(limit);
        pageInfo.setPageIndex(page);
        IPage<UserDynamicDto> userDynamicIPage = userDynamicService.queryDynamic(pageInfo, dynamic);
        for (UserDynamicDto userDynamicDto : userDynamicIPage.getRecords()) {
            System.out.println(userDynamicDto.getUser().getUsername());
            userDynamicDto.setUsername(userDynamicDto.getUser().getUsername());
            for (DynamicFile dynamicFile : userDynamicDto.getDynamicFile()) {
                dynamicFile.setFilePath( webRoot+ dynamicFile.getFilePath());
            }
            if(userDynamicDto.getDeleteStatus()==0){
                userDynamicDto.setDeleteStatusString("可见");
            }
            else{
                userDynamicDto.setDeleteStatusString("不可见");
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", userDynamicIPage.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        data.put("data", userDynamicIPage.getRecords());
        return data;
    }

    /***
     * 插找单条动态信息
     * @param id
     * @return
     */
    @RequestMapping("/queryADynamic")
    public UserDynamic queryADynamic(@RequestParam Long id) {
        UserDynamic byId = userDynamicService.getById(id);
        if (null != byId) {
            QueryWrapper<DynamicFile> wrapper = new QueryWrapper<>();
            wrapper.eq("dynamic_id", byId.getId());
            List<DynamicFile> list = dynamicFileService.list(wrapper);
        }
        return byId;
    }

    /**
     * 修改动态状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateDyStatus")
    public ResponseObject deleteDyStatus(@RequestParam Long id,@RequestParam Integer status){
        UpdateWrapper<UserDynamic> wrapper=new UpdateWrapper<>();
        wrapper.set("delete_status",status);
        wrapper.eq("id",id);
        boolean update = userDynamicService.update(wrapper);
        return new ResponseObject(StatusCode.SUCCESS.getCode(),"",update);
    }
}
