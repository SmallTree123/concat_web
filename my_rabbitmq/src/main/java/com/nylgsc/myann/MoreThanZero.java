package com.nylgsc.myann;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 表示该注解仅作用在属性上
@Target(ElementType.FIELD)
// 运行时可以获取到该注解, 如果不标注则运行时无法获取到该注解
@Retention(RetentionPolicy.RUNTIME)
//注解是一个验证注解，并且指定了一个实现验证逻辑的验证器
@Constraint(validatedBy = AgeValidator.class)
public @interface MoreThanZero {

    String message() default "年龄必须大于 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
