package com.nylgsc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nylgsc.mapper.GoodsMapper;
import com.nylgsc.mapper.MyDictMapper;
import com.nylgsc.mapper.UserMapper;
import com.nylgsc.model.Goods;
import com.nylgsc.model.MyDict;
import com.nylgsc.model.User;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyDictMapper myDictMapper;

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



    /**
     * 垂直分库，水平分库和水平分表插入和查询测试。。。
     */
    @GetMapping("/insert3")
    public void insert3(){
        User user = new User();
        user.setUsername("天上掉下来个琳妹妹");
        user.setUstatus("0");
        userMapper.insert(user);
    }

    @GetMapping("/get3")
    public void get3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //此处请填写自己程序生成的ID
        queryWrapper.eq("user_id",655047626240032768L);
        User good = userMapper.selectOne(queryWrapper);
        System.out.println(good.toString());
    }



    /**
     * 下面是公共表测试方法
     */
    @GetMapping("/addDict")
    public void addDict(){
        MyDict myDict = new MyDict();
        myDict.setDictName("已启用");
        myDict.setDictCode("1");
        myDictMapper.insert(myDict);
    }

    @GetMapping("/getDict")
    public void getDict(){
        QueryWrapper<MyDict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_id","655050541017071616");
        MyDict myDict = myDictMapper.selectOne(wrapper);
        System.out.println(myDict.toString());
    }

    @GetMapping("/delectDict")
    public void delectDict(){
        QueryWrapper<MyDict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_id","655050541017071616");
        myDictMapper.delete(wrapper);
    }

}
