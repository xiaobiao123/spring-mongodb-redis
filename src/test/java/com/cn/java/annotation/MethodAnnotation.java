package com.cn.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
@Documented
@Inherited  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD) //次注解只能作用于方法上  
public @interface MethodAnnotation {  
  
    String desc() default "method1";  
}  