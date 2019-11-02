package cn.itcast.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bean.User;
import cn.itcast.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User u=new User();
	private UserService us;

	public void setUs(UserService us) {
		this.us = us;
	}
	public String login() throws Exception {
		User user = us.getUserByCodePassword(u);
		ActionContext.getContext().getSession().put("user", user);
		return "toHome";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return u;
	}
	
}
