package com.kyee.iszx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyee.iszx.dao.SystemDao;
import com.kyee.iszx.service.IKyApplicationService;

@Service("kyApplicationService")
public class KyApplicationService implements IKyApplicationService{
	
	@Autowired
	private SystemDao systemDao;
	
	@Override
	public List findAllApp() {
		return systemDao.findAll(2);
	}

}
