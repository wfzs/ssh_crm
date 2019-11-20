package cn.itcast.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.bean.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//��У���¼��ע��
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//��ȡsession
		Map<String, Object> session = ActionContext.getContext().getSession();
		//��õ�¼��ʶ
		User user = (User) session.get("user");
		//�ж��Ƿ����
		if(user!=null){
			return invocation.invoke();
		}else{
			return "toLogin";
		}
	}

}
