package com.springsecurity.nfc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.constants.Queries;
import com.springsecurity.nfc.model.Colors;
import com.springsecurity.nfc.model.DataSetsFootfall;
import com.springsecurity.nfc.model.DataSetsTxn;
import com.springsecurity.nfc.model.FootFallGraph;
import com.springsecurity.nfc.model.FootfallGraphData;
import com.springsecurity.nfc.model.Menu;
import com.springsecurity.nfc.model.MenuGraph;
import com.springsecurity.nfc.model.Orders;
import com.springsecurity.nfc.model.TxnGraph;
import com.springsecurity.nfc.model.TxnGraphData;
import com.springsecurity.nfc.prototype.Prototype;

@Repository
public class MerchantDao<T> implements Constants, Queries.merchant {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * This method fetch Active Status Entities merchant wise.
	 * Entities can be TxnHistory, Orders etc
	 * @param entity
	 * @param merchantId
	 * @return List<Entity> Entity we will pass from controller.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getActiveData(T entity, Long merchantId) {
		try {
			return jdbcTemplate.query(Prototype.getSelectQueries().get(entity.getClass().getSimpleName()) + MID_SID_SQL,  new Object[]{merchantId, ACTIVE},new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method fetch the data for graphs, Sum & count of Amount from Transaction History Table for last 2 weeks
	 * @param merchantId
	 * @return List<TxnGraph>
	 */
	public TxnGraphData getTxnGraphData(long merchantId) {
		try {
			List<TxnGraph> txnGraphList = jdbcTemplate.query(TXN_GRAPH, new Object[]{merchantId}, new BeanPropertyRowMapper<TxnGraph>(TxnGraph.class));
			String[] labels = new String[txnGraphList.size()];
			String[] data = new String[txnGraphList.size()];
			int index=0;
			for (TxnGraph txnGraph : txnGraphList) {
				labels[index] = txnGraph.getLabel();
				data[index++] = txnGraph.getValue();
			}	
			DataSetsTxn dataSets = new DataSetsTxn(data);
			return new TxnGraphData(labels, dataSets);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method fetch the data for graphs, count of menu item datewise from Order Table for last 2 weeks
	 * @param merchantId
	 * @return List<MenuGraph>
	 */
	public List<MenuGraph> getMenuGraphData(long merchantId) {
		try {
			List<MenuGraph> menuGraphList = jdbcTemplate.query(MENU_GRAPH, new Object[]{merchantId}, new BeanPropertyRowMapper<MenuGraph>(MenuGraph.class));
			int i=1;
			for (MenuGraph menuItem : menuGraphList) {
				menuItem.setColor(Colors.getColor(i));
				menuItem.setHighlight(Colors.getColor(i));
				i++;
			}
			return menuGraphList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method fetch the data for graphs, count of footfall datewise from Order Table for last 2 weeks
	 * @param merchantId
	 * @return List<MenuGraph>
	 */
	public FootfallGraphData getFootfallGraphData(long merchantId) {
		try {
			List<FootFallGraph> footFallGraphList = jdbcTemplate.query(FOOTFALL_GRAPH, new Object[]{merchantId}, new BeanPropertyRowMapper<FootFallGraph>(FootFallGraph.class));
			String[] labels = new String[footFallGraphList.size()];
			String[] data = new String[footFallGraphList.size()];
			int index=0;
			for (FootFallGraph footFallGraph : footFallGraphList) {
				labels[index] = footFallGraph.getLabel();
				data[index++] = footFallGraph.getData();
			}
			DataSetsFootfall dataSets  = new DataSetsFootfall(data);
			return new FootfallGraphData(labels, dataSets);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * RequestMapping for changing password
	 * @param ID
	 * @param PW
	 */
	public void updatePW(String ID, String PW) {
	      try {
	    	  jdbcTemplate.update(CHANGE_PW_SQL, new Object[]{PW, ID});
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}
	
	public List<Menu> getMenuItems(long merchantId, String LIKE_NAME) {
		try {
			if (LIKE_NAME.isEmpty())
				return jdbcTemplate.query(GET_ALL_MENU.replace(PARAM1, merchantId+EMPTY), new BeanPropertyRowMapper<Menu>(Menu.class));
			else
				return jdbcTemplate.query(GET_FILTERED_MENU.replace(PARAM1, merchantId+EMPTY).replace(PARAM2, LIKE_NAME), new BeanPropertyRowMapper<Menu>(Menu.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * This method used to get single order on the basis of merchant id and order id.
	 * @param userId
	 * @param order_id
	 * @return List<Orders>
	 */
	public List<Orders> getOrders(long userId,String order_id) {
		List<Orders> orderList = null;
		try {
			orderList = jdbcTemplate.query(SINGLE_ORDER, new Object[]{userId,order_id}, new BeanPropertyRowMapper<Orders>(Orders.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}	
}