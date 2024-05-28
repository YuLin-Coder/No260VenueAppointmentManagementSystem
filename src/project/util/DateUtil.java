
package project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学习免费 下载
 * 供大家下载学习 参考
 */
public final class DateUtil extends DateUtils {
	private DateUtil() {

	}

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS_zh = "yyyy年MM月dd HH时mm分ss秒";
	public static final String YYYY_MM_DD_HH_MM_zh = "yyyy年MM月dd HH时mm分";
	public static final String YYYY_MM_DD_zh = "yyyy年MM月dd";

	/**
	 * 返回当前日期+时间
	 * 
	 * @return Date 返回该日期
	 */
	public final static Date getNow() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 返回当前日期+时间
	 * 
	 * @return Date 返回该日期
	 */
	public final static String getNow(String pattern) {
		Calendar calendar = Calendar.getInstance();
		if (StringUtils.isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 给指定的日期注入一个开始时间00:00:00 如果date为null 则自动获取当前时间
	 * 
	 * @return Date 返回该日期
	 */
	public final static Date getStartTimeOfDay(Date date) {
		if (date == null) {
			date = getNow();
		}
		return setHours(setMinutes(setSeconds(date, 0), 0), 0);
	}

	/**
	 * 给指定的日期注入一个结束时间23:59:59 如果date为null 则自动获取当前时间
	 * 
	 * @return Date 返回该日期
	 */
	public final static Date getEndTimeOfDay(Date date) {
		if (date == null) {
			date = getNow();
		}
		return setHours(setMinutes(setSeconds(date, 59), 59), 23);
	}

	/**
	 * 转换指定的时间为指定的格式的时间字符串 如果date为null 则自动获取当前时间
	 * 
	 * @param date
	 * @param pattern
	 * @return 格式化后的时间字符串
	 */
	public final static String formatDateToString(Date date, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH_MM_SS;
		}
		if (date == null) {
			date = getNow();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
		return sdf.format(date);
	}

	/**
	 * 转换指定的时间字符串为指定的格式的时间 如果date为null 则自动获取当前时间
	 * 
	 * @param date
	 * @param pattern
	 * @return 格式化后的时间
	 */
	public final static Date formatStringTodate(String date, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH_MM_SS;
		}
		if (date == null) {
			date = formatDateToString(getNow(), YYYY_MM_DD_HH_MM_SS);
		}
		Date returnDate;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			returnDate = sdf.parse(date);
		} catch (ParseException e) {
			returnDate = new Date();
		}
		return returnDate;
	}

	/**
	 * 比较2个日期,返回相差天数
	 * 
	 * @param from
	 *            被减数
	 * @param to
	 *            减数
	 * @return Long 天数
	 */
	public final static Long compareDate(Date from, Date to) {
		if (from == null || to == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (to.getTime() - from.getTime()) / MILLIS_PER_DAY;
	}

	/**
	 * 返回指定日期的月份第一天
	 */
	public final static Date getStartDayOfMonth(Date date) {
		if (date == null) {
			date = getNow();
		}
		return setDays(date, 1);
	}

	/**
	 * 返回指定日期的月份最后一天
	 */
	public final static Date getEndDayOfMonth(Date date) {
		if (date == null) {
			date = getNow();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的年
	 * 
	 * @return Integer 返回年
	 */
	public final static Integer getYear(Date date) {
		if (date == null) {
			date = getNow();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回指定日期的月份
	 * 
	 * @return Integer 返回月份
	 */
	public final static Integer getMonth(Date date) {
		if (date == null) {
			date = getNow();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 返回指定日期的星期几
	 * 
	 * @return Integer 星期几
	 */
	public final static Integer getWeek(Date date) {
		if (date == null) {
			date = getNow();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 返回指定日期的周一
	 */
	public final static Date getStartDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		return getStartTimeOfDay(calendar.getTime());
	}

	/**
	 * 返回指定日期的周日
	 */
	public final static Date getEndDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer weekPlus = calendar.get(Calendar.DAY_OF_WEEK);
		if (weekPlus == 1) {
			weekPlus = -6;
		} else {
			weekPlus = 2 - weekPlus;
		}
		calendar.add(Calendar.DAY_OF_WEEK, weekPlus + 6);

		return getEndTimeOfDay(calendar.getTime());
	}

	/**
	 * 返回指定日期的周日
	 */
	public final static Date calculateDate(Date date, Integer roll) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.roll(Calendar.MONTH, roll);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.DAY_OF_MONTH, -5);
		System.out.println(formatDateToString(calendar.getTime(), null));

	}
}
