package org.lxc.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="tencent.cos")
public class TencentCOSConfig {

	private String domain;
	
	private String region;
	
	private String userName;
	
	private String secretId;
	
	private String secretKey;
	
	private String goodsBucketName;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getGoodsBucketName() {
		return goodsBucketName;
	}

	public void setGoodsBucketName(String goodsBucketName) {
		this.goodsBucketName = goodsBucketName;
	}
	
	
	
}
