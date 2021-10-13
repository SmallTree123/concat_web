package com.nylgsc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.mapper.GoodsMapper;
import com.nylgsc.model.Goods;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 水平分表插入和查询测试。。。
     */
    @GetMapping("/insert")
    public void insert(){
        Goods good = new Goods();
        good.setGname("小米手机");
        good.setUserId(100);
        good.setGstatus("已发布");
        goodsMapper.insert(good);
    }

    @GetMapping("/get")
    public void get(){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid",654994454825926656L);
        Goods good = goodsMapper.selectOne(queryWrapper);
        System.out.println(good.toString());
    }


    /**
     * 水平分库和水平分表插入和查询测试。。。
     */
    @GetMapping("/insert2")
    public void insert2(){
        Goods good = new Goods();
        good.setGname("华为手机");
        good.setUserId((int) (Math.random()*100));
        good.setGstatus("已发布");
        goodsMapper.insert(good);
    }

    @GetMapping("/get2")
    public void get2(){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid",655000698726907904L);
        queryWrapper.eq("user_id",100);
        Goods good = goodsMapper.selectOne(queryWrapper);
        System.out.println(good.toString());
    }

}
