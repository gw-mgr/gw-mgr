package com.gewei.commons.utils;

import java.util.Collection;
import java.util.UUID;

/**
 * 继承自Spring util的工具类，减少jar依赖
 * @author L.cm
 */
public class StringUtils extends org.springframework.util.StringUtils {
	public static boolean isBlank(final CharSequence cs) {
		return !StringUtils.isNotBlank(cs);
	}

	//判断不为null 并且不为空
	public static boolean isNotNullEmptyStr(String str) {
		return !StringUtils.isNullOrEmptyStr(str);
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return StringUtils.hasText(cs);
	}

	public static String join(Collection<?> coll, String delim) {
		return StringUtils.collectionToDelimitedString(coll, delim);
	}

	public static String join(Object[] arr, String delim) {
		return StringUtils.arrayToDelimitedString(arr, delim);
	}

	//判断为null 或者 为空
	public static boolean isNullOrEmptyStr(String str) {
		return str == null || StringUtils.EMPTY_STRING.equals(str);
	}

	/**
	 * 生成uuid
	 * @return UUID
	 */
	public static String getUUId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static final String EMPTY_STRING = "";

	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str.replace("{" + i + "}", params[i] == null ? StringUtils.EMPTY_STRING : params[i]);
		}
		return str;
	}
}
