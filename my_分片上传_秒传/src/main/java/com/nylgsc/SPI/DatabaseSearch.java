package com.nylgsc.SPI;

import java.util.List;

public class DatabaseSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("通过数据库搜索，DatabaseSearch："+keyword);
        return null;
    }
}
