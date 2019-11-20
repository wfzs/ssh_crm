package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bean.Customer;

public interface CustomerDao extends BaseDao<Customer> {

	//按照行业统计客户数量
	List<Object[]> getIndustryCount();
	//按照来源统计客户数量
	List<Object[]> getSourceCount();
}
