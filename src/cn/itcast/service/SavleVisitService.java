package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bean.SavleVisit;
import cn.itcast.utils.PageBean;

public interface SavleVisitService {

	void save(SavleVisit sv);

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	SavleVisit getById(String visit_id);

}
