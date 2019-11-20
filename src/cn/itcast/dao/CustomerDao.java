package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bean.Customer;

public interface CustomerDao extends BaseDao<Customer> {

	//������ҵͳ�ƿͻ�����
	List<Object[]> getIndustryCount();
	//������Դͳ�ƿͻ�����
	List<Object[]> getSourceCount();
}
