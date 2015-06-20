<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../sweetlib/sweet-alert.css" rel="stylesheet">
<link href="../jquery-ui/jquery-ui.min.css" rel="stylesheet">
<link href="../css/jquery.dataTables_themeroller.css" rel="stylesheet">
<link href="../css/dataTables.bootstrap.css" rel="stylesheet">
<link href="../css/switchButton.css" rel="stylesheet">
<link href="../css/uploadfile.css" rel="stylesheet">
<link href="../css/selectize.legacy.css" rel="stylesheet">

<script src="../js/jquery.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../js/home.js"></script>
<script src="../js/notify.js"></script>
<script src="../sweetlib/sweet-alert.min.js"></script>
<script src="../jquery-ui/jquery-ui.min.js"></script>
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/dataTables.bootstrap.js"></script>
<script src="../js/switchButton.js"></script>
<script src="../js/jquery.uploadfile.min.js"></script>
<script src="../js/selectize.min.js"></script>

<nav class="navbar navbar-default navbar-fixed-top">
	<div>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"
				onclick="loadHome();setTitle('${user.role.roleName} Panel');">${user.role.roleName}
				Panel</a>
		</div>
		<div id="navbar" class="collpase navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:forEach var="menu" items="${user.menu}">
					<c:choose>
						<c:when test="${empty menu.subMenu}">
							<li><a class='ahover' href="#"
								onclick="UrlBuilder(this);setTitle('${menu.name}');"
								attr-url='${menu.uri}'>${menu.name}</a></li>
						</c:when>
						<c:otherwise>
							<li class="dropdown"><a href='#'
								class="dropdown-toggle ahover" data-toggle="dropdown"
								role="button" aria-expanded="false"> ${menu.name} <b
									class="caret"></b>
							</a>
								<ul class="dropdown-menu" role="menu">
									<c:forEach var="subMenu" items="${menu.subMenu}">
										<li><a class='ahover' href="#"
											onclick="UrlBuilder(this);setTitle('${subMenu.name}');"
											attr-url='${subMenu.uri}'>${subMenu.name}</a></li>
									</c:forEach>
								</ul></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user.role.id==1}">
						<li><a href="#" class='ahover'><span> Hello, <b>${user.name}</b></span></a></li>
					</c:when>
					<c:otherwise>
					<c:if test="${user.role.id==2}">
						<li id='txnCountNotify_li'><a
							onclick='showPopUp("txnNotifyDetails","txnCountNotify_anchor");'
							href='#' id='txnCountNotify_anchor' class='ahover_notify'> <span>Transaction</span>
								<span class='counterWrap' id='txnCountNotify'></span>
						</a>
							<div class='notificationDetailsWrap' id='txnNotifyDetails'>
								<div class='rows_head'>
									<span class='header_notify'>new transactions</span> <span
										class='header_notify_old'>older transaction</span>
								</div>
								<div style='margin-top: 60px;' id='txnNotifyRowContainer'></div>
							</div></li>
						<li id='orderCountNotify_li'><a
							onclick='showPopUp("orderNotifyDetails","orderCountNotify_anchor");'
							href='#' id='orderCountNotify_anchor' class='ahover_notify'>
								<span>Orders</span> <span class='counterWrap'
								id='orderCountNotify'></span>
						</a>
							<div class='notificationDetailsWrap' id='orderNotifyDetails'>
								<div class='rows_head'>
									<span class='header_notify'>new orders</span> <span
										class='header_notify_old'>older orders</span>
								</div>
								<div style='margin-top: 60px;' id='orderNotifyRowContainer'>
								</div>
							</div></li>
							</c:if>
						<li class="dropdown"><a href='#'
							class="dropdown-toggle ahover" data-toggle="dropdown"
							role="button" aria-expanded="false"> <b>${user.name}</b> <b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a class='ahover' href="#"
									onclick="openPopUp(this);setTitle('View Profile');"
									attr-url='/merchant/profile?edit=0'>View Profile</a></li>
								<li><a class='ahover' href="#"
									onclick="openPopUp(this);setTitle('Edit Profile');"
									attr-url='/merchant/profile?edit=1'>Edit Profile</a></li>
								<li><a class='ahover' href="#"
									onclick="openPopUp(this);setTitle('Chnage Password');"
									attr-url='/merchant/profile?edit=2'>Chnage Password</a></li>
							</ul></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</nav>

<div>
	<input type="hidden" id="role" value="${user.role.roleName}" /> 
	<input type="hidden" id="roleId" value="${user.role.id}" /> 
	<input type="hidden" id="merchant_id_global" value="${user.userId}" /> 
	<input type="hidden" id="notifyURL" value="<spring:message code='url.notify' />/${user.userId}" />
</div>
<div style="height: 55px;"></div>
<div>
	<script>
		getNewOrders();
	</script>
	<div id='orders_notify'></div>
	<div id='transactions_notify'></div>
</div>
<div class='modal_lay_notify' id='modal_lay_notify_1'
	onclick='hidePopup();'></div>
<div id='modal_wrap' class="modal_lay"></div>
<div id='modal_profile' class="modal_popup"></div>