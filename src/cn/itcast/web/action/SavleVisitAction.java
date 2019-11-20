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
import cn.itcast.bean.SavleVisit;
import cn.itcast.bean.User;
import cn.itcast.service.CustomerService;
import cn.itcast.service.SavleVisitService;
import cn.itcast.utils.PageBean;

@Controller("savleVisitAction")
@Scope("prototype")
public class SavleVisitAction extends ActionSupport implements ModelDriven<SavleVisit>{

	private SavleVisit sv=new SavleVisit();
	@Resource(name="savleVisitService")
	private SavleVisitService svs;		

	private Integer currentPage;
	private Integer pageSize;
			
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
	public String list() throws Exception {
		//封装离线对象
		DetachedCriteria dc=DetachedCriteria.forClass(SavleVisit.class);
		if(sv.getCustomer()!=null&&sv.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id",sv.getCustomer().getCust_id()));
		}
		//1 调用Service查询分页数据
		PageBean pb=svs.getPageBean(dc,currentPage,pageSize);
		//2 将数据返回页面
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	public String add() throws Exception {
		User user=(User) ActionContext.getContext().getSession().get("user");
		sv.setUser(user);
		svs.save(sv);
		return "tolist";
	}
	public String toEdit() throws Exception {		
		SavleVisit sVisit=svs.getById(sv.getVisit_id());
		ActionContext.getContext().put("saleVisit", sVisit);
		return "edit";
	}
	@Override
	public SavleVisit getModel() {
		// TODO Auto-generated method stub
		return sv;
	}
	public void setSvs(SavleVisitService svs) {
		this.svs = svs;
	}	
}
