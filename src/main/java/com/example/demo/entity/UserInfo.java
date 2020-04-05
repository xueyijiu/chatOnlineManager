package com.example.demo.entity;

import lombok.Data;

/**
 * @ Author     ：zjc
 * @ Date       ：Created in 20:34 2020/3/13
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Data
public class UserInfo {
    private Long id;

    private String username;

    private String password;

    private String sex;

    private String userPic;
}
