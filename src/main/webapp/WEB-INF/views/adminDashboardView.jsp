<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class='dashContainer'>
	<div style="background:transparent" class='dashWrap'>
		<div class='broderGrid'>
			<div class='gridTitle'>Recent merchants</div>
			<table class="table table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Business Name</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>Landline</th>
						<th>Location</th>
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
							<td>${merchant.city},${merchant.state}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>