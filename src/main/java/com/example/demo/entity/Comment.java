package com.example.demo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {

//    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 动态id
     */
    private String dynamicId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 用户id
     */
    private Long userId;

    private Long commentUserId;

    @TableField(exist = false)
    private String commentUser;


}
