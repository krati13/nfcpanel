package com.springsecurity.nfc.constants;

public interface Constants {
	String INSERT_SQL = "INSERT INTO P1(P2) VALUES(P3)";
	String UPDATE_SQL = "UPDATE P1 SET P2 WHERE ID=?";
	String SELECT_SQL = "SELECT * FROM P1";
	String DELETE_SQL = "DELETE FROM P1 WHERE ID = P2";
	String DELETE_SQL_ANY = "DELETE FROM P1 WHERE P2 = P3";
	String SELECT_SQL_ANY = "SELECT * FROM P1 WHERE P2 = P3";
	String UPDATE_SQL_ANY = "UPDATE P1 SET P2=P3 WHERE P4 = P5";
	String MID_SQL = " WHERE MERCHANT_ID = ?";
	String MID_OID_SQL = " AND ORDER_ID = ?";
	String MID_ID_SQL = " WHERE ID = ?";
	String MID_ANY_SQL = " WHERE P1 = ?";
	String MID_CO_SQL = " AND CREATED_ON >= ? AND CREATED_ON <= ?";
	String MID_STATUS_SQL = " AND STATUS = 'ACTIVE' ORDER BY ORDER_ID";
	String MID_SID_SQL = " WHERE MERCHANT_ID = ? AND STATUS = ?";
	String CHANGE_PW_SQL="UPDATE USER SET PASSWORD=? WHERE ID=?";
	String QUESTION_MARK = "?";
	String EMPTY = "";
	String COMMA = ",";
	String PIPE = "|";
	String SESSION_USER = "user";
	String ID = "id";
	String QUANTITY = "quantity";
	String TABLE_ID = "tableId";
	String ORDER_ID = "orderId";
	String ORDER_DETAILS = "orderDetails";
	String MENU_ID = "menuId";
	String PARAM1 = "P1";
	String PARAM2 = "P2";
	String PARAM3 = "P3";
	String PARAM4 = "P4";
	String PARAM5 = "P5";
	String TABLE_NAME = "tableName";
	String ACTIVE = "ACTIVE";
	String INACTIVE = "INACTIVE";
	String COOKIE_ORDER = "order";
	String EMPTY_STRING = "";
	String MYSQL_DATE_FORMAT = "yyyy-MM-dd";
	
	int ACTIVE_INT=111;
	int COMPLETE_INT=112;
	
	int SEARCH_BY_MERCHANT_ID = 0;
	int SEARCH_BY_MERCHANT_ID_AND_ACTIVE_STATUS = 1;
	int SEARCH_BY_PRIMARY_KEY = 2;
	int SEARCH_BY_ANY_COLUMN = 3;
	int SEARCH_BY_ORDER_ID_AND_ACTIVE_STATUS = 4;
	int SEARCH_AVAILABLE_TABLES = 5;
	
	String ORDER_ID_KEY="ORDER_ID";
	String TXN_AMOUNT_KEY = "TOTAL_AMT";
	String BANK_REF_ID_KEY = "BANK_TXN_ID";
	String STATUS_KEY = "STATUS";
	String SUCCESS = "SUCCESS";

	String MESSAGE_FILE = "messages";
	String START_TIME = " 00:00:00";
	String END_TIME = " 23:59:59";
	String CREATED_FROM = "created_from";
	String CREATED_TO = "created_to";
	int ONE = 1;
	
	String USER_STATUS_PENDING="PENDING";
	String USER_STATUS_ACTIVE="ACTIVE";
	
	String MENU_IMG_FOLDER="MenuImages";
	
	String TXN_HEADER = "TXN_ID,ORDER_ID,TAX_AMT,SHIPPING_AMT,ORDER_AMT,TOTAL_AMT,BANK_TXN_ID,STATUS,CREATED_ON";
	String ORDER_HEADER = "ORDER ID,TABLE ID,MENU ID,QUANTITY,AMOUNT,STATUS,CREATED ON,MODIFIED ON";
	
	String FTP_URL="ftp.nikata.in";
	String FTP_HOST_DIR_PREFIX="public_html/upload/";
	String FTP_IMAGE_SERVER="http://www.nikata.in/upload/";
	String FTP_USERNAME = "arjunagarwal";
	String FTP_PASSWORD = "Arjun@2015";
	
	String BACKSLASH = "/";
	
	int PORT = 21;
	
	int ADMIN_ROLE=1;
	int ACTIVE_ROLE=2;
	int PASSIVE_ROLE=3;
}
