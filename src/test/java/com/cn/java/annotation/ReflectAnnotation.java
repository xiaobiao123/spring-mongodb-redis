package com.cn.java.annotation;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.common.recycler.Recycler;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@FieldTypeAnnotation(type = "class", hobby = {"smoke"})
public class ReflectAnnotation {

    @FieldTypeAnnotation(hobby = {"sleep", "play"})
    private String maomao;

    @FieldTypeAnnotation(hobby = {"phone", "buy"}, age = 27, type = "normal")
    private String zhangwenping;

    @MethodAnnotation()
    public void method1() {
        System.out.println("this is method 1");
    }

    @MethodAnnotation(desc = "method2")
    public void method2(String name) {
        System.out.println("this is method 2" + name);
    }

    Class<ReflectAnnotation> aClass = ReflectAnnotation.class;

    /**
     * 判断对象上是否有注解
     */
    @Test
    public void isAnnotationPresent() {
        boolean annotation = aClass.isAnnotationPresent(FieldTypeAnnotation.class);
        System.out.println(annotation);
        //对象是不是注解
        System.out.println(FieldTypeAnnotation.class.isAnnotation());
    }

    /**
     * 获取类上的注解
     */
    @Test
    public void getAnnotation() {
        FieldTypeAnnotation annotation = aClass.getAnnotation(FieldTypeAnnotation.class);
        int                 age        = annotation.age();
        String[]            hobby      = annotation.hobby();
        String              type       = annotation.type();
        System.out.println(String.format("age %s type %s hobby %s", age, type, hobby));
    }

    /**
     * 解析字段上是否有注解
     * ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的
     * getFields:则会返回包括父类的所有的public字段，和getMethods()一样
     */
    @Test
    public void getField() {
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(FieldTypeAnnotation.class);

            if (annotationPresent) {
                FieldTypeAnnotation annotation = field.getAnnotation(FieldTypeAnnotation.class);
                int                 age        = annotation.age();
                String[]            hobby      = annotation.hobby();
                String              type       = annotation.type();
            }
        }
        System.out.println(JSONObject.toJSONString(fields));
    }

    @Test
    public void getMethods() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            boolean methodHasAnno = method.isAnnotationPresent(MethodAnnotation.class);
            if (methodHasAnno) {
                //得到注解
                MethodAnnotation methodAnno = method.getAnnotation(MethodAnnotation.class);
                //输出注解属性
                String desc = methodAnno.desc();
                System.out.println(method.getName() + " desc = " + desc);

                Class<?>[] parameterTypes = method.getParameterTypes();
                String     names          = "";
                if (parameterTypes.length > 0) {
                    for (Class cl : parameterTypes) {
                        String name = cl.getName();
                        if (name.equals("java.lang.String")) {
                            names = "xxx";
                        }
                        System.out.println(name);
                    }
                }
                if (method.getName().equals("method2")) {
                    method.invoke(new ReflectAnnotation(), names);
                }

            }
        }

    }


    public static void main(String[] args) {
        // 此处要用反射将字段中的注解解析出来
        Class<ReflectAnnotation> clz = ReflectAnnotation.class;
        // 判断类上是否有次注解  
        boolean clzHasAnno = clz.isAnnotationPresent(FieldTypeAnnotation.class);
        if (clzHasAnno) {
            // 获取类上的注解  
            FieldTypeAnnotation annotation = clz.getAnnotation(FieldTypeAnnotation.class);
            // 输出注解上的属性  
            int      age   = annotation.age();
            String[] hobby = annotation.hobby();
            String   type  = annotation.type();
            System.out.println(clz.getName() + " age = " + age + ", hobby = " + Arrays.asList(hobby).toString() + " type = " + type);
        }
        // 解析字段上是否有注解  
        // ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的  
        // getFields:则会返回包括父类的所有的public字段，和getMethods()一样  
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            boolean fieldHasAnno = field.isAnnotationPresent(FieldTypeAnnotation.class);
            if (fieldHasAnno) {
                FieldTypeAnnotation fieldAnno = field.getAnnotation(FieldTypeAnnotation.class);
                //输出注解属性  
                int      age   = fieldAnno.age();
                String[] hobby = fieldAnno.hobby();
                String   type  = fieldAnno.type();
                System.out.println(field.getName() + " age = " + age + ", hobby = " + Arrays.asList(hobby).toString() + " type = " + type);
            }
        }
        //解析方法上的注解  
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            boolean methodHasAnno = method.isAnnotationPresent(MethodAnnotation.class);
            if (methodHasAnno) {
                //得到注解  
                MethodAnnotation methodAnno = method.getAnnotation(MethodAnnotation.class);
                //输出注解属性  
                String desc = methodAnno.desc();
                System.out.println(method.getName() + " desc = " + desc);
            }
        }
    }
}  