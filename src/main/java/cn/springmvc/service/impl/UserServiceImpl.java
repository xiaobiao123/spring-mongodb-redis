package cn.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.UserDAO;
import cn.springmvc.model.User;
import cn.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	private String string;


	public int insertUser(User user) {
		return userDAO.insertUser(user);
	}

	@Override
	public  void updateUser(Long i) {
		 userDAO.updateUser(i);
//		logger.error("AtomicInteger测试:"+integer.get());
//		integer.addAndGet(1);
	}

	@Override
	public User getUser( String nickName) {
		return userDAO.getUser(nickName);
	}

}
