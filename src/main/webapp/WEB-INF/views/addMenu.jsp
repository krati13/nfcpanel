<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id='subJSON' style="display: none">${subCatList }</div>
<div style="margin-top: 30px"></div>
<div class='dashContainer'>
	<div class='dashWrap'>
		<div style='display: none' id='menuadding'>
			<div class='broderGrid'>
				<div class='downBtnWrap'>
					<input style='padding: 3px; display: inline-block' type="button"
						class="btn btn-danger animateBtn"
						onclick='hideElem("menuadding");showElem("menuaddbtn");'
						value="X" title='Close'/>
				</div>
				<div class='gridTitle'>Add new item</div>

				<form method="post" id="menuform">
					<table class="table table-striped table-hover table-condensed">
						<thead>
							<tr>
								<th style='width: 200px;'><spring:message code="label.name" /></th>
								<th style='width: 100px'><spring:message code="label.price" /></th>
								<th><spring:message code="label.desc" /></th>
								<th <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if> style='width: 80px'><spring:message code="label.qty" /></th>
								<th <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if> style='width: 120px'><spring:message code="label.preptime" /></th>
								<th style='width: 100px;'><spring:message code="label.discount" /></th>
								<th style='width: 100px;'><spring:message code="label.category" /></th>
								<th style='width: 100px;'>Sub-Category</th>
								<th>Add</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input required='true' jname='Name' type='text'
									class='menuInput' name='name' placeholder='Enter name' /></td>
								<td><input required='true' jname='Price' dataType='number'
									type='number' class='menuInput' min="0" name='amount'
									placeholder='Enter price' /></td>
								<td><textarea class='menuArea' name='description'
										placeholder='Enter desc'></textarea></td>
								<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>><input style='width: 100%' name='quantity'
									dataType='number' type="number" class="menuInput" value="1"
									min="1"></td>
								<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>><input style='width: 100%' name='prep_time'
									dataType='number' type="number" class="menuInput" value="1"
									min="1"></td>
								<td><input style='width: 100%' name='discount'
									dataType='number' type="number" class="menuInput" value="0"
									min="0"></td>
								<td><select onchange='appendOptions("subCategory",this.value);' required='true' jname='Category'
									class='menuSelect' name='category'>
										<option value=''>Select</option>
										<c:forEach items="${cateList}" var="category">
											<option value='${category.id}|${category.sortorder}'>${category.fullName}</option>
										</c:forEach>
								</select></td>
								<td><select jname='Sub-Category'
									class='menuSelect' id='subCategory' name='subCategory'>
										<option value=''>Select Sub-Category</option>
								</select></td>
								<td><input style='width: 90%; padding: 3px' type="button"
									class="btn btn-success btn-block animateBtn"
									attr-form='menuform'
									onclick='j_loader_POST("/merchant/addMenu","json","mainContainer","menuform")'
									value="Add" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div style="margin-top: 20px"></div>
	<div class='dashWrap'>
		<div class='broderGrid'>
			<div class='downBtnWrap'>
				<input id='menuaddbtn' style='padding: 3px' type="button"
					class="btn btn-success btn-block animateBtn"
					onclick='showElem("menuadding");hideElem("menuaddbtn");'
					value="Add item" />
			</div>
			<div class='gridTitle'>
				<c:choose>
					<c:when test="${empty menuList}">
						<script>showElem("menuadding");</script>
						<span style='color: #ddd'>There are no menu items</span>
					</c:when>
					<c:otherwise>
						Menu List
					</c:otherwise>
				</c:choose>
			</div>
			<div id='menudisplay'>
				<c:if test="${not empty menuList}">
					<table id='addMenuList'
						class="table table-striped table-hover table-condensed">
						<thead>
							<tr>
								<th><spring:message code="label.name" /></th>
								<th style='width: 100px'><spring:message code="label.price" /></th>
								<th><spring:message code="label.desc" /></th>
								<th <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if> style='width: 80px'><spring:message code="label.qty" /></th>
								<th <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if> style='width: 120px'><spring:message
										code="label.preptime" /></th>
								<th style='width: 100px;'><spring:message code="label.discount" /></th>
								<th><spring:message code="label.category" /></th>
								<th style='width: 100px;'>Update</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${menuList}" var="menu">
								<tr id='menu_${menu.id}'>
									<td>${menu.name}</td>
									<td>${menu.amount}</td>
									<td>${menu.description}</td>
									<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>>${menu.quantity}</td>
									<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>>${menu.prep_time}</td>
									<td>${menu.discount}</td>
									<td>${menu.categoryObj.fullName}</td>
									<td><input
										style='width: 44%; padding: 3px; display: inline-block'
										type="button" class="btn btn-warning animateBtn menuOpt"
										onclick='menuEdit(${menu.id});' value="Edit" /> <input
										style='width: 44%; padding: 3px; display: inline-block'
										type="button" class="btn btn-danger animateBtn menuOpt"
										onclick='menuDelete(${menu.id},"${menu.name}")' value="Del" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script>enableDataTable('addMenuList');</script>