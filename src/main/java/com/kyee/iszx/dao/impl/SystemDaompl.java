package com.kyee.iszx.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyee.iszx.bean.KyParameter;
import com.kyee.iszx.dao.SystemDao;
import com.kyee.iszx.mapper.AccessMethodMapper;
import com.kyee.iszx.mapper.KyAppSettingMapper;
import com.kyee.iszx.mapper.KyApplicationMapper;
import com.kyee.iszx.mapper.KyMethodMapper;
import com.kyee.iszx.mapper.KyParameterMapper;
import com.kyee.iszx.mapper.ParameterTypeMapper;
import com.kyee.iszx.mapper.SysDictionaryItemMapper;

@Service
public class SystemDaompl implements SystemDao{

	@Autowired
	private KyParameterMapper parameter;
	
	@Autowired
	private AccessMethodMapper accessMethod;
	
	@Autowired
	private KyApplicationMapper kyApplication;
	
	@Autowired
	private KyMethodMapper kyMethod;
	
	@Autowired
	private ParameterTypeMapper parameterType;
	
	@Autowired
	private SysDictionaryItemMapper sysDictionaryItem;
	
	@Autowired
	private KyAppSettingMapper kyAppSettingMapper;
	
	
	@SuppressWarnings("unchecked")
	@Override
	/***
	 * type: 1:方法KyMethod	 2.应用KyApplication  3.参数KyParameter  4.访问方式AccessMethod 5.参数类型ParameterType  6.系统字典SysDictionaryItem 7.应用请求公共参数
	 * 
	 **/
	public <T> T findAll(int type) {
		T t = null;
		switch (type) {
		case 1:
			t = (T) kyMethod.findKyMethodAll();
			break;
		case 2:
			t = (T) kyApplication.findKyApplicationAll();
			break;
		default:
			break;
		}
		return t;
	}
	@SuppressWarnings("unchecked")
	@Override
	/***
	 * 通过条件查询
	 * type: 1:方法KyMethod	 2.应用KyApplication  3.参数KyParameter  4.访问方式AccessMethod 5.参数类型ParameterType  6.系统字典SysDictionaryItem 7.应用请求公共参数
	 * 
	 **/
	public <T> T findByCondition(int type,Map map) {
		T t = null;
		switch (type) {
		case 1:
			t = (T) kyMethod.findKyMethodByCondition(map);
			break;
		case 3:
			t = (T) parameter.findKyParameterByCondition(map);
			break;
		case 6:
			t = (T) sysDictionaryItem.findSysDictionaryItemByCondition(map);
			break;
		case 7:
			t = (T) kyAppSettingMapper.findKyAppSettingByCondition(map);
		default:
			break;
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/***
	 * 通过条件查询
	 * type: 1:方法KyMethod	 2.应用KyApplication  3.参数KyParameter  4.访问方式AccessMethod 5.参数类型ParameterType  6.系统字典SysDictionaryItem 7.应用请求公共参数
	 * 
	 **/
	public <T> T findByIds(int type,List<String> id) {
		T t = null;
		switch (type) {
		case 1:
			t = (T) kyMethod.findKyMethodByIds(id);
			break;
		case 3:
			t = (T) parameter.findKyParameterByIds(id);
			break;
		default:
			break;
		}
		return t;
	}
	
	@Override
	/***
	 * 更新默认参数值
	 * type: 1:方法KyMethod	 2.应用KyApplication  3.参数KyParameter  4.访问方式AccessMethod 5.参数类型ParameterType  6.系统字典SysDictionaryItem 7.应用请求公共参数
	 * flag:标识调用的sql
	 * 
	 **/
	public int updateDatas(int type,int flag,List list) {
		int ret = 0;
		switch (type) {
		case 1:
			//t =  kyMethod.findKyMethodByIds(id);
			break;
		case 3:
			if(flag == 0) {
				ret =  parameter.updateKyParametersValue(list);
			}else {
				ret =  parameter.updateKyParameters(list);
			}
			break;
		case 7:
			ret = kyAppSettingMapper.updateKyAppSettingsValue(list);
		default:
			break;
		}
		return ret;
	}
	
}
