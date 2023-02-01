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
