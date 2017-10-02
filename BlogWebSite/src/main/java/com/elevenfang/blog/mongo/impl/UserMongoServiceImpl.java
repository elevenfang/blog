package com.elevenfang.blog.mongo.impl;

import java.util.List;

import com.elevenfang.blog.model.User;
import com.elevenfang.blog.mongo.MongoSerive;

public class UserMongoServiceImpl extends MongoSerive<User> {

	public int batchInsertUser(List<User> users) {
		return super.batchInsert(users);
	}

	public int sigleInsertUser(User user) {
		return super.insert(user);
	}

	public long getNextSequence(String sequenceName) {
		long sequence = super.getNextSequence(sequenceName);
		logger.info("sequence number:{}", sequence);
		return sequence;
	}
	
	public boolean verifyUser(User user){
		String userName = user.getUserName();
		String passwd = user.getUserPasswd();
		List<User> queryResult= super.queryForList("{userName:#,userPasswd:#}", userName,passwd);
		return queryResult.size() == 1;
	}
	
	public boolean isUserNotExist(User user){
		String userName = user.getUserName();
		List<User> queryResult = super.queryForList("{userName:#}",userName);
		return queryResult.isEmpty();
	}
}
