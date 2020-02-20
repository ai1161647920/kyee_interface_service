package com.kyee.iszx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kyee.iszx.bean.KyAppSetting;
import com.kyee.iszx.dao.SystemDao;
import com.kyee.iszx.service.IAppSettingService;
import com.kyee.iszx.util.string.StringUtil;


@Service("appSettingService")
public class AppSettingService implements IAppSettingService{
	
	Gson gson = new GsonBuilder().serializeNulls().create();
	
	@Autowired
	private SystemDao systemDao;
	
	@Override
	public List findAppSettingByCondition(Map appFind) {
		return systemDao.findByCondition(7,appFind);
	}
	
	@Override
	public List findAppSettingByCondition(String appid) {
		Map appFind = new HashMap();
		appFind.put("appId", appid);
		List<KyAppSetting> appSetting = findAppSettingByCondition(appFind);
		return systemDao.findByCondition(7,appFind);
	}
	
	@Override
	public boolean saveAppParam(String paramData) {
		paramData = StringUtil.deleteSpace(paramData);
		Map<String, Object> paramDataMaps = JSON.parseObject(paramData);  
		List<KyAppSetting> params = new ArrayList<KyAppSetting>();
        for(String paramDataKey :paramDataMaps.keySet()){
        	if(StringUtil.isNotEmpty((String) paramDataMaps.get(paramDataKey))) {
        		KyAppSetting appSetting= new KyAppSetting();
        		Integer id = Integer.parseInt(paramDataKey);
            	appSetting.setId(id);
            	appSetting.setParamValue((String) paramDataMaps.get(paramDataKey));
            	params.add(appSetting);
        	}
        }
		return saveAppParam(params);
	}
	
	@Override
	public boolean saveAppParam(List list) {
		try {
			for (int i = 0; i < list.size(); i++) {
				KyAppSetting appset = (KyAppSetting) list.get(i);
				if (StringUtil.isEmpty(appset.getParamValue())) {
					list.remove(i);
					i--;
					continue;
				}
			}
			// 更新value值
			systemDao.updateDatas(7, 0, list);
			return true;
		}catch(Exception e){
			logger.error("保存应用全局参数出错：" + e);
			return false;
		}
	}
	
	@Override
	public String getConfig(String code,String appid) {
		List<KyAppSetting> appSetting = findAppSettingByCondition(appid);
		for(KyAppSetting setting:appSetting) {
			if(code.equals(setting.getParamCode())) {
				return setting.getParamValue();
			}
		}
		return "";
	}
	
	@Override
	public String getConfig(String code,List list) {
		List<KyAppSetting> appSetting = list;
		for(KyAppSetting setting:appSetting) {
			if(code.equals(setting.getParamCode())) {
				return setting.getParamValue();
			}
		}
		return "";
	}
}
