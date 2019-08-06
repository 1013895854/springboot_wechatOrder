package com.order.until;


import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {
	//日志
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
//	private static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	public static String doPost(String url,String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建HttpPost
		HttpPost post = new HttpPost(url);
		//设置消息头
		post.setHeader("Content-type", "application/json; charset=utf-8");
		//构造消息体
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		//发送Json
		post.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(post);
//			System.out.println(response);
			//检验返回码
			int statusCode = response.getStatusLine().getStatusCode();
			String jsonString = null;
			if(statusCode ==HttpStatus.SC_OK) {
				//获取字符串
				HttpEntity responseEntity = response.getEntity();
            	jsonString = EntityUtils.toString(responseEntity);
            	return jsonString;
			}else {
				HttpEntity responseEntity = response.getEntity();
            	jsonString = EntityUtils.toString(responseEntity);
            	return jsonString;
			}
		}catch (Exception e) { 
			e.printStackTrace();
			logger.error("post请求错误:" + url+e);
			return "";
		}finally{
			if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			 try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	/**
	 *GET请求
	 */
    public static String doGet(String url,Map<String,Object> map){
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	CloseableHttpResponse response = null;
    	String jsonString = null;
    	// 创建http GET请求
    	HttpGet httpGet = new HttpGet(url);
    	try {
        	 //判断是否有参数
        	if(map != null && !map.isEmpty()) {
        		 // 定义请求的参数
               URIBuilder uriBuilder = new URIBuilder(url);
               /** 第一种添加参数的形式 */
               /*uriBuilder.addParameter("", "");
               uriBuilder.addParameter("", "");*/
               /** 第二种添加参数的形式 */
               List<NameValuePair> list = new LinkedList<>();
               for (Map.Entry<String, Object> entry : map.entrySet()) {
            	   BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue()+"");
            	   list.add(param);
               }
               URI build = uriBuilder.setParameters(list).build();
               httpGet = new HttpGet(build);
        	}
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                //请求体内容
            	//获取字符串
				HttpEntity responseEntity = response.getEntity();
            	jsonString = EntityUtils.toString(responseEntity);
            	return jsonString;
            }else {
            	HttpEntity responseEntity = response.getEntity();
            	jsonString = EntityUtils.toString(responseEntity);
            	return jsonString;
            }
        }catch(Exception e) { 
			e.printStackTrace();
			logger.error("get请求错误:" + url+e);
			return "";
		} finally {
			if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			 try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
    }
	
}
