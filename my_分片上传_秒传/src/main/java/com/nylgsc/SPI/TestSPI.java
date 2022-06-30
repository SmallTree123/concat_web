package com.nylgsc.SPI;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();
        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
    }
}
