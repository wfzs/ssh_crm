package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bean.Customer;
import cn.itcast.utils.PageBean;

public interface CustomerService {

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	void save(Customer c);

	Customer getById(Long cust_id);

	List<Object[]> getIndustryCount();
	
	List<Object[]> getSourceCount();
}
