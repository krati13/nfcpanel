<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
select {
	display: inline-block !important;
}

.forminput {
	width: 48% !important;
	display: inline-block !important;
	margin: 10px 0px 10px 0px;
}

select {
	width: 150px !important;
	padding: 5px;
	border: 1px solid #ddd;
	border-radius: 4px;
}
</style>
<div>
	<div class='centerContainer'>
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong> Edit Your Details</strong>
			</div>
			<div style="text-align: center;" class="panel-body">
				<form method="post" id="merchantForm">
					<br />
					<div>
						<input required="required" type="text" value="${merchant.firstName}" name='firstName'
							class="form-control forminput" placeholder="First Name" pattern=".{5,30}" />
						<input required="required" type="text"
							name='lastName' class="form-control forminput" value="${merchant.lastName}" placeholder="Last Name"
							pattern=".{5,30}" />
					</div>

					<div>
						<input  required="required" type="text" readonly="readonly" value="${merchant.businessName}"
							name='businessName' class="form-control forminput"
							placeholder="Business Name" pattern=".{5,30}" /> <input
							 required="required" type="email" value="${merchant.email}" name='email'
							class="form-control forminput" placeholder="Email" pattern=".{5,30}" />
					</div>

					<div>
						<input dataType='number'  required="required"
							type="text" name='mobile' value="${merchant.mobile}" class="form-control forminput"
							placeholder="Mobile" pattern=".{5,30}" /> <input
							 required="required" type="text" value="${merchant.landline}"
							name='landline' class="form-control forminput" dataType='number'
							placeholder="Landline" pattern=".{5,30}" />
					</div>

					<div>
						<div class='forminput'>
							<h4>Want to change number of tables in restaurant?</h4>
							<input type='number' dataType='number' required="required"  value="${merchant.tableCount}"
								placeholder='Number' name='tableCount'
								style='text-align: center'
								onkeyup='document.getElementById("countReflect2").value=this.value'
								id='countReflect' style='width:50px;' />
						</div>

						<div class='forminput'>
							<div class='forminput'>
							<h4>Payment gateway</h4>
							<input type='hidden' name='paymentOption' value='${merchant.paymentOption}'/>
							<select disabled required="required" class='menuSelect' placeholder='Payment Option'>
								<option value='0'>Payment Gateway</option>
								<option <c:if test="${merchant.paymentOption == 1}">selected</c:if> value='1'>Enable</option>
								<option <c:if test="${merchant.paymentOption == 2}">selected</c:if> value='2'>Disable</option>
							</select>
						</div>
						<div class='forminput'>
							<h4>Merchant type</h4>
							<input type='hidden' name='merchantType' value='${merchant.merchantType}'/>
							<select disabled required="required" class='menuSelect' placeholder='Merchant Type'>
								<option value=''>Payment Gateway</option>
								<option <c:if test="${merchant.merchantType == 2}">selected</c:if> value='2'>Active</option>
								<option <c:if test="${merchant.merchantType == 3}">selected</c:if> value='3'>Passive</option>
							</select>
						</div>
						</div>
					</div>

					<div>
						<input  required="required" type="text"
							id="from" name='validFrom' readonly class="form-control forminput"
							placeholder="Valid From"  value="${merchant.validFrom}"/> <input 
							required="required" type="text" id="to" name='validTo'
							class="form-control forminput" placeholder="Valid To" readonly value="${merchant.validTo}"/>
					</div>

					<div>
						<input  required="required" type="text"
							name='city' class="form-control forminput"  value="${merchant.city}" placeholder="City"
							pattern=".{5,30}" /> <input 
							required="required" type="text" name='state' value="${merchant.state}" class="form-control forminput"
							placeholder="State" pattern=".{5,30}" />
					</div>

					<div>
						<input  required="required" type="number" value="${merchant.pincode}"
							name='pincode' dataType='number' class="form-control forminput"
							placeholder="PIN" pattern=".{5,30}" /> <input  value="${merchant.website}"
							required="required" type="url" name='website'
							class="form-control forminput" placeholder="Website" pattern=".{5,30}" />
					</div>
					<div>
						<input  required="required" type="text" value="${merchant.fbLikeUrl}"
							name='fbLikeUrl' class="form-control forminput"
							placeholder="Facebook Page" pattern=".{5,30}" />
					</div>
					<div>
					<input type='hidden' name='id' value='${merchant.id}'/>
					<input style="display:inline-block" type="button" class="btn btn-danger"
						value='Close'
						onclick='hidePopUp()' />
						<c:choose>
						<c:when test="${sessionScope.user.role.id==1 }">
					<input style="display:inline-block" type="button" class="btn btn-success"
						value='Update'
						onclick='updateMerchant("admin")' />
						</c:when>
						<c:otherwise>
						<input style="display:inline-block" type="button" class="btn btn-success"
						value='Update'
						onclick='updateMerchant("merchant")' />
						</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>