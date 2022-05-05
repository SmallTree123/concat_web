package com.nylgsc.test;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import sun.applet.Main;

import java.util.Arrays;

public class testMain {
    public static void main(String[] args) {
        System.out.println(beforeFinally());
//        int a = 127;
//        Integer integer = new Integer(-128);
//        System.out.println(integer==a);

       Integer a =200;
       Integer b= 200;

        System.out.println(a==b);

    }

    /**
     * 当程序执行到try{}语句中的return方法时，它会干这么一件事，将要返回的结果存储到一个临时栈中，
     * 然后程序不会立即返回，而是去执行finally{}中的程序，
     * 在执行`a = 2`时，程序仅仅是覆盖了a的值，但不会去更新临时栈中的那个要返回的值 。
     * 执行完之后，就会通知主程序“finally的程序执行完毕，可以请求返回了”，
     * 这时，就会将临时栈中的值取出来返回。这下应该清楚了，要返回的值是保存至临时栈中的。
     * @return
     */
    public static int beforeFinally(){
        int a = 0;
        try{
            a = 1;
            return a;
        }finally{
            a = 2;
        }
    }
}
