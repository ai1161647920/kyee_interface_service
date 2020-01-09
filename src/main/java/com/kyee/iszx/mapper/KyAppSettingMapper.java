package com.kyee.iszx.mapper;

import java.util.List;
import java.util.Map;

import com.kyee.iszx.bean.KyAppSetting;
import com.kyee.iszx.bean.KyParameter;

public interface KyAppSettingMapper {
	List<KyAppSetting> findKyAppSettingByCondition(Map map);//根据条件查询
	
	int updateKyAppSettingsValue (List list);//更新数据
}
