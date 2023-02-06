package com.nylgsc.controller;


import com.nylgsc.anno.EnumValue;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Component
public class UserParam {
    private String name;

    private String id;

    MultipartFile file;

    private List<String> list;

    @EnumValue(intValues = {0, 1}, message = "是否提醒只能是0或者1")
    private Integer age;

}
