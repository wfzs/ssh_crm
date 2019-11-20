package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bean.Customer;
import cn.itcast.dao.CustomerDao;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource(name="customerDao")
	private CustomerDao cd;
	
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		Integer totalcount=cd.getTotalCount(dc);
		PageBean pb=new PageBean(currentPage, totalcount, pageSize);
		List<Customer> list=cd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		return pb;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(Customer c) {
		//维护Customer与数据字典 对象的关系
		//调用Dao保存数据
		cd.saveOrUpdate(c);
	}

	@Override
	public Customer getById(Long cust_id) {		
		return cd.getById(cust_id);
	}

	@Override
	public List<Object[]> getIndustryCount() {
		return cd.getIndustryCount();
	}

	@Override
	public List<Object[]> getSourceCount() {
		return cd.getSourceCount();
	}

}
