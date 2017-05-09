package cn.springmvc.model;

import com.mongodb.DBObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 
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

}
