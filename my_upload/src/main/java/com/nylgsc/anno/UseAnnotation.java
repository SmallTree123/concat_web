package com.nylgsc.anno;

import org.springframework.stereotype.Component;

@Component
public class UseAnnotation {

    @SimpleAnnotation("testSimpleAnnotation")
    public String testMethod;

}
