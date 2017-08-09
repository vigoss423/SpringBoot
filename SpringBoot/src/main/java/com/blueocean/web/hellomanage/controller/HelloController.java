package com.blueocean.web.hellomanage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.util.RetInfoUtil;
import com.blueocean.common.vo.RetInfo;


@RequestMapping(value = "hello")
@RestController
public class HelloController {
	
	@RequestMapping(value = "helloWorld")
	public RetInfo queryDrugOrderDetail() {
		RetInfo ret = RetInfoUtil.initRetInfo4Succ();
		return ret;
	}


}
