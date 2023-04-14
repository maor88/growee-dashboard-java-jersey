package com.growee.database.redis;

/**
 * @author Maor
 *
 */
public class RedisKey {

	public static String getHistoryKey(String deviceId, String currentDate) {
		String key =  String.format("%s:%s", deviceId, currentDate);
		return key;
	}

	public static String getUserLoginCounterKey(String deviceId, String currentDate) {
		String key =  String.format("id:u-%s:%s:cnt", deviceId,currentDate);
		return key;
	}

}
