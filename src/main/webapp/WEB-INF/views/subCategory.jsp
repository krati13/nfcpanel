<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class='dashWrap'>
		<div id='menuadding'>
			<div class='broderGrid'>
				<div class='gridTitle'>Add Sub-Categories</div>
				<div style='text-align:left'>
				<c:forEach items="${listCategories}" var="category">
					<div title="Click to Update" onclick="updateCat('${category.id}','${category.shortName}','${category.fullName}','${category.sortorder}','${category.merchant_id}','_sub');" class='catBlock'>
					${category.fullName}
					</div>
				</c:forEach>
				<hr/>
				<div id='addDiv_sub'>
				<form id='categoryAdd_sub'>
					<div class='catAddBlock'>
					<input type='hidden' name='sortorder' id='sortorder' value='${nextSortOrder}'/>
					<input type='hidden' name='levelnumber' id='levelnumber' value='2'/>
					<input style='width:240px;padding:3px;line-height:30px;border:none;' type='text' jname='Category Name' required="required" name='fullName' id='fullName' autofocus="autofocus" placeholder='Add new sub-category'/>
					<div class='catAddBtn' onclick='j_loader_POST("/merchant/addCategory","JSON","subcategories","categoryAdd_sub")'>Add</div>
					</div>
				</form>
				</div>
				<div id='updateDiv_sub' style="display: none">
				<form id='categoryUpdate_sub'>
					<div class='catAddBlock'>
					<input type='hidden' name='sortorder' id='u_sortorder_sub'/>
					<input type='hidden' name='shortName' id='u_shortName_sub'/>
					<input type='hidden' name='id' id='u_id_sub'/>
					<input type='hidden' name='merchant_id' id='u_merchant_id_sub'/>
					<input type='hidden' name='levelnumber' id='levelnumber' value='2'/>
					<input style='width:240px;padding:3px;line-height:30px;border:none;' type='text' jname='Category Name' required="required" name='fullName' id='u_fullName_sub' autofocus="autofocus" placeholder='Update sub-category'/>
					<div class='catUpdBtn' onclick='j_loader_PUT("/merchant/updateCategory","JSON","subcategories","categoryUpdate_sub")'>Update</div>
					</div>
					<div class='catCanBtn' onclick="canUpdate('_sub');">Cancel</div>
					
				</form>
				</div>
				</div>
			</div>
		</div>
	</div>
	<script>
$('.catBlock').tooltip({
      position: {
        my: "center bottom-20",
        at: "center top",
        using: function( position, feedback ) {
          $( this ).css( position );
          $( "<div>" )
            .addClass( "arrow" )
            .addClass( feedback.vertical )
            .addClass( feedback.horizontal )
            .appendTo( this );
        }
      }
    });
 </script>