package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.PageInfo;
import com.example.demo.dto.UserDynamicDto;
import com.example.demo.entity.UserDynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户动态 服务类
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
public interface IUserDynamicService extends IService<UserDynamic> {

    IPage<UserDynamicDto> queryDynamic(PageInfo page, @Param("dynamic") UserDynamic userDynamic);//查找动态功能
}
