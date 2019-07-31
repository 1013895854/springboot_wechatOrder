package com.order.until;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * httputil
 *
 * @author 
 */
public class HttpUtil {

    private static final Logger logger = LogManager.getLogger(HttpUtil.class);
    private static final String CHARSET = "UTF-8";

    /**
     * http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String get(String url) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String str = EntityUtils.toString(entity, CHARSET);
                    return str;
                }
            } finally {
                response.close();
                httpClient.close();
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * http get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String get(String url, Map<String, Object> params) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            url = url + "?";
            for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                String temp = key + "=" + params.get(key) + "&";
                url = url + temp;
            }
            url = url.substring(0, url.length() - 1);
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String str = EntityUtils.toString(entity, CHARSET);
                    return str;
                }
            } finally {
                response.close();
                httpClient.close();
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String post(String url, Map<String, Object> params) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                parameters.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(parameters, CHARSET);
            httpPost.setEntity(uefEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String str = EntityUtils.toString(entity, CHARSET);
                    return str;
                }
            } finally {
                response.close();
                httpClient.close();
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }
//    public static void main(String[] args) {
//    	Map<String, Object> params = new HashMap<String, Object>();
//		params.put("seed", SeedEncrypt.encrypt("ZDMLMGQNYWI9NO9YODAZMBQZZZBYZZX9NWVBZWINOT9HZWUOYOB9ZDQYOWQNMGQYYOJYYBYXZZWMNGFXY"));
////		params.put("addressList", "[]");
//		
//			String addressStr = HttpUtil.post("http://101.132.177.123:3000/api/getAddressList", params);
//			
//			System.out.println(addressStr);
//		
//	}

    /**
     * http post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String post(String url, String params) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity sEntity = new StringEntity(params, CHARSET);
            httpPost.setEntity(sEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
                httpClient.close();
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 发送post请求
     *
     * @param url        路径
     * @param jsonObject 参数(json类型)
     * @param encoding   编码格式
     * @return
     * @throws IOException
     */
//    public static String send(String url, JSONObject jsonObject, String encoding) throws Exception {
//        String body = "";
//
//        //创建httpclient对象
//        CloseableHttpClient client = HttpClients.createDefault();
//        //创建post方式请求对象
//        HttpPost httpPost = new HttpPost(url);
//        RequestConfig requestConfig = RequestConfig.custom()  
//                .setConnectTimeout(60000).setConnectionRequestTimeout(60000)  
//                .setSocketTimeout(60000).build();  
//        httpPost.setConfig(requestConfig); 
//        //装填参数
//        StringEntity s = new StringEntity(jsonObject.toString(), "utf-8");
//        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//        //设置参数到请求对象中
//        httpPost.setEntity(s);
//
//        //设置header信息
//        //指定报文头【Content-type】、【User-Agent】
//        httpPost.setHeader("Content-type", "application/json");
//        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        //执行请求操作，并拿到结果（同步阻塞）
//        CloseableHttpResponse response = client.execute(httpPost);
//        //获取结果实体
//        HttpEntity entity = response.getEntity();
//        if (entity != null) {
//            //按指定编码转换结果实体为String类型
//            body = EntityUtils.toString(entity, encoding);
//        }
//        EntityUtils.consume(entity);
//        //释放链接
//        response.close();
//        return body;
//    }

}
