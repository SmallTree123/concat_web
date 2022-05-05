package com.nylgsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nylgsc.entity.Address;
import com.nylgsc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    @Insert("insert into address(id,address) value (#{id},#{address})")
    int insert(Address address);
    @Select("select * from address")
    List<User> selectAll();
}
