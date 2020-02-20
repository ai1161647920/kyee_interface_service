package com.kyee.iszx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kyee.iszx.base.Result;
import com.kyee.iszx.base.Results;
import com.kyee.iszx.bean.KyParameter;
import com.kyee.iszx.dao.SystemDao;
import com.kyee.iszx.service.IParameterService;
import com.kyee.iszx.util.string.StringUtil;

import net.sf.json.JSONArray;

@Service("parameterService")
public class ParameterService implements IParameterService {

	@Autowired
	private SystemDao systemDao;
	
	Gson gson = new GsonBuilder().serializeNulls().create();
	
	@Override
	public Result getParameterIn(String metid,String metAffectContent) {
		String ret = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("methodId", metid);
		map.put("attribute", "0");
		List<KyParameter> params = systemDao.findByCondition(3, map);
		// 取影响入参参数的值,目前默认只有一个影响因素，多个影响时按需求更改
		String metAffectContentValue = "";
		if (StringUtil.isNotEmpty(metAffectContent)) {
			for (KyParameter paramt : params) {
				if (paramt.getNameEn().equals(metAffectContent)) {
					metAffectContentValue = paramt.getValue();
					break;
				}
			}
		}
		paramDeal(params , metAffectContentValue);

		JSONArray obj = JSONArray.fromObject(params);
		// System.out.println(obj.toString());
//		String test = "[{\"affectContent\":\"\",\"attribute\":0,\"description\":\"\",\"hide\":true,\"id\":0,\"mark\":0,\"methodId\":0,\"nameCn\":\"测试\",\"nameEn\":\"\",\"otherClaim\":\"\",\"type\":0,\"value\":\"\",\"valueItem\":0},{\"affectContent\":\"\",\"attribute\":0,\"description\":\"\",\"hide\":true,\"id\":0,\"mark\":0,\"methodId\":0,\"nameCn\":\"测试1\",\"nameEn\":\"\",\"otherClaim\":\"\",\"type\":0,\"value\":\"\",\"valueItem\":0},{\"affectContent\":\"\",\"attribute\":0,\"description\":\"\",\"hide\":false,\"id\":0,\"mark\":0,\"methodId\":0,\"nameCn\":\"测试2\",\"nameEn\":\"\",\"otherClaim\":\"\",\"type\":0,\"value\":\"\",\"valueItem\":0},{\"affectContent\":\"\",\"attribute\":0,\"description\":\"\",\"hide\":false,\"id\":0,\"mark\":0,\"methodId\":0,\"nameCn\":\"测试3\",\"nameEn\":\"\",\"otherClaim\":\"\",\"type\":0,\"value\":\"\",\"valueItem\":0}]";
//		if ("1".equals(metid)) {
//			ret = "{\"code\":0,\"msg\":\"\",\"count\":" + params.size() + ",\"data\":" + obj.toString() + "}";
//		} else {
//			ret = "{\"code\":0,\"msg\":\"\",\"count\":100,\"data\":" + test + "}";
//		}
		
		return Results.newSuccessResult(obj,params.size());
	}

	@Override
	public Result upDateParameterIn(String metAffectValue, String tableData) {
		//更新参数信息
		tableData = StringUtil.deleteSpace(tableData);
		String ret = "";
		List<KyParameter> params = gson.fromJson(tableData, new TypeToken<List<KyParameter>>() {
		}.getType());
		paramDeal(params , metAffectValue);
		JSONArray obj = JSONArray.fromObject(params);
		ret = "{\"code\":0,\"msg\":\"\",\"count\":" + params.size() + ",\"data\":" + obj.toString() + "}";
		return Results.newSuccessResult(obj,params.size());
	}
	
	@Override
	public Boolean saveParam(String tableData) {
		tableData = StringUtil.deleteSpace(tableData);
		List<KyParameter> params = gson.fromJson(tableData, new TypeToken<List<KyParameter>>() {}.getType());
		return saveParam(params);
	}
	
	@Override
	public boolean saveParam(List<KyParameter> params) {
		try {
			for (int i = 0; i < params.size(); i++) {
				KyParameter param = params.get(i);
				if (param.getHide()) {
					params.remove(i);
					i--;
					continue;
				}
			}
			// 更新value值
			systemDao.updateDatas(3, 0, params);
			return true;
		}catch(Exception e){
			logger.error("保存参数值出错：" + e);
			return false;
		}
	}
	
	public static void paramDeal(List<KyParameter> params ,String metAffectContentValue) {
		if(params.size() == 0 || StringUtil.isEmpty(metAffectContentValue)) {
			return;
		}
		for (KyParameter param : params) {
			if(StringUtil.isNotEmpty(metAffectContentValue)) {
				if(StringUtil.isNotEmpty(param.getAffectContent())) {
					int flag = param.getMark();
					if (StringUtil.contains(param.getAffectContent(), metAffectContentValue, ",")) {
						param.setHide(false);
						if(flag < 3) {
							flag = flag + 3;
							param.setMark(flag);
						}
					} else if(Pattern.matches(param.getAffectContent(), metAffectContentValue)){//正则匹配校验
						param.setHide(false);
						if(flag < 3) {
							flag = flag + 3;
							param.setMark(flag);
						}
					}else {
						if (flag > 2) {
							flag = flag - 3;
							param.setMark(flag);
						}
						param.setHide(true);
					}
					continue;
				}
			}
			param.setHide(false);
		}
	}
}
