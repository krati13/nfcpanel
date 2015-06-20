<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="<%=request.getContextPath()%>" />

<c:choose>
	<c:when test="${empty editMode}">
		<tr id='menu_${menu.id}'>
			<td>${menu.name}</td>
			<td>${menu.amount}</td>
			<td>${menu.description}</td>
			<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>>${menu.quantity}</td>
			<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>>${menu.prep_time}</td>
			<td>${menu.discount}</td>
			<td>${menu.categoryObj.fullName}</td>
			<td><input style='width: 44%; padding: 3px; display: inline-block'
				type="button" class="btn btn-warning animateBtn"
				onclick='menuEdit(${menu.id});' value="Edit" /> <input
				style='width: 44%; padding: 3px; display: inline-block'
				type="button" class="btn btn-danger animateBtn"
				onclick='menuDelete(${menu.id})' value="Del" /></td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr id='menu_${menu.id}'>
			<td><input required='true' jname='Name' type='text'
				value='${menu.name}' class='menuInput' name='name'
				placeholder='Enter name' /></td>
			<td style='width: 50px'><input style='width: 50px'
				required='true' jname='Price' type='text' value='${menu.amount}'
				class='menuInput' name='amount' placeholder='Enter price'  min="0"/></td>
			<td><textarea class='menuArea' name='description'
					placeholder='Enter desc'>${menu.description}</textarea></td>
			<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>><input style='width: 100%' name='quantity' type="number"
				value='${menu.quantity}' class="menuInput" min="1"></td>
			<td <c:if test="${sessionScope.user.role.id==3}">style="display:none;"</c:if>><input style='width: 100%' name='prep_time' type="number"
				value='${menu.prep_time}' class="menuInput" min="1"></td>
			<td><input style='width: 100%' name='discount' type="number"
				value='${menu.discount}' class="menuInput" min="0"></td>
			<td style='width: 200px'><select onchange='appendOptions_sub("subCategory_${menu.id }",this.value,"${menu.subCategory }");' style='display: inline-block'
				required='true' jname='Category' class='menuSelect' name='category'>
					<option value=''>Select</option>
					<c:forEach items="${cateList}" var="category">
						<option
<%-- 							<c:if test="${category.id == menu.categoryObj.id}">selected</c:if> --%>
							value='${category.id}|${category.sortorder}'>${category.fullName}</option>
					</c:forEach>
			</select> <select title='You can close this menu temporily' class='menuSelect'
				name='closed' required="true" placeholder='Order status'>
					<option value=''>Menu status</option>
					<option <c:if test="${menu.closed == 0}">selected</c:if> value='0'>Available</option>
					<option <c:if test="${menu.closed == 1}">selected</c:if> value='1'>Closed</option>
			</select>
				<select jname='Sub-Category'
									class='menuSelect' id='subCategory_${menu.id }' name='subCategory'>
										<option value=''>Select Sub-Category</option>
								</select>
								</td>
			<td><input
				style='width: 44%; padding: 3px; display: inline-block'
				type="button" class="btn btn-primary animateBtn"
				onclick='menuUpdate(${menu.id});' value="Save" /> <input
				style='width: 44%; padding: 3px; display: inline-block'
				type="button" class="btn btn-info animateBtn menuEditCancel"
				onclick='menuCancel(${menu.id})' value="Cancel" /> <input
				type='hidden' name='id' value='${menu.id}' />
				<input
				type='hidden' name='url' id='iURL' value='${menu.URL}' /></td>
		</tr>
		<tr id='menu_${menu.id}_img'>
			<td colspan="8">
			<div style='min-height:60px'>
			<div style="display: inline-block;"
					id="filePreview"><img src="${menu.URL}" class="imgPreview"/></div>
					
				<div style="display: inline-block;float:right;" id='getMyImageMenu'>
					<div id="mulitplefileuploader">Upload Image</div>
					<div id="status"></div>
				</div>
				</div>
			</td>
		</tr>
		<script>
$(document).ready(function()
{
var settings = {
    url: "${context}/merchant/uploadFile?name=${menu.id}",
    dragDrop:true,
    fileName: "file",
    allowedTypes:"jpg,png,gif,jpeg",	
    returnType:"html",
	 onSuccess:function(files,data,xhr)
    {
		 $('#getMyImageMenu').remove();
       if(data=='1'){
    	   $('#filePreview').html('<span class="error">File upload failed</span>');
       }else if(data=='2'){
    	   $('#filePreview').html('<span class="error">File is empty</span>');
       }else{
    	   $('#iURL').val(data);
    	   setTimeout(function() {
    	   $('#filePreview').html('<img src="'+data+'" class="imgPreview"/>');
    	   }, 1000);
       }
    },
    showDelete:false,
    deleteCallback: function(data,pd)
	{
    for(var i=0;i<data.length;i++)
    {
        $.post("delete.php",{op:"delete",name:data[i]},
        function(resp, textStatus, jqXHR)
        {
            //Show Message  
            $("#status").append("<div>File Deleted</div>");      
        });
     }      
    pd.statusbar.hide(); //You choice to hide/not.

}
}
var uploadObj = $("#mulitplefileuploader").uploadFile(settings);
});
</script>
	</c:otherwise>
</c:choose>
