package com.cn.mongodb;

import java.util.List;

import com.cn.mongodb.example.data.UserDao;
import com.cn.mongodb.example.data.impl.UserDaoImpl;
import com.cn.mongodb.example.data.model.Message;

public class MongodbPage {
      
    public static void main(String[] args) {  
        UserDao userDao = new UserDaoImpl();  
        List<Message> largePageList = userDao.largePageList(1,5,0);//第一页  
        print(largePageList);  
        System.out.println("============");  
        List<Message> largePageList2 = userDao.largePageList(2,5,5);//第二页   需要记录上一页的最大id  
        print(largePageList2);  
        System.out.println("============");  
        List<Message> largePageList3 = userDao.largePageList(3,5,11);//第三页   需要记录上一页的最大id  
        print(largePageList3);  
    }  
      
    public static void print(List<Message> largePageList){  
        for(Message user : largePageList){  
            System.out.println(user);  
        }  
    }  
}
