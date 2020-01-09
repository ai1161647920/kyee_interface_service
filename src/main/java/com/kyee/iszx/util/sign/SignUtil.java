package com.kyee.iszx.util.sign;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.kyee.iszx.util.string.StringUtil;

import java.util.SortedMap;
import java.util.TreeMap;

public class SignUtil {
	/**
	 * 数字签名使用的算法名称(MD2withRSA、MD5withRSA或SHA1withRSA、SHA256WithRSA)
	 */
	public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";
	/**
	 * 加密解密使用的算法名称
	 */
	public static final String KEY_ALGORITHM = "RSA";
	
	public static String sign(Map<String, String> signMap) {
		String privateKey = signMap.remove("privateKey");

		String signStr = SignUtil.getSignString(signMap, false);
		String signValue = SignUtil.rsaSign(signStr, privateKey);
		return signValue;
	}
	/**
     * 加签
     * @param src 待加签原数据
     * @param privateKey 私钥(经BASE64包装，所以第一步要还原为可读的私钥)
     * @return 签名串(经BASE64包装)
     */
	public static String rsaSign(String src, String privateKey) {
		String result = null;
		try {
			result = sign(src, privateKey, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("加签发生错误", e);
		}
        return result;
	}
	
	/**
	 * <pre>
	 * 描述：用私钥对信息生成签名(指定字符集)
	 * 作者：fengqianning 
	 * 时间：2016年5月4日下午9:15:08
	 * @param content 要生成签名的字符串
	 * @param privateKey base64加密的私钥字符串
	 * @param input_charset 要生成签名的字符串对应的字符集
	 * @return
	 * @throws Exception
	 * returnType：String
	 * </pre>
	 */
	public static String sign(String content, String privateKey,
			String input_charset) throws Exception {
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(getPrivateKey(privateKey));
		signature.update(content.getBytes(input_charset));
		return getBase64StringByByteArray("BASIC",signature.sign());
	}
	
	/**
	 * <pre>
	 * 描述：根据base64加密的私钥字符串生成RSA私钥对象
	 * 时间：2016年5月4日下午9:41:50
	 * @param privateKey base64加密的私钥字符串
	 * @return
	 * @throws Exception
	 * returnType：PrivateKey
	 * </pre>
	 */
	private static PrivateKey getPrivateKey(String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = getByteArrayByBase64String("BASIC", privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		return priKey;
	}

	/**
	 * <pre>
	 * 描述：将为byte[]加密并转换为base64字符串
	 * 作者：fengqianning
	 * 时间：2017年3月16日下午5:19:05
	 * @param type 可选值为 BASIC,URL,MIME;(默认BASIC)
	 * @param byteArray
	 * @return
	 * returnType：String
	 * </pre>
	 */
	private static String getBase64StringByByteArray(String type,
			byte[] byteArray) {
		Encoder encoder = null;
		if ("URL".equalsIgnoreCase(type)) {
			encoder = Base64.getUrlEncoder();
		} else if ("MIME".equalsIgnoreCase(type)) {
			encoder = Base64.getMimeEncoder();
		} else {
			encoder = Base64.getEncoder();
		}
		return encoder.encodeToString(byteArray);
	}
	
	/**
	 * <pre>
	 * 描述：将base64字符串解密并转换为byte[]
	 * @param type 可选值为 BASIC,URL,MIME;(默认BASIC)
	 * @param byteArray
	 * @return
	 * returnType：byte[]
	 * </pre>
	 */
	private static byte[] getByteArrayByBase64String(String type, String text) {
		Decoder decoder = null;
		if ("URL".equalsIgnoreCase(type)) {
			decoder = Base64.getUrlDecoder();
		} else if ("MIME".equalsIgnoreCase(type)) {
			decoder = Base64.getMimeDecoder();
		} else {
			decoder = Base64.getDecoder();
		}
		return decoder.decode(text);
	}
	
	/**
	 * 从map得到一个key1=value1&key2=value2的格式字符串(主要用于签名运算), 
	 * 1.排除map中value为空的entry
	 * 2.排除签名字段
	 * 3.key按字典顺序
	 * @param map 
	 * @param isSortedMap 是否是有序的map
	 * @return
	 */
	public static <V> String getSignString(Map<String, V> map, boolean isSortedMap) {
		if (map == null || map.size() == 0) {
			throw new IllegalArgumentException("map不能为空！");
		}
		
		SortedMap<String, V> sortedMap;
		if (isSortedMap && map instanceof SortedMap) {
			sortedMap = (SortedMap<String, V>) map;
		} else {
			sortedMap = getNewTreeMap(true);
			sortedMap.putAll(map);
		}
		
		StringBuilder sb = new StringBuilder();
		for (Entry<String, V> e : sortedMap.entrySet()) {
			String k = e.getKey();
			String v;
			if (e.getValue() instanceof String[]) {
				v = String.valueOf(((String[]) e.getValue())[0]);
			} else {
				v = String.valueOf(e.getValue());
			}
			if (StringUtil.isNotEmpty(v)) {
				sb.append("&").append(k).append("=").append(v);
			}
		}
		sb = sb.length() > 0 ? sb.deleteCharAt(0) : sb;
		return sb.toString();
	}

	/**
	 * <pre>
	 * 描述：获取一个key按指定顺序排序的TreeMap<K,V>
	 * 时间：2016年1月8日 11:10:50
	 * @param  flag：true按key升序排列的TreeMap<K,V>;false按key降序排列的TreeMap<K,V>
	 * @return 返回按key指定顺序排列的TreeMap<K,V>
	 * returnType：TreeMap<K,V>
	 * </pre>
	 */
	public static <K extends Comparable<K>, V> TreeMap<K, V> getNewTreeMap(
			boolean flag) {
		TreeMap<K, V> treeMap = null;
		if (flag) {
			treeMap = new TreeMap<K, V>(new Comparator<K>() {
				public int compare(K key1, K key2) {
					return key1.compareTo(key2);
				}
			});
		} else {
			treeMap = new TreeMap<K, V>(new Comparator<K>() {
				public int compare(K key1, K key2) {
					return key2.compareTo(key1);
				}
			});
		}
		return treeMap;
	}
}
