package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bean.Customer;
import cn.itcast.bean.LinkMan;
import cn.itcast.utils.PageBean;

public interface LinkManService {

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	void save(LinkMan linkMan);
	LinkMan getById(Long lkm_id);

}
