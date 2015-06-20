<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="margin-top: 30px"></div>
<div class='dashContainer'>
	<div class='dashWrap'>
		<div id='menuadding'>
			<div class='broderGrid'>
				<div class='gridTitle'>Manage Categories</div>
				<div style='text-align:left'>
				<c:forEach items="${listCategories}" var="category">
					<div title="Click to Update / Add Sub-categories" onclick="updateCat('${category.id}','${category.shortName}','${category.fullName}','${category.sortorder}','${category.merchant_id}','');" class='catBlock'>
					${category.fullName}
					</div>
				</c:forEach>
				<hr/>
				<div id='addDiv'>
				<form id='categoryAdd'>
					<div class='catAddBlock'>
					<input type='hidden' name='sortorder' id='sortorder' value='${nextSortOrder}'/>
					<input type='hidden' name='levelnumber' value='1'/>
					<input style='width:240px;padding:3px;line-height:30px;border:none;' type='text' jname='Category Name' required="required" name='fullName' id='fullName' autofocus="autofocus" placeholder='Add new category'/>
					<div class='catAddBtn' onclick='j_loader_POST("/merchant/addCategory","JSON","mainContainer","categoryAdd")'>Add</div>
					</div>
				</form>
				</div>
				<div id='updateDiv' style="display: none">
				<form id='categoryUpdate'>
					<div class='catAddBlock'>
					<input type='hidden' name='levelnumber' id='u_levelnumber' value='1'/>
					<input type='hidden' name='sortorder' id='u_sortorder'/>
					<input type='hidden' name='shortName' id='u_shortName'/>
					<input type='hidden' name='id' id='u_id'/>
					<input type='hidden' name='merchant_id' id='u_merchant_id'/>
					<input style='width:240px;padding:3px;line-height:30px;border:none;' type='text' jname='Category Name' required="required" name='fullName' id='u_fullName' autofocus="autofocus" placeholder='Update category'/>
					<div class='catUpdBtn' onclick='j_loader_PUT("/merchant/updateCategory","JSON","mainContainer","categoryUpdate")'>Update</div>
					</div>
					<div class='catCanBtn' onclick="canUpdate('');">Cancel</div>
					
				</form>
				</div>
				</div>
			</div>
		</div>
	</div>
	<div style="height: 30px;"></div>
	<div id='subcategories'></div>
</div>
<style>
  .ui-tooltip, .arrow:after {
    background: #555;
    border: 1px solid #333;
  }
  .ui-tooltip {
    padding: 5px 10px;
    color: white;
    border-radius: 5px;
    font: 12px "Helvetica Neue", Sans-Serif;
    box-shadow: 0 0 7px black;
  }
  .arrow {
    width: 70px;
    height: 16px;
    overflow: hidden;
    position: absolute;
    left: 50%;
    margin-left: -35px;
    bottom: -16px;
  }
  .arrow.top {
    top: -16px;
    bottom: auto;
  }
  .arrow.left {
    left: 20%;
  }
  .arrow:after {
    content: "";
    position: absolute;
    left: 20px;
    top: -20px;
    width: 25px;
    height: 25px;
    box-shadow: 6px 5px 9px -9px black;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
  }
  .arrow.top:after {
    bottom: -20px;
    top: auto;
  }
  </style>
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