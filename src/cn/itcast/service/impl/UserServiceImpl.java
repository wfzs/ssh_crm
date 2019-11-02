package cn.itcast.service.impl;

import cn.itcast.bean.User;
import cn.itcast.dao.UserDao;
import cn.itcast.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao ud;
	@Override
	public User getUserByCodePassword(User u) {
		User excitU = ud.getUserByUserCode(u.getUser_code());
		if(excitU==null){
			throw new RuntimeException("用户名不存在！");
		}
		if(!excitU.getUser_password().equals(u.getUser_password())){
			throw new RuntimeException("密码错误！");
		}
		return excitU;
	}

	@Override
	public void saveUser(User user) {
		ud.save(user);
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

}
