package com.kyee.iszx.util.string;


import java.util.Objects;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class StringUtil {
	
	private static final String RANDOM_BASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static final String RANDOM_BASE_CHARS_DIGIT = "0123456789";
	
	/**
	 * 为给定数字前面补充0, 补足到给定位数, 如果给定数字位数超过了给定位数, 则进行截断, 留低位部分, 相等保持不变
	 * @param num 给定数字
	 * @param expectDigits 给定位数
	 * @return 补充0或截取后的数字字符串
	 */
	public static String numToStrWithZeroFillOrTruncate(String num, int expectDigits) {
		Objects.requireNonNull(num);
		int fillDigits = expectDigits - num.length();
		if (fillDigits < 0) {
//			throw new IllegalArgumentException("num's digits must less than or equal to expectDigits");
			return num.substring(Math.abs(fillDigits));
		}
		StringBuilder fillStr = new StringBuilder(fillDigits);
		for (int i = 0; i < fillDigits; i++) {
			fillStr.append("0");
		}
		return fillStr.append(num).toString();
	}
	
	public static boolean isEmpty(String text)
    {
        if(text == null)
        {
            return true;
        } else
        {
            text = text.trim().toLowerCase().replaceAll("'|\"", "");
            return "".equals(text) || "null".equals(text);
        }
    }
	
	public static boolean isNotEmpty(String text)
    {
        return !isEmpty(text);
    }
	
	/**
	 * 生成指定位数的随机字符串，字符包含大写字母和数字
	 */
	public static String getRandomStr(int length) {
		return getRandomStr(length, RANDOM_BASE_CHARS);
	}
	
	/**
	 * 生成指定位数的随机字符串，字符仅包含数字
	 */
	public static String getRandomStrDigit(int length) {
		return getRandomStr(length, RANDOM_BASE_CHARS_DIGIT);
	}
	
	/**
	 * 生成指定位数的随机字符串，基础字符串由调用者提供
	 * @param randomBaseChars eg： ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789
	 */
	public static String getRandomStr(int length, String randomBaseChars) {
		if (length < 1) {
			throw new IllegalArgumentException("length must >= 1");
		}
		
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
	        int number = random.nextInt(randomBaseChars.length());     
	        sb.append(randomBaseChars.charAt(number));     
	    }
		return sb.toString();
	}
	
	/**
	 * 移除UTF-8 with Bom文本的Bom头
	 */
	public static String removeUTF8Bom(String utf8WithBomText) {
		if (utf8WithBomText == null) {
			return null;
		}
		
		if (utf8WithBomText.startsWith("\uFEFF")) {
			return utf8WithBomText.substring(1);
		}
		return utf8WithBomText;
	}
	
	/**
	 * 字节数组转换为十六进制字符串
	 */
	public static String bytes2HexStr(byte[] bytes) {
		Objects.requireNonNull(bytes);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hexStr = Integer.toHexString(bytes[i] & 0xff);
			if (hexStr.length() == 1) {
				hexStr = "0" + hexStr;
			}
			sb.append(hexStr);
		}
		return sb.toString();
	}
	
	/**
	* <pre>
	* 描述：金额负值转正值
	* 作者：gaomeng
	* 时间：2018年8月14日下午1:57:01
	* @param amtStr
	* @return
	* returnType：String
	* </pre>
	*/
	public static String StrNegativeToPositive(String source) {
		if (!isEmpty(source)) {
			source = source.replace("-", "");
		}
		return source;
	}
	
	/**
	    * <pre>
	    * 描述：判断某个字符串中是否含有以某个字符分割的字符串
	    * 作者：xiaoxinzhou
	    * 时间：2018年8月14日下午1:57:01
	    * @param parentString 父字符串  
	    * @param substring 包含的字符串
	    * @param splitter 分隔符
	    * @return
	    * returnType：boolean
	    * </pre>
	    */
	    public static boolean contains(String parentString, String substring,String splitter) {
	        
	        if (isEmpty(parentString)||isEmpty(substring)||isEmpty(splitter)) {
	            return false;
	        }
	        String listStr[] = parentString.split(splitter);
	        for(String str: listStr) {
	            if(str.equals(substring)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    
	    /**  
	     * @标题: deleteSpace  
	     * @描述: 删除字符串中的空格  
	     * @参数: @param str 需要删除空格的字符串
	     * @参数: @return  删除空格后的字符串
	     * @返回: String      
	     * @异常  
	    */
	    public static String deleteSpace (String str) {
	    	str = str.replaceAll(" ", "");
	    	str = str.replaceAll(" +", "");
	    	str = str.replaceAll("\\s*", "");
			return str;
	    }
	    
	    
	    /**  
	     * @标题: getJsonValue  
	     * @描述: 根据键值获取字符串中的  
	     * @参数: @param key
	     * @参数: @param jsonStr
	     * @参数: @return 
	     * @返回: String      
	     * @异常  
	    */
	    public static String getJsonValue(String key,String jsonStr) {
	    	JSONObject obj = (JSONObject) JSON.parse(jsonStr);
	    	return obj.getString(key);
	    }
	    
	    /**  
	     * @标题: isJson  
	     * @描述: 是否为json字符串
	     * @参数: @param content
	     * @参数: @return 
	     * @返回: boolean      
	     * @异常  
	    */
	    public static boolean isJson(String content) {
	    	try {
	    		JSON.parse(content);
	    		return true;
	    	}catch(Exception e) {
	    		return false;
	    	}
	    }
}
