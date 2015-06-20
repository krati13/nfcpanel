<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div style="margin-top: 30px"></div>
<div class='dashContainer'>
	<div class='dashWrap'>
		<div id='menudisplay'>
			<div class='broderGrid'>
				<div class='downBtnWrap'>
					<input id='menuaddbtn' style='padding: 3px' type="button"
						class="btn btn-success btn-block animateBtn"
						onclick='showTables("");hideElem("menuaddbtn");'
						value="Place an order" />
				</div>
				<div class='gridTitle'>
					<c:choose>
						<c:when test="${empty orders}">
							<span style='color: #ccc'>Currently, there are no active orders.</span>
						</c:when>
						<c:otherwise>
								Active Order List
							</c:otherwise>
					</c:choose>
				</div>
				<div style='text-align: left' id='collapsable'>
					<c:if test="${not empty orders}">
						<c:forEach items="${orders}" var="entry" varStatus="mapLoop">
							<c:forEach items="${entry.value}" var="item" varStatus="loop">
								<c:if test="${loop.index==0}">
									<div id='header_${mapLoop.index}' class='collapseHeader'>
										<span>Order ID: ${item.order_id}</span> <span
											style='float: right'><b>Table No. ${item.table_id}</b></span>
									</div>
									<div id='headerDetail_${mapLoop.index}'>
										<div style='padding-bottom: 10px'>
											<span><b>Order status:</b> ${item.status}</span> <span
												style='float: right; margin-left: 10px;'><b>Order
													time:</b> <fmt:formatDate type="time"
													value="${item.created_on}" /></span> <span style='float: right;'><b>Order
													date:</b> <fmt:formatDate type="date"
													value="${item.created_on}" /></span>
										</div>
										<table class="table table-striped table-bordered">
											<thead>
												<tr>
													<th>Menu Name</th>
													<th>Quantity</th>
													<th>Amount</th>
												</tr>
											</thead>
											<tbody id='${item.table_id}_tbody'>
												</c:if>
												<tr id='${item.table_id}_${item.menu_id}'>
													<td>${item.menu_name}</td>
													<td>${item.quantity}</td>
													<td>${item.amount}</td>
												</tr>
												<c:if test="${loop.last}">
											</tbody>
										</table>
										<div id='appendOrder_${mapLoop.index}'></div>
										<div id='appendOrderBtnWrap_${mapLoop.index}' style='text-align:center'>
											<div class='activeBottomBtns'>
												<input type="button"
													class="btn btn-danger btn-block animateBtn"
													value="Cancel this order" onclick="manageOrder(1,'${item.order_id}','${item.table_id}',${mapLoop.index});"/>
											</div>
											<div class='activeBottomBtns'>
												<input type="button"
													class="btn btn-warning btn-block animateBtn"
													value="Print Bill" onclick="manageOrder(2,'${item.order_id}','${item.table_id}',${mapLoop.index});"/>
											</div>

											<div class='activeBottomBtns'>
												<input type="button"
													class="btn btn-success btn-block animateBtn"
													value="Append more items" onclick="manageOrder(3,'${item.order_id}','${item.table_id}',${mapLoop.index});"/>
											</div>
										</div>
										<div id='appendOrderActionWrap_${mapLoop.index}' style='text-align:center;display:none;'>
											<div class='activeBottomBtns'>
												<input type="button"
													class="btn btn-danger btn-block animateBtn"
													value="Cancel" onclick="manageOrder(4,'${item.order_id}','${item.table_id}',${mapLoop.index});"/>
											</div>

											<div class='activeBottomBtns'>
												<input type="button"
													class="btn btn-success btn-block animateBtn"
													value="Add to List" onclick="manageOrder(5,'${item.order_id}','${item.table_id}',${mapLoop.index});"/>
											</div>
										</div>
										<div id='downAppend_${item.table_id}'></div>
									</div>
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var icons = {
		header : "ui-icon-circle-arrow-e",
		activeHeader : "ui-icon-circle-arrow-s"
	};
	enableDataTable('activeOrderList');
	$("#collapsable").accordion({
		collapsible : true,
		heightStyle : "content",
		icons : icons
	});
	$('#header_0').click();
</script>
<script>
	var menufilter = appendContext('/merchant/filterMenu');
	var dataAppend = [];
	$.getJSON(menufilter, function(menuList) {
		dataAppend=menuList;
	});
	
	function showTables() {
		
	}
</script>