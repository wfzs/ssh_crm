package cn.itcast.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.itcast.bean.Customer;
import cn.itcast.dao.CustomerDao;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	
	@Resource(name="sessionFactory")
	private void setSF(SessionFactory sf){
		super.setSessionFactory(sf);
	}

	@Override
	@SuppressWarnings("all")
	public List<Object[]> getIndustryCount() {
		// 原生SQL查询		
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			String sql="select dict_item_name,count(*) total "
					+ "from cst_customer c,base_dict bd "
					+ "where c.cust_industry = bd.dict_id "
					+ "group by c.cust_industry";
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

	@Override
	@SuppressWarnings("all")
	public List<Object[]> getSourceCount() {
		// 原生SQL查询		
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			String sql="select dict_item_name,count(*) total "
					+ "from cst_customer c,base_dict bd "
					+ "where c.cust_source = bd.dict_id "
					+ "group by c.cust_source";
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

}
