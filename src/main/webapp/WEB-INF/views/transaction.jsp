<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class='dashContainer'>
	<div style='display: inline-block; max-width: 800px;'>
		<div class='dashWrap'>
			<div class='broderGrid'>
				<div class='gridTitle'>Search</div>

				<div id='calendarSearchBox'>
					<div style='display: inline-block; width: 300px;'>
						<div class='input-group date' id='datetimepicker1'>
							<input required jname='Start Date' placeholder='Start date' id='from' type='text' class="form-control datepicker" /> 
							<span class="input-group-addon"> 
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>

					<div style='display: inline-block; width: 300px;'>
						<div class='input-group date' id='datetimepicker2'>
							<input required jname='End Date' placeholder="End date" id='to' type='text' class="form-control datepicker" /> 
							<span class="input-group-addon"> 
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div style=' display: inline-block;position: absolute; margin-top: 5px; margin-left: 5px'>
						<input style='padding: 3px;' type="button" class="btn btn-success animateBtn" onclick='transactionSearch()' value="Search" />
					</div>
					<div style="display: inline-block;width:60px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: 10px;"></div>
	<div class='dashWrap' id="transactionTable">
		<div class='broderGrid'>
			<div class='downBtnWrap'>
				<input id='menuaddbtn' style='padding: 3px' type="button" class="btn btn-warning btn-block animateBtn" onclick='transactionDownload()' value="Download Report" />
			</div>
			<div class='gridTitle'>Recent Transaction</div>
			<table id='transactionTableData' class="table table-striped table-bordered">
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
							<td><fmt:formatDate type="date" value="${txn.created_on}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script>
$(function() {
    $( "#from" ).datepicker({
      //defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function() {
        $( "#from" ).datepicker("option", "dateFormat", "yy-mm-dd");
      }
    });
    $( "#to" ).datepicker({
      //defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      onClose: function() {
        $( "#to" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
      }
    });
    
    enableDataTable('transactionTableData');
    //enablePagination('transactionTableData');
 });
</script>