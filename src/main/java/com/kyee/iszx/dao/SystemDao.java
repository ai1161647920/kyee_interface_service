package com.kyee.iszx.dao;

import java.util.List;
import java.util.Map;

public interface SystemDao {
	<T> T findAll(int type);//查找所有
	<T> T findByCondition(int type,Map map);//通过条件查询
	<T> T findByIds(int type,List<String> id);//通过主键查询
	int updateDatas(int type, int flag, List list);//批量更新数据
	
	
	
}
