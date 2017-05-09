package cn.springmvc.commen;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReturnData {
	static ObjectMapper objectMapper;
	private String returnCode = "0000";
	private String msg = "";
	private Object data;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
//		return JSONObject.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
		return JSONObject.toJSON(this).toString();
	}
	/**
	 * jackson         
	 * 解决fastjson将对象转换成json时出现的递归错误
	 * toJSon
	 * @author gwb
	 * @param object
	 * @return
	 * 2015年9月18日 下午5:45:56
	 */
	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

