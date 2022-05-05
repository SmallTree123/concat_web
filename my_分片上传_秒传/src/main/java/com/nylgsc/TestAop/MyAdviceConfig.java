package com.nylgsc.TestAop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


// 表示当前的类是一个配置类
@Configuration
//该注解只能用在类上，作用：代表当前类是一个切面类，等价于spring.xml中的<aop:config>标签
//所以现在有了<aop:config>切面，还需要  通知 + 切入点
// 切面 == 通知 +　切面
@Aspect
public class MyAdviceConfig {

    /**
     * @param joinPoint
     * @Before：前置通知
     * value：切入点表达式  二者加起来构建成为一个切面
     * JoinPoint：连接点：可以理解为两个圆形的切点，从这个切点就可以获取到当前执行的目标类及方法
     * 前置通知和后置通知的参数的都是 JoinPoint， 前置后置通知都没有返回值
     */
    // 方法级别：具体到某个具体的方法
    // @Before(value = "execution(* com.liu.aop.service.impl.*.*(..))")
    // 表示service包下的所有类所有方法都执行该前置通知
    @Before(value = "within(com.nylgsc.TestAop.*)")
    public void before(JoinPoint joinPoint) {
        System.out.println("before开始执行查询.......");
        System.out.println("正在执行的目标类是: " + joinPoint.getTarget());
        System.out.println("正在执行的目标方法是: " + joinPoint.getSignature().getName());
    }

    /**
     * 后置通知，属性参数同上面的前置通知
     * @param joinPoint 前置通知和后置通知独有的参数
     */
    @After(value = "execution(* com.nylgsc.TestAop.*.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("after查询结束.......");
        // 获取执行目标类和方法名等等
    }

    /**
     * @param proceedingJoinPoint 环绕通知的正在执行中的连接点（这是环绕通知独有的参数）
     * @return 目标方法执行的返回值
     * @Around: 环绕通知，有返回值，环绕通知必须进行放行方法（就相当于拦截器），否则目标方法无法执行
     */
    @Around(value = "execution(* com.nylgsc.TestAop.*.*(..))")
    public Object aroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroud环绕通知开始.......");
        System.out.println("执行的目标类 = " + proceedingJoinPoint.getTarget());
        System.out.println("执行的目标方法 = " + proceedingJoinPoint.getSignature().getName());
        // 必须方法目标方法
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("aroud环绕通知结束.......");
        // 将目标方法的返回值进行返回，否则调用目标方法的方法无法获取到返回值
        return proceed;
    }
}
