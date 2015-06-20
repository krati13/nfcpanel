/**
JMathur
*/

package com.springsecurity.nfc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springsecurity.auth.model.User;
import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.dao.GenericDao;
import com.springsecurity.nfc.dao.MerchantDao;
import com.springsecurity.nfc.model.FootfallGraphData;
import com.springsecurity.nfc.model.MenuGraph;
import com.springsecurity.nfc.model.TxnGraphData;

@Controller
@RequestMapping("/merchant/graph")
public class MerchantGraphController implements Constants{
	@SuppressWarnings("rawtypes")
	@Autowired
	private GenericDao genericDao;
	@SuppressWarnings("rawtypes")
	@Autowired
	private MerchantDao merchantDao;
	
	@RequestMapping(value = "/txn_graph", method = RequestMethod.GET)
	public @ResponseBody TxnGraphData getTxnGraph(HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		return merchantDao.getTxnGraphData(user.getUserId());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/menu_graph", method = RequestMethod.GET)
	public @ResponseBody List<MenuGraph> getMenuGraph(HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		return  merchantDao.getMenuGraphData(user.getUserId());
	}
	
	@RequestMapping(value = "/footfall_graph", method = RequestMethod.GET)
	public @ResponseBody FootfallGraphData getFootFallGraph(HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		return merchantDao.getFootfallGraphData(user.getUserId());
	}
	
}
