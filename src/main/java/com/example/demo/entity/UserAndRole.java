package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("sys_user_role")
public class UserAndRole {
    private Long id;

    private Long userId;

    private Long roldId;

    public UserAndRole(Long userId, Long roldId) {
        this.userId = userId;
        this.roldId = roldId;
    }
}