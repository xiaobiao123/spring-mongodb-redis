package com.cn.reflect;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestRe {

    public static void main(String[] args) {
        Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = getClassType(args[i]);
            System.out.println(getClassType(args[i]));
        }
    }

    private static Class<?> getClassType(Object obj) {
        Class<?> classType = obj.getClass();
        String   typeName  = classType.getName();
        switch (typeName) {
            case "java.lang.Integer":
                return Integer.TYPE;
            case "java.lang.Long":
                return Long.TYPE;
            case "java.lang.Float":
                return Float.TYPE;
            case "java.lang.Double":
                return Double.TYPE;
            case "java.lang.Character":
                return Character.TYPE;
            case "java.lang.Boolean":
                return Boolean.TYPE;
            case "java.lang.Short":
                return Short.TYPE;
            case "java.lang.Byte":
                return Byte.TYPE;
        }

        return classType;
    }

    @Test
    public void listByRecordIds() {

        PageRecordRequest request = new PageRecordRequest();
        getList(request, request.getClass());
    }


    public <T> List<T> getList(Object object, Class<T> clazz) {
        List<T>           list          = Lists.newArrayList();
        PageRecordRequest recordRequest = new PageRecordRequest();
        try {
            T       instance = clazz.newInstance();
            Field[] ids      = object.getClass().getDeclaredFields();

            for (Field field : ids) {
                field.setAccessible(true);
//                System.out.println(field.getType().getName());
                switch (field.getType().toString()) {
                    case "class java.lang.String":
                        field.set(instance, "this is name");
                        break;
                    case "class java.lang.Integer":
                        field.set(instance, 1);
                        break;
                    case "class java.util.Set":
                        field.set(instance, Sets.newHashSet(10L, 20L, 30L));
                        break;
                    default:
                        break;
                }
                // System.out.println(JSONObject.toJSONString(instance));
                recordRequest = (PageRecordRequest) instance;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        Method[] declaredMethods = recordRequest.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            try {
                if (method.getName().toString().contains("get")) {
                    System.out.println(method.invoke(recordRequest));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            // System.out.println(method.getName());
        }
//        System.out.println(clazz.getName());
        return list;
    }


}
