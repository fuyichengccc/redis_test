package com.bjsxt.jedisdao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.jedisdao.JedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class JedisDaoSingleImpl implements JedisDao{
	//使用@Autowired要开启组件扫描，不使用的话要通过pagebean的propertie注入进来
	@Autowired
	private JedisPool jedispool;
	
	@Override
	public String set(String key, String value) {
		Jedis resource=jedispool.getResource();
		return resource.set(key, value);
	}

	@Override
	public String get(String key) {
		Jedis resource=jedispool.getResource();
		return resource.get(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis resource=jedispool.getResource();
		return resource.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		Jedis resource=jedispool.getResource();
		return resource.hget(key, field);
	}

	@Override
	public Long expire(String key, int sec) {
		Jedis resource = jedispool.getResource();
		
		return resource.expire(key, sec);
	}

	@Override
	public Long del(String key) {
		Jedis resource = jedispool.getResource();
		
		return resource.del(key);
	}

}
