package cn.springmvc.collection.String类为什么是final的;

import java.util.*;
public class Student { 
        private String name; 
        private int age; 
        private static HashSet<Student> pool = new HashSet<Student>(); // 对象池 

        public Student(String name, int age) { 
                this.name = name; 
                this.age = age; 
        } 
        // 使用对象池来得到对象的方法 
        public static Student newInstance(String name, int age) { 
                for (Student stu : pool) { // 循环遍历对象池 
                        if (stu.name.equals(name) && stu.age == age) { 
                                return stu; 
                        } 
                } 
                // 如果找不到值相同的Student对象，则创建一个Student对象 
                //并把它加到对象池中然后返回该对象。 
                Student stu = new Student(name, age); 
                pool.add(stu); 
                return stu; 
        } 
} 

class Test { 
        public static void main(String[] args) { 
                Student stu1 = Student.newInstance("tangliang", 30); // 对象池中拿 
                Student stu2 = Student.newInstance("tangliang", 30); // 所以stu1==stu2 
                Student stu3 = new Student("tangliang", 30); // 重新创建，所以stu1!=stu3 
                System.out.println(stu1 == stu2); 
                System.out.println(stu1 == stu3); 
        } 
} 