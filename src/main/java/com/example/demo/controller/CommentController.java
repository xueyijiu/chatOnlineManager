package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.JsonData;
import com.example.demo.common.ResponseObject;
import com.example.demo.constant.StatusCode;
import com.example.demo.entity.Comment;
import com.example.demo.entity.ControlTable;
import com.example.demo.entity.User;
import com.example.demo.service.ICommentService;
import com.example.demo.service.IControlTableService;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private IControlTableService controlTableService;


    /**
     * 监控消息
     *
     * @param message
     * @return
     */
    @RequestMapping("/intercept")
    public JsonData intercept(String message) {
        List<ControlTable> controlTables = controlTableService.list();
        if (controlTables.size() > 0) {
            for (ControlTable table : controlTables) {
                if (table.getContent().contains(message)) {
                    return new JsonData(400, "你的言语不能通过该系统！");
                }
            }
        }
        return new JsonData(200, "成功");
    }

    /**
     * 查找评论列表
     *
     * @param page
     * @param limit
     * @param message
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/queryCommentList")
    public Map<String, Object> queryCommentList(@RequestParam Long page, @RequestParam Long limit, @RequestParam(required = false) String message,
                                                @RequestParam(required = false) Date startTime, @RequestParam(required = false) Date endTime,
                                                @RequestParam(required = false) String username) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(message)) {
            wrapper.like("comment_content", message);
        }
        if (null != startTime) {
            wrapper.gt("create_time", startTime);
        }
        if (null != endTime) {
            wrapper.lt("create_time", endTime);
        }
        if(StringUtils.isNotBlank(username)){
            User byUserName = userService.findByUserName(username);
            wrapper.eq("user_id",byUserName.getId());
        }
        Page page2 = new Page();
        page2.setCurrent(page);
        page2.setSize(limit);
        IPage<Comment> page1 = commentService.page(page2, wrapper);
        List<User> allUser = userService.list();
        List<Comment> commentList=new ArrayList<>();
        for (Comment comment:page1.getRecords()) {
            if(allUser.size()>0)
            {
                for (User user:allUser) {
                    if(comment.getUserId().equals(user.getId())){
                        comment.setCommentUser(user.getUsername());
                    }
                }
                commentList.add(comment);
            }
        }
//        pageInfo.setPageIndex(page);
//        pageInfo.setPageSize(limit);
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", page1.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        data.put("data", commentList);
      return data;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping("/deleteComment")
    public ResponseObject deleteComment(@RequestParam Long id){
        boolean b = commentService.removeById(id);
        return new ResponseObject(StatusCode.SUCCESS.getCode(),"",b);
    }

}
