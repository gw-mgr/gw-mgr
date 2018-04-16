package com.gewei.commons.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: UUIDUtil
 * @Description: 系统编号生成类
 * @author JOJIN
 * @date 2015年11月11日 下午4:20:45
 * @version V2.0
 */
public class UUIDUtil {
	/**
	 * @Title: getUUID32 @Description: 获取32位UUID @param @return @return
	 * String @throws
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * @Title: getUUID36 @Description: 获取36位UUID @param @return @return
	 * String @throws
	 */
	public static String getUUID36() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @Title: getFormatCode @Description: TODO @param @param radix
	 * 前缀 @param @param value 值 @param @param valueLen
	 * 格式化位数 @param @return @return String
	 *
	 * eg: getFormatCode("5",30,6) ---> 500030 getFormatCode("5",76,6) --->
	 * 500076 @throws
	 */
	public synchronized static String getFormatCode(String radix, long value, int valueLen) {
		String num = "";
		int inx = String.valueOf(value).length() + radix.length();
		if (inx < 0) {
			inx = 0;
		}
		for (int i = valueLen - inx; 0 < i; i--) {
			num += "0";
		}
		num += value;
		num = radix + num;
		return num;
	}

	public synchronized static Long getRandomCode(int len) {
		if (10 < len || len < 1) {
			throw new IllegalArgumentException("传入的参数必须在1-10之间");
		}
		double a = 10, b = len;
		int radix1 = (int) Math.pow(a, b);
		int radix2 = (int) Math.pow(a, b - 1);
		return (long) (new Random().nextInt(radix1 - radix2) + radix2);
	}
}
