package com.kyee.iszx.service;

import java.util.List;
import java.util.Map;

import com.kyee.iszx.base.Result;

import net.sf.json.JSONArray;

public interface IItemSevice {

	Result getItem(Map<String, String> map);//获取字典值
	List getItemList(Map<String, String> map);//获取字典值
}
