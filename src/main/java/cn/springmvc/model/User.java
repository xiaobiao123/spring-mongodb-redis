package cn.springmvc.model;

import com.google.common.hash.HashCode;
import com.mongodb.DBObject;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * 用户表
 */
@Data
public class User implements Serializable {

    private int id;

    private int state;

    private String nickname;

    private String name;

    private int age;

    private String password;

    private String mark;


    public User() {

    }

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public int getState() {

        return state;

    }

    public void setState(int state) {

        this.state = state;

    }

    public String getNickname() {

        return nickname;

    }

    public void setNickname(String nickname) {

        this.nickname = nickname;

    }

    public void parse(DBObject next) {
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User p = (User) obj;
        //认为名字相同并且年龄一样大的两个对象是一个
        return this.name.equals(p.name) && this.age == p.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        return this.name + "::" + this.age;
    }

    public static void main(String[] args) {

        User p1 = new User("Jack", 12);
        System.out.println(p1.hashCode());

        HashMap<User, Integer> hashMap = new HashMap<User, Integer>();
        hashMap.put(p1, 1);

        System.out.println(hashMap.get(new User("Jack", 12)));
    }
}
