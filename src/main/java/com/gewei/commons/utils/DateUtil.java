package com.gewei.commons.utils;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 时间/日期工具类
 * @author JOJIN
 * @date 2015年8月19日 上午10:00:16
 */
public class DateUtil {
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String chMMdd = "MM月dd日";
	public static final String ch_yyyy_MM_dd = "yyyy-MM-dd";
	/** 星期字典, 默认从1开始即代表星期一, 0结束即代表星期天 */
	public static final String WEEKS_DIGITS[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * @Title: get_String$yyyyMMddHHmmss  
	 * @Description: 根据date型时间获取字符串时间
	 * @param @param date_yyyyMMddHHmmss
	 * @param @return 
	 * @return String 
	 * @throws
	 */
	public static String get_String$yyyyMMddHHmmss(java.util.Date date_yyyyMMddHHmmss) {
		String dateStr = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			dateStr = formatter.format(date_yyyyMMddHHmmss);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateStr;
	}

	public static Long getDate2Long$yyyyMMddHHmmss(java.util.Date date) {
		Long Result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			Result = Long.parseLong(formatter.format(date));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Result;
	}

	/**
	 * @Title: get_Date$yyyyMMddHHmmss  
	 * @Description: 字符串转换为时间格式
	 * @param @param date_yyyy_MM_dd_HH_mm_ss
	 * @param @return 
	 * @return java.util.Date 
	 * @throws
	 */
	public static java.util.Date get_Date$yyyyMMddHHmmss(String date_yyyy_MM_dd_HH_mm_ss) {
		java.util.Date result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
		try {
			result = formatter.parse(date_yyyy_MM_dd_HH_mm_ss);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: get_String$yyyyMMddHHmmss  
	 * @Description: 根据long型时间获取字符串时间
	 * @param @param date_yyyyMMddHHmmss
	 * @param @return 
	 * @return String 
	 * @throws
	 */
	public static String get_String$yyyyMMddHHmmss(long date_yyyyMMddHHmmss) {
		long millis = get_Long$TimeMillis(date_yyyyMMddHHmmss);
		java.util.Date result = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			result = calendar.getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String dateStr = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
		try {
			dateStr = formatter.format(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * @Title: get_String$yyyyMMddHHmm 
	 * @Description: 根据long型时间获取字符串时间    yyyy_MM_dd hh:mm
	 * @param @param date_yyyyMMddHHmm
	 * @param @return 
	 * @return String 
	 * @throws
	 */
	public static String get_String$yyyyMMddHHmm(String date_yyyyMMddHHmmss) {
		Long date = Long.valueOf(date_yyyyMMddHHmmss);
		long millis = get_Long$TimeMillis(date);
		java.util.Date result = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			result = calendar.getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String dateStr = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyy_MM_dd_HH_mm);
		try {
			dateStr = formatter.format(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * @Title: get_Long$yyyyMMddHHmmss
	 * @Description: TODO
	 * @param @param date
	 * @param @return
	 * @return Long
	 * @throws
	 */
	public static Long get_Long$yyyyMMddHHmmss(java.util.Date date) {
		Long result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			result = Long.parseLong(formatter.format(date));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: get_Long$yyyyMMddHHmmss  
	 * @Description: 根据毫秒数返回数字日期
	 * @param @param timeMillis
	 * @param @return 
	 * @return Long 
	 * @throws
	 */
	public static Long get_Long$yyyyMMddHHmmss(long millis) {
		Long result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			result = Long.parseLong(formatter.format(calendar.getTime()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: Long$currentTimeMillis
	 * @Description: 获取系统当前时间
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long get_Long$currentTimeMillis() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}

	/**
	 * @Title: Long$TimeMillis
	 * @Description: TODO
	 * @param @param date
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long get_Long$TimeMillis(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * @Title: Long$TimeMillis
	 * @Description: 根据指定时间获取时分秒
	 * @param @param date
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long get_Long$TimeMillis(long date_yyyyMMddHHmmss) {
		java.util.Date result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			result = formatter.parse(String.valueOf(date_yyyyMMddHHmmss));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(result);
		return calendar.getTimeInMillis();
	}

	/**
	 * @Title: getWeekFromLong
	 * @Description: 获取根据14位日期获取中文星期
	 * @param @param DateValue
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getCHWeekFromLong(long DateValue) {
		java.util.Date result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			result = formatter.parse(String.valueOf(DateValue));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(result);
		return WEEKS_DIGITS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * @Title: getCHMMddFromLong
	 * @Description: 获取根据14位日期获取中文月日
	 * @param @param DateValue
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getCHMMddFromLong(long DateValue) {
		java.util.Date result = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMddHHmmss);
		try {
			result = formatter.parse(String.valueOf(DateValue));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		formatter = new java.text.SimpleDateFormat(chMMdd);
		return formatter.format(result);
	}

	public static String getDateCH_YYYY_MM_DD() {
		return new SimpleDateFormat(DateUtil.ch_yyyy_MM_dd).format(new Date());
	}

	public static String getDateYmd() {
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	}

	/**
	   * 将长时间格式yyyyMMddHHmmss字符串转换为短时间格式字符串 yyyy-MM-dd
	   * @param strDate
	   * @return
	   */
	public static String strToShot(String strDate) {
		if (null == strDate || "".equals(strDate)||strDate.length() < 8) {
			return "";
		}
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
		ParsePosition pos = new ParsePosition(0);
		if (strDate.length() < 14) {
			strDate = strDate.substring(0, 8) + "000000";
		}
		Date strtodate = formatter1.parse(strDate, pos);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter2.format(strtodate);
		return dateString;
	}

	/**
	   * 将长短时间格式 yyyy-MM-dd字符串转换为长时间格式字符串 yyyyMMddHHmmss
	   * @param strDate
	   * @return
	   */
	public static String strToLong(String strDate) {
		if (null == strDate || "".equals(strDate)) {
			return "";
		}
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strDate = strDate + " 00:00:00";
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter1.parse(strDate, pos);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter2.format(strtodate);
		return dateString;
	}

	public static void main(String[] args) throws Exception {
		long a1 = get_Long$yyyyMMddHHmmss(new Date());
		System.out.println("a1:" + a1);
		long a2 = get_Long$TimeMillis(a1) + 1 * 60 * 1000;
		System.out.println("a2:" + a2);
		long a3 = get_Long$yyyyMMddHHmmss(a2);
		System.out.println("a3:" + a3);
		System.out.println("a3:" + strToLong("2018-03-21"));
		BigDecimal b = new BigDecimal(Float.valueOf("0.23"));
		BigDecimal c = new BigDecimal(Float.toString(100f));
		System.out.println((b.multiply(c).longValue()));
	}
}
