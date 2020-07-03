package com.bjsxt.service;

import com.bjsxt.pojo.Users;

public interface UserService {
	
	void addUser(Users users);
	
	Users findByUserid(int userid);
	void updateUser(Users users);
	
	
}
