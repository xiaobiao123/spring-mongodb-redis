package com.cn.mongodb.example.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NameEntity")  
public class NameEntity {  
  
    private String username;  
  
    private String nickname;  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getNickname() {  
        return nickname;  
    }  
  
    public void setNickname(String nickname) {  
        this.nickname = nickname;  
    }  
  
}  