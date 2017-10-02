package com.elevenfang.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevenfang.blog.model.User;
import com.elevenfang.blog.mongo.impl.UserMongoServiceImpl;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMongoServiceImpl mongoServiceImpl;

	private static List<User> users = new ArrayList<>();
	static {
		users.add(new User("zhangsan", "1223456"));
		users.add(new User("lisi", "666666"));
		users.add(new User("wangwu", "888888"));
		users.add(new User("zhaoliu", "999999"));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void init(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("userList", users);
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("userName") String userName, @RequestParam("userPasswd") String userPasswd) {
		String response = "FAIL";
		if (verifyUser(userName, userPasswd)) {
			logger.info("verify user info success, username is:{}",userName);
			response = "SUCCESS";
		}else{
			logger.error("verify user info fail, please check and resubmit, username is:{}",userName);
		}
		return response;
	}

	private boolean verifyUser(String userName, String userPasswd) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		return mongoServiceImpl.verifyUser(user);
	}

}
