package com.cloud.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * HttpClient 工具类
 * HttpClient2Utils 相对 HttpClientUtil 工具类 封装了更多灵活的 使用
 * 建议使用HttpClient2Utils类来发来有关Http 请求处理
 * @author : kezhan
 * @since : 2017年4月6日
 * @version : v0.0.1
 */
public class HttpUtil {

	/* 声明httpClient 客户端连接管理 这里需要连接池初始化 */
	private static PoolingHttpClientConnectionManager clientConnectionManager;
	/* 请求编码格式 */
	private static String UTF_8 = "UTF-8";
	/* 声明Content-Type */
	private static String CONTENT_TYPE = "Content-Type";

	/* 请求头类型数组 */
	private static String[] ContentTypeArray = { "application/json", "application/xml", "text/html" };
	
	/**
	 * 初始化客户端连接管理 
	 * @author : kezhan	
	 * @since : 2017年3月14日
	 */
	private static void init() {
		if (clientConnectionManager == null) {
			clientConnectionManager = new PoolingHttpClientConnectionManager();
			clientConnectionManager.setMaxTotal(50);// 整个连接池最大连接数
			clientConnectionManager.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 * @author : kezhan
	 * @since : 2017年3月14日
	 */
	private static CloseableHttpClient getHttpClient() {
		init();
		return HttpClients.custom().setConnectionManager(clientConnectionManager).build();
	}

	
	/**
	 * ************************************** Get请求  ***************************************
	 */
	 /** 
     *  
     * @param url 
     * @return 
     */  
    public static String get(String url) {
        HttpGet httpGet = null;
		CloseableHttpClient httpClient = null;
		try {
			httpGet = new HttpGet(url);
			httpClient = getHttpClient();
			return getResult(httpGet, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
    }  
  
    /**
     * http Get 请求
     * @param url 地址
     * @param params 参数
     * @return
     * @author : kezhan	
     * @since : 2017年3月14日
     */
    public static String get(String url, Map<String, Object> params) {
        HttpGet httpGet = null;
		CloseableHttpClient httpClient = null;
		try {
			URIBuilder ub = new URIBuilder();
			ArrayList<NameValuePair> pairs = covertMapParams2NVPS(params);
			ub.setParameters(pairs);
			ub.setPath(url);
			httpGet = new HttpGet(ub.build());
			httpClient = getHttpClient();
			return getResult(httpGet, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
  
    /**
     * httpGetRequest
     * @param url
     * @param headers
     * @param params
     * @return
     * @author : kezhan	
     * @since : 2017年3月14日
     */
    public static String get(String url, Map<String, Object> headers, Map<String, Object> params) {
    	CloseableHttpClient httpClient = null;
    	HttpGet httpGet = null;
    	try {
			URIBuilder ub = new URIBuilder();
			ub.setPath(url);
			ArrayList<NameValuePair> pairs = covertMapParams2NVPS(params);
			ub.setParameters(pairs);  
			httpGet = new HttpGet(ub.build());
			httpClient = getHttpClient();
			for (Map.Entry<String, Object> param : headers.entrySet()) {  
			    httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));  
			}
			return getResult(httpGet, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * ************************************** Post请求  ***************************************
	 */

	/**
	 * httpPost 请求
	 * 
	 * @param url
	 * @return
	 * @author : kezhan
	 * @since : 2017年3月14日
	 */
	public static String post(String url) {
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		try {
			httpPost = new HttpPost(url);
			httpClient = getHttpClient();
            httpPost.setHeader(CONTENT_TYPE, ContentTypeArray[0].toString());
			return getResult(httpPost, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * httpClient post
	 * json
	 * @param url 地址
	 * @param jsonText json串
	 * @return
	 * @author : kezhan	
	 * @since : 2017年3月14日
	 */
	public static String post(String url, String jsonText) {
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		try {
			httpPost = new HttpPost(url);
			httpClient = getHttpClient();
			//httpClient.getParams().setParameter("", ""); //自定义头参数
			httpPost.setHeader(CONTENT_TYPE, ContentTypeArray[0].toString());
			httpPost.setEntity(new StringEntity(jsonText, UTF_8));
			return getResult(httpPost, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Map 入参方式 Form提交方式
	 * 
	 * @param url 地址
	 * @param params 参数
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 * @author : kezhan
	 * @since : 2017年3月14日
	 */
	public static String post(String url, Map<String, Object> params) {
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		ArrayList<NameValuePair> pairs = null;;
		try {
			httpPost = new HttpPost(url);
			httpClient = getHttpClient();
            httpPost.setHeader(CONTENT_TYPE, ContentTypeArray[0].toString());
			pairs = covertMapParams2NVPS(params);
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
			return getResult(httpPost, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * httpPostFormRequest 
	 * Form提交方式
	 * Header 头参数设置 
	 * @param url
	 * @param headers
	 *            头参数设置
	 * @param params
	 *            body参数体
	 * @return
	 * @author : kezhan
	 * @since : 2017年3月14日
	 */
	public static String post(String url, Map<String, Object> headers, Map<String, Object> params) {
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		// 处理 请求体
		ArrayList<NameValuePair> pairs = null;
		try {
			httpPost = new HttpPost(url);
			httpClient = getHttpClient();
            httpPost.setHeader(CONTENT_TYPE, ContentTypeArray[0].toString());
			// 处理header
			for (Map.Entry<String, Object> header : headers.entrySet()) {
				httpPost.addHeader(header.getKey(), String.valueOf(header.getValue()));
			}
			pairs = covertMapParams2NVPS(params);
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
			return getResult(httpPost, httpClient);
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * map 参数组装
	 * 
	 * @param params
	 * @return
	 * @author : kezhan
	 * @since : 2017年3月14日
	 */
	private static ArrayList<NameValuePair> covertMapParams2NVPS(Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}
		return pairs;
	}

	/**
	 * 处理Http请求
	 *
	 * @param request
	 * @return
	 * @author : kezhan
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @since : 2017年3月14日
	 */
	private static String getResult(HttpRequestBase request, CloseableHttpClient httpClient) throws ClientProtocolException, IOException {
		request.setHeader("Connection","close"); //设置请求报头 请求完毕立马关闭
		CloseableHttpResponse response = httpClient.execute(request);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				response.close();  //强制关闭
				return result;
			}
		}
		response.close();  //强制关闭
		return null;
	}


}
