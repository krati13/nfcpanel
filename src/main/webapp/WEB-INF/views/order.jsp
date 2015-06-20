<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class='dashContainer'>
	<div style='display: inline-block; width: 80%;'>
		<div class='dashWrap'>
			<div class='broderGrid'>
				<div class='gridTitle'>Search</div>

				<div style='padding: 10px;' id='calendarSearchBox'>
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
					<div style='display: inline-block; position: absolute; margin-top: 5px; margin-left: 5px'>
						<input style='padding: 3px;' type="button" class="btn btn-success animateBtn" onclick='orderSearch()' value="Search" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: 10px;"></div>
	<div class='dashWrap' id="orderTable">
		<div class='broderGrid'>
			<div class='downBtnWrap'>
				<input id='menuaddbtn' style='padding: 3px' type="button" class="btn btn-warning btn-block animateBtn" onclick='orderDownload()' value="Download Report" />
			</div>
			<div class='gridTitle'>Recent Orders</div>
			<table id='transactionTableData' class="table table-striped table-bordered">
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
							<td>${fn:substring(order.order_id, 0, 10)}***********</td>
							<td>${order.table_id}</td>
							<td>${order.menu_id}</td>
							<td>${order.quantity}</td>
							<td>${order.amount}</td>
							<td>${order.status}</td>
							<td><fmt:formatDate type="date" value="${order.created_on}" /></td>
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