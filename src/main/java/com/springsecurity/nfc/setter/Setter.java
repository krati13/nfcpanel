package com.springsecurity.nfc.setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.springsecurity.nfc.model.Orders;

public abstract class Setter {
	
	public static Map<String, List<Orders>> generateCollapseView(List<Orders> ordersList) {
		ordersList=mergeDuplicateOrders(ordersList);
		Map<String, List<Orders>> collpaseViewMap = new HashMap<String, List<Orders>>();
		for (Orders order : ordersList) {
			if (null!=collpaseViewMap.get(String.valueOf(order.getTable_id()))) {
				List<Orders> orderList = collpaseViewMap.get(String.valueOf(order.getTable_id()));
				orderList.add(order);
			} else {
				List<Orders> orderList = new ArrayList<Orders>();
				orderList.add(order);
				collpaseViewMap.put(String.valueOf(order.getTable_id()), orderList);
			}
		}
		return collpaseViewMap;
	}
	
	public static List<Orders> mergeDuplicateOrders(List<Orders> ordersList) {
		List<Orders> orders = new ArrayList<Orders>();

		for (Orders order : ordersList) {
			int index = orders.indexOf(order);
			if (index > -1) {
				Orders tempOrder = orders.get(index);
				order.setQuantity(tempOrder.getQuantity() + order.getQuantity());
				order.setAmount(tempOrder.getAmount() + order.getAmount());
				orders.remove(index);
			}
			orders.add(order);
		}
		return orders;
	}
}
