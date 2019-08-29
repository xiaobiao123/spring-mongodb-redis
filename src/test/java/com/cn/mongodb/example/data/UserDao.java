package com.cn.mongodb.example.data;
  
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cn.mongodb.example.data.model.Message;
import com.cn.mongodb.example.data.model.NameEntity;
import com.cn.mongodb.example.data.model.UserEntity;
  
@Transactional  
public interface UserDao {  
  
    public abstract void _test();  
  
    public abstract void createCollection();  
  
    public abstract List<UserEntity> findList(int skip, int limit);  
  
    public abstract List<UserEntity> findListByAge(int age);

    public abstract List<UserEntity> findListByAge2(int age);

    public abstract UserEntity findOne(String id);  
  
    public abstract UserEntity findOneByUsername(String username);  
  
    public abstract void insert(UserEntity entity);  
  
    public abstract int update(UserEntity entity);

	public abstract void insertNameEntity(NameEntity entity);

	void remove(UserEntity entity);

	public abstract List<UserEntity> findListByAgeSortByBirth(int i);

	public abstract List<UserEntity> findPage(int i);

	public abstract List<Message> largePageList(int page, int pageSize, int lastId);

    public abstract Long count();

    public abstract List<UserEntity> findListGtAge(int age);

	public abstract void aggregate();

	void distinc();  
  
}  