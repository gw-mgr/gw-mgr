package com.gewei.commons.utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @Description: 工具类
 * @author: Tiger
 * @date: 2018年1月25日 上午10:55:39
 */
public class PropertUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static Calendar cal = Calendar.getInstance();
	private final static String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	public static String getWeek() {
		cal.setTime(new Date());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	public static String getDateYmd() {
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	}
}
