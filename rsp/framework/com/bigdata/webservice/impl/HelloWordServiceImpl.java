package com.bigdata.webservice.impl;

import com.bigdata.webservice.HelloWordService;

public class HelloWordServiceImpl implements HelloWordService {

	@Override
	public String sayHello(String name) {
		return "hello,"+name;
	}

}
