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
</style>
<div>
	<div class='centerContainer'>
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong> Change your password </strong>
			</div>
			<div style="text-align: center;" class="panel-body">
			<span style='color:darkblue'>${msg}</span>
				<form method="post" id="passwordForm">
					<br />
					<c:choose>
						<c:when test="${sessionScope.user.role.id!=1 }">
					<div>
						<input required="required" type="password"  name='oldpassword'
							class="form-control forminput" id='op' placeholder="Old Password" pattern=".{5,30}" />
					</div>
					</c:when>
					<c:otherwise>
					<div><h5>Current Password:</h5><div>${user.password }</div></div>
					</c:otherwise>
					</c:choose>
					<div>
						<input required="required" type="password"  name='password'
							class="form-control forminput" id='np' placeholder="New Password" pattern=".{5,30}" />
					</div>
					<div>
						<input required="required" type="password"  name='cpassword'
							class="form-control forminput" id='npc' placeholder="Confirm Password" pattern=".{5,30}" />
					</div>

					<div>
					<input type='hidden' name='id' value='${merchant.id}'/>
					<input style="display:inline-block" type="button" class="btn btn-danger"
						value='Close'
						onclick='hidePopUp()' />
						<c:choose>
						<c:when test="${sessionScope.user.role.id==1 }">
						<input style="display:inline-block" type="button" class="btn btn-success"
						value='Change Password'
						onclick='changepw("/admin/changePW?mid=${merchant.id}")' />
						</c:when>
						<c:otherwise>
						<input style="display:inline-block" type="button" class="btn btn-success"
						value='Change Password'
						onclick='changepw("/merchant/changePW?mid=0")' />
						</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>