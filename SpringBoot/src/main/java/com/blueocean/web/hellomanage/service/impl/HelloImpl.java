package com.blueocean.web.hellomanage.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blueocean.web.hellomanage.dao.userDoMapper;
import com.blueocean.web.hellomanage.dto.userDo;
import com.blueocean.web.hellomanage.service.HelloInfo;
import com.github.pagehelper.PageHelper;

@Service
public class HelloImpl implements HelloInfo{
	
	@Resource
	private userDoMapper mapper;

	@Override
	public userDo getUser() {
		userDo  user = mapper.selectByPrimaryKey(1);
		PageHelper.startPage(1, 10, true);
		System.out.println(user.toString());
		return user;
	}

}
