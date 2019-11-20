package cn.itcast.web.action;

import java.io.File;
import java.util.List;

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
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer c=new Customer();
	@Resource(name="customerService")
	private CustomerService cs;
	
	private File photo;
	private String photoFileName;
	private String photoContentType;

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
	public void setCs(CustomerService cs) {
		this.cs = cs;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public String list() throws Exception {
		//封装离线对象
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(c.getCust_name())){
			dc.add(Restrictions.like("cust_name", "%"+c.getCust_name()+"%"));
		}
		//1 调用Service查询分页数据
		PageBean pb=cs.getPageBean(dc,currentPage,pageSize);
		//2 将数据返回页面
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	public String add() throws Exception {
		if(photo!=null){
			System.out.println("文件名："+photoFileName);
			System.out.println("文件类型："+photoContentType);
			photo.renameTo(new File("E:/upload/123.jpg"));
		}		
		//--------------------------------------
		cs.save(c);
		return "tolist";
	}
	public String toEdit() throws Exception {
		Customer customer=cs.getById(c.getCust_id());
		ActionContext.getContext().put("customer", customer);
		return "edit";
	}
	public String industryCount() throws Exception {
		List<Object[]> list = cs.getIndustryCount();
		ActionContext.getContext().put("list", list);
		return "industryCount";
	}
	public String sourceCount() throws Exception {
		List<Object[]> list = cs.getSourceCount();
		ActionContext.getContext().put("list", list);
		return "sourceCount";
	}
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return c;
	}
	
}
