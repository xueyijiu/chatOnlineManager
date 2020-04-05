package com.example.demo.service;

import com.example.demo.common.JsonData;
import com.example.demo.entity.UserAndRole;

/**
 * @ Author     ：zjc
 * @ Date       ：Created in 19:44 2020/3/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface UserRoleService {
    public void save1(UserAndRole userAndRole);

    public void setUserAdmin(Long userID);

    public JsonData setUser(Long userID);
}
