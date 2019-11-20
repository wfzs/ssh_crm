package cn.itcast.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.itcast.bean.LinkMan;
import cn.itcast.dao.LinkManDao;

@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {	
	
	@Resource(name="sessionFactory")
	private void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}

}
