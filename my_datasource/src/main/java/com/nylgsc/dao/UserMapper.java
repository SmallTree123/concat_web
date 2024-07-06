package com.nylgsc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nylgsc.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
