package com.thoughtworks.Demo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 * 
 * @author Dan
 *
 */
public class TimeUtil {
	// 判断一个时间点是不是整点
	public static boolean IsWhole(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		if ((gc.get(gc.MINUTE) == 0)) {
			return true;
		}
		return false;
	}

	// 判断两个时间是否冲突(两个时间段相交)
	public static boolean IsConflict(Date startTime1, Date startTime2, Date endTime1, Date endTime2) {
		return ((startTime1.getTime() >= startTime2.getTime()) && startTime1.getTime() < endTime2.getTime())
				|| ((startTime1.getTime() >= startTime2.getTime()) && endTime1.getTime() <= endTime2.getTime())
				|| ((startTime2.getTime() >= startTime1.getTime()) && startTime2.getTime() < endTime1.getTime())
				|| ((startTime2.getTime() >= startTime1.getTime()) && endTime2.getTime() <= endTime1.getTime());

	}

	public static boolean LRM(Date left, Date right, Date midle) {

		if (midle.getTime() >= left.getTime() && midle.getTime() <= right.getTime()) {
			return true;
		}
		return false;
	}

	public static int getHours(Date d1, Date d2) {
		Calendar c = Calendar.getInstance();
		c.setTime(d1);
		int h1 = c.get(Calendar.HOUR);
		c.setTime(d2);
		int h2 = c.get(Calendar.HOUR);
		return (h2 - h1);
	}

}
