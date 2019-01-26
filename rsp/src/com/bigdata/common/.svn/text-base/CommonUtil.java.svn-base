package com.bigdata.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class CommonUtil {

    /**
     * 获取spring容器中的用户对象
     *
     * @param beanId
     * @return
     */
    public static final Object getBean(String beanId) {
        WebApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
        return act.getBean(beanId);
    }
    
    /**
     * 得到$之前的URL,没找到就原样返回,例如   dfProjRptAction.do?method=monthRptInput&fffff=23432&projId=${0}&propCode=${1}
     *
     * @param url
     * @return dfProjRptAction.do?method=monthRptInput&fffff=23432
     */
    public static final String getTrimMethodUrl(String url) {
//        return request.getRequestURI()+"?method="+ ((String[])request.getParameterMap().get("method"))[0];
        if (org.apache.commons.lang.StringUtils.containsAny(url, "$")) {
//            int i=org.apache.commons.lang.StringUtils.indexOf(url,"$") ;
            url = org.apache.commons.lang.StringUtils.substringBefore(url, "$");
            return org.apache.commons.lang.StringUtils.substringBeforeLast(url, "&");
        } else {
            return url;
        }
//        return url;
    }
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * 判断字符串是否非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}


	/**
	 * @Description: 子项代码递增（例：父项code01，父项code02，父项code03……） parentCode:父项的code
	 * @param parentCode
	 * @param existedChildrenSize:已存在的子项的数量(仅包含有效的)
	 * @return:生成的新的子项的code
	 */
	public static String autoIncreaseCode(String parentCode, int existedChildrenSize) {
		String half = String.valueOf(existedChildrenSize + 1);
		for (int i = 0; i < half.length() && i < 1; i++) {
			half = "0" + half;
		}
		return (parentCode + half);
	}

	/**
	 * @Description: 处理XML字符串中的特殊字符，防止出错
	 * @param xmlData:待处理的字符串
	 * @return:处理完的字符串
	 */
	public static String dealXMLData(String xmlData) {
		xmlData = xmlData.replaceAll(">", "&gt;");
		xmlData = xmlData.replaceAll("<", "&lt;");
		return xmlData;
	}

	/**
	 * @Description:  字符串转码 对用JS中的encodeURIComponent(encodeURIComponent(xxx))传到后台的字符串进行转码，防止乱码
	 * @param encodedStr :request中获取的从前台传过来的值
	 * @return:解码后的字符串
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String decodeStr(String encodedStr) throws UnsupportedEncodingException {
		return encodedStr == null ? "" : java.net.URLDecoder.decode(encodedStr, "UTF-8").trim();
	}

	/**
	 * @Description: 处理JSON字符串中的特殊字符
	 * @param jsonData :原字符串
	 * @return:处理完的字符串
	 */
	public static String dealJsonData(String jsonData) {
		if (jsonData == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < jsonData.length(); i++) {
			char ch = jsonData.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	public static void doAjaxResponse(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
		out.close();
	}

	/**
	 * 把page、total、list转换为json格式的数据，形如{"page":1,"rows":[{"id":"001","name":"ser"
	 * },{"id":"002","name":"serdd"}],"total":"10"} 供页面的flexigrid使用
	 * 
	 * @param page
	 * @param list
	 * @param total
	 * @return
	 */
	public static String list2FlexigridJson(String page, List list, String total) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"page\":");
		sb.append(page);
		sb.append(",\"rows\":");
		sb.append(JsonUtils.list2json(list));
		sb.append(",\"total\":");
		sb.append(total);
		sb.append("}");
		return sb.toString();
	}
	/**
	 * 把page、total、list转换为json格式的数据，形如{"page":1,"rows":[{"id":"001","name":"ser"
	 * },{"id":"002","name":"serdd"}],"total":"10"} 供页面的flexigrid使用
	 * 
	 * @param page
	 * @param list
	 * @param total
	 * @return
	 */
	public static String list2FlexigridJson2(String page, List list, String total) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"page\":");
		sb.append(page);
		sb.append(",\"Rows\":");
		sb.append(JsonUtils.list2json(list));
		sb.append(",\"Total\":");
		sb.append(total);
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * @func：   把list转换为json格式的数据，形如{rows":[{"id":"001","name":"ser"
	 * },{"id":"002","name":"serdd"}]} 供页面的flexigrid使用
	 * @param list
	 * @return： String
	 */
	public static String listLigerUIgridJson(List list) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"rows\":");
		sb.append(JsonUtils.list2json(list));
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 处理用于SQL查询的字符串，将特殊字符转换
	 * @param paramStr :原字符串
	 * @return 转换后的字符串
	 */
	public static String dealSQLStr(String paramStr){
		if (paramStr == null)
		    return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < paramStr.length(); i++) {
		    char ch = paramStr.charAt(i);
		    if(ch=='[' || ch==']' || ch=='%' || ch=='^' || ch=='_' )
		    	sb.append("["+ch+"]");
		    else
		    	sb.append(ch);
		}
		return sb.toString();
	}
	
	/**
	 * @Description: 将一个整型数组生成SQL用的where … in ( … )语句
	 * 例如数组{ 1,2,3 }，则生成   ("1","2","3")
	 * @param array
	 * @return 返回生成的字符串
	 */
	public static String genIn(int[] array){
		String in="(";
		for(int i=0;i<array.length;i++){
			in+="'"+array[i]+"'"+(i==array.length-1?"":",");
		}
		in+=")";
		return in;
	}
	
	/**
	 * @Description: 将一个字符串数组生成SQL用的where … in ( … )语句
	 * 例如数组{ "1","2","3" }，则生成   ("1","2","3")
	 * @param array
	 * @return 返回生成的字符串
	 */
	public static String genIn(String[] array){
		String in="(";
		for(int i=0;i<array.length;i++){
			in+="'"+array[i]+"'"+(i==array.length-1?"":",");
		}
		in+=")";
		return in;
	}
	
	public static String genNewVersion(String tempVersion){
		Double newVersion=0.0;
		newVersion=Double.valueOf(tempVersion)+0.1;
		return newVersion.toString();
	}
	
	/**
	 * @Description: aaa,bbb,ccc转换成('aaa','bbb','ccc')
	 * @param data
	 * @return
	 */
	public static String stringToIn(String data){
		String[] arr=data.split(",");
		String result="( ";
		for(int i=0;i<arr.length;i++){
			result+="'"+arr[i]+"'"+(i==arr.length-1?"":",");
		}
		result+=" )";
		return result;
	}
	
	/**
	 * @Description: 初始化分页的起始数，结尾数
	 * @param pages
	 * @param size
	 * @param map
	 */
	public static void initParperStartAndEnd(int pages,int size,Map map){
	    int start = (pages - 1) * size; // 起始位置
        int end = start + size;// 结束位置
        String between=(start+1)+" and "+end;
        map.put("between", between);
	}
	
    /**
     * 数组转换为('xxx','yyyy','zzz')
     *
     * @param strs
     * @return
     */
    public static final String generateInString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        } else {
            String strAll = "(";
            for (int i = 0, length = strs.length; i < length; i++) {
                String str = strs[i];
                strAll += "'" + str + "'" + (i == length - 1 ? ")" : ",");

            }
            return strAll;
        }
    }
    
    /**
     * 将模版按顺序整合成自然语言
     *
     * @param params
     * @param tmpl
     * @return
     */
    public static final String tmplToStr(List<String> params, String tmpl) {
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                tmpl = StringUtils.replace(tmpl, "${" + i + "}", params.get(i) == null ? "" : params.get(i));
            }
        }
        return tmpl;
    }
}
