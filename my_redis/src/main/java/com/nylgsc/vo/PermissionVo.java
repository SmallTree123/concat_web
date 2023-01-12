package com.nylgsc.vo;

import lombok.Data;

import java.util.List;

@Data
public class PermissionVo {
    private String key;
    private String name;
    private List<ActionVo> actions;
}
