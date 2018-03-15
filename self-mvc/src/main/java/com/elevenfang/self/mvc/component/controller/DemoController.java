package com.elevenfang.self.mvc.component.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elevenfang.self.mvc.annotation.SelfController;
import com.elevenfang.self.mvc.annotation.SelfRequestMapping;
import com.elevenfang.self.mvc.annotation.SelfRequestParam;

@SelfController
@SelfRequestMapping("/demo")
public class DemoController {

	@SelfRequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response,
			@SelfRequestParam("param") String param) {
		try {
			response.getWriter().write("demo controller test method response success, param:" + param);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
