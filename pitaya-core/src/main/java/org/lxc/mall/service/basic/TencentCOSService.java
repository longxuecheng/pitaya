package org.lxc.mall.service.basic;

import java.io.File;

import javax.annotation.PostConstruct;

import org.lxc.mall.config.TencentCOSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

@Service
public class TencentCOSService {

	@Autowired
	private TencentCOSConfig cosConfig;
	
	private COSClient cosClient;
	
	@PostConstruct
	private void initCOSClient() {
		COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
		ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegion()));
		cosClient = new COSClient(cred, clientConfig);
	}
	
	/**
	 * saveGoodsSource will transfer a file from local to tencent COS
	 * with a given key to a bucket used for goods
	 * @param key
	 * @param source
	 * @return
	 */
	public String saveGoodsSource(String key,File source) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getGoodsBucketName(), key, source);
		PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		return buildSourceURL(key);
	}
	
	/**
	 *  Build a url like https://xxxx/key/file.type
	 * @param key
	 * @param source
	 * @return
	 */
	private String buildSourceURL(String key) {
		StringBuilder sb = new StringBuilder(cosConfig.getDomain());
		sb.append("/");
		sb.append(key);
		return sb.toString();
	}
}
