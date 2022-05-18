package com.nylgsc.创建型.原型;

import lombok.Data;

import java.util.Date;

@Data
public class Video implements Cloneable {
    private String name;
    private Date createTime;

    public Video(String name,Date date){
        this.name = name;
        this.createTime = date;
    }

    /**
     * 这里将对象的属性也进行克隆，实现了深clone
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Video clone = (Video) super.clone();
        clone.createTime= (Date) this.createTime.clone();
        return clone;
    }
}
