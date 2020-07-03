package com.bjsxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bjsxt.commons.JsonUtils;
import com.bjsxt.jedisdao.JedisDao;
import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UserService;

import redis.clients.jedis.JedisPool;
/**
 * 业务逻辑 -查询：首先对接受到的userid在redis数据库中查询，如果没有在查询mysql数据库，查询以后将拆线呢结果封装成json类型发送给redis缓存
 *  业务逻辑 -更新：执行更新操作后，需要将redis中的缓存也一并删除，并将更新过的数据重新插入redis缓存
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersMapper userMapper;	
	@Autowired
	private JedisDao jedisDao;
	//@Autowired
	//private JedisPool jedisPool;
	
	@Override
	public void addUser(Users users) {
		this.userMapper.insertUsers(users);
	}
	@Override
	public void updateUser(Users users) {
		this.userMapper.updateUser(users);
		int userid = users.getUserid();
		
		try {
			//删除原来的redis缓存
			this.jedisDao.del(this.REDIS_USERS_PRIFX+":"+userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//删除后将更新过后的信息放入redis
			String objectToJson = JsonUtils.objectToJson(users);
			//String redis_Key = this.REDIS_USERS_PRIFX+":"+userid;
			jedisDao.set(this.REDIS_USERS_PRIFX+":"+userid, objectToJson);
			jedisDao.expire(this.REDIS_USERS_PRIFX+":"+userid,600);//如果设置失败，最好clean一下
			//jedisPool.getResource().expire(redis_Key, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Value,获取配置文件中相关参数的值
	@Value("${REDIS_USERS_PRIFX}")
	private String REDIS_USERS_PRIFX;
	
	@Override
	public Users findByUserid(int userid) {
		
		try {
			//查看缓存
			String json=this.jedisDao.get(this.REDIS_USERS_PRIFX+":"+userid );//p拼接成json类型字符串key：value
			//在缓存中是否命中
			if (json != null && json.length()>0) {//命中
				System.out.println("-----------------调用查看redis缓存操作--------------------");
				Users users=JsonUtils.jsonToPojo(json, Users.class);
				return users;
			}
		} catch (Exception e) {//保证不影响功能正常实现，需要用try。。。catch
			e.printStackTrace();
		}
		
		
		//查询数据库
		Users users=this.userMapper.selByUserId(userid);
		System.out.println(users);
		System.out.println("==================查询mysql并放入redis缓存=========================");
		try {
			//放入redis中
			String objectToJson = JsonUtils.objectToJson(users);
			//String redis_Key = this.REDIS_USERS_PRIFX+":"+userid;
			jedisDao.set(this.REDIS_USERS_PRIFX+":"+userid, objectToJson);
			jedisDao.expire(this.REDIS_USERS_PRIFX+":"+userid,600);//如果设置失败，最好clean一下
			//jedisPool.getResource().expire(redis_Key, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
		
		
	}
	
}
