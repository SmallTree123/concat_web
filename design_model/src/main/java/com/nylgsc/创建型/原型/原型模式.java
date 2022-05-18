package com.nylgsc.创建型.原型;

import java.util.Date;

public class 原型模式 {
    public static void main(String[] args) throws Exception {
        Date date = new Date();
        Video video = new Video("图灵学院",date);
        System.out.println(video.toString());

        Video clone = (Video) video.clone();
        //修改video中对象的属性值，看看克隆对象的值会不会变
        date.setTime(111111);
        System.out.println(clone.toString());
    }
}
