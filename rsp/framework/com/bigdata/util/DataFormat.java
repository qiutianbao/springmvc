package com.bigdata.util;

/*
 * Created on 2003-8-7
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * @author jameson.fang
 */
public class DataFormat {
	
	private static final Logger logger = Logger.getLogger(DataFormat.class);
	
	/**
	 * 反向格式化金额，将","去掉
	 * 
	 * @param strData
	 *            数据
	 */
	public static String reverseFormatAmount ( String strData )
	{
		int i ;
		String strTemp ;
		//去掉所有的","
		strTemp = new String ( strData ) ;
		strData = "" ;
		for (i = 0; i < strTemp.length ( ); i++)
		{
			char cData ;
			cData = strTemp.charAt ( i ) ;
			if (cData != ',')
			{
				strData = strData + cData ;
			}
		}
		return strData ;
	}
	
	/**
	 * 格式化利率(double)
	 * 
	 * @param fValue
	 *            利率值
	 */
	public static String formatRate ( double dValue )
	{
		if (dValue == 0)
		{
			return "0.000000" ;
		} else
		{
			return format ( dValue , 6 ) ;
		}
	}
	public static String format ( double dValue , int lScale )
	{
		//		////负数，则装化为正数后进行四舍五入
		boolean bFlag = false ;
		if (dValue < 0)
		{
			bFlag = true ;
			dValue = -dValue ;
		}
		long lPrecision = 10000 ; //精度值，为了避免double型出现偏差，增加校验位
		long l45Value = lPrecision / 2 - 1 ; //四舍五入的判断值
		long lLength = 1 ; //乘的数字
		for (int i = 0; i < lScale; i++)
		{
			lLength = lLength * 10 ; //比如保留2位，乘以100
		}
		long lValue = (long) dValue ; //值的整数部分
		long lValue1 = (long) ((dValue - lValue) * lLength) ; //乘以保留位数
		long lValue2 = (long) ((dValue - lValue) * lLength * lPrecision) ; //
		long lLastValue = lValue2 - (lValue2 / lPrecision) * lPrecision ;
		if (lLastValue >= l45Value)
		{
			lValue1++ ;
		}
		dValue = lValue + (double) lValue1 / lLength ; //四舍五入后的值
		if (bFlag)
		{
			dValue = -dValue ;
		}
		BigDecimal bd = new BigDecimal ( dValue ) ;
		bd = bd.setScale ( lScale , BigDecimal.ROUND_HALF_UP ) ;
		return bd.toString ( ) ;
		//Replace by Huang Ye
		//double d = UtilOperation.Arith.round(dValue, lScale);
		//return String.valueOf(dValue);
	}
	
	
	/**
	 * 格式化字符串，将空字符串或null转换为""
	 * 
	 * @param strData
	 *            需要格式化的字符串
	 * @return String
	 */
	public static String formatNullString ( String strData )
	{
		if (strData == null || strData.length ( ) == 0 || strData.equals("null"))
		{
			return "" ;
		} else
		{
			return strData.trim() ;
		}
	}
	
	/**
	 * 格式化数字，例如：12345转化为12,345
	 * 
	 * @param dValue
	 *            被格式化的数值 
	 * @param iScale
	 *            小数点后保留位数,不足补0
	 * @return
	 */
	public static String formatNumber ( double dValue , int iScale )
	{
		DecimalFormat df = null ;
		StringBuffer sPattern = new StringBuffer ( "##0" ) ;
		if (iScale > 0)
		{
			sPattern.append ( "." ) ;
			for (int i = 0; i < iScale; i++)
			{
				sPattern.append ( "0" ) ;
			}
		}
		df = new DecimalFormat ( sPattern.toString ( ) ) ;
		return df.format ( dValue ) ;
	}
	
	/**
	 * 解析格式化的字符串，转化为数值，例如：12,345.00转化为12345
	 * 
	 * @param text
	 *            被格式化的数值
	 * @return
	 */
	public static double parseNumber ( String text )
	{
		if(text.indexOf("ERROR")>=0||text.trim().equals("")){
			return Double.parseDouble("0") ;
		}
		if(text.indexOf("%")>=0){
			text = text.replaceAll("%", "") ;
		}
		if(text.indexOf("(")>=0||text.indexOf(")")>=0){
			text = text.substring(text.indexOf("(")+1);
			text = text.substring(0,text.indexOf(")"));
		}
		int index = text.indexOf ( "," ) ;
		String sbNumber = "" ;
		sbNumber = text.replaceAll(",", "") ;
		return Double.parseDouble ( sbNumber ) ;
	}
	
	/**
	 * the following const is to define date format.
	 */
	public static final int	FMT_DATE_YYYYMMDD			= 1 ;
	public static final int	FMT_DATE_YYYYMMDD_HHMMSS	= 2 ;
	public static final int	FMT_DATE_HHMMSS				= 3 ;
	public static final int	FMT_DATE_HHMM				= 4 ;
	public static final int	FMT_DATE_SPECIAL    		= 5 ;
	/**
	 * this function is to format date into a string @ param date the date to
	 * be formatted.
	 * 
	 * @param nFmt
	 *            FMT_DATE_YYYYMMDD to format string like 'yyyy-mm-dd' or to
	 *            format string like 'yyyy-mm-dd hh:mm:ss'
	 * @return String
	 */
	public static String formatDate ( Date date , int nFmt )
	{
		SimpleDateFormat fmtDate = new SimpleDateFormat ( ) ;
		switch (nFmt)
		{
			default :
			case DataFormat.FMT_DATE_YYYYMMDD :
				fmtDate.applyPattern ( "yyyy-MM-dd" ) ;
				break ;
			case DataFormat.FMT_DATE_YYYYMMDD_HHMMSS :
				fmtDate.applyPattern ( "yyyy-MM-dd HH:mm:ss" ) ;
				break ;
			case DataFormat.FMT_DATE_HHMM :
				fmtDate.applyPattern ( "HH:mm" ) ;
				break ;
			case DataFormat.FMT_DATE_HHMMSS :
				fmtDate.applyPattern ( "HH:mm:ss" ) ;
				break ;
			case DataFormat.FMT_DATE_SPECIAL :
				fmtDate.applyPattern ( "yyyyMMdd" ) ;
				break ;
		}
		return fmtDate.format ( date ) ;
	}
	/**
	 * parse date from string with specific format.
	 * 
	 * @param strDate
	 *            a date string
	 * @param nFmtDate
	 *            specific date string format defined in this class.
	 * @exception raise
	 *                ParseException, if string format dismathed.
	 * @return Date,
	 */
	public static Date parseDate ( String strDate , int nFmtDate )
			throws Exception
	{
		SimpleDateFormat fmtDate = new SimpleDateFormat ( ) ;
		switch (nFmtDate)
		{
			default :
			case DataFormat.FMT_DATE_YYYYMMDD :
				fmtDate.applyLocalizedPattern ( "yyyy-MM-dd" ) ;
				break ;
			case DataFormat.FMT_DATE_YYYYMMDD_HHMMSS :
				fmtDate.applyLocalizedPattern ( "yyyy-MM-dd HH:mm:ss" ) ;
				break ;
			case DataFormat.FMT_DATE_HHMM :
				fmtDate.applyLocalizedPattern ( "HH:mm" ) ;
				break ;
			case DataFormat.FMT_DATE_HHMMSS :
				fmtDate.applyLocalizedPattern ( "HH:mm:ss" ) ;
				break ;
		}
		return fmtDate.parse ( strDate ) ;
	}
	/**
	 * parse date from string with specific format.
	 * 
	 * @param strDate
	 *            a date string
	 * @param nFmtDate
	 *            specific date string format defined in this class.
	 * @exception raise
	 *                ParseException, if string format dismathed.
	 * @return Date,
	 */
	public static Date parseUtilDate ( String strDate , int nFmtDate )
			throws Exception
	{
		SimpleDateFormat fmtDate = null ;
		switch (nFmtDate)
		{
			default :
			case DataFormat.FMT_DATE_YYYYMMDD :
				fmtDate = new SimpleDateFormat ( "yyyy-MM-dd" ) ;
				break ;
			case DataFormat.FMT_DATE_YYYYMMDD_HHMMSS :
				fmtDate = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" ) ;
				break ;
			case DataFormat.FMT_DATE_HHMM :
				fmtDate = new SimpleDateFormat ( "HH:mm" ) ;
				break ;
			case DataFormat.FMT_DATE_HHMMSS :
				fmtDate = new SimpleDateFormat ( "HH:mm:ss" ) ;
				break ;
		}
		return fmtDate.parse ( strDate ) ;
	}
	
	/**
	 * construct a timestamp through year,month,day,hour,minute,second
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	static public Timestamp getDateTime ( int year , int month ,
			int day , int hour , int minute , int second )
	{
		Timestamp ts = null ;
		Date dt = null ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.set ( year , month - 1 , day , hour , minute , second ) ;
		calendar.set(Calendar.MILLISECOND, 0);//毫秒置为0
		dt = calendar.getTime ( ) ;
		ts = new Timestamp ( dt.getTime ( ) ) ;
		return ts ;
	}
	
	/**
	 * convert Timestamp to string "yyyy-mm-dd hh:mm:ss.fffffffff"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getDateTimeString ( Timestamp ts )
	{
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		/*
		return calendar.get ( Calendar.YEAR ) + "-"
				+ (calendar.get ( Calendar.MONTH ) + 1) + "-"
				+ calendar.get ( Calendar.DATE ) + " "
				+ calendar.get ( Calendar.HOUR_OF_DAY ) + ":"
				+ calendar.get ( Calendar.MINUTE ) + ":"
				+ calendar.get ( Calendar.SECOND ) ;
		*/

		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		if (strMonth.length ( ) == 1)
		{
			strMonth = "0" + strMonth ;
		}
		String strDay = String.valueOf ( calendar.get ( Calendar.DATE ) ) ;
		if (strDay.length ( ) == 1)
		{
			strDay = "0" + strDay ;
		}
		String strHour = String.valueOf ( calendar.get ( Calendar.HOUR_OF_DAY ) ) ;
		if (strHour.length ( ) == 1)
		{
		    strHour = "0" + strHour ;
		}
		String strMinute = String.valueOf ( calendar.get ( Calendar.MINUTE ) ) ;
		if (strMinute.length ( ) == 1)
		{
		    strMinute = "0" + strMinute ;
		}
		String strSecond = String.valueOf ( calendar.get ( Calendar.SECOND ) ) ;
		if (strSecond.length ( ) == 1)
		{
		    strSecond = "0" + strSecond ;
		}
		return calendar.get ( Calendar.YEAR ) + "-" + strMonth + "-" + strDay + " " + strHour + ":" + strMinute + ":" + strSecond;
		//return ts.toString();
	}
	/**
	 * convert Timestamp to string "yyyy-mm-dd hh:mm:ss.fffffffff"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getTimeString ( Timestamp ts )
	{
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		return calendar.get ( Calendar.HOUR_OF_DAY ) + ":"
				+ calendar.get ( Calendar.MINUTE ) ;
		//return ts.toString();
	}
	/**
	 * convert Timestamp to string "hh:mm:ss"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getFullTimeString ( Timestamp ts )
	{
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		String temp1 = calendar.get(Calendar.HOUR_OF_DAY)<10?"0"+calendar.get(Calendar.HOUR_OF_DAY):calendar.get(Calendar.HOUR_OF_DAY)+"";
		String temp2 = calendar.get(Calendar.MINUTE)<10?"0"+calendar.get(Calendar.MINUTE):calendar.get(Calendar.MINUTE)+"";
		String temp3 = calendar.get(Calendar.SECOND)<10?"0"+calendar.get(Calendar.SECOND):calendar.get(Calendar.SECOND)+"";
		return temp1 +  ":" + temp2 + ":" + temp3;
	}

	/**
	 * convert Timestamp to string "yyyy-mm-dd"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getDateString ( Timestamp ts )
	{
		Date dt = null ;
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		if (strMonth.length ( ) == 1)
		{
			strMonth = "0" + strMonth ;
		}
		String strDay = String.valueOf ( calendar.get ( Calendar.DATE ) ) ;
		if (strDay.length ( ) == 1)
		{
			strDay = "0" + strDay ;
		}
		return calendar.get ( Calendar.YEAR ) + "-" + strMonth + "-" + strDay ;
	}
	/**
	 * convert Timestamp to string "mm/dd/yyyy"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getDateString1 ( Timestamp ts )
	{
		Date dt = null ;
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		if (strMonth.length ( ) == 1)
		{
			strMonth = "0" + strMonth ;
		}
		String strDay = String.valueOf ( calendar.get ( Calendar.DATE ) ) ;
		if (strDay.length ( ) == 1)
		{
			strDay = "0" + strDay ;
		}
		return strMonth + "/" + strDay + "/" +calendar.get ( Calendar.YEAR ) ;
	}
	/**
	 * convert Timestamp to string "yyyy-mm-dd"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getChineseDateString ( Timestamp ts )
	{
		Date dt = null ;
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		if (strMonth.length ( ) == 1)
		{
			strMonth = "0" + strMonth ;
		}
		String strDay = String.valueOf ( calendar.get ( Calendar.DATE ) ) ;
		if (strDay.length ( ) == 1)
		{
			strDay = "0" + strDay ;
		}
		return calendar.get ( Calendar.YEAR ) + "年" + strMonth + "月" + strDay
				+ "日" ;
	}
	/**
	 * convert Timestamp to string "yyyy-mm-dd"
	 * 
	 * @param ts
	 * @return
	 */
	static public String getChineseMonthString ( Timestamp ts )
	{
		Date dt = null ;
		if (null == ts)
			return "" ;
		Calendar calendar = Calendar.getInstance ( ) ;
		calendar.setTime ( ts ) ;
		String strMonth = String.valueOf ( calendar.get ( Calendar.MONTH ) + 1 ) ;
		return calendar.get ( Calendar.YEAR ) + "年" + strMonth + "月" ;
	}	
	
	/**
	 * 读取EXCEL的日期类型特殊转换 01/12/2010 转换为 2010-12-01
	 * @param date
	 * @return
	 */
	public static Date parseExcelDate(String date){
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simp.parse(simp.format(new SimpleDateFormat("dd/MM/yyyy").parse(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 格式化数字，例如：12345转化为12,345
	 * 
	 * @param dValue
	 *            被格式化的数值 
	 * @param iScale
	 *            小数点后保留位数,不足补0
	 * @return
	 */
	public static String formatAmount ( String dValue , int iScale )
	{
		DecimalFormat df = null ;
		StringBuffer sPattern = new StringBuffer ( ",##0" ) ;
		if (iScale > 0)
		{
			sPattern.append ( "." ) ;
			for (int i = 0; i < iScale; i++)
			{
				sPattern.append ( "0" ) ;
			}
		}
		df = new DecimalFormat ( sPattern.toString ( ) ) ;
		return df.format ( Double.parseDouble(dValue) ) ;
	}
	
	/**
	 * 得到下几天
	 * 
	 * @param tsDate
	 *            日期
	 */
	static public Timestamp getNextDate ( Timestamp tsDate ,int nDay )
	{
		if (null == tsDate)
			return null ;
		GregorianCalendar calendar = new GregorianCalendar ( ) ;
		calendar.setTime ( tsDate ) ;
		calendar.add ( Calendar.DATE , nDay ) ;
		Date resDate = calendar.getTime ( ) ;
		return new Timestamp ( resDate.getTime ( ) ) ;
	}
	
	/**
	 * 得到传入日期的前或后的某个日期。
	 * 
	 * @param intDay,
	 *            为负，得到之前的日期。为正，得到之后的日期。
	 */
	public static java.sql.Date getPreviousOrNextDate ( Date date , int intDay )
	{
		java.sql.Date dtTemp = null ;
		Timestamp tsTemp = null ;
		if (date == null)
		{
			return null ;
		} else
		{
			Calendar calendar = Calendar.getInstance ( ) ;
			calendar.setTime ( date ) ;
			tsTemp = getDateTime ( calendar.get ( 1 ) , calendar.get ( 2 ) + 1 ,
					calendar.get ( 5 ) + intDay , calendar.get ( 11 ) ,
					calendar.get ( 12 ) , calendar.get ( 13 ) ) ;
			dtTemp = java.sql.Date.valueOf ( getDateString ( tsTemp ) ) ;
			return dtTemp ;
		}
	}
	
	/**
	 * 格式化对象，将请求MAP中空对象或null转换为""
	 * @param strData  需要格式化的对象
	 * @return String
	 * @author jameson.fang on 2011-02-19
	 */
	public static String formatNullObjectString ( Object strData ){
		if (strData == null || strData.toString().length()==0 || strData.equals("null")){
			return "" ;
		} else{
			return strData.toString().trim() ;
		}
	}
	
	/**
	 * 格式化字对象，将请求MAP中空对象或null转换为整型
	 * @param strData  需要格式化的对象
	 * @return String
	 * @author jameson.fang on 2011-02-19
	 */
	public static int formatNullObjectInteger ( Object strData ){
		if (strData == null || strData.toString().length()==0 || strData.equals("null")){
			return 0 ;
		} else{
			try{
				return Integer.valueOf(strData.toString());
			}catch(NumberFormatException e){
				logger.debug(strData+"转换为整型异常！");
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	/**
	 * 格式化对象，将请求MAP中空对象或null转换为double
	 * @param strData  需要格式化的对象
	 * @return String
	 * @author jameson.fang on 2011-02-19
	 */
	public static double formatNullObjectDouble ( Object strData ){
		if (strData == null || strData.toString().length()==0 || strData.equals("null")){
			return 0d ;
		} else{
			try{
				return Double.parseDouble(strData.toString()) ;
			}catch(Exception e){
				logger.debug(strData+"转换为Double类型异常！");
				e.printStackTrace();
				return 0d;
			}
		}
	}

	public static String getDate(String pattern, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateStr = formatter.format(date);
		return dateStr;
	}

	public static Date parseStringToDate(String input, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String formatNumberDigits(String input,int len) { 
		java.text.NumberFormat formater = DecimalFormat.getInstance();
	    formater.setMaximumFractionDigits(len);  //设置小数点后最长的个数
	    formater.setMinimumFractionDigits(len);  //设置小数点后最短的个数
	    ////System.out.println(formater.format(3.1415927)); ==3.14
	    ////System.out.println(formater.format(0.9865927)); ==0.99
	    ////System.out.println(formater.format(Double.parseDouble(input)));
	    return formater.format(Double.parseDouble(input));
	}
	
	/**
	 * @作者 zhujiangfeng
	 * @日期 2011-8-4
	 */
	public static String nvl(String str1, String str2){
		if(str1 == null){
			return str2;
		}else if(str1.trim().equals("") || str1.length() == 0){
			return str2;
		}else{
			return str1;
		}
	}
	
	
	/**
	 * 日期转换-- yyyy-MM-dd(字符串)  ---> yyyy 年 MM 月 dd 日
	 * 
	 * @param 		yyyy-MM-dd(字符串)  
	 * 
	 * @return		yyyy 年 MM 月 dd 日
	 */
	public static String getDateOfChina(String str){
		if(str==null || "".equals(str)){
			return "-";
		}
		String dateStr= "";
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			dateStr = new SimpleDateFormat("yyyy 年 MM 月 dd 日").format(d);
		} catch (ParseException e) {
			return "-";
		}
		return dateStr;
	}
	
	public static void main(String args[]) {
		Object obj = "";
		//System.out.println(formatNullObjectDouble(obj));
		Date da = new Date();
		//注意：这个地方da.getTime()得到的是一个long型的值
		//System.out.println(da.getTime());
	    //由日期date转换为timestamp
		//第一种方法：使用new Timestamp(long)
		Timestamp t = new Timestamp(new Date().getTime());
		//System.out.println(t);

		//System.out.println( getNextDate(t,2) );
		
		
		//System.out.println( getPreviousOrNextDate( da , +2) );
		
		//System.out.println( getPreviousOrNextDate( da , -2) );
		
		formatNumberDigits("0.9865927",2);
		   
	}
	
}