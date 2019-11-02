package cn.itcast.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bean.User;
import cn.itcast.dao.UserDao;
import cn.itcast.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

	@Resource(name="sessionFatory")
	private SessionFactory sf;
	@Test
	//测试单独的hibernate
	public void fun1(){
		Configuration configure = new Configuration().configure();
		SessionFactory sf=configure.buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tr = ss.beginTransaction();
		User u=new User();
		u.setUser_code("tom");
		u.setUser_name("汤姆");
		u.setUser_password("1234");
		ss.save(u);
		tr.commit();
		ss.close();
		sf.close();
	}
	@Test
	//测试spring容器管理的sessionFactory
	public void fun2(){
		Session ss=sf.openSession();
		Transaction tr = ss.beginTransaction();
		User u=new User();
		u.setUser_code("rtty");
		u.setUser_name("地方分");
		u.setUser_password("1234");
		ss.save(u);
		tr.commit();
		ss.close();
	}
	@Resource(name="userDao")
	private UserDao ud;
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	@Test
	//测试spring管理hibernate模板
	public void fun3(){
		User user = ud.getUserByUserCode("sdf");
		System.out.println(user);
	}
	@Resource(name="userService")
	private UserService us;
	@Test
	//测试aop事务
	public void fun4(){
		User user=new User();
		user.setUser_code("sfsd");
		user.setUser_name("地方是");
		user.setUser_password("2345");
		us.saveUser(user);
	}
}
