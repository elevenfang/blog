package com.elevenfang.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elevenfang.blog.model.User;
import com.elevenfang.blog.mongo.impl.UserMongoServiceImpl;

@Controller
public class RegisterController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMongoServiceImpl mongoServiceImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String showIndex() {
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void init() {

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam("userName") String userName, @RequestParam("userPasswd") String userPasswd) {
		String response = "FAIL";
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		if(isNotExist(user)){
			mongoServiceImpl.insert(user);
			logger.info("register user  success, username is:{}",userName);
			response = "SUCCESS";
		}else{
			logger.error("register user fail, please check and resubmit, username is:{}",userName);
		}
		return response;
	}

	private boolean isNotExist(User user) {
		return mongoServiceImpl.isUserNotExist(user);
	}

}
