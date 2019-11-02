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
	//���Ե�����hibernate
	public void fun1(){
		Configuration configure = new Configuration().configure();
		SessionFactory sf=configure.buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tr = ss.beginTransaction();
		User u=new User();
		u.setUser_code("tom");
		u.setUser_name("��ķ");
		u.setUser_password("1234");
		ss.save(u);
		tr.commit();
		ss.close();
		sf.close();
	}
	@Test
	//����spring���������sessionFactory
	public void fun2(){
		Session ss=sf.openSession();
		Transaction tr = ss.beginTransaction();
		User u=new User();
		u.setUser_code("rtty");
		u.setUser_name("�ط���");
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
	//����spring����hibernateģ��
	public void fun3(){
		User user = ud.getUserByUserCode("sdf");
		System.out.println(user);
	}
	@Resource(name="userService")
	private UserService us;
	@Test
	//����aop����
	public void fun4(){
		User user=new User();
		user.setUser_code("sfsd");
		user.setUser_name("�ط���");
		user.setUser_password("2345");
		us.saveUser(user);
	}
}
