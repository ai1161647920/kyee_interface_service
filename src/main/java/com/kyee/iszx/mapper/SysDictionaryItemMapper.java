package com.kyee.iszx.mapper;

import java.util.List;
import java.util.Map;


public interface SysDictionaryItemMapper {
	List<SysDictionaryItemMapper> findSysDictionaryItemByCondition(Map map);//根据条件查询字典
}
