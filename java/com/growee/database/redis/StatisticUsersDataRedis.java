package com.growee.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;
import java.util.List;

public class StatisticUsersDataRedis {

	private static Jedis redis = null;


	public static void updateUserEnterancesPerDay(String userId) {

		try {
			redis = RedisPool.getPool().getResource();
			updateCounter(userId);
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



	private static List<List<String>> getAllUserEntercesFromRedis(String deviceId) {

		String indexKey = RedisKey.getHistoryKey(deviceId, TTLCalculator.getCurrentDate());
		List<List<String>> history = new ArrayList<List<String>>();
		long listSize;
		int numberOfDaysBack = 0;
		while (redis.exists(indexKey.toString())) {
			listSize = redis.llen(indexKey.toString());
			history.add(redis.lrange(indexKey.toString(), 0, listSize - 1));
			numberOfDaysBack++;
			indexKey = RedisKey.getHistoryKey(deviceId, TTLCalculator.getCurrentDateMinusDays(numberOfDaysBack));
		}

		return history;

	}

	private static void updateCounter(String userId) {
		String dayCounterQuery = RedisKey.getUserLoginCounterKey(userId, TTLCalculator.getCurrentDate());
		if (!redis.exists(dayCounterQuery)) {
			redis.incr(dayCounterQuery);
			redis.expire(dayCounterQuery, TTLCalculator.getTimeToExpiredInSeconds());
		} else {
			redis.incr(dayCounterQuery);
		}

	}


	private static void deleteAllUserRecoedsFromHistory(String deviceId, String timeDeviceCreated) {
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
