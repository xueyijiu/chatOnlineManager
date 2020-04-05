package com.example.demo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户动态
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserDynamic implements Serializable {

//    private static final long serialVersionUID = 1L;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除状态，0为未删除，1为删除
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer deleteStatus;

    /**
     * 动态内容
     */
    private String dynaminContent;

    @TableField(exist = false)
    private Date endTime;


}
