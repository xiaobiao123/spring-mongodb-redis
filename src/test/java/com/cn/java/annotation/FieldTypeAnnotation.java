package com.cn.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.Authenticator.RequestorType;

/**
 * 自定义注解
 *
 * @Documented:指明该注解可以用于生成doc  'dɒkjʊm(ə)ntɪd  备有证明文件的
 * @Inherited：该注解可以被自动继承        ɪn'hɛrɪtid  继承
 * @Retention:指明在什么级别显示该注解：    rɪ'tɛnʃən  保留；扣留，滞留；记忆力；闭尿
 * RetentionPolicy.SOURCE 注解存在于源代码中，编译时会被抛弃    'pɑləsi  政策，方针；保险单
 * RetentionPolicy.CLASS 注解会被编译到class文件中，但是JVM会忽略
 * RetentionPolicy.RUNTIME JVM会读取注解，同时会保存到class文件中
 *
 * @Target:指明该注解可以注解的程序范围
 *
 * ElementType.TYPE 用于类，接口，枚举但不能是注解
 * ElementType.FIELD 作用于字段，包含枚举值
 * ElementType.METHOD 作用于方法，不包含构造方法
 * ElementType.PARAMETER 作用于方法的参数
 * ElementType.CONSTRUCTOR 作用于构造方法
 * ElementType.LOCAL_VERIABLE 作用于本地变量或者catch语句
 * ElementType.ANNOTATION_TYPE 作用于注解
 * ElementType.PACKAGE 作用于包
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})//次注解作用于类和字段上  
public @interface FieldTypeAnnotation {

    String type() default "ignore";

    int age() default 27;

    String[] hobby(); //没有指定defalut的，需要在注解的时候显式指明  
}  