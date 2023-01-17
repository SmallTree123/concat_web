package com.nylgsc.anno;

import java.lang.reflect.Field;

public class SimpleAnnotationImpl {
    public static void annotationLogic(){
        Class useAnnotationClass = UseAnnotation.class;
        for(Field field : useAnnotationClass.getFields()) {
            SimpleAnnotation simpleAnnotation = field.getAnnotation(SimpleAnnotation.class);
            if(simpleAnnotation != null) {
                System.out.println(" Method Name : " + field.getName());
                System.out.println(" value : " + simpleAnnotation.value());
                System.out.println(" --------------------------- ");
            }
        }
    }
}
