package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.UserDynamicDto;
import com.example.demo.entity.UserDynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户动态 Mapper 接口
 * </p>
 *
 * @author zjc
 * @since 2020-03-11
 */
public interface UserDynamicMapper extends BaseMapper<UserDynamic> {

    IPage<UserDynamicDto> queryDynamic(Page page, @Param("dynamic") UserDynamic userDynamic);//查找动态功能
}
