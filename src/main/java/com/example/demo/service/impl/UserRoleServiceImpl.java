package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.JsonData;
import com.example.demo.mapper.UserAndRoleMapper;
import com.example.demo.entity.UserAndRole;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by linziyu on 2019/2/12.
 * <p>
 * 处理用户角色
 */

@Service("UserRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserAndRoleMapper, UserAndRole> implements UserRoleService {

    @Resource
    private UserAndRoleMapper userAndRoleMapper;

    public void save1(UserAndRole userAndRole) {
        userAndRoleMapper.insert(userAndRole);
    }

    public void setUserAdmin(Long userID) {
        userAndRoleMapper.setUserAdmin(userID);
    }

    public JsonData setUser(Long userID) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        List<String> the_roles = new ArrayList<String>();
//        for (GrantedAuthority authority : authorities) {
//            the_roles.add(authority.getAuthority());
//        }
//        if (the_roles.contains("ROLE_ADMIN")){
//            userAndRoleMapper.setUser(userID);
//            return new JsonData(200,"OK");
//        } else {
//            return new JsonData(500,"NO");
//        }
        userAndRoleMapper.setUser(userID);
        return new JsonData(200, "ok");


    }
}
