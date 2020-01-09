package com.kyee.iszx.service;

import java.util.List;
import java.util.Map;

import com.kyee.iszx.util.log.LogService;

public interface IAppSettingService  extends LogService{

	List findAppSettingByCondition(Map appFind);

	boolean saveAppParam(List list);

	boolean saveAppParam(String paramData);

	List findAppSettingByCondition(String appid);

	String getConfig(String code, String appid);

	String getConfig(String code, List list);

}
