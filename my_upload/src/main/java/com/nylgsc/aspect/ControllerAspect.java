package com.nylgsc.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component//定义一个切面
public class ControllerAspect {


    /**
     * execution(): 表达式主体。
     * 第一个*号：表示返回类型， *号表示所有的类型。
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
     * 第二个*号：表示类名，*号表示所有的类。
     * *(…):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut(value = "execution(* *.*..controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        Signature signature = joinPoint.getSignature();
        System.out.println("进入到Aop的前置方法中......");
        // 记录下请求内容
        log.info(" request --- " +
                signature.getDeclaringTypeName() + "." + signature.getName() +
                " : " + new Gson().toJson(joinPoint.getArgs()));
    }

}
