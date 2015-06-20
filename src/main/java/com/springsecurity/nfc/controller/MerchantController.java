package com.springsecurity.nfc.controller;
/**
 * @author Gaurav Oli
 * This is a main controller for Manager Role
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springsecurity.auth.model.User;
import com.springsecurity.auth.util.BillGenerator;
import com.springsecurity.nfc.constants.Constants;
import com.springsecurity.nfc.dao.GenericDao;
import com.springsecurity.nfc.dao.MerchantDao;
import com.springsecurity.nfc.download.XlsxDownloader;
import com.springsecurity.nfc.ftp.FTPLoader;
import com.springsecurity.nfc.model.Category;
import com.springsecurity.nfc.model.DateSearch;
import com.springsecurity.nfc.model.Menu;
import com.springsecurity.nfc.model.Merchant;
import com.springsecurity.nfc.model.Orders;
import com.springsecurity.nfc.model.OrdersWrapper;
import com.springsecurity.nfc.model.Tables;
import com.springsecurity.nfc.model.TxnHistory;
import com.springsecurity.nfc.setter.Setter;
import com.springsecurity.plugin.util.ImageResizer;
import com.springsecurity.plugin.util.RandomFuncs;

@Controller
@RequestMapping("/merchant")
@SuppressWarnings("unchecked")
public class MerchantController implements Constants {
	@SuppressWarnings("rawtypes")
	@Autowired
	private GenericDao genericDao;
	@SuppressWarnings("rawtypes")
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private XlsxDownloader xlsxDownloader;
	@Autowired
	ServletContext context;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat(MYSQL_DATE_FORMAT);
	private Calendar calender = null;
	
	private Orders orders = new Orders();
	private TxnHistory txnHistory = new TxnHistory();
	private Menu menu = new Menu();
	private Tables tables = new Tables();
	private Category category=new Category();
	private Merchant merchant = new Merchant();
	private User user = new User();
	
	/**
	 * This is the home page for manager role.
	 * @return ModelAndView view
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView getDashboard() {
		ModelAndView view=new ModelAndView("merchantDashboard");
		return view;
	}
	
	
	/**
	 * This is the first request comes when manager login. 
	 * Its a dashboard view page where graphs is deployed, which display information like Txn Graph, Menu Graph and FootFall graph.
	 * @param view
	 * @return  ModelAndView view, GraphData
	 */
	@RequestMapping(value = "/dashboardView", method = RequestMethod.GET)
	public ModelAndView getDashBoardView(ModelAndView view, HttpSession session) {
		view.setViewName("merchantDashboardView");
		return view;
	}
	
	
	/**
	 * This request to display Order data for last 30 days.
	 * Active and Inactive both types of Order fetch and display on webpage
	 * @param session
	 * @param view
	 * @return  ModelAndView view, List<Orders> orderList
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView getOrders(HttpSession session, ModelAndView view) {
		User user = (User) session.getAttribute(SESSION_USER);
		List<Orders> orderList=genericDao.fetchById(orders, user.getUserId(), SEARCH_BY_MERCHANT_ID,  getPastDate(30));
		view.setViewName("order");
		view.addObject("orders", orderList);
		return view;
	}
	
	/**
	 * This request to display Transaction data for last 30 days.
	 * Active and Inactive both types of Transaction fetch and display on webpage
	 * This feature is enable for those merchant who has payment gateway.
	 * @param session
	 * @param view
	 * @return  ModelAndView view, List<Orders> orderList
	 */
	@RequestMapping(value = "/orderSearch", method = RequestMethod.GET)
	public ModelAndView getOrdersByDate(@RequestParam("created_from") String created_from,
										@RequestParam("created_to") String created_to, HttpSession session, ModelAndView view) {
		DateSearch dateSearch = null;
		if (created_from==null && created_to==null) {
			dateSearch =  getPastDate(30);
		} else
			dateSearch = new DateSearch(created_from, created_to);
		
		User user = (User) session.getAttribute(SESSION_USER);
		List<Orders> orderList=genericDao.fetchById(orders, user.getUserId(), SEARCH_BY_MERCHANT_ID, dateSearch);
		view.setViewName("orderSearch");
		view.addObject("orders", orderList);
		view.addObject("startDate",created_from);
		view.addObject("endDate",created_to);
		return view;
	}
	
	/**
	 * This request to download Transaction data as per the date selected in input boxes.
	 * Active and Inactive both types of Transaction fetch and display on webpage
	 * This feature is enable for those merchant who has payment gateway.
	 * @param session
	 * @param view
	 * @return  ModelAndView view, List<Orders> orderList
	 * @throws IOException 
	 */
	@RequestMapping(value = "/orderDownload", method = RequestMethod.GET)
	public void getOrderDownloadFile(@RequestParam("created_from") String created_from,
									 @RequestParam("created_to") String created_to, HttpSession session, HttpServletResponse response) throws IOException {
		
		DateSearch dateSearch = null;
		if (EMPTY_STRING.equals(created_from) && EMPTY_STRING.equals(created_to)) 
			dateSearch =  getPastDate(30);
		else
			dateSearch = new DateSearch(created_from, created_to);
		
		User user = (User) session.getAttribute(SESSION_USER);
		xlsxDownloader.downloadOrders(genericDao.fetchById(orders, user.getUserId(), SEARCH_BY_MERCHANT_ID, dateSearch), response);
	}
	
	/**
	 * This request to display Active Order page.
	 * @param session
	 * @param view
	 * @return orderList of Active Orders with a active order page.
	 */
	@RequestMapping(value = "/activeOrder", method = RequestMethod.GET)
	public ModelAndView getActiveOrders(HttpSession session, ModelAndView view) {
		User user = (User) session.getAttribute(SESSION_USER);
		Map<String, List<Orders>> collapseViewMap = Setter.generateCollapseView(genericDao.getActiveOrders(user.getUserId()));
		view.setViewName("activeOrder");
		view.addObject("orders", collapseViewMap);
		return view;
	}
	
	
	/**
	 * This request to display Transaction data for last 30 days.
	 * Active and Inactive both types of Transaction fetch and display on webpage
	 * This feature is enable for those merchant who has payment gateway.
	 * @param session
	 * @param view
	 * @return  ModelAndView view, List<Orders> orderList
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public ModelAndView getTransactions(HttpSession session, ModelAndView view) {
		User user = (User) session.getAttribute(SESSION_USER);
		List<TxnHistory> txn=genericDao.fetchById(txnHistory, user.getUserId(), SEARCH_BY_MERCHANT_ID, getPastDate(30));
		view.setViewName("transaction");
		view.addObject("txns", txn);
		return view;
	}
	
	/**
	 * This request to display Transaction data for last 30 days.
	 * Active and Inactive both types of Transaction fetch and display on webpage
	 * This feature is enable for those merchant who has payment gateway.
	 * @param session
	 * @param view
	 * @return  ModelAndView view, List<Orders> orderList
	 */
	@RequestMapping(value = "/transactionSearch", method = RequestMethod.GET)
	public ModelAndView getTransactionsByDate(@RequestParam("created_from") String created_from,
											  @RequestParam("created_to") String created_to, HttpSession session, ModelAndView view) {
		DateSearch dateSearch = new DateSearch(created_from, created_to);
		User user = (User) session.getAttribute(SESSION_USER);
		List<TxnHistory> txn=genericDao.fetchById(txnHistory, user.getUserId(), SEARCH_BY_MERCHANT_ID, dateSearch);
		view.setViewName("transactionSearch");
		view.addObject("txns", txn);
		view.addObject("startDate",created_from);
		view.addObject("endDate",created_to);
		return view;
	}
	
	/**
	 * This request to download Transaction data as per the date selected in input boxes.
	 * Active and Inactive both types of Transaction fetch and display on webpage
	 * This feature is enable for those merchant who has payment gateway.
	 * @param session
	 * @param view
	 * @return  ModelAndView view, List<TxnHistory> orderList
	 * @throws IOException 
	 */
	@RequestMapping(value = "/transactionDownload", method = RequestMethod.GET)
	public void getTransactionsDownloadFile(@RequestParam("created_from") String created_from,
											@RequestParam("created_to") String created_to, HttpSession session, HttpServletResponse response) throws IOException {
		
		DateSearch dateSearch = null;
		if (EMPTY_STRING.equals(created_from) && EMPTY_STRING.equals(created_to)) {
			dateSearch =  getPastDate(30);
		} else
			dateSearch = new DateSearch(created_from, created_to);
		
		User user = (User) session.getAttribute(SESSION_USER);
		xlsxDownloader.downloadTxnHistory(genericDao.fetchById(txnHistory, user.getUserId(), SEARCH_BY_MERCHANT_ID, dateSearch), response);
	}
	
	/**
	 * This request is to display the existing menu items for any particular merchant. Besides it 
	 * will have an option to add or edit menu items.
	 * @param session
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/addMenu", method = RequestMethod.GET)
	public ModelAndView addMenu(HttpSession session, ModelAndView view) {
		User user = (User) session.getAttribute(SESSION_USER);
		view.setViewName("addMenu");
		return prepareMenu(view, user.getUserId());
	}
	
	/**
	 * This mapping will save menu object in database
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public @ResponseBody Menu addMenu(@RequestBody Menu menu) {
		genericDao.save(menu);
		return menu;
	}
	
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
	public @ResponseBody Menu deleteMenu(HttpServletRequest request) {
		String id=request.getParameter("id");
		menu.setId(Long.parseLong(id));
		genericDao.delete(menu);
		return menu;
	}
	
	
	@RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
	public @ResponseBody Menu updateMenu(@RequestBody Menu menu) {
		genericDao.update(menu);
		return menu;
	}
	
	@RequestMapping(value = "/editMenu", method = RequestMethod.GET)
	public ModelAndView editMenu(HttpSession session, HttpServletRequest request, ModelAndView view) {
		User user = (User) session.getAttribute(SESSION_USER);
		String editMode=request.getParameter("edit");
		String id=request.getParameter("id");
		int pid=0;
		try{
			pid=Integer.parseInt(id);
		}catch(Exception e){
			pid=0;
		}
		
		if(editMode==null || !editMode.equals("1")){
			editMode="";
		}
		
		List<Menu> menuList=genericDao.fetchById(menu, pid, SEARCH_BY_PRIMARY_KEY, getPastDate(30));
		List<Category> cateList=genericDao.getCategories(user.getUserId(),1,EMPTY);
		for(Menu menu:menuList){
			for(Category category:cateList){
				if(category.getId()==Long.parseLong(menu.getCategory())){
					menu.setCategoryObj(category);
					break;
				}else{
					continue;
				}
			}	
		}
		view.setViewName("updateMenu");
		view.addObject("editMode",editMode);
		view.addObject("menu", menuList.get(0));
		view.addObject("cateList", cateList);
		return view;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView manageProfile(HttpSession session, HttpServletRequest request, ModelAndView view) {
		User userObj = (User) session.getAttribute(SESSION_USER);
		String editMode = request.getParameter("edit");
		if (editMode != null && editMode.equals("0")) {
			view.setViewName("profileView");
			List<Merchant> merchantList = genericDao.fetchById(merchant, userObj.getUserId(), SEARCH_BY_PRIMARY_KEY, null);
			view.addObject("merchant", merchantList.get(0));
		} else if (editMode != null && editMode.equals("1")) {
			view.setViewName("profile");
			List<Merchant> merchantList = genericDao.fetchById(merchant, userObj.getUserId(), SEARCH_BY_PRIMARY_KEY, null);
			view.addObject("merchant", merchantList.get(0));
		} else if (editMode != null && editMode.equals("2")) {
			List<User> userList = genericDao.fetchById(user, userObj.getUserId(), SEARCH_BY_PRIMARY_KEY, null);
			view.setViewName("changePW");
			view.addObject("user", userList.get(0));
		}

		view.addObject("controlMode", "user");
		view.addObject("editMode", editMode);
		return view;
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public @ResponseBody Merchant addMerchant(@RequestBody Merchant merchant,HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		
		genericDao.update(merchant);
		
		if(user.getRole().getId()==ACTIVE_ROLE){
		genericDao.setAnyColumn("MERCHANT_ID");
		genericDao.setAnyColumnValue(merchant.getId()+"");
		genericDao.deleteAny(tables);

		
		
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
		
		return merchant;
	}
	
	/**
	 * This request is made to change password of a merchant.
	 * @param session
	 * @param request
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/changePW", method = RequestMethod.GET)
	public ModelAndView changePW(HttpSession session,HttpServletRequest request, ModelAndView view) {
		User user = (User) session.getAttribute(SESSION_USER);
		String oldPass=request.getParameter("op");
		String newPass=request.getParameter("np");
		String newPassC=request.getParameter("npc");
		
		if (!user.getPassword().equals(oldPass) && user.getRole().getId()!=ADMIN_ROLE) {
			view.addObject("msg", "Invalid current password");
		} else if (!newPass.equals(newPassC)) {
			view.addObject("msg", "Password do not match");
		} else {
			merchantDao.updatePW(user.getUserId()+"", newPass);
			view.addObject("msg", "Password changed successfully");
		}
		
		view.setViewName("changePW");
		return view;
	}
	

	/**
	 * This request is to generate bill regarding the passing orderId as parameter.
	 * Bill is generated in PDF format.
	 * @param orderId
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="/generateBill", method=RequestMethod.GET)
	private void generateBill(@RequestParam("orderId") String orderId, HttpServletResponse response, HttpSession session) throws IOException {
		User user = (User) session.getAttribute(SESSION_USER);
		genericDao.setAnyColumn("ORDER_ID");
		genericDao.setAnyColumnValue(orderId);
		BillGenerator.generateBill(genericDao.fetchById(orders, user.getUserId(), SEARCH_BY_ANY_COLUMN, null), response);
	}
	
	
	/**
	 * Request mapping done for uploading menu images
	 * @param name
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam(value="name",required=false) String name,
    											  @RequestParam("file") MultipartFile file, HttpSession session) throws Exception{
		User user = (User) session.getAttribute(SESSION_USER);
		if (!file.isEmpty()) {
            try {
            	RandomFuncs RF=new RandomFuncs();
            	ImageResizer IR=new ImageResizer();
            	long ctime=System.currentTimeMillis();
            	String smallFile=ctime+"_small.png";
            	String originalFile=ctime+".png";
            	
            	File fileOriginal=IR.createFile(file, context, originalFile);
            	File fileSmall=new File(fileOriginal.getPath().replace(fileOriginal.getName(), smallFile));

            	String width = RF.width_height(fileOriginal, "w");
                String height = RF.width_height(fileOriginal, "h");

            	int w = Integer.parseInt(width);
                int h = Integer.parseInt(height);

                int fh = 100;
                w = (int) ((fh * 100 / h) * w) / 100;
            	fileSmall = IR.reziseTo(fileOriginal, fileSmall, w, fh, "png");

            	String ext=".png";
                if (name==null || name.isEmpty()) {
                	throw new Exception("No file name provided");
                }
                String fileName="2"+ext;
                String fileName_small="2_small"+ext;
                
                String returnPath=FTP_IMAGE_SERVER + user.getUserId()+"/"+name+"/"+fileName_small;
                String hostDir=FTP_HOST_DIR_PREFIX+user.getUserId()+"/"+name+"/";

                FTPLoader fTPLoader = new FTPLoader();
                fTPLoader.uploadFile(new FileInputStream(fileSmall), fileName_small, hostDir);
                
                fTPLoader = new FTPLoader();
                fTPLoader.uploadFile(new FileInputStream(fileOriginal), fileName, hostDir);
                
                return returnPath;
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "2";
        }
    }
	
	/**
	 * RequestMapping made to cancel an existing active order.
	 * @param orderId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public @ResponseBody String cancelOrder(@RequestParam("orderId") String orderId) throws IOException {
		genericDao.setAnyColumn("order_Id");
		genericDao.setAnyColumnValue("'"+orderId+"'");
		genericDao.setSetColumn("STATUS");
		genericDao.setSetColumnValue("'CANCELED'");
		genericDao.updateAny(orders);
		return "SUCCESS";
	}
	
	/**
	 * This method is used to append new order in existing ACTIVE Orders
	 * @param orderList
	 * @return Orders
	 */
	@RequestMapping(value = "/appendOrder", method = RequestMethod.POST)
	public @ResponseBody OrdersWrapper  appendOrder(@RequestBody OrdersWrapper orders,HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		List<Orders> orderList=orders.getOrders();
		String orderId=orderList.get(0).getOrder_id();
		String menuId = genericDao.fetchMenuId(orderId);
		if(menuId==null){
			menuId="";
		}
		for (Orders order : orderList) {
			if (menuId.contains(String.valueOf(order.getMenu_id())))
				genericDao.updateQuantity(order);
			else
				genericDao.save(order);
		}
		
		List<Orders> ordersUpdated=merchantDao.getOrders(user.getUserId(), orderId);
		ordersUpdated=Setter.mergeDuplicateOrders(ordersUpdated);
		orders.setOrders(ordersUpdated);
		
		return orders;
	}
	
	/**
	 * This method is used to append new order in existing ACTIVE Orders
	 * @param orderList
	 * @return Orders
	 */
	@RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
	public @ResponseBody String updateQuantity(@RequestBody Orders order) {
		int count = genericDao.updateQuantity(order);
		if (count > 0)
			return "SUCCESS";
		else
			return "FAILURE";
	}
	
	
	/**
	 * This method return the list of tables for thet merchant
	 * @return List<Tables>
	 */
	@RequestMapping(value = "/tableStatus", method = RequestMethod.GET)
	public @ResponseBody List<Tables> getTablesStatus(HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		return genericDao.fetchById(tables, user.getUserId(), SEARCH_AVAILABLE_TABLES, null);
	}
	
	/**
	 * This method is used to generate Order Id for Manual Ordering
	 * @return OrderId
	 */
	@RequestMapping(value = "/generateOrder", method = RequestMethod.GET)
	public @ResponseBody String generateOrderId() {
		return UUID.randomUUID().toString();
	}
	
	
	@RequestMapping(value = "/appendOrder", method = RequestMethod.GET)
	public ModelAndView getAppendOrder(@RequestParam("o_id") String o_id,@RequestParam("t_id") String t_id,ModelAndView view) {
		view.setViewName("appendOrder");
		view.addObject("o_id", o_id);
		view.addObject("t_id", t_id);
		return view;
	}
	
	/**
	 * Mapping used for menu autocomplete based on search input
	 * @param name
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/filterMenu", method = RequestMethod.GET)
	public @ResponseBody List<Menu> getFilteredMenu(
			@RequestParam(value = "name", required = false) String name,
			HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		if (name == null || name.isEmpty()) {
			return merchantDao.getMenuItems(user.getUserId(), "");
		} else {
			return merchantDao.getMenuItems(user.getUserId(), name);
		}
	}
	
	@RequestMapping(value = "/manageCategory", method = RequestMethod.GET)
	public ModelAndView getCategoryManager(
			@RequestParam(value = "levelno", defaultValue = "1", required = false) int levelnumber,
			@RequestParam(value = "levelcode", defaultValue = "", required = false) String levelcode,
			@RequestParam(value = "leveldata", defaultValue = "", required = false) String leveldata,
			ModelAndView view, HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		List<Category> listCategories = genericDao.getCategories(user.getUserId(), levelnumber,levelcode);
		String nextSortOrder = genericDao.getNextSortOrder(user.getUserId(),levelnumber, levelcode);
		view.setViewName("manageCategory");
		if (levelnumber == 2) {
			view.setViewName("subCategory");
			view.addObject("leveldata", leveldata);
		}
		view.addObject("listCategories", listCategories);
		view.addObject("nextSortOrder", nextSortOrder);
		return view;
	}
	
	/**
	 * This mapping will save menu object in database
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public @ResponseBody Category addCategory(@RequestBody Category category,HttpSession session) {
		User user = (User) session.getAttribute(SESSION_USER);
		category.setMerchant_id(user.getUserId());
		genericDao.save(category);
		return category;
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
	public @ResponseBody Category deleteCategory(@RequestParam long id, HttpServletRequest request) {
		category.setId(id);
		genericDao.delete(category);
		return category;
	}
	
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
	public @ResponseBody Category updateCategory(@RequestBody Category category) {
		genericDao.update(category);
		return category;
	}
	
	/**
	 * Method is implemented to put common logic in one place for loading saved menu
	 * @param view
	 * @param merchant_id
	 * @return
	 */
	private ModelAndView prepareMenu(ModelAndView view,long merchant_id){
		List<Menu> menuList=genericDao.fetchById(menu, merchant_id, SEARCH_BY_MERCHANT_ID, getPastDate(30));
		Collections.reverse(menuList);
		List<Category> cateList=genericDao.getCategories(merchant_id,1,EMPTY);
		List<Category> subCateList=genericDao.getCategories(merchant_id,2,EMPTY);
		ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
		for(Menu menu:menuList){
			for(Category category:cateList){
				if(category.getId()==Long.parseLong(menu.getCategory())){
					menu.setCategoryObj(category);
					break;
				}else{
					continue;
				}
			}	
		}
		try {
			view.addObject("subCatList", mapper.writeValueAsString(subCateList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("menuList", menuList);
		view.addObject("cateList", cateList);
		return view;
	}
	
	@SuppressWarnings("static-access")
	private DateSearch getPastDate(int pastDays){
		calender = Calendar.getInstance();
		calender.add(calender.DATE, - pastDays);
		DateSearch dateSearch = new DateSearch(dateFormat.format(calender.getTime()), dateFormat.format(new Date()));
		return dateSearch;
	}
}
