package cn.itcast.service;

import cn.itcast.bean.User;

public interface UserService {

	User getUserByCodePassword(User u);
	
	void saveUser(User user);
}
