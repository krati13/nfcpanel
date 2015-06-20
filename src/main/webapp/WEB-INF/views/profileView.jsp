<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
select {
	display: inline-block !important;
}

.forminput {
	width: 48%;
	display: inline-block !important;
	margin: 10px 0px 10px 0px;
}

select {
	width: 150px;
	padding: 5px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.leftTitle {
	font-size: 12px;
	text-align: right;
	color:#777 !important;
	margin-top:5px;
}

.rightContent {
	font-size: 18px;
	text-align: left;
}
</style>
<div>
	<div class='centerContainer'>
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>Your Details</strong>
			</div>
			<div style="text-align: center;" class="panel-body">
				<table class="table table-striped table-hover table-condensed">
					<tbody>
						<tr>
							<td style='width: 50%'>
								<div class='leftTitle'>Name:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.firstName}</span> <span>${merchant.lastName}</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class='leftTitle'>Business name:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.businessName}</span>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<div class='leftTitle'>Email:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.email}</span>
								</div>
							</td>
						</tr>


						<tr>
							<td>
								<div class='leftTitle'>Mobile:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.mobile}</span> <span>${merchant.landline}</span>
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								<div class='leftTitle'>Table Number:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.tableCount}</span>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<div class='leftTitle'>Payment option:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>
									<c:if test="${merchant.paymentOption == 1}">Enable</c:if>
									<c:if test="${merchant.paymentOption == 2}">Disable</c:if>
									</span>

								</div>
							</td>
						</tr>


						<tr>
							<td>
								<div class='leftTitle'>Validity:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.validFrom}</span> to <span>${merchant.validTo}</span>
								</div>
							</td>
						</tr>


						<tr>
							<td>
								<div class='leftTitle'>Address:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.city} </span>, <span>${merchant.state}</span>, <span>${merchant.pincode}</span>
								</div>
							</td>
						</tr>
						
						<tr>
							<td>
								<div class='leftTitle'>Facebook Page:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.fbLikeUrl} </span>
								</div>
							</td>
						</tr>


						<tr>
							<td>
								<div class='leftTitle'>Website:</div>
							</td>
							<td>
								<div class='rightContent'>
									<span>${merchant.website}</span>
								</div>
							</td>
						</tr>
				</table>
				<div>
					<input type='hidden' name='id' value='${merchant.id}' /> <input
						style="display: inline-block" type="button" class="btn btn-danger"
						value='Close' onclick='hidePopUp()' /> <input
						style="display: inline-block" type="button"
						class="btn btn-warning" value='Edit'  onclick="openPopUp(this);" attr-url='/merchant/profile?edit=1' />

				</div>

			</div>
		</div>
	</div>
</div>