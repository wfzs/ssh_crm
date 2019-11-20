package cn.itcast.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bean.User;
import cn.itcast.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User u=new User();
	@Resource(name="userService")
	private UserService us;

	public void setUs(UserService us) {
		this.us = us;
	}
	public String login() throws Exception {
		User user = us.getUserByCodePassword(u);
		ActionContext.getContext().getSession().put("user", user);
		return "toHome";
	}
	public String regist() throws Exception {
		try {
			us.saveUser(u);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("error", e.getMessage());
			return "regist";
		}		
		return "toLogin";
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return u;
	}
	
}
