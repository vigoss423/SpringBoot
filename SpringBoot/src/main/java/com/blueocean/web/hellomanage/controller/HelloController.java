package com.blueocean.web.hellomanage.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.util.RetInfoUtil;
import com.blueocean.common.vo.RetInfo;
import com.blueocean.web.hellomanage.service.HelloInfo;


@RequestMapping(value = "hello")
@RestController
public class HelloController {
	
	@Resource
	private HelloInfo hi;
	
	@RequestMapping(value = "helloWorld")
	public RetInfo queryDrugOrderDetail() {
		RetInfo ret = RetInfoUtil.initRetInfo4Succ();
		hi.getUser();
		return ret;
	}


}
