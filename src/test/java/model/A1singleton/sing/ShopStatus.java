package model.A1singleton.sing;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth : luxt
 * @e-mail : luxt@cloudyigou.com
 * @desc : 店铺状态
 */
public enum ShopStatus {

	/**
	 * 开店
	 **/
	OPEN("open", "开店"),

	/**
	 * 关店
	 **/
	CLOSE("close", "关店"),

	/**
	 * 删除
	 **/
	DELETED("deleted", "删除");


	private static Map<String, ShopStatus> maps = new HashMap();

	static {
		for (ShopStatus item : ShopStatus.values()) {
			maps.put(item.getStatus(), item);
		}
	}

	private final String status;
	private final String value;

	ShopStatus(String status, String value) {
		this.status = status;
		this.value = value;
	}

	public static ShopStatus getByStatus(final String status) {
		if (status == null) {
			return null;
		}
		return maps.get(status);
	}


	public String getStatus() {
		return status;
	}

	public String getValue() {
		return value;
	}
}
