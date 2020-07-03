package com.bjsxt.mapper;

import java.util.List;

import com.bjsxt.pojo.Users;

public interface UsersMapper {
	void insertUsers(Users users);
	List<Users> findAll();
	void del(int userid);
	
	Users selByUserId(int userid);
	void updateUser(Users users);
}
