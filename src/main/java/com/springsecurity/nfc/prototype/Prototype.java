package com.springsecurity.nfc.prototype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.constants.Queries;

@Component
public class Prototype implements Queries.Prototype, Constants, ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	static Map<String, String> saveQueries = new HashMap<String, String>();
	static Map<String, String> selectQueries = new HashMap<String, String>();
	static Map<String, String> updateQueries = new HashMap<String, String>();
	
	public static Map<String, String> getSaveQueries() {
		return saveQueries;
	}

	public static Map<String, String> getSelectQueries() {
		return selectQueries;
	}
	
	public static Map<String, String> getUpdateQueries() {
		return updateQueries;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		String columnNames = null;
		String columnNamesForUpdate=null;
		List<String> tableList = jdbcTemplate.queryForList(GET_TABLES, String.class);
		for (String tableName : tableList) {
			columnNames = jdbcTemplate.queryForObject(GET_COLUMNS, String.class, tableName);
			columnNamesForUpdate = jdbcTemplate.queryForObject(GET_COLUMNS_UPATE, String.class, tableName);
			saveQueries.put(tableName, createInsertStatement(tableName, columnNames));
			selectQueries.put(tableName, SELECT_SQL.replace(PARAM1, tableName));
			updateQueries.put(tableName,createUpdateStatement(tableName, columnNamesForUpdate));
		}
	}
	
	private String createInsertStatement(String tableName, String columnNames) {
		return INSERT_SQL.replace(PARAM1, tableName).replace(PARAM2, columnNames).replace(PARAM3, createInputParametes(columnNames));
	}
	
	private String createUpdateStatement(String tableName, String columnNames) {
		return UPDATE_SQL.replace(PARAM1, tableName).replace(PARAM2, createSetParametes(columnNames));
	}
	
	private String createInputParametes(String columnNames) {
		StringBuilder parameterQueue = new StringBuilder();
		for(@SuppressWarnings("unused") String column : columnNames.split(COMMA)) {
			parameterQueue.append(QUESTION_MARK+COMMA);
		}
		parameterQueue.setLength(parameterQueue.length() - ONE);
		return parameterQueue.toString();
	}
	
	private String createSetParametes(String columnNames) {
		StringBuilder parameterQueue = new StringBuilder();
		for(String column : columnNames.split(COMMA)) {
			parameterQueue.append(column+"="+QUESTION_MARK+COMMA);
		}
		parameterQueue.setLength(parameterQueue.length() - ONE);
		return parameterQueue.toString();
	}
	
}
