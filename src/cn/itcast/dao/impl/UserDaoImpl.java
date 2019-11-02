package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.bean.User;
import cn.itcast.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	
	@Override
	public User getUserByUserCode(String code) {
		//HQL
		return getHibernateTemplate().execute(new HibernateCallback<User>() {

			@Override
			public User doInHibernate(Session session) throws HibernateException {
				String sql="from User where user_code = ? ";
				Query query = session.createQuery(sql);
				query.setParameter(0, code);
				User user = (User) query.uniqueResult();
				return user;
			}
		});
		//Criteria
		/*DetachedCriteria dc=DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code", code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}*/
	}

	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
		
	}

}
