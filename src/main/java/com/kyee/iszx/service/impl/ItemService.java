package com.kyee.iszx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyee.iszx.base.Result;
import com.kyee.iszx.base.Results;
import com.kyee.iszx.bean.SysDictionaryItem;
import com.kyee.iszx.dao.SystemDao;
import com.kyee.iszx.service.IItemSevice;

import net.sf.json.JSONArray;

@Service("itemService")
public class ItemService implements IItemSevice {

	@Autowired
	private SystemDao systemDao;
	
	@Override
	public Result getItem(Map<String, String> map) {
		String ret = "";
		List<SysDictionaryItem> sysDictionaryItems = systemDao.findByCondition(6, map);
		JSONArray obj = JSONArray.fromObject(sysDictionaryItems);
		return Results.newSuccessResult(obj,obj.size());
	}
	@Override
	public List getItemList(Map<String, String> map) {
		List<SysDictionaryItem> sysDictionaryItems = systemDao.findByCondition(6, map);
		return sysDictionaryItems;
	}
}
