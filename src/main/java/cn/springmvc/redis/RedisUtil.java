/**
 * CopyRight 2010 商盟商务服务有限公司
 *
 * http://www.sumpay.cn
 */

package cn.springmvc.redis;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @class         RedisUtil.java
 * @creater       ss_sunshan@163.com
 * @date          2014-3-17 下午06:16:43
 * @declaration  
 */

public class RedisUtil {
	
	public static BASE64Encoder encoder = new BASE64Encoder();
	public static BASE64Decoder decoder = new BASE64Decoder();
	
	public static String getStringFromObject(Serializable obj){
		byte[] bytes = getByteArrayFromObject(obj);
		return encoder.encode(bytes);
	}
	
	public static Object getObjectFromString(String str){
		try {
			byte[] bytes = decoder.decodeBuffer(str);
			return getObjectFromByteArray(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将对象转换为 byte数组
	 *
	 * @param obj
	 * @return
	 */
	public static byte[] getByteArrayFromObject(Serializable obj) {
		ObjectOutputStream os = null;
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(obj);
			os.flush();
			byte[] sendBuf = byteStream.toByteArray();
			os.close();
			return sendBuf;
		} catch (Exception ex) {
			//throw new IMException(ex);
			return null;
		}
	}

	/**
	 * 将 byte 数组 转换为对象
	 *
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectFromByteArray(byte[] bytes) {
		ObjectInputStream in = null;
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			in = new ObjectInputStream(new BufferedInputStream(byteStream));
			Object result = in.readObject();
			in.close();
			return (T) result;
		} catch (Exception ex) {
			//throw new IMException(ex);
			return null;
		}
	}
}
