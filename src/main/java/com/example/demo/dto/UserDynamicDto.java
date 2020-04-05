package com.example.demo.dto;

import com.example.demo.entity.DynamicFile;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ Author     ：zjc
 * @ Date       ：Created in 19:47 2020/3/13
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Data
public class UserDynamicDto {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 动态标题
     */
    private String dynamicTitle;

    /**
     * 动态内容
     */
    private String dynaminContent;

    private String username;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 删除状态，0为未删除，1为删除
     */
    private Integer deleteStatus;

    private UserInfo user;

    private String deleteStatusString;

    private List<DynamicFile> dynamicFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDynamicTitle() {
        return dynamicTitle;
    }

    public void setDynamicTitle(String dynamicTitle) {
        this.dynamicTitle = dynamicTitle;
    }

    public String getDynaminContent() {
        return dynaminContent;
    }

    public void setDynaminContent(String dynaminContent) {
        this.dynaminContent = dynaminContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDynamicDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", dynamicTitle='" + dynamicTitle + '\'' +
                ", dynaminContent='" + dynaminContent + '\'' +
                ", createTime=" + createTime +
                ", deleteStatus=" + deleteStatus +
                ", user=" + user +
                '}';
    }
}
