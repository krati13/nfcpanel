package com.springsecurity.nfc.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springsecurity.auth.model.User;
import com.springsecurity.auth.service.UserServiceImpl;
import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.dao.GenericDao;
import com.springsecurity.nfc.dao.MerchantDao;
import com.springsecurity.nfc.model.Merchant;
import com.springsecurity.nfc.model.Tables;
import com.springsecurity.plugin.util.AlphaNum;

@Controller
@RequestMapping("/admin")
public class AdminController implements Constants{
	@SuppressWarnings("rawtypes")
	@Autowired
	private GenericDao genericDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private UserServiceImpl userServiceImpl;
	private Tables tables = new Tables();
	private Merchant merchant = new Merchant();
	private User user = new User();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addMerchant", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public @ResponseBody Merchant addMerchant(@RequestBody Merchant merchant) {
		String mType=merchant.getMerchantType();
//		if(mType==null || !mType.equals("1")){
//			mType="2";
//		}else{
//			mType="3";
//		}
		merchant.setMerchantType(mType);
		merchant.setBusinessName(new AlphaNum().replaceSpecials(merchant.getBusinessName(), EMPTY));
		
		genericDao.save(merchant);
		genericDao.setAnyColumn("email");
		genericDao.setAnyColumnValue(merchant.getEmail());
		merchant=(Merchant)genericDao.fetchById(merchant, 0, SEARCH_BY_ANY_COLUMN, null).get(0);
		
		
		
		if(mType.equals(ACTIVE_ROLE+"")){
		int tableCount=merchant.getTableCount();
		Tables tab=new Tables();
		for(int i=1;i<=tableCount;i++){
			tab.setMerchant_id(merchant.getId());
			tab.setReserved(0);
			tab.setTID("T"+i);
			tab.setStatus(0);
			genericDao.save(tab);
		}
		}
		
		User user=new User();
		user.setUserId(merchant.getId());
		user.setName(merchant.getFirstName()+" "+merchant.getLastName());
		user.setPassword(UUID.randomUUID().toString().substring(0,6));
		user.setStatus(USER_STATUS_ACTIVE);
		user.setLoginName(merchant.getBusinessName());
		user.setEmail(merchant.getEmail());
		
		user=userServiceImpl.addUser(user);
		userServiceImpl.addUserRole((int)user.getUserId(), Integer.parseInt(mType));
		
		
		return merchant;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMerchantList", method = RequestMethod.GET)
	public ModelAndView getMerchantList(@RequestParam("msg") String message) {
		List<Merchant> merchants=genericDao.fetch(new Merchant());
		ModelAndView view=new ModelAndView("listMerchants");
		view.addObject("message", message);
		view.addObject("merchants", merchants);
		return view;
	}
	
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView getDashboard() {
		ModelAndView view=new ModelAndView("adminDashboard");
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dashboardView", method = RequestMethod.GET)
	public ModelAndView getDashboardView() {
		List<Merchant> merchants=genericDao.fetch(new Merchant());
		merchants=merchants.subList(0, Math.min(merchants.size(), 10));
		ModelAndView view=new ModelAndView("adminDashboardView");
		view.addObject("merchants", merchants);
		return view;
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public @ResponseBody Merchant updateMerchant(@RequestBody Merchant merchant) {
		genericDao.update(merchant);
		return merchant;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView manageProfile(@RequestParam("mid") String merchantId,HttpSession session, HttpServletRequest request, ModelAndView view) {
		String editMode = request.getParameter("edit");
		if (editMode != null && editMode.equals("0")) {
			view.setViewName("profileView");
			List<Merchant> merchantList = genericDao.fetchById(merchant, Long.parseLong(merchantId), SEARCH_BY_PRIMARY_KEY, null);
			view.addObject("merchant", merchantList.get(0));
		} else if (editMode != null && editMode.equals("1")) {
			view.setViewName("profile");
			List<Merchant> merchantList = genericDao.fetchById(merchant, Long.parseLong(merchantId), SEARCH_BY_PRIMARY_KEY, null);
			view.addObject("merchant", merchantList.get(0));
		} else if (editMode != null && editMode.equals("2")) {
			List<User> userList = genericDao.fetchById(user, Long.parseLong(merchantId), SEARCH_BY_PRIMARY_KEY, null);
			List<Merchant> merchantList = genericDao.fetchById(merchant, Long.parseLong(merchantId), SEARCH_BY_PRIMARY_KEY, null);
			view.setViewName("changePW");
			view.addObject("merchant", merchantList.get(0));
			view.addObject("user", userList.get(0));
		}

		view.addObject("editMode", editMode);
		view.addObject("controlMode", "admin");
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changePW", method = RequestMethod.GET)
	public ModelAndView changePW(@RequestParam("mid") String merchantId,HttpSession session,HttpServletRequest request, ModelAndView view) {
		List<User> userList = genericDao.fetchById(user, Long.parseLong(merchantId), SEARCH_BY_PRIMARY_KEY, null);
		User rootUser=(User)session.getAttribute(SESSION_USER);
		User user = userList.get(0);
		String oldPass=request.getParameter("op");
		String newPass=request.getParameter("np");
		String newPassC=request.getParameter("npc");
		
		if (rootUser.getRole().getId()!=ADMIN_ROLE && !user.getPassword().equals(oldPass)) {
			view.addObject("msg", "Invalid current password");
		} else if (!newPass.equals(newPassC)) {
			view.addObject("msg", "Password do not match");
		} else {
			merchantDao.updatePW(merchantId, newPass);
			view.addObject("msg", "Password changed successfully");
		}
		
		view.setViewName("changePW");
		return view;
	}
}
