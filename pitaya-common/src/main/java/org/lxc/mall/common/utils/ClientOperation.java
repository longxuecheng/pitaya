package org.lxc.mall.common.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 */
@Service
public class ClientOperation {
	
	protected CloseableHttpClient httpclient;
	
	private static String local_url = "http://localhost:8090/login";
	private static String wx_url = "https://api.weixin.qq.com/sns/jscode2session";
	private static final String APP_ID = "appid";
	private static final String SECRET = "secret";
	private static final String JS_CODE = "js_code";
	private static final String GRANT_TYPE = "grant_type";
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, Exception {
		Map<String,String> formdata = new HashMap<String,String>();
		formdata.put(APP_ID, "wx48b125b125deff5e");
		formdata.put(SECRET, "f22e5c39919fdbfa6be401077be5701e");
		formdata.put(JS_CODE, "071DYooP1c7rc21Fj9nP1vzsoP1DYoot");
		formdata.put(GRANT_TYPE, "authorization_code");
		new ClientOperation().sendHttpsPost(wx_url, formdata);
	}
	
	/**
	 * @param address
	 */
	public ClientOperation() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		 createClient();
	}

	/**
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	private void createClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLConnectionSocketFactory sslf = HttpClientUtil.buildSslFactory();
//   	  	BasicCookieStore cookieStore = new BasicCookieStore();
		httpclient = HttpClients.custom().setProxy(new HttpHost("10.1.31.133", 8080)).setSSLSocketFactory(sslf).build();
	   			
//	   		builder.setDefaultCookieStore(cookieStore);
       		  
	}

    
    @SuppressWarnings("deprecation")
	public static String postRequest(CloseableHttpClient httpclient,Map<String,String> formdata,String target) throws IOException {
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>(formdata.keySet().size());
    	for (String k : formdata.keySet()) {
    		BasicNameValuePair p = new BasicNameValuePair(k, formdata.get(k));
    		params.add(p);
    	}
    	HttpPost httpost = new HttpPost(target);
        httpost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        CloseableHttpResponse response2 = httpclient.execute(httpost);
        try {
            HttpEntity entity = response2.getEntity();
            
            String resp = EntityUtils.toString(entity);
            
            System.out.println(resp);
            
            EntityUtils.consume(entity);
            return resp;
        } finally {
            response2.close();
        }
    }
    
    public String sendHttpsPost(String target,Map<String,String> formdata) throws Exception {
          try {
        	  if(httpclient == null) {
        		  createClient();
        	  }
        	  return postRequest(httpclient, formdata, target);
          } catch (Exception e) {
        	  e.printStackTrace();
        	  throw e;
          } 
    }
    
    public void close() throws IOException {
    	httpclient.close();
    }
}
