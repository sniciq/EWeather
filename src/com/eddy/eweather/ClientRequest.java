package com.eddy.eweather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具类
 * 
 */
public class ClientRequest {

	/**
	 * 发起一个异步请求，请求将在新的线程中完成
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void ajaxRequest(final String url) throws Exception {
		new Thread(new Runnable() {
			public void run() {
				try {
					requestPost(url, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 执行请求
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String request(final String url, boolean isGet) throws Exception {
		if (isGet) {
			return executeGetRequest(url);
		} else {
			return requestPost(url, null);
		}
	}

	/**
	 * 执行请求
	 * 
	 * @param url
	 *            地址
	 * @param paramMap
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public String requestPost(String url, Map<String, String> paramMap) throws Exception {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		if (paramMap != null) {
			for (String s : paramMap.keySet()) {
				qparams.add(new BasicNameValuePair(s, paramMap.get(s)));
			}
		}
		String retStr = executeRequest(url, qparams);
		return retStr;
	}

	/**
	 * 执行http请求
	 * 
	 * @param url
	 *            请求地址
	 * @param qparams
	 *            参数列表
	 * @return
	 * @throws Exception
	 */
	private String executeRequest(String url, List<NameValuePair> qparams) throws Exception {
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 1000 * 10;
		int timeoutSocket = 1000 * 60 * 10;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		DefaultHttpClient client = new DefaultHttpClient(httpParameters);
		HttpPost post = new HttpPost(url);

		post.setEntity(new UrlEncodedFormEntity(qparams, HTTP.UTF_8));
		post.addHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		post.addHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));
		post.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36"));
		HttpResponse response = client.execute(post);
		return EntityUtils.toString(response.getEntity());
	}

	private String executeGetRequest(String url) throws Exception {
		String context = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		HttpResponse response = httpclient.execute(request);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				context = EntityUtils.toString(entity, HTTP.UTF_8);
			}
		}

		return context;
	}
}
