package com.growee.database.mysql;

/**
 * @author Maor
 *
 */
public class QueryGenerator {

	
	
	public static String selectAllTable(String tableName) {
		return "select * from "+ tableName;
	}
	
	public static String selectRowByPrimatyKey(String tableName, String primaryKey, String primaryKeyValue) {
		return "select * from "+ tableName  +" where " +primaryKey + "= '" + primaryKeyValue + "'";
	}
	
	public static String updateColumnById(String tableName, String Column, String value, String primaryKey, String primaryKeyValue) {
		return "UPDATE "+tableName +" SET " + Column +" = '" + value + "' WHERE " + primaryKey + "= '" + primaryKeyValue + "'";
	}

	
	
}
