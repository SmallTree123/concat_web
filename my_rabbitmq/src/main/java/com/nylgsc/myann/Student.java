package com.nylgsc.myann;

import lombok.Data;

@Data
public class Student {

    @MoreThanZero
    private Integer age;

    private String name;


}
