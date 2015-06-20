/**
JMathur
*/

package com.springsecurity.nfc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.constants.Queries;
import com.springsecurity.nfc.model.Category;
import com.springsecurity.nfc.model.DateSearch;
import com.springsecurity.nfc.model.Orders;
import com.springsecurity.nfc.model.Updatable;
import com.springsecurity.nfc.prototype.Prototype;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericDao<T> implements Constants, Queries.merchant {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String anyColumn;
	private String anyColumnValue;
	private String setColumn;
	private String setColumnValue;
	
	public String getAnyColumnValue() {
		return anyColumnValue;
	}


	public void setAnyColumnValue(String anyColumnValue) {
		this.anyColumnValue = anyColumnValue;
	}


	public String getAnyColumn() {
		return anyColumn;
	}


	public void setAnyColumn(String anyColumn) {
		this.anyColumn = anyColumn;
	}


	public String getSetColumn() {
		return setColumn;
	}


	public void setSetColumn(String setColumn) {
		this.setColumn = setColumn;
	}


	public String getSetColumnValue() {
		return setColumnValue;
	}


	public void setSetColumnValue(String setColumnValue) {
		this.setColumnValue = setColumnValue;
	}


	/**
	 * This is a generic method, which fetch insert query from Prototype class,
	 * It is mandatory to override toString() & within toString(), member variables order is crucial.
	 * @param entity
	 */
	public int save(final T entity) {
		int noOfRowsUpdated=0;
		System.out.println(entity.toString());
	      try {
	    	  noOfRowsUpdated = jdbcTemplate.update(Prototype.getSaveQueries().get(entity.getClass().getSimpleName().toUpperCase()), (Object[])entity.toString().split(Pattern.quote(PIPE)));
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return noOfRowsUpdated;
	}
	
	
	/**
	 * This is a generic method, which fetch update query from Prototype class,
	 * It is mandatory to override toUpdate() & within toUpdate(), member variables order is crucial.
	 * toUpdate() should not contain CREATED_ON,CREATED_BY and MERCHANT_ID. Primary key should be last parameter.
	 * Need to implement Update Interface to use this method.
	 * @param entity
	 */
	public void update(final Updatable entity) {
	      try {
	    	  jdbcTemplate.update(Prototype.getUpdateQueries().get(entity.getClass().getSimpleName().toUpperCase()), (Object[])entity.toUpdate().split(Pattern.quote(PIPE)));
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}
	
	public void delete(final Updatable entity) {
	      try {
	    	  jdbcTemplate.execute(DELETE_SQL.replace(PARAM1,entity.getClass().getSimpleName().toUpperCase()).replace(PARAM2, entity.toDelete()));
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}
	
	public void deleteAny(final Updatable entity) {
	      try {
	    	  jdbcTemplate.execute(DELETE_SQL_ANY.replace(PARAM1,entity.getClass().getSimpleName().toUpperCase()).replace(PARAM2, getAnyColumn()).replace(PARAM3, getAnyColumnValue()));
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}
	
	/**
	 * This is a generic method, which fetch data for last 30 Days without any other constraints.
	 * Entity need to be passed from controller
	 * @param entity
	 * @return List<Entity> 
	 */
	public List<T> fetch(final T entity) {
		try {
			return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()), new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method to get list of objects based on any condition. anyColumn and anyColumnValue must not be null.
	 * @param entity
	 * @return
	 */
	public List<T> fetchAny(final T entity) {
		try {
			return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()).concat(" WHERE "+getAnyColumn()+" ='"+getAnyColumnValue()+"'"), new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This is a generic method, which fetch data for last 30 Days without any other constraints.
	 * Entity need to be passed from controller & Merchant ID
	 * @param entity
	 * @param merchantId
	 * @param searchBy
	 * @return List<Entity> 
	 */
	public List<T> fetchById(T entity, long merchantId, int searchBy, DateSearch date) {
		try {
			switch (searchBy) {
			case SEARCH_BY_MERCHANT_ID:
				return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()) + MID_SQL + MID_CO_SQL, new Object[]{merchantId, date.getCreated_from()+START_TIME, date.getCreated_to()+END_TIME}, new BeanPropertyRowMapper(entity.getClass()));
			case SEARCH_BY_MERCHANT_ID_AND_ACTIVE_STATUS:
				return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()) + MID_SQL + MID_STATUS_SQL, new Object[]{merchantId}, new BeanPropertyRowMapper(entity.getClass()));
			case SEARCH_BY_PRIMARY_KEY:
				return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()) + MID_ID_SQL, new Object[]{merchantId}, new BeanPropertyRowMapper(entity.getClass()));
			case SEARCH_BY_ANY_COLUMN:
				return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()) + MID_ANY_SQL.replace(PARAM1, anyColumn), new Object[]{anyColumnValue}, new BeanPropertyRowMapper(entity.getClass()));
			case SEARCH_AVAILABLE_TABLES:
				return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName().toUpperCase()) + MID_SID_SQL, new Object[]{merchantId,0}, new BeanPropertyRowMapper(entity.getClass()));
			default:
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method can be used for getting single object using uniquely identifying key
	 * @param entity
	 * @return
	 */
	public Object fetchByUniqueKey(T entity) {
		try {
	    	  return jdbcTemplate.queryForObject(SELECT_SQL_ANY.replace(PARAM1,entity.getClass().getSimpleName().toUpperCase()).replace(PARAM2, getAnyColumn()).replace(PARAM3, getAnyColumnValue()), new BeanPropertyRowMapper(entity.getClass()));
	      } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	      }
	}
	
	public void updateAny(T entity) {
	      try {
	    	  String sql=UPDATE_SQL_ANY.replace(PARAM1,entity.getClass().getSimpleName().toUpperCase()).replace(PARAM2, getSetColumn()).replace(PARAM3, getSetColumnValue()).replace(PARAM4, getAnyColumn()).replace(PARAM5, getAnyColumnValue());
	    	  System.out.println(sql);
	    	  jdbcTemplate.execute(sql);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}
	
	/**
	 * This method used to get active orders on the basis of merchant id.
	 * @param userId
	 * @return List<Orders>
	 */
	public List<Orders> getActiveOrders(long userId) {
		List<Orders> orderList = null;
		try {
			orderList = jdbcTemplate.query(ACTIVE_MENU, new Object[]{userId}, new BeanPropertyRowMapper(Orders.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public String fetchMenuId(String orderId) {
		String menuId = null;
		try {
			menuId = jdbcTemplate.queryForObject(GET_MENU_ID_BY_ORDER_ID,new Object[]{orderId}, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuId;
	}	
	
	public int updateQuantity(Orders order) {
		int noOfRowsUpdated = 0;
		try {
			noOfRowsUpdated = jdbcTemplate.update(UPDATE_QUANTITY_BY_MENU_ID, new Object[]{order.getQuantity(), order.getAmount(), order.getOrder_id(), order.getMenu_id()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfRowsUpdated;
	}
	
	public List<Category> getCategories(long userId,int levelnumber, String firstLevel) {
		List<Category> categoryList = new ArrayList<Category>();
		String like="";
		if(levelnumber==1){
			firstLevel="%%";
		}else if(levelnumber==2){
			if(firstLevel.length()>=2){
			like=firstLevel.substring(0,2);
			firstLevel=""+like+"%";
			}else{
			firstLevel="%%";
			}
		}
		try {
			categoryList = jdbcTemplate.query(GET_CATEGORIES, new Object[]{userId,levelnumber,firstLevel}, new BeanPropertyRowMapper(Category.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public String getNextSortOrder(long userId,int levelno, String firstLevel) {
		String sortorder="";
		String like="";
		if(levelno==1){
			firstLevel="%%";
		}else if(levelno==2){
			like=firstLevel.substring(0,2);
			firstLevel=""+like+"%";
		}
		try {
			int max= jdbcTemplate.queryForInt(GET_MAX_SORTORDER, new Object[]{userId,levelno,firstLevel});
			int next=11;
			if(max!=0)
				next=Integer.parseInt(String.valueOf(max).substring(2*levelno-2, 2*levelno))+1;
			
			sortorder=next+"00000";
			if(levelno==2)
				sortorder=like+next+"000";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortorder;
	}
}