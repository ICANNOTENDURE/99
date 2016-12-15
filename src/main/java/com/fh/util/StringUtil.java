package com.fh.util;

import java.util.List;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
	/**获取字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   } 
	
	/**
	 * 将驼峰标识转换为下划线
	 * 
	 * @param text
	 * @return camel
	 */
	public static String camelToUnderline(String text) {
		if (text == null || "".equals(text.trim())) {
			return "";
		}
		StringBuffer result = new StringBuffer(text.length() + 1);
		result.append(text.substring(0, 1));
		for (int i = 1; i < text.length(); i++) {
			if (!Character.isLowerCase(text.charAt(i))) {
				result.append('_');
			}
			result.append(text.substring(i, i + 1));
		}
		return result.toString().toLowerCase();
	}

	/**
	 * 将下划线标识转换为驼峰
	 * 
	 * @param text
	 * @return underline
	 */
	public static String underlineToCamel(String text) {
		if (text == null || "".equals(text.trim())) {
			return "";
		}
		int length = text.length();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			char c = text.charAt(i);
			if (c == '_') {
				if (++i < length) {
					result.append(Character.toUpperCase(text.charAt(i)));
				}
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}

	/**
	 * 将String list字符串集合用逗号","分割
	 * 
	 * @param list
	 *            集合
	 * @return string
	 */
	public static String splitListByComma(List<String> list) {
		StringBuffer result = new StringBuffer();
		for (String str : list) {
			result.append(",").append(str);
		}
		return result.substring(1, result.length());
	}
}
