package cn.itcast.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	//增或更新
	void saveOrUpdate(T t);
	//增
	void save(T t);
	//删
	void delete(T t);
	//删
	void delete(Serializable id);
	//改
	void update(T t);
	//查
	T getById(Serializable id);
	//查
	Integer getTotalCount(DetachedCriteria dc);
	//查
	List<T>  getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
}
