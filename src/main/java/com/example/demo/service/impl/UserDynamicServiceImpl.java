package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.PageInfo;
import com.example.demo.dto.UserDynamicDto;
import com.example.demo.entity.UserDynamic;
import com.example.demo.mapper.UserDynamicMapper;
import com.example.demo.service.IUserDynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户动态 服务实现类
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
@Service
public class UserDynamicServiceImpl extends ServiceImpl<UserDynamicMapper, UserDynamic> implements IUserDynamicService {

    @Autowired
    private UserDynamicMapper userDynamicMapper;

    @Override
    public IPage<UserDynamicDto> queryDynamic(PageInfo pageInfo, UserDynamic userDynamic) {
        Page page = new Page();
        page.setCurrent(pageInfo.getPageIndex());
        page.setSize(pageInfo.getPageSize());
        return userDynamicMapper.queryDynamic(page, userDynamic);
    }
}
