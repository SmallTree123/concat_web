package com.nylgsc.SPI;

import java.util.List;

public class FileSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("通过文件搜索器，FileSearch："+keyword);
        return null;
    }
}
