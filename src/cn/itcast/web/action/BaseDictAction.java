package cn.itcast.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bean.BaseDict;
import cn.itcast.bean.Customer;
import cn.itcast.service.BaseDictService;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;
import net.sf.json.JSONArray;

@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport{

	private String dict_type_code;
	
	@Resource(name="baseDictService")
	private BaseDictService bService;
	@Override
	public String execute() throws Exception {
		//1 调用Service根据typecode获得数据字典list
		List<BaseDict> list = bService.getListByTypeCode(dict_type_code);
		//2 将list转化为json格式
		String string = JSONArray.fromObject(list).toString();
		//3 将json发送给浏览器
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(string);
		return null;
	}
	public String getDict_type_code() {
		return dict_type_code;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	public BaseDictService getbService() {
		return bService;
	}
	public void setbService(BaseDictService bService) {
		this.bService = bService;
	}		
}
