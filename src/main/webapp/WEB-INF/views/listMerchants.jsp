<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div  class="container" style="text-align: left;margin-top:20px;" id='listOfMerchants'>
	<div style="text-align:center"><h2>List of merchants</h2>
	<c:if test="${not empty message}">
		<h4>
			<span style="color: darkblue;">${message}</span>
		</h4>
	</c:if>
	</div>
	<div>
			<table id='listMerchantTable' class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Business Name</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>Landline</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${merchants}" var="merchant">
						<tr>
							<td>${merchant.id}</td>
							<td>${merchant.firstName}</td>
							<td>${merchant.lastName}</td>
							<td>${merchant.businessName}</td>
							<td>${merchant.email}</td>
							<td>${merchant.mobile}</td>
							<td>${merchant.landline}</td>
							<td>
							<li class="dropdown"><a href='#'
							class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-expanded="false"> <b>Manage Profile</b> <b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a class='ahover' href="#"
									onclick="openPopUp(this);setTitle('View Profile');"
									attr-url='/admin/profile?edit=0&mid=${merchant.id}'>View Profile</a></li>
								<li><a class='ahover' href="#"
									onclick="openPopUp(this);setTitle('Edit Profile');"
									attr-url='/admin/profile?edit=1&mid=${merchant.id}'>Edit Profile</a></li>
								<li><a class='ahover' href="#"
									onclick="openPopUp(this);setTitle('Chnage Password');"
									attr-url='/admin/profile?edit=2&mid=${merchant.id}'>Chnage Password</a></li>
							</ul></li>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
<script type="text/javascript">
enableDataTable('listMerchantTable');
  </script>