package cn.itcast.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bean.User;
import cn.itcast.dao.UserDao;
import cn.itcast.service.UserService;
import cn.itcast.utils.MD5Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao ud;
	@Override
	public User getUserByCodePassword(User u) {
		User excitU = ud.getUserByUserCode(u.getUser_code());
		if(excitU==null){
			throw new RuntimeException("用户名不存在！");
		}
		if(!excitU.getUser_password().equals(MD5Utils.md5(u.getUser_password()))){
			throw new RuntimeException("密码错误！");
		}
		return excitU;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(User user) {
		User exitU=ud.getUserByUserCode(user.getUser_code());
		if(exitU!=null){
			throw new RuntimeException("用户名已存在");
		}
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		ud.save(user);
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

}
