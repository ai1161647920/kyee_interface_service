package com.kyee.iszx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kyee.iszx.Enums.ErrorCodeEnum;
import com.kyee.iszx.base.Result;
import com.kyee.iszx.base.Results;
import com.kyee.iszx.bean.KyParameter;
import com.kyee.iszx.common.AppConstants;
import com.kyee.iszx.common.PageData;
import com.kyee.iszx.dao.SystemDao;
import com.kyee.iszx.service.IAppSettingService;
import com.kyee.iszx.service.IMethodService;
import com.kyee.iszx.service.IParameterService;
import com.kyee.iszx.util.http.HttpUtil;
import com.kyee.iszx.util.sign.SignUtil;
import com.kyee.iszx.util.string.StringUtil;


@Service("methodService")
public class MethodService implements IMethodService{
	
	@Autowired
	private IParameterService parameterService;
	
	@Autowired
	private SystemDao systemDao;
	
	@Autowired
	private IAppSettingService appSettingService;
	
	
	Gson gson = new GsonBuilder().serializeNulls().create();
	
	@Override
	public Result useMethod(String tableData,String reqURL,String appid) {
		Result result = null;
		tableData = StringUtil.deleteSpace(tableData);
		List<KyParameter> params = gson.fromJson(tableData, new TypeToken<List<KyParameter>>() {}.getType());
		parameterService.saveParam(params);
		Map<String, String> reqParam = new HashMap<String, String>();
		for(KyParameter param : params) {
			if(!param.getHide()) {
				if(StringUtil.isNotEmpty(param.getValue())) {
					reqParam.put(param.getNameEn(), param.getValue());
				}
			}
		}
		List list = appSettingService.findAppSettingByCondition(appid);
		if(params == null || params.size() == 0) {
			return Results.newFailedResult(ErrorCodeEnum.S003);
		}
		if(AppConstants.IS_YES.equals(appSettingService.getConfig(AppConstants.AUTO_SIGN,list))) {
			String privateKey = appSettingService.getConfig(AppConstants.PRIVATE_KEY,list);
			reqParam.remove("signValue");
			reqParam.put("privateKey", privateKey);
			String signValue = "";
			if(AppConstants.IS_YES.equals(appSettingService.getConfig(AppConstants.IIPS_SIGN,list))) {
				String urlIp = HttpUtil.getIP(reqURL);
				if(StringUtil.isEmpty(urlIp)) {
					urlIp = appSettingService.getConfig(AppConstants.IIPS_URL,list);
				}
				String url = urlIp + appSettingService.getConfig(AppConstants.SIGN_URL,list);
				Result resp = HttpUtil.doPost(url, reqParam);
				if(!resp.getSuccess()) {
					return resp;
				}
				String ret = (String) resp.getData();
				if("true".equals(StringUtil.getJsonValue("success", ret))) {
					String data = StringUtil.getJsonValue("data", ret);
					signValue = StringUtil.getJsonValue("signValue", data);
					reqParam.put("signValue", signValue);
					reqParam.remove("privateKey");
				}else {
					return Results.newFailedResult(ErrorCodeEnum.S002);
				}
			}else {
				signValue = SignUtil.sign(reqParam);
				reqParam.put("signValue", signValue);
				reqParam.remove("privateKey");
			}
		}
		
		//请求综合支付
		result = HttpUtil.doPost(reqURL, reqParam);
		if(result.getSuccess()) {
			String ret = (String) result.getData();
			if("true".equals(StringUtil.getJsonValue("success", ret))) {
				try {
				String htmlCode = StringUtil.getJsonValue("html", ret);
				if(StringUtil.isNotEmpty(htmlCode)) {
					PageData.htmlCode = htmlCode;
					PageData.count = PageData.count + 1;
				}
				}catch(Exception e) {
					//可忽略异常
				}
			}
		}
		return result;
	}

	@Override
	public List findMethodByIds(List ids) {
		return (List) systemDao.findByIds(1,ids);
	}
	
	@Override
	public List findAllMethod() {
		return (List) systemDao.findAll(1);
	}
	
}
