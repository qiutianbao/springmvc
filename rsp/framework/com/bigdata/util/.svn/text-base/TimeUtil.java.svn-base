package com.bigdata.util;
/**
 * @author Peter 2009.9.27   
 * @version 1.0
 * Function: 日历统一处理类
 * 1、java中的日期getYear()返回要+1900年，getMonth()要+1天的bug
 * 2、增加了java.util.Date java.sql.Date, Time, Timestamp之间的转换  
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
       /**
	     * 内置时间变量
	     */
	    private Calendar calendar = GregorianCalendar.getInstance();
	    
	    /**
	     * 当前时间
	     */
	    public TimeUtil() {
	        setTime(new Date());
	    }
	    /**
	     * 指定时间
	     * @param date
	     */
	    public TimeUtil(Date date) {
	        setTime(date);
	    }

	    /** *//**
	     * 指定时间
	     * @param timestamp
	     */
	    public TimeUtil(java.sql.Timestamp timestamp) {
	        setTimestamp(timestamp);
	    }
	    
	    /**
	     * 指定　年、月、日
	     * @param year　年：自然年份，如2008
	     * @param month 月：1～12，自然月份，如12月
	     * @param date 日：1～31，如1日
	     */
	    public TimeUtil(int year, int month, int date) {
	        this(year, month, date, 0, 0, 0);
	    }

	    /**
	     * 指定　年、月、日、时、分、秒
	     * @param year 年：自然年份，如2008
	     * @param month 月：1～12，自然月份，如12月
	     * @param date 日：1～31，如1日
	     * @param hrs 时：0～23，如0时
	     * @param min 分：0～59，如59分
	     * @param sec 秒：0～59，如59秒
	     */
	    public TimeUtil(int year, int month, int date, int hrs, int min, int sec) {
	        setTime(year, month, date, hrs, min, sec);
	    }

	    /**
	     * 指定　年、月、日、时、分、秒
	     * @param year 年：自然年份，如2008
	     * @param month 月：1～12，自然月份，如12月
	     * @param date 日：1～31，如1日
	     * @param hrs 时：0～23，如0时
	     * @param min 分：0～59，如59分
	     * @param sec 秒：0～59，如59秒
	     * @param millsec 毫秒：0～9999，如10毫秒
	     */
	    public TimeUtil(int year, int month, int date, int hrs, int min, int sec, int millsec) {
	        setTime(year, month, date, hrs, min, sec, millsec);
	    }  
	    /**
	     * 取年份
	     * @return
	     */
	    public int getYear() {
	        return calendar.get(Calendar.YEAR);
	    }

	    /**
	     * 设置年份
	     * @param year 年份
	     */
	    public void setYear(int year) {
	        calendar.set(Calendar.YEAR, year);
	    }

	    /**
	     * 取月份 1～12，自然月
	     * @return
	     */
	    public int getMonth() {
	        return calendar.get(Calendar.MONTH) + 1; // 月份要加1，因为java是从0~11
	    }

	    /**
	     * 设置月份 1～12，自然月
	     * @param month 自然月 1～12
	     */
	    public void setMonth(int month) {
	        calendar.set(Calendar.MONTH, month - 1); // 月份要-1，因为java是从0~11
	    }

	    /**
	     * 取月内天数 1～31
	     * @return
	     */
	    public int getDate() {
	        return calendar.get(Calendar.DATE);
	    }

	    /**
	     * 设置月内天数 1～31
	     * @param date 天数 1～31
	     */
	    public void setDate(int date) {
	        calendar.set(Calendar.DATE, date);
	    }

	    /**
	     * 取小时 0～23
	     * @return
	     */
	    public int getHour() {
	        return calendar.get(Calendar.HOUR_OF_DAY);
	    }

	    /**
	     * 设置小时 0～23
	     * @param hour 小时 0～23
	     */
	    public void setHour(int hour) {
	        calendar.set(Calendar.HOUR_OF_DAY, hour);
	    }

	    /**
	     * 取分钟 0～59
	     * @return
	     */
	    public int getMinute() {
	        return calendar.get(Calendar.MINUTE);
	    }

	    /** 
	     * 设置分钟 0～59
	     * @param minute 分钟 0～59
	     */
	    public void setMinute(int minute) {
	        calendar.set(Calendar.MINUTE, minute);
	    }

	    /** 
	     * 取秒 0～59
	     * @return
	     */
	    public int getSecond() {
	        return calendar.get(Calendar.SECOND);
	    }

	    /** 
	     * 设置秒 0～59
	     * @param second 秒 0～59
	     */
	    public void setSecond(int second) {
	        calendar.set(Calendar.SECOND, second);
	    }

	    /** 
	     * 取毫秒 0～9999
	     * @return
	     */
	    public int getMilliSecond() {
	        return calendar.get(Calendar.MILLISECOND);
	    }

	    /** 
	     * 设置毫秒
	     * @param millisecond 毫秒 0～9999
	     */
	    public void setMilliSecond(int millisecond) {
	        calendar.set(Calendar.MILLISECOND, millisecond);
	    }
	   


	    /**
	     * 增加时间 如加1天 add(TimeUtil.DATE, 1)
	     * @param field 字段：YEAR, MONTH, DATE=DATE_OF_MONTH, HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND
	     * @param amount 增加量
	     *
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DATE
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #MILLISECOND
	     */
	    public void add(int field, int amount) {
	        calendar.add(field, amount);
	    }

	    /** 
	     * 保留年月日，清除时间部分
	     */
	    public void clearTime() {
	        calendar.clear(Calendar.HOUR_OF_DAY);
	        calendar.clear(Calendar.MINUTE);
	        calendar.clear(Calendar.SECOND);
	        calendar.clear(Calendar.MILLISECOND);
	    }    

	    /** 
	     * 指定　年、月、日
	     * @param year 年：自然年份，如2008
	     * @param month 月：1～12，自然月份，如12月
	     * @param date 日：1～31，如1日
	     */    
	    public void setTime(int year, int month, int date) {
	        setTime(year, month, date, 0, 0, 0);
	    }

	    /**
	     * 指定　年、月、日、时、分、秒
	     * @param year 年：自然年份，如2008
	     * @param month 月：1～12，自然月份，如12月
	     * @param date 日：1～31，如1日
	     * @param hrs 时：0～23，如0时
	     * @param min 分：0～59，如59分
	     * @param sec 秒：0～59，如59秒
	     */    
	    public void setTime(int year, int month, int date, int hrs, int min, int sec) {
	        calendar.clear();
	        month = month - 1;// 月份要减1，因为java是从0~11
	        calendar.set(year, month, date, hrs, min, sec);
	    }

	    /**
	     * 指定　年、月、日、时、分、秒、毫秒
	     * @param year 年：自然年份，如2008
	     * @param month 月：1～12，自然月份，如12月
	     * @param date 日：1～31，如1日
	     * @param hrs 时：0～23，如0时
	     * @param min 分：0～59，如59分
	     * @param sec 秒：0～59，如59秒
	     * @param millsec 毫秒：0～9999，如10毫秒
	     */    
	    public void setTime(int year, int month, int date, int hrs, int min, int sec, int millsec) {
	        calendar.clear();
	        month = month - 1;// 月份要减1
	        calendar.set(year, month, date, hrs, min, sec);
	        calendar.set(Calendar.MILLISECOND, millsec);
	    }

	    /**
	     * 指定时间
	     * @param date
	     */
	    public void setTime(Date date) {
	        calendar.setTime(date);
	    }

	    /**
	     * 指定时间
	     * @param timestamp
	     */
	    public void setTimestamp(java.sql.Timestamp timestamp) {
	        calendar.setTimeInMillis(timestamp.getTime());
	    }
	    
	    /** *//**
	     * 从字符串解释时间
	     * @param datetime 时间
	     * @param partten 格式
	     */
	    public void parseDate(String datetime, String partten) {
	        SimpleDateFormat formatter = new SimpleDateFormat(partten);
	        try {
	            this.calendar.setTime(formatter.parse(datetime));
	            this.calendar.setTimeInMillis(new java.sql.Timestamp(formatter.parse(datetime).getTime()).getTime());
	            //return this;
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	     
	    /** 
	     * 转成字符串：格式yyyy-MM-dd HH:mm:ss
	     * @return
	     */	    
	    public String toString() {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return format.format(calendar.getTime());
	    }

	    /**
	     * 获取字符串
	     * @param partten 格式
	     * @return
	     */
	    public String stringValue(String partten) {
	        SimpleDateFormat format = new SimpleDateFormat(partten);
	        return format.format(calendar.getTime());
	    }

	    /**
	     * 获取 java.util.Date
	     * @return
	     */
	    public Date dateValue() {
	        return calendar.getTime();	        
	    }	    

	    /**
	     * 获取 java.util.Date
	     * @return
	     */
	    public static Date dateValue(String datetime) {
	    	if(datetime==null || datetime.equals(""))
	    		return null;
	    	DateFormat df = DateFormat.getDateInstance();
	    	try {
	    		return  df.parse(datetime);
			} catch (ParseException e) {				
				e.printStackTrace();
				return null;
			}	        
	    }

	    /**
	     * 获取 java.sql.Date
	     * @return
	     */
	    public java.sql.Date sqldateValue() {
	        return new java.sql.Date(calendar.getTime().getTime());
	    }
	    
	    /**
	     * 获取 java.sql.Time
	     * @return
	     */
	    public java.sql.Time sqltimeValue() {
	        return new java.sql.Time(calendar.getTime().getTime());
	    }
	    
	    /**
	     * 获取 java.sql.Timestamp
	     * @return
	     */
	    public java.sql.Timestamp timestampValue(){
	        return new java.sql.Timestamp(calendar.getTime().getTime());
	    }
	    
	    
 
	     /**
	     * 年[字段常量]，用于 add函数.
	      * 
	     * @see #MONTH
	     * @see #DATE
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int YEAR = 1;

	    /**
	     * 月份[字段常量]，用于 add函数.
	     * 
	     * @see #YEAR
	     * @see #DATE
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int MONTH = 2;

	    /**
	     * 天[字段常量]，用于 add函数. 与<code>DATE_OF_MONTH</code>等同
	     * 
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int DATE = 5;

	    /**
	     * 天[字段常量]，用于 add函数 与<code>DATE</code>等同.
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int DAY_OF_MONTH = 5;

	    /**
	     * 小时[字段常量]，0~23，用于 add函数
	     * 
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DATE
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int HOUR_OF_DAY = 11;

	    /**
	     * 分钟[字段常量]，用于 add函数
	     * 
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DATE
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #SECOND
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int MINUTE = 12;

	    /**
	     * 秒[字段常量]，用于 add函数
	     * 
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DATE
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #MILLISECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int SECOND = 13;

	    /**
	     * 微秒[字段常量]，用于 add函数
	     * 
	     * @see #YEAR
	     * @see #MONTH
	     * @see #DATE
	     * @see #DAY_OF_MONTH
	     * @see #HOUR_OF_DAY
	     * @see #MINUTE
	     * @see #SECOND
	     * @see #add(int field, int amount)
	     */
	    public final static int MILLISECOND = 14;	
	    
	    /**
	     * 根据传入的Calendar获取周一的日期
	     * 
	     * @param calendar
	     * @return String
	     * @author jameson.fang
	     * @time :2011-4-21 下午12:54:59
	     */
	    public static String getFirstDayOfWeek(Calendar calendar){
	    	calendar = calendar==null?GregorianCalendar.getInstance():calendar;
	    	int curDay =  calendar.get(calendar.DAY_OF_WEEK);
	    	if(curDay==1){
	    		calendar.add(calendar.DATE, -6);
	    	}else
	    		calendar.add(calendar.DATE, 2-curDay);
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        return format.format(calendar.getTime());
	    }
	    
	    public static void main(String[] args) {
			System.out.println(getCurrentWeek());
		}
	    /**
	     * 获取当前的星期
	     * @return 大写的星期：比如 星期一，星期二
	     */
	    public static String getCurrentWeek(){
			Calendar calendar=GregorianCalendar.getInstance();
			String currentWeek="";
			switch (calendar.get(calendar.DAY_OF_WEEK)) {
				case 1:
					currentWeek="星期日";
					break;
				case 2:
					currentWeek="星期一";
					break;
				case 3:
					currentWeek="星期二";
					break;
				case 4:
					currentWeek="星期三";
					break;
				case 5:
					currentWeek="星期四";
					break;
				case 6:
					currentWeek="星期五";
					break;
				case 7:
					currentWeek="星期六";
					break;
				default:
					break;
			}
	    	return currentWeek;
	    }
	   
	    /**
	     * 根据传入的Calendar获取周日的日期
	     * 
	     * @param calendar
	     * @return String
	     * @author jameson.fang
	     * @time :2011-4-21 下午12:54:45
	     */
	    public static String getLastDayWeek(Calendar calendar){
	    	calendar = calendar==null?GregorianCalendar.getInstance():calendar;
	    	int curDay =  calendar.get(calendar.DAY_OF_WEEK);
	    	if(curDay!=1){
	    		calendar.add(calendar.DATE, 8-curDay);
	    	}
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        return format.format(calendar.getTime());
	    }
	    
	    /**
	     * 根据数据库中取出的时间转换为yyyy-MM-dd HH:mm:ss的字符串
	     * str:数据库中取出的时间
	     * @param str
	     * @return
	     * @throws java.text.ParseException
	     */
	    public static String getDateStr(String str) throws ParseException{
	    	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	    	String returnStr=str==null?"":formatter.format(formatter.parse(str));  
	    	return returnStr;
	    }
	    
		/**
		 * 处理日期，将日期转化为符合阅读习惯的字符串
		 * @Description: TODO
		 * @param date1:开始日期
		 * @param date2:结束日期
		 * @return
		 * @throws java.text.ParseException
		 */
		public static String dealDate(String date1,String date2) throws ParseException{
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
			if(date1!=null)
				date1=formatter.format(formatter.parse(date1));
			if(date2!=null)
				date2=formatter.format(formatter.parse(date2));
			if(date1==null && date2==null)
				return "";
			if(date1!=null && date2==null)
				return "起始于"+date1;
			if(date1==null && date2!=null)
				return "截止于"+date2;
			if(date1!=null && date2!=null)
				return (date1+"至"+date2);
			return "";
		}
		
		/**
		 * 处理日期，将日期转化为符合阅读习惯的字符串
		 * @Description: TODO
		 * @param date1:开始日期
		 * @param date2:结束日期
		 * @return
		 * @throws java.text.ParseException
		 */
		public static String dealStrToDate(String date){
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
			if(date!=null){
				try {
					date=formatter.format(formatter.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return (date);
			}
			return "";
		}
		
		/**
		 * @Description: 时间字符串转化为  年-月-日 时:分:秒  的格式
		 * @param date
		 * @return
		 * @throws java.text.ParseException
		 */
		public static String dealStrToTime(String date){
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			if(date!=null){
				try {
					date=formatter.format(formatter.parse(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return (date);
			}
			return "";
		}
	         
		/**
		 * 比较两个日期
		 * 
		 * @param a
		 *            :第一个日期
		 * @param b
		 *            :第二个日期
		 * @return 相同返回TRUE，否则返回false
		 */
		public Boolean compare(Date a, Date b) {
			if (a == null && b == null)
				return true;
			if (a == null && b != null)
				return false;
			if (a != null && b == null)
				return false;
			if (a != null && b != null && a.equals(b))
				return true;
			return false;
		}
		
		
	    /**
	     * 获取上周的周一的日期
	     * 
	     * @return String
	     * @author jameson.fang
	     * @time :2011-4-21 下午12:54:11
	     */
	    public static String getFirstDayOfPreWeek(){
	    	return getFirstDayOfWeek(getCalendar(-7));
	    }
	    /**
	     * 获取上周的周日的日期
	     * 
	     * @return String
	     * @author jameson.fang
	     * @time :2011-4-21 下午12:54:11
	     */
	    public static String getLastDayOfPreWeek(){
	    	return getLastDayWeek(getCalendar(-7));
	    }
	    /**
	     * 得到当前周的周一日期
	     * @return
	     * String
	     * 2011-4-20 下午08:34:03
	     */
	    public static String getFirstDayOfCurWeek(){
	    	return getFirstDayOfWeek(null);
	    }
	    /**
	     * 获取当前周的周日的日期
	     * @return String
	     * @time :2011-4-20 下午09:12:12
	     */
	    public static String getLastDayOfCurWeek(){
	    	return getLastDayWeek(null);
	    }
	    /**
	     * 获取下周的最后一天的日期
	     * @return String
	     * @author jameson.fang
	     * @time :2011-4-22 上午08:34:12
	     */
	    public static String getLastDayOfNextWeek(){
	    	return getLastDayWeek(getCalendar(7));
	    }
	    /**
	     * 获取下周第一天的日期
	     * @return String
	     * @author jameson.fang
	     * @time :2011-4-22 上午08:35:50
	     */
	    public static String getFirstDayOfNextWeek(){
	    	return getFirstDayOfWeek(getCalendar(7));
	    }
	    /**
	     * 根据传入的天数，对当前时间进行加或减
	     * @param day 正数或者负数，用于到当前时间的加或减
	     * @return Calendar
	     * @author jameson.fang
	     * @time :2011-4-22 上午08:38:12 
	     */
	    public static Calendar getCalendar(int day){
	    	Calendar calendar = GregorianCalendar.getInstance() ;
	    	calendar.add(calendar.DATE, day);
	    	return calendar ;
	    }
		/**
		 * @Description: 将格式装化成yyyy-MM-dd格式的字符串
		 * @param date 时间类型的字符串
		 * @return stringDate （yyyy-MM-dd格式的字符串）
		 * @throws java.text.ParseException
		 */
		public static String formatDateToString(String date) throws ParseException{
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			String stringDate = (String)sdf.format(sdf.parse(date));
			return stringDate;
		}
	    
	    /**
	     * @Description: 获取当前的年份
	     * @return
	     * @throws java.text.ParseException
	     */
		public static String getTempYear(){
			Calendar calendar=Calendar.getInstance();
			int year=Calendar.getInstance().get(calendar.YEAR);
			return String.valueOf(year);
		}
		
		/**
		 * @Description: 获取过去的N年的年份
		 * @param length
		 * @return
		 */
		public static int[] getPastYears(int length){
			if(length<3)
				length=3;
			Calendar calendar=Calendar.getInstance();
			int year=Calendar.getInstance().get(calendar.YEAR);
			int[] years=new int[length];
			for(int i=length-1;i>=0;i--){
				years[length-1-i]=year-i;
			}
			return years;
		}
		
		/**
		 * @Description: TODO
		 * @param resouce 给定的时间字符串
		 * @param format 给定的解析的格式
		 * @return 解析之后的dat额对象 如果解析出错则返回当前时间 new Date();
		 */
		public static Date parserStringToDate(String resouce,String format){
			if("".equals(format)||format==null){
				format = "yyyy-MM-dd" ;
			}
			SimpleDateFormat sdf = new SimpleDateFormat (format);
			try {
				return sdf.parse(resouce) ;
			} catch (ParseException e) {
				e.printStackTrace();
				return new Date();
			}
		}
	
    /**
     * 时间字符串转换
     * 如果为空，则不处理，返回空，否则转化为Date类型
     * @param dateStr
     * @return
     */
	public static Date parserString2Date(String dateStr) {
		if (null != dateStr && !"".equals(dateStr)) {
			String format = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();

			}
		}

		return null;
	}
		
		/**
		 * @Description: TODO
		 * @param resouce 给定的时间字符串
		 * @param format 给定的解析的格式
		 * @return 解析之后的dat额对象 如果解析出错则返回当前时间 new Date();
		 */
		public static Date parserStringToDate(String resouce){
			return parserStringToDate(resouce, null);
		}
		
		
		
		/**
		 * @Description: 把时间按照format 格式，格式化
		 * @param date 时间对象
		 * @param format  格式化字符串
		 * @return
		 */
		public static String dateToString(Date date ,String format){
			SimpleDateFormat sdf = new SimpleDateFormat (format);
			return sdf.format(date);
		}
		/**
		 * @Description: 把当前时间格式化为yyyy-MM-dd HH:mm:ss格式
		 * @return String yyyy-MM-dd HH:mm:ss
		 */
		public static String dateToString(){
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			return sdf.format(new Date());
		}
		/**
		 * @Description: 获取当前的时间段（上午/下午）
		 * @return
		 */
		public static String noon(){
			GregorianCalendar timeRange=new GregorianCalendar();
			return timeRange.get(GregorianCalendar.AM_PM)==0?"上午":"下午";
		}
		/**
         * @Description: 把时间按照format 格式，格式化
         * @param date 时间对象
         * @param format  格式化字符串
         * @return
         */
        public static String dateToString(Date date ){
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
//        	//修改成年-月-日   2012-02-14	jameson
//            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
            return sdf.format(date);
        }
        /**
         * 制作六位长度的日期字符
         * @Description: 六位长度的字符：110929
         * @return
         */
        public static String getsixcharformatdate(){
        	String y = TimeUtil.getTempYear().substring(2);
        	String m=new TimeUtil().getMonth()+"",d=new TimeUtil().getDate()+"";
        	int im = new TimeUtil().getMonth(),id=new TimeUtil().getDate();
    		if(new TimeUtil().getMonth()<10){
    			m = "0"+im;
    		}
    		if(new TimeUtil().getDate()<10){
    			d = "0"+id;
    		}
        	return y+m+d;
        }
	} 