package com.blueocean.web.hellomanage.service.impl;


import java.util.List;

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
		List<userDo>  user = mapper.selectAll();
		PageHelper.startPage(1, 8, "id");
		System.out.println(user.size());
		return user.get(0);
	}

}
