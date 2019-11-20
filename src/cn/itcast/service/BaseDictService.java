package cn.itcast.service;

import java.util.List;

import cn.itcast.bean.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
