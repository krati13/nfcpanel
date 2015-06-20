<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class='dashWrap' id="orderTable">
		<div class='broderGrid'>
			<div style='float: right; margin-top: 5px; margin-right: 5px;'>
				<input id='menuaddbtn' style='padding: 3px' type="button" class="btn btn-warning btn-block animateBtn" onclick='transactionDownload()' value="Download Report" />
			</div>
			<div class='gridTitle'>Recent Orders</div>
			<table id='transactionTableData' class="table table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th>ID</th>
						<th>Order ID</th>
						<th>Table ID</th>
						<th>Menu ID</th>
						<th>Quantity</th>
						<th>Amount</th>
						<th>Status</th>
						<th>Created Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orders}" var="order">
						<tr>
							<td>${order.id}</td>
							<td>${order.order_id}</td>
							<td>${order.table_id}</td>
							<td>${order.menu_id}</td>
							<td>${order.quantity}</td>
							<td>${order.amount}</td>
							<td>${order.status}</td>
							<td>${order.created_on}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr />
	</div>