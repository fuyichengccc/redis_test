package com.bjsxt.jedisdao;

public interface JedisDao {
	
	 String set(String key,String value);
	 String get(String key);
	 
	 Long hset(String key,String field,String value);
	 String hget(String key,String field);
	 
	 /**
	  * 用于设置失效时间
	  * @param key
	  * @param sec
	  * @return
	  */
	 Long expire(String key,int sec);
	 /**
	  * 删除jedis缓存
	  * @param key
	  * @return
	  */
	 Long del(String key);
}
