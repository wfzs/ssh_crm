package cn.itcast.dao;

import cn.itcast.bean.User;

public interface UserDao {

	User getUserByUserCode(String code);

	void save(User user);
}
