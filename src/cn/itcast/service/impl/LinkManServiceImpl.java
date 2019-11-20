package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bean.Customer;
import cn.itcast.bean.LinkMan;
import cn.itcast.dao.LinkManDao;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

@Service("linkManService")
public class LinkManServiceImpl implements LinkManService {

	@Resource(name="linkManDao")
	private LinkManDao lmd;
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		Integer totalcount=lmd.getTotalCount(dc);
		PageBean pb=new PageBean(currentPage, totalcount, pageSize);
		List<LinkMan> list=lmd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		return pb;
	}
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(LinkMan linkMan) {
		// TODO Auto-generated method stub
		lmd.saveOrUpdate(linkMan);
	}
	
	@Override
	public LinkMan getById(Long lkm_id) {		
		return lmd.getById(lkm_id);
	}

	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}
}
