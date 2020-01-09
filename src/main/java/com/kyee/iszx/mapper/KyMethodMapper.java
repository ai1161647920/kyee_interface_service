package com.kyee.iszx.mapper;

import java.util.List;
import java.util.Map;

import com.kyee.iszx.bean.KyMethod;


public interface KyMethodMapper {
	List<KyMethod> findKyMethodAll();//查找所有方法
	List<KyMethod> findKyMethodByCondition(Map map);//根据条件查询
	List<KyMethod> findKyMethodByIds(List<String> id);//根据主键查询
}
