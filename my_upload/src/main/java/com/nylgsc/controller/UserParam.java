package com.nylgsc.controller;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UserParam {
    private String name;
    private String id;
    MultipartFile file;
    private List<String> list;
}
