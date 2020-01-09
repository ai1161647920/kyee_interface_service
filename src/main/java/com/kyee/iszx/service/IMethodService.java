package com.kyee.iszx.service;

import java.util.List;

import com.kyee.iszx.base.Result;

public interface IMethodService{
	

	List findMethodByIds(List ids);//通过id查找方法

	List findAllMethod();

	Result useMethod(String tableData, String reqURL, String appid);//调用方法

}
