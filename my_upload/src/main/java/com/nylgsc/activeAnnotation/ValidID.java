package com.nylgsc.activeAnnotation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Documented -注解是否将包含在JavaDoc中
 * @Retention  -什么时候使用该注解(生命周期)
 * 	RetentionPolicy.SOURCE: 在编译阶段丢弃，这些注解在编译结束之后就不再有任何意义，所以不会写入到字节码中
 * 	RetentionPolicy.CLASS: 在类加载的时候丢弃，在字节码文件的处理中有用，注解默认使用方式
 * 	RetentionPolicy.RUNTIME: 始终不会丢弃，运行器也保留该注解，因此可以使用反射机制读取该注解的信息，自定义注解通常使用这个方式。
 * @Target     -注解用于什么地方
 * 	ElementType.CONSTRUCTOR: 用于描述构造器
 * 	ElementType.FIELD: 成员变量，对象，属性（包括enum实例）
 * 	ElementType.LOCAL_VARIABLE: 用于描述局部变量
 * 	ElementType.METHOD:用于描述方法
 * 	ElementType.PACKAGE: 用于描述包
 * 	ElementType.PARAMETER: 用于描述参数
 * 	ElementType.TYPE: 用于描述类，接口或enum声明
 * @Inherited  -是否允许子类继承该注解
 */


@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidIDImpl.class)
public @interface ValidID {

    String message();
}
