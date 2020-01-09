package com.kyee.iszx.controller.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyee.iszx.base.Result;
import com.kyee.iszx.base.Results;
import com.kyee.iszx.service.IAppSettingService;
import com.kyee.iszx.service.IItemSevice;
import com.kyee.iszx.service.IMethodService;
import com.kyee.iszx.service.IParameterService;
import com.kyee.iszx.util.log.LogService;


@RestController
public class DataProcess implements LogService{

	@Autowired
	private IMethodService methodService;
	
	@Autowired
	private IParameterService parameterService;
	
	@Autowired
	private IItemSevice itemSevice;
	
	@Autowired
	private IAppSettingService appSettingService;
	

	@RequestMapping("/testData")
	public String testData() {
		String ret = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"\",\"city\":\"城市-0\",\"sign\":\"签名-0\",\"experience\":255,\"logins\":24,\"wealth\":82830700,\"classify\":\"作家\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"男\",\"city\":\"城市-1\",\"sign\":\"签名-1\",\"experience\":884,\"logins\":58,\"wealth\":64928690,\"classify\":\"词人\",\"score\":27},{\"id\":10002,\"username\":\"user-2\",\"sex\":\"女\",\"city\":\"城市-2\",\"sign\":\"签名-2\",\"experience\":650,\"logins\":77,\"wealth\":6298078,\"classify\":\"酱油\",\"score\":31},{\"id\":10003,\"username\":\"user-3\",\"sex\":\"女\",\"city\":\"城市-3\",\"sign\":\"签名-3\",\"experience\":362,\"logins\":157,\"wealth\":37117017,\"classify\":\"诗人\",\"score\":68},{\"id\":10004,\"username\":\"user-4\",\"sex\":\"男\",\"city\":\"城市-4\",\"sign\":\"签名-4\",\"experience\":807,\"logins\":51,\"wealth\":76263262,\"classify\":\"作家\",\"score\":6},{\"id\":10005,\"username\":\"user-5\",\"sex\":\"女\",\"city\":\"城市-5\",\"sign\":\"签名-5\",\"experience\":173,\"logins\":68,\"wealth\":60344147,\"classify\":\"作家\",\"score\":87},{\"id\":10006,\"username\":\"user-6\",\"sex\":\"女\",\"city\":\"城市-6\",\"sign\":\"签名-6\",\"experience\":982,\"logins\":37,\"wealth\":57768166,\"classify\":\"作家\",\"score\":34},{\"id\":10007,\"username\":\"user-7\",\"sex\":\"男\",\"city\":\"城市-7\",\"sign\":\"签名-7\",\"experience\":727,\"logins\":150,\"wealth\":82030578,\"classify\":\"作家\",\"score\":28},{\"id\":10008,\"username\":\"user-8\",\"sex\":\"男\",\"city\":\"城市-8\",\"sign\":\"签名-8\",\"experience\":951,\"logins\":133,\"wealth\":16503371,\"classify\":\"词人\",\"score\":14},{\"id\":10009,\"username\":\"user-9\",\"sex\":\"女\",\"city\":\"城市-9\",\"sign\":\"签名-9\",\"experience\":484,\"logins\":25,\"wealth\":86801934,\"classify\":\"词人\",\"score\":75}]}";
		return ret;
	}

	@RequestMapping("/getParamIn")
	public Result getPara(@RequestParam("appid") Integer appid, @RequestParam(required = false) String metid,
			@RequestParam(required = false) String metAffectContent) {
		return parameterService.getParameterIn(metid, metAffectContent);
	}
	
	@RequestMapping("/saveParam")
	public Result saveParam(@RequestParam String tableData) {
		if(parameterService.saveParam(tableData)) {
			return Results.newSuccessResult();
		}
		return Results.newFailedResult("保存信息出错！");
	}

	@RequestMapping("/upDateData")
	public Result upDateData(@RequestParam String metAffectContent, @RequestParam String metAffectValue,
			@RequestParam String tableData) {
		return parameterService.upDateParameterIn(metAffectContent,metAffectValue,tableData);
	}

	@RequestMapping("/getItem")
	public Result getItem(@RequestParam(required = false) String valueItem) {
		return itemSevice.getItem(valueItem);
	}

	@RequestMapping("/useMethod")
	public Result useMethod(@RequestParam String tableData, @RequestParam String reqURL,@RequestParam String appid) {
		
		return methodService.useMethod(tableData, reqURL,appid);
	}
	
	@RequestMapping("/saveAppParam")
	public Result saveAppParam(@RequestParam String paramData) {
		if(appSettingService.saveAppParam(paramData)) {
			return Results.newSuccessResult();
		}
		return Results.newFailedResult("保存信息出错！");
	}
	
	@RequestMapping("/test")
	public Result test() {
		String ret = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("test", "123");
		paramMap.put("test1", "1233");
		paramMap.put("test2", "12343");
		paramMap.put("privateKey",
				"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPupORdqoiZrdkwUlwjcrVjlvgbVCJsznugvQXIdx2yVNTrSJOZUMF2ALOUqEwEVG/V126JVF+aBJ4y52oiCfqGicSPqsvxeQ1iErKnu1KKMQ9flw9EIvb2LUicLtcG8ah6cfj4elij7XWk1QDpXyPy9KMBZ0G10sHHWs+mTgRTNAgMBAAECgYBWMD2STH3eJJ2uLrsPWOyxbnVnR5yb8tDwegay+4nqISvKyl/C1oB99UDq9z7s9vrNVSD0dqop0n/mDVzPtcmCCg/ue4lV3kj4rCDWVCc1Ooyjcu1JKCruOgrCdL72EwUeriBtXs+alGmU0KMrzpvUnSpKb6rcwMkwcvpsCii8TQJBAP/FVxmY4Z9HZz2GJuudHyokwTUUnCFJ198G5ttLhLAo0m5wCSKo8Aw8k0MZHwB9pHbHPrEjHatu+T9Re1jthXsCQQD74vCxlCyz7xPQKSoAv0JOLKlQmy1nycb7D8O5jUdY7t9PYbDS8kg0wgprvLuJNaj+KU3PKu3FGtCJ2PJSkKhXAkBKw4+bTuIc4g1eoZk85DfY5NeJQZYF8QTmD6lpzuBhcf1ZBOzWsdVBbm+JfI1gYlmx0roTo3UnyI+ZH6NTslKDAkBdD5JnuVvuupSyYpL/JaqEoWGIw1w6LUVTupZQdo72n4JnnKsstJDS1SHufhM3dVjTVkVGFhE0slx7oWid8o4PAkEAr9L1uUWyhXbcVo50dHC9e2OiDSmUPVZvzVj2AKJU1dz3lI+ZLLLdzCgdRNngT5CPz8DRttXCGLjiQ52P4g3atQ==");
		logger.info("test info测试日志：" + paramMap);
		logger.error("test error测试日志：" + paramMap);
		logger.debug("test debug测试日志：" + paramMap);
		//ret = HttpUtil.doPost("http://localhost:8080/qy_iips_pay/tool/sign/sign", paramMap);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("methodId", "1");
		map.put("attribute", "0");
		//List<KyParameter> params = systemDao.findByCondition(3, map);

		return Results.newSuccessResult("ss");
	}

}
