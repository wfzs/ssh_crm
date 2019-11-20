package cn.itcast.web.action;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bean.Customer;
import cn.itcast.bean.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	private LinkMan linkMan=new LinkMan();
	@Resource(name="linkManService")
	private LinkManService lms;
	
	private Integer currentPage;
	private Integer pageSize;
				
	
	public String list() throws Exception {		
		//封装离线对象
		DetachedCriteria dc=DetachedCriteria.forClass(LinkMan.class);
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		//1 调用Service查询分页数据
		PageBean pb=lms.getPageBean(dc,currentPage,pageSize);
		//2 将数据返回页面
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	public String add() throws Exception {
		lms.save(linkMan);
		return "tolist";
	}
	public String toEdit() throws Exception {
		LinkMan lMan=lms.getById(linkMan.getLkm_id());
		ActionContext.getContext().put("linkMan", lMan);
		return "edit";
	}
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	public void setLms(LinkManService lms) {
		this.lms = lms;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}	
}
