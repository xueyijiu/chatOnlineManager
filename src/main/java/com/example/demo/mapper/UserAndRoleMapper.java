package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserAndRole;

public interface UserAndRoleMapper extends BaseMapper<UserAndRole> {
    int deleteByPrimaryKey(Long id);

    int insert(UserAndRole record);

    int insertSelective(UserAndRole record);

    UserAndRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAndRole record);

    int updateByPrimaryKey(UserAndRole record);

    int setUserAdmin(Long userID);

    int setUser(Long userID);
}