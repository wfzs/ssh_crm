package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.bean.BaseDict;
import cn.itcast.dao.BaseDictDao;
import cn.itcast.service.BaseDictService;

@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {

	@Resource(name="baseDictDao")
	private BaseDictDao bdd;
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		// TODO Auto-generated method stub
		return bdd.getListByTypeCode(dict_type_code);
	}
	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}

}
