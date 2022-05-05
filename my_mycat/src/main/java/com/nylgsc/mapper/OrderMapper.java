package com.nylgsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nylgsc.entity.Order;
import com.nylgsc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Insert("insert into order(id,name) value (#{id},#{name})")
    int insert(Order order);
    @Select("select * from order")
    List<User> selectAll();
}
