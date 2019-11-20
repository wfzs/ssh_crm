package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bean.Customer;
import cn.itcast.bean.SavleVisit;
import cn.itcast.dao.SavleVisitDao;
import cn.itcast.service.SavleVisitService;
import cn.itcast.utils.PageBean;

@Service("savleVisitService")
public class SavleVisitServiceImpl implements SavleVisitService{

	@Resource(name="savleVisitDao")
	private SavleVisitDao svd;
	
	public void setSvd(SavleVisitDao svd) {
		this.svd = svd;
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(SavleVisit sv) {
		svd.saveOrUpdate(sv);
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		Integer totalcount=svd.getTotalCount(dc);
		PageBean pb=new PageBean(currentPage, totalcount, pageSize);
		List<SavleVisit> list=svd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		return pb;
	}

	@Override
	public SavleVisit getById(String visit_id) {		
		return svd.getById(visit_id);
	}

}
