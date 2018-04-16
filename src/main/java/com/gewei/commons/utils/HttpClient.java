package com.gewei.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClient {
	private static final String $line_feed = System.getProperty("line.separator");

	public static String posts(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;
		HttpPost post = postForm(url, params);
		body = invoke(httpclient, post);
		httpclient.getConnectionManager().shutdown();
		return body;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		return body;
	}

	private static String paseResponse(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String charset = EntityUtils.getContentCharSet(entity);
		String body = null;
		try {
			body = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
	}

	/**
	 * post方式提交请求
	 * 
	 * @param strURL.
	 *            请求地址
	 * @param requestStr.
	 *            参数值
	 * @return 服务器返回
	 */
	public static String doPostMethod(String strURL, String requestStr) {
		StringBuffer sbReturn = new StringBuffer("");
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			url = new URL(strURL);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConnection.setRequestProperty("Cache-Control", "no-cache");
			httpConnection.setRequestProperty("Content-Length", String.valueOf(requestStr.length()));
			httpConnection.setRequestProperty("Cache-Control", "no-cache");
			httpConnection.setRequestProperty("accept", "*/*");
			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setConnectTimeout(60000);
			httpConnection.setReadTimeout(60000);
			out = httpConnection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
			osw.write(requestStr);
			osw.flush();
			osw.close();
			in = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				sbReturn.append(strRead);
				sbReturn.append($line_feed);
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (in != null)
					in.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (br != null)
					br.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null)
					httpConnection.disconnect();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}

	/**
	 * get方式提交请求
	 * 
	 * @param strURL.
	 *            请求地址
	 * @param requestStr.
	 *            参数值
	 * @return 服务器返回
	 */
	public static String doGetMethod(String strURL, String requestStr) {
		HttpURLConnection httpConnection = null;
		StringBuffer sbReturn = new StringBuffer("");
		InputStream in = null;
		BufferedReader reader = null;
		try {
			URL address_url = new URL(strURL);
			httpConnection = (HttpURLConnection) address_url.openConnection();
			//设置访问超时时间及读取网页流的超时时间,毫秒值
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			//得到访问页面的返回值
			int response_code = httpConnection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {
				in = httpConnection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sbReturn.append(line);
					sbReturn.append($line_feed);
				}
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (reader != null)
					reader.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null)
					httpConnection.disconnect();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}

	/**
	 * post方式提交请求
	 * 
	 * @param strURL.
	 *            请求地址
	 * @param requestStr.
	 *            参数值
	 * @return 服务器返回
	 */
	public static String doJSONPostMethod(String strURL, String requestStr) {
		StringBuffer sbReturn = new StringBuffer("");
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			url = new URL(strURL);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setRequestProperty("Cache-Control", "no-cache");
			httpConnection.setRequestProperty("Content-Length", String.valueOf(requestStr.length()));
			httpConnection.setRequestProperty("Cache-Control", "no-cache");
			httpConnection.setRequestProperty("accept", "*/*");
			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setConnectTimeout(60000);
			httpConnection.setReadTimeout(60000);
			out = httpConnection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
			osw.write(requestStr);
			osw.flush();
			osw.close();
			in = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				sbReturn.append(strRead);
				sbReturn.append($line_feed);
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (in != null)
					in.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (br != null)
					br.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null)
					httpConnection.disconnect();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}
}