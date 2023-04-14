package com.growee.database.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author Maor
 *
 */
public class HistoryDeviceDataRedis {

	private static Jedis redis = null;

	public static void insertHistory(String userId, String newHistoryData) {

		try {
			redis = RedisPool.getPool().getResource();
			insertHistoryToRedis(userId, newHistoryData);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				RedisPool.getPool().returnBrokenResource(redis);
				redis = null;
			}
			System.out.println(e);
		} finally {
			if (redis != null) {
				RedisPool.getPool().returnResource(redis);
			}
		}

	}

	public static List<List<String>> getHistory(String deviceId, String timeDeviceCreate) {
		List<List<String>> deviceDataHistory = null;
		try {
			redis = RedisPool.getPool().getResource();
			deviceDataHistory = getAllUserHistoryFromRedis(deviceId, timeDeviceCreate);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				RedisPool.getPool().returnBrokenResource(redis);
				redis = null;
			}
			System.out.println(e);
		} finally {
			if (redis != null) {
				RedisPool.getPool().returnResource(redis);
			}
		}
		return deviceDataHistory;
	}
	
	public static void deleteDeviceHistory(String deviceId, String timeDeviceCreate) {
		List<List<String>> deviceDataHistory = null;
		try {
			redis = RedisPool.getPool().getResource();
		deleteAllDeviceRecoedsFromHistory(deviceId, timeDeviceCreate);
		} catch (JedisConnectionException e) {
			if (redis != null) {
				RedisPool.getPool().returnBrokenResource(redis);
				redis = null;
			}
			System.out.println(e);
		} finally {
			if (redis != null) {
				RedisPool.getPool().returnResource(redis);
			}
		}
	}

	private static List<List<String>> getAllUserHistoryFromRedis(String deviceId, String timeDeviceCreated) {

		String indexTomorrowKey = RedisKey.getHistoryKey(deviceId, TTLCalculator.getCurrentDatePlusDays(1));
		String currentKey = RedisKey.getHistoryKey(deviceId, timeDeviceCreated);
		List<List<String>> history = new ArrayList<List<String>>();
		long listSize;
		int numberOfDaysForward = 0;
		while (!currentKey.equals(indexTomorrowKey)) {
			if (redis.exists(currentKey.toString())) {
				listSize = redis.llen(currentKey.toString());
				history.add(redis.lrange(currentKey.toString(), 0, listSize - 1));
			}
			numberOfDaysForward++;
			currentKey = RedisKey.getHistoryKey(deviceId,TTLCalculator.getThisDatePlusDays(timeDeviceCreated, numberOfDaysForward));
		}

		return history;
	}

	private static void insertHistoryToRedis(String userId, String newHistoryData) {
		String newHistoryQuery = RedisKey.getHistoryKey(userId, TTLCalculator.getCurrentDate());
		if (!redis.exists(newHistoryQuery)) {
			redis.lpush(newHistoryQuery, newHistoryData);
			redis.expire(newHistoryQuery, TTLCalculator.getTimeToExpiredInSeconds());
		} else {
			redis.lpush(newHistoryQuery, newHistoryData);
		}
	}
	
	private static void deleteAllDeviceRecoedsFromHistory(String deviceId, String timeDeviceCreated) {
		String indexTomorrowKey = RedisKey.getHistoryKey(deviceId, TTLCalculator.getCurrentDatePlusDays(1));
		String currentKey = RedisKey.getHistoryKey(deviceId, timeDeviceCreated);
		int numberOfDaysForward = 0;
		while (!currentKey.equals(indexTomorrowKey)) {
			if (redis.exists(currentKey.toString())) {
				redis.del(currentKey.toString());
			}
			numberOfDaysForward++;
			currentKey = RedisKey.getHistoryKey(deviceId,TTLCalculator.getThisDatePlusDays(timeDeviceCreated, numberOfDaysForward));
		}

	}

}
