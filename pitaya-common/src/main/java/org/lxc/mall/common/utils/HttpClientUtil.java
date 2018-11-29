/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.lxc.mall.common.utils;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class HttpClientUtil {
	
static String wx_login = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
static String wx_login1 = "https://api.weixin.qq.com/sns/jscode2session?appid=wx48b125b125deff5e&secret=f22e5c39919fdbfa6be401077be5701e&js_code=061nuXCO0vyvYb2MmmBO0ZLVCO0nuXCa&grant_type=authorization_code";
	private static String wx_url = "https://api.weixin.qq.com/sns/jscode2session";
	
	private static String local_url = "http://localhost:8090/login";
	
	private static final String APP_ID = "appid";
	private static final String SECRET = "secret";
	private static final String JS_CODE = "js_code";
	private static final String GRANT_TYPE = "grant_type";
	
	
	public static SSLConnectionSocketFactory buildSslFactory() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		  // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null,new TrustAllStrategy()).build();
                
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new AllowAllHostnameVerifier());
        return sslsf;
	}

    public final static void main(String[] args) throws Exception {
    	SSLConnectionSocketFactory sslsf = buildSslFactory();
    	
//        CloseableHttpClient httpclient = HttpClients.custom().setProxy(new HttpHost("10.1.31.133", 8080)).setSSLSocketFactory(sslsf).build();
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                
        try {

            HttpGet httpget = new HttpGet(local_url);
            
            HttpParams params = httpget.getParams();
            params.setParameter(APP_ID, "wx48b125b125deff5e");
            params.setParameter(SECRET, "f22e5c39919fdbfa6be401077be5701e");
            params.setParameter(JS_CODE, "061nuXCO0vyvYb2MmmBO0ZLVCO0nuXCa");
            params.setParameter(GRANT_TYPE, "authorization_code");

            System.out.println("Executing request " + httpget.getRequestLine());
            
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(entity));
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
