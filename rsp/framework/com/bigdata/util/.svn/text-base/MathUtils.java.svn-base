package com.bigdata.util;

import java.text.DecimalFormat;

public class MathUtils {

	/**
	 * @Description: 根据传入的格式类型，把double数据转换成所需double
	 * @param formatStr
	 * @param number
	 * @return
	 */
	public static double formatDouble(String formatStr, double number) {
		if (formatStr == null || "".equals(formatStr)) {
			formatStr = "0.0000";
		}
		DecimalFormat df = new DecimalFormat(formatStr);
		return Double.parseDouble(df.format(number));
	}
	
	/**
	 * @Description: 根据传入的格式类型，把double数据转换成所需double
	 * @param formatStr
	 * @param number
	 * @return
	 */
	public static float formatFloat(String formatStr, float number) {
		if (formatStr == null || "".equals(formatStr)) {
			formatStr = "0.0000";
		}
		DecimalFormat df = new DecimalFormat(formatStr);
		return Float.parseFloat(df.format(number));
	}
	
	/**
	 * @Description: 根据传入的格式类型，把double数据转换成所需double
	 * @param formatStr
	 * @param number
	 * @return
	 */
	public static String formatDoubleToString(Double number ,String formatStr) {
		if (formatStr == null || "".equals(formatStr)) {
			formatStr = "0.0000";
		}
		DecimalFormat df = new DecimalFormat(formatStr);
		return df.format(number);
	}
	
	/**
	 * @func：  文档版本之版本处理
	 * @param docVersion
	 * @return： String
	 * @version: v1.0
	 */
	public static String docVersionDeal(String docVersion){
		if(null == docVersion || "".equals(docVersion)){
			docVersion = "1.0" ;
		}
		
		float version = Float.valueOf(docVersion) ;
		version += 1.0 ;
		
		return String.valueOf(version) ;
	}

}