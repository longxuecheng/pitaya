package org.lxc.mall.sys;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Component
public class SessionCacheManager {
	
	private Cache<String,Object> cache;
	
	@PostConstruct
	public void init() {
		cache = Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES).build();
	}
		
	public void put(String key, Object value) {
		cache.put(key, value);
	}
	
	public Object get(String key) {
		return cache.getIfPresent(key);
	}
}
