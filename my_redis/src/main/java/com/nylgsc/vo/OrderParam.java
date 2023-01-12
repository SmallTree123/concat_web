package com.nylgsc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderParam implements Serializable {

    private Boolean isDim;
    private Integer index;
    private String orderType;
}
