package com.nylgsc.activeAnnotation;

import lombok.Data;

@Data
public class ValidIDVo {

    @ValidID(message = "ID格式不规范")
    private String id;
}
