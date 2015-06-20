<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class='dashWrap'  id="transactionTable">
	<div class='broderGrid'>
		<div style='margin-top: 5px;position: absolute;'>
				<input id='menuaddbtn' style='padding: 3px' type="button" class="btn btn-warning btn-block animateBtn" onclick='transactionDownload()' value="Download Report" />
			</div>
		<div class='gridTitle'>Recent Transaction</div>
		<table id='transactionTableData' class="table table-striped table-hover table-condensed">
			<thead>
					<tr>
						<th style='width:60px'>S.No</th>
						<th>Order ID</th>
						<th>Tax Amount</th>
						<th>Order Amount</th>
						<th>Shipment Amount</th>
						<th>Total Amount</th>
						<th>Bank Txn ID</th>
						<th>Status</th>
						<th>Created On</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${txns}" var="txn" varStatus="loop">
						<tr>
							<td attr-id='${txn.txn_id}'>${loop.index + 1}</td>
							<td>${txn.order_id}</td>
							<td>${txn.tax_amt}</td>
							<td>${txn.order_amt}</td>
							<td>${txn.shipping_amt}</td>
							<td>${txn.total_amt}</td>
							<td>${txn.bank_txn_id}</td>
							<td>${txn.status}</td>
							<td>${txn.created_on}</td>
						</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>
	<hr />
</div>
<script>enableDataTable('transactionTableData');</script>