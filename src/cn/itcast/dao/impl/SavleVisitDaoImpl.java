package cn.itcast.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.itcast.bean.SavleVisit;
import cn.itcast.dao.SavleVisitDao;

@Repository("savleVisitDao")
public class SavleVisitDaoImpl extends BaseDaoImpl<SavleVisit> implements SavleVisitDao{

	@Resource(name="sessionFactory")
	private void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}
}
