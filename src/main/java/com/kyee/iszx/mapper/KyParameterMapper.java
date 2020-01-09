package com.kyee.iszx.mapper;

import java.util.List;
import java.util.Map;

import com.kyee.iszx.bean.KyMethod;
import com.kyee.iszx.bean.KyParameter;

public interface KyParameterMapper {
	List<KyParameter> findKyParameterAll();//查找所有参数
	List<KyParameter> findKyParameterByCondition(Map map);//根据条件查询
	List<KyParameter> findKyParameterByIds(List<String> id);//根据主键查询
	int updateKyParameters(List list);//批量更新数据
	int updateKyParametersValue(List list);//批量更新数据
	

}
