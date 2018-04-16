package com.gewei.wx.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.gewei.commons.utils.AppUtil;
import com.gewei.wx.util.http.HttpUtil;
import com.gewei.wx.util.http.entity.HttpResult;

/**
 * 
 * <pre>
 * <b>.</b>
 * <b>Description:</b> 
 *    微信工具类
 * <b>Author:</b> tanlibo@iyooc.cn
 * <b>Date:</b> 2016年8月22日上午11:27:00
 * <b>Copyright:</b> Copyright ©1998-2016 iyooc.cn Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver   Date                    Author                           Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2016年8月22日上午11:27:00   tanlibo@iyooc.cn            new file.
 * </pre>
 */
public class WchatUtil {
	protected static final Logger logger = Logger.getLogger(WchatUtil.class);

	// Sha1签名
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

	// 创建签名SHA1
	public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = signParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
			// 要采用URLENCODER的原始值！
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		// System.out.println("sha1之前:" + params);
		// System.out.println("SHA1签名为："+getSha1(params));
		return getSha1(params);
	}

	/**
	 * wx 获取当前时间时分秒
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * wx 生成随机数
	 * 
	 * @return
	 */
	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 32; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	/**
	 * @author tlibo
	 * @date 2015-7-2下午2:29:34
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k) || "return_code".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * @author tlibo
	 * @date 2015-7-2下午2:29:34
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数 主要用于微信支付通知
	 * @return
	 */
	public static String getRequestRepXml(SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("return_msg".equalsIgnoreCase(k) || "return_code".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static void getToken_Tiket(String appid, String appsecret) {

		// 重试次数
		int count = 3;

		while (count > 0) {
			try {
				// 凭证获取（GET）
				String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
				String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
				logger.info("requestUrl: " + requestUrl);
				long s = System.currentTimeMillis();
				// 发起GET请求获取凭证
				HttpResult httpResult = HttpUtil.doGet("atoker", requestUrl);
				String accessTokenStr = httpResult.getText();
				logger.info(((System.currentTimeMillis() - s) * 0.001) + "秒  返回：" + accessTokenStr);

				if (null == accessTokenStr || "".equals(accessTokenStr))
					throw new RuntimeException("get accessToken exception !");
				JSONObject accessTokenJson = JSONObject.parseObject(accessTokenStr);
				if (accessTokenJson != null) {
					// 设置到变量
					Comm.ACCESS_TOKEN = accessTokenJson.getString("access_token");
					s = System.currentTimeMillis();
					String jsapiTicketurl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + Comm.ACCESS_TOKEN + "&type=jsapi";
					HttpResult httpResults = HttpUtil.doGet("atokers", jsapiTicketurl);
					String tokenTiketStr = httpResults.getText();
					logger.info(((System.currentTimeMillis() - s) * 0.001) + "秒  返回：" + tokenTiketStr);
					if (null == tokenTiketStr || "".equals(tokenTiketStr))
						throw new RuntimeException("get tokenTiket exception !");

					JSONObject tokenTiketJson = JSONObject.parseObject(tokenTiketStr);
					if (tokenTiketJson != null) {
						Comm.TICKET = tokenTiketJson.getString("ticket");
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			count--;
		}
	}

	/**
	 * 
	 * @Title: writefile @Description: TODO 把wxtoken 寫入文件 @param @param
	 * path @param @param content @param @throws IOException @return
	 * void @throws
	 */
	public static void writefile(String path, String content) throws IOException {
		FileWriter fileWriter = new FileWriter(path);
		fileWriter.write(String.valueOf(content));
		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * @param path
	 *            文件夹路径
	 */
	public static void isExist(String path) {
		File file = new File(path);
		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 
	 * <pre>
	 * <b>.</b>
	 * <b>Description:</b> 
	 *     验证token有效性
	 * <b>Author:</b> tlibo 973297639@qq.com
	 * <b>Date:</b> 2016年12月2日 下午12:48:01
	 * &#64;return Map<String,Object>
	 * </pre>
	 */
	public static Map<String, Object> vaildToken() {
		// String urls =
		// PropertUtil.getProperties("weixin_url")+"/cgi-bin/material/get_materialcount?access_token="
		// + Variable.ACCESS_TOKEN;
		// logger.info(LogsUtil.LINE2);
		// logger.info("获取素材总数-->url:" + urls);
		// HttpResult httpResult = HttpUtil.doGet("sucai", urls);
		// String result = httpResult.getText();
		// logger.info("获取素材总数-->result:" + result);
		// logger.info(LogsUtil.LINE2);
		// return JSONObject.parseObject(result);
		return null;
	}

	/**
	 * 
	 * <pre>
	 * <b>.</b>
	 * <b>Description:</b> 
	 *   获取用户openId   
	 * <b>Author:</b> tlibo 973297639@qq.com
	 * <b>Date:</b> 2016年12月2日 下午12:42:16
	 * &#64;param appId
	 * &#64;param code
	 * &#64;param app_secrect
	 * &#64;return String
	 * </pre>
	 */
	public static String getOpenId(String appId, String code, String app_secrect) {
		String openId = "";
		String wxUrl = AppUtil.getPropertie("weixin_url") + "/sns/oauth2/access_token?appid=" + appId + "&secret=" + app_secrect + "&code=" + code + "&grant_type=authorization_code";
		logger.info("wxOpenIdUrl:" + wxUrl);
		HttpResult result = HttpUtil.doPost("getopenId", wxUrl, null);
		String res = result.getText();
		JSONObject jsonObject = JSONObject.parseObject(res);
		if (null != jsonObject) {
			openId = jsonObject.getString("openid");
		}
		logger.info("wxOpenIdUrl-->result:" + res);
		return openId;
	}
}
