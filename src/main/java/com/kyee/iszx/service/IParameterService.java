package com.kyee.iszx.service;


import java.util.List;

import com.kyee.iszx.base.Result;
import com.kyee.iszx.bean.KyParameter;
import com.kyee.iszx.util.log.LogService;

public interface IParameterService extends LogService{

	Result getParameterIn(String metid, String metAffectContent);//获取入参

	Result upDateParameterIn(String metAffectValue, String tableData);//更新入参

	Boolean saveParam(String tableData);//保存更新的参数值

	boolean saveParam(List<KyParameter> params);
}
