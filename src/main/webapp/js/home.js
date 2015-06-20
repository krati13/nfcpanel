window.onload = function() {
	loadHome();
}

var bitCheck = 0;
var bitId = '';

function j_loader_DELETE(URL, DataType, hideID) {
	var req = $.ajax({
		url : URL,
		beforeSend : function(request) {
			request.setRequestHeader("SID", getSID());
		},
		type : "POST",
		dataType : DataType
	});

	req.done(function(msg) {
		var ids = hideID.split(",");
		for (var i = 0; i < ids.length; i++) {
			$('#' + ids[i]).remove();
		}
	});

	req.fail(function(jqXHR, textStatus) {
		sweetAlert("Oops...", "Something went wrong :( \n Try reloading page!",
				"error");
	});
}

function j_loader_GET(URL, DataType, DisplayID) {
	enableLoading(DisplayID);
	var req = $.ajax({
		url : URL,
		beforeSend : function(request) {
			request.setRequestHeader("SID", getSID());
		},
		type : "GET",
		dataType : DataType
	});

	req.done(function(msg) {
		// alert(msg);
		if (bitCheck == 9) {
			$("#" + DisplayID).replaceWith(msg);
			bitCheck = 0;
		} else {
			$("#" + DisplayID).html(msg);
		}
		if (bitCheck == 1) {
			$('#' + bitId).click();
			bitCheck = 0;
			bitId = '';
		}
		disableLoading(DisplayID);
	});

	req.fail(function(jqXHR, textStatus) {
		sweetAlert("Oops...", "Something went wrong :( \n Try reloading page!",
				"error");
		disableLoading(DisplayID);
	});
}

function j_loader_POST(URL, DataType, DisplayID, formID) {
	var flag = false;
	if (formID == 'merchantForm') {
		flag = validateForm(formID, 'placeholder');
	} else {
		flag = validateForm(formID, 'jname');
	}

	var dataJSON = getFormData(formID);
	if (bitCheck == 2) {
		dataJSON = getAnyData(formID);
		bitCheck = 0;
	}
	if (flag) {
		enableLoading(DisplayID);
		var req = $.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			url : appendContext(URL),
			data : JSON.stringify(dataJSON),
			beforeSend : function(request) {
				request.setRequestHeader("SID", getSID());
			},
			type : "POST",
			dataType : DataType
		});

		req
				.done(function(data) {
					console.log(JSON.stringify(data));
					if (formID == 'merchantForm') {
						if (bitCheck == 4) {
							var msg = 'Updated successfully.';
							var url = appendContext('/merchant/profile?msg='
									+ msg + '&edit=0&mid='+data.id);
							j_loader_GET(url, 'html', DisplayID);
						} else if (bitCheck == 44) {
							var msg = 'Updated successfully.';
							var url = appendContext('/admin/profile?msg='
									+ msg + '&edit=0&mid='+data.id);
							j_loader_GET(url, 'html', DisplayID);
						} else {
							var msg = data.firstName
									+ ' has been added successfully.';
							var url = appendContext('/admin/getMerchantList?msg='
									+ msg);
							j_loader_GET(url, 'html', DisplayID);
						}
					} else if (formID == 'menuform') {
						bitCheck = 1;
						bitId = 'menuaddbtn';
						loadURL('/merchant/addMenu');
					} else if (formID.substring(0, 4) == 'menu') {
						menuCancel(formID.substring(5));
					}else if(formID.substring(0,8)=='category'){
						var url = appendContext('/merchant/manageCategory?levelno='+data.levelnumber+'&levelcode='+data.sortorder+'&leveldata='+data.fullName);
						j_loader_GET(url, 'html', DisplayID);
					}
					disableLoading(DisplayID);
				});

		req.fail(function(jqXHR, textStatus) {
			sweetAlert("Oops...", "Something went wrong, Try reloading page!",
					"error");
			disableLoading(DisplayID);
		});
	}
}


function j_loader_PUT(URL, DataType, DisplayID, formID) {
	var flag = false;
	flag = validateForm(formID, 'jname');

	var dataJSON = getFormData(formID);
	if (bitCheck == 2) {
		dataJSON = getAnyData(formID);
		bitCheck = 0;
	}
	if (flag) {
		enableLoading(DisplayID);
		var req = $.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			url : appendContext(URL),
			data : JSON.stringify(dataJSON),
			beforeSend : function(request) {
				request.setRequestHeader("SID", getSID());
			},
			type : "PUT",
			dataType : DataType
		});

		req.done(function(data) {
					console.log(JSON.stringify(data));
					if(formID.substring(0,8)=='category'){
						var url = appendContext('/merchant/manageCategory?levelno='+data.levelnumber+'&levelcode='+data.sortorder+'&leveldata='+data.fullName);
						j_loader_GET(url, 'html', DisplayID);
					}
					disableLoading(DisplayID);
				});

		req.fail(function(jqXHR, textStatus) {
			sweetAlert("Oops...", "Something went wrong, Try reloading page!",
					"error");
			disableLoading(DisplayID);
		});
	}
}

function menuEdit(id) {
	$('.menuOpt').each(function() {
		$(this).attr('disabled', 'true');
	});
	$('#menuaddbtn').hide();
	$('#menuadding').slideUp(300);
	var URL = appendContext('/merchant/editMenu?edit=1&id=' + id);
	bitCheck = 9;
	j_loader_GET(URL, 'html', 'menu_' + id);
}

function menuUpdate(x) {
	bitCheck = 2;
	j_loader_POST("/merchant/updateMenu", "json", "mainContainer", "menu_" + x);
}

function menuCancel(x) {
	$('.menuOpt').each(function() {
		$(this).removeAttr('disabled');
	});
	$('#menuaddbtn').show();
	$('#menu_' + x + '_img').remove();
	var URL = appendContext('/merchant/editMenu?edit=0&id=' + x);
	bitCheck = 9;
	j_loader_GET(URL, 'html', 'menu_' + x);
}

function menuDelete(x, y) {
	swal({
		title : "Are you sure?",
		text : y + " will be removed from menu",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, Delete!",
		closeOnConfirm : false
	}, function() {
		var URL = appendContext('/merchant/deleteMenu?id=' + x);
		j_loader_DELETE(URL, 'html', 'menu_' + x);
		swal({
			title : "Menu Deleted!",
			text : "Menu has been removed",
			timer : 1000,
			showConfirmButton : false
		});
	});
}

function getFormData(id) {
	var $form = $("#" + id);
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};

	$.map(unindexed_array, function(n, i) {
		indexed_array[n['name']] = n['value'];
	});
	console.log(JSON.stringify(indexed_array));
	return indexed_array;
}

function getSID() {
	return $("#SID_TOKEN").val();
}

function UrlBuilder(that) {
	var val = that.getAttribute('attr-url');
	if (val !== '') {
		var URL = $('#CONTEXT').val() + val;
		if (val == '/logout') {
			swal({
				title : "Are you sure?",
				text : "You will require to login again!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, log out!",
				closeOnConfirm : false
			}, function() {
				sessionStorage.realodURL='';
				window.location.href = URL;
			});
		} else {
			if (typeof (Storage) !== "undefined") {
				sessionStorage.realodURL = URL;
			} else {
				console
						.log("Sorry, your browser does not support web storage...");
			}
			j_loader_GET(URL, 'html', 'mainContainer');
		}
	}
}

function appendContext(url) {
	var URL = $('#CONTEXT').val() + url;
	return URL;
}

function loadHome() {
	var url = '';
	var id = $('#roleId').val();
	if (id == 1) {
		url = appendContext('/admin/dashboardView?msg=');
	} else if (id == 2) {
		url = appendContext('/merchant/dashboardView?msg=');
	} else if (id == 3) {
		url = appendContext('/merchant/addMenu');
	}

	if (typeof (Storage) !== "undefined") {
		if (sessionStorage.realodURL) {
			var auth=url.split('/');
			var sess=sessionStorage.realodURL.split('/');
			if(auth[2]==sess[2]){
				url = sessionStorage.realodURL;
			}else{
				sessionStorage.realodURL=url;
			}
		}
	} else {
		console.log("Sorry, your browser does not support web storage...");
	}

	j_loader_GET(url, "html", "mainContainer");
}

function loadURL(url) {
	j_loader_GET($('#CONTEXT').val() + url, "html", "mainContainer");
}

function endsWith(str, suffix) {
	return str.indexOf(suffix, str.length - suffix.length) !== -1;
}

function startsWith(str, prefix) {
	return str.indexOf(prefix) == 0;
}

function showElem(x) {
	$('#' + x).slideDown(500);
}
function hideElem(x) {
	$('#' + x).hide(300);
}

function removeElem(x) {
	$('#' + x).remove();
}

function emptyElem(x) {
	$('#' + x).html('');
}

function validateForm(formid, attrName) {
	var flag = true;
	var msg = '';
	var msg1 = '';
	var i = 0;
	$('#' + formid).find(':input').each(function() {
		if ($(this).attr('required')) {
			if ($(this).val().trim() == '') {
				$(this).addClass('required_err');
				if (i == 0) {
					msg1 = $(this).attr(attrName);
					$(this).focus();
				} else {
					msg1 = msg1 + ', ' + $(this).attr(attrName);
				}
				i++;
				flag = false;
			} else {
				$(this).removeClass('required_err');
			}
		}
	});

	if (!flag) {
		msg = "You forgot to mention values for " + msg1;
	}

	i = 0;

	if (flag) {
		$('#' + formid).find(':input').each(function() {
			if ($(this).attr('dataType') == 'number') {
				var val = $(this).val();
				if (val != '' && isNaN(val)) {
					$(this).addClass('required_err');
					if (i == 0) {
						d
						msg1 = $(this).attr(attrName);
						$(this).focus();
					} else {
						msg1 = msg1 + ', ' + $(this).attr(attrName);
					}
					alert(msg);
					i++;
					flag = false;
				} else {
					$(this).removeClass('required_err');
				}
			}
		});

		if (!flag) {
			msg = msg1 + ' should be in number format.';
		}
	}
	if (!flag) {
		sweetAlert("Wait...", msg, "error");
	}
	return flag;
}

function getAnyData(id) {
	var data = {};
	$('#' + id).find(':input').each(function(e) {
		if ($(this).attr('type') != 'button') {
			data[$(this).attr('name')] = $(this).val();
		}
	});
	console.log(JSON.stringify(data));
	return data;
}

function transactionSearch() {
	var flag = validateForm('calendarSearchBox', 'jname');
	if (flag) {
		j_loader_GET(appendContext('/merchant/transactionSearch?created_from='
				+ $('#from').val() + '&created_to=' + $('#to').val()), 'html',
				'transactionTable');
	}
}

function transactionDownload() {
	window.location.href = appendContext('/merchant/transactionDownload?created_from='
			+ $('#from').val() + '&created_to=' + $('#to').val());
}

function orderSearch() {
	var flag = validateForm('calendarSearchBox', 'jname');
	if (flag) {
		j_loader_GET(appendContext('/merchant/orderSearch?created_from='
				+ $('#from').val() + '&created_to=' + $('#to').val()), 'html',
				'transactionTable');
	}
}

function orderDownload() {
	window.location.href = appendContext('/merchant/orderDownload?created_from='
			+ $('#from').val() + '&created_to=' + $('#to').val());
}

function enableLoading(id) {
	$('#' + id).hide();
	$('#mainContainerLoading').show();
}

function disableLoading(id) {
	if (bitCheck == 999) {
		$('#mainContainerLoading').hide();
		$('#' + id).show();
		bitCheck = 0;
	} else {
		setTimeout(function() {
			$('#mainContainerLoading').hide();
			$('#' + id).show();
		}, 100);
	}
}

function openPopUp(that) {
	$('#modal_wrap').show();
	$('#modal_profile').show('drop',500);
	var val = that.getAttribute('attr-url');
	if (val !== '') {
		var URL = $('#CONTEXT').val() + val;
		j_loader_GET(URL, 'html', 'modal_profile');
	}
}

function hidePopUp() {
	$('#modal_wrap').hide();
	$('#modal_profile').html('');
	$('#modal_profile').hide();
	setTitle($('#role').val() + ' Panel');
}

function updateMerchant(x) {
	if(x=="admin"){
			bitCheck = 44;
				j_loader_POST("/admin/updateProfile", "json", "modal_profile",
				"merchantForm");
	}else{
			bitCheck = 4;
				j_loader_POST("/merchant/updateProfile", "json", "modal_profile",
				"merchantForm");
	}
}

function changepw(middleURL) {
	var op = getIdValue('op');
	var np = getIdValue('np');
	var npc = getIdValue('npc');
	var flag = false;
	var msg = '';

	if (!flag && np == '') {
		msg = "Please enter new password";
		flag = true;
	}
	if (!flag && np != npc) {
		msg = "New passwords do not match";
		flag = true;
	}

	if (!flag) {
		var URL = $('#CONTEXT').val() + middleURL+'&op=' + op + '&np='
				+ np + '&npc=' + npc;
		bitCheck = 5;
		j_loader_GET(URL, 'html', 'modal_profile');
	} else {
		sweetAlert("Oops...", msg, "error");
	}
}

function getIdValue(id) {
	if(document.getElementById(id)){
		return document.getElementById(id).value;	
	}else{
		return '';
	}
}

function enablePagination(pageId) {
	var pageSizeContent = '<div style="float: left;margin-left: 10px;">'
			+ '<span style="padding-right:10px;color:darkblue !important">Page Size</span>'
			+ '<select onchange="createPages(\'' + pageId
			+ '\',this.value)" id="pageSizeCount" style="font-size: 18px;">'
			+ '<option value="5">5</option>'
			+ '<option selected value="10">10</option>'
			+ '<option value="15">15</option>'
			+ '<option value="20">20</option>' + '</select></div>';
	$('#' + pageId)
			.after(
					'<div id="navSelect_j' + pageId + '">' + pageSizeContent
							+ '</div>');
	var rowsShown = 10;
	createPages(pageId, rowsShown);
}

function createPages(pageId, rowsShown) {
	$("#navPaging_j" + pageId).remove();
	$('#' + pageId + ' tbody tr').removeAttr('style');
	$('#' + pageId + ' tbody tr').removeAttr('rel');

	$('#navSelect_j' + pageId).after(
			'<div id="navPaging_j' + pageId + '"></div>');
	var rowsTotal = $('#' + pageId + ' tbody tr').length;
	var numPages = Math.round(rowsTotal / rowsShown);

	if (numPages < 1) {
		$("#navSelect_j" + pageId).remove();
	}

	for (i = 0; i < numPages; i++) {
		var pageNum = i + 1;
		$('#navPaging_j' + pageId).append(
				'<a class="navPage" rel="' + i + '">' + pageNum + '</a> ');
	}
	$('#' + pageId + ' tbody tr').hide();
	$('#' + pageId + ' tbody tr').slice(0, rowsShown).show();
	$('#navPaging_j' + pageId + ' a:first').addClass('activePage');
	$('#navPaging_j' + pageId + ' a').bind(
			'click',
			function() {
				$('#navPaging_j' + pageId + ' a').removeClass('activePage');
				$(this).addClass('activePage');
				var currPage = $(this).attr('rel');
				var startItem = currPage * rowsShown;
				var endItem = parseInt(startItem) + parseInt(rowsShown);
				$('#' + pageId + ' tbody tr').css('opacity', '0.0').hide()
						.slice(startItem, endItem).css('display', 'table-row')
						.animate({
							opacity : 1
						}, 300);
			});
}

function enableDataTable(id) {
	var table = $('#' + id + '').DataTable({
		stateSave : true
	});
}

function loadJSON(URL, toElem, furl) {
	$.getJSON(URL, function(json) {
		// console.log("JSON Data: " + JSON.stringify(json));
		$('#' + toElem).val(JSON.stringify(json));
		var u = appendContext(furl);
		setTimeout(function() {
			document.getElementById(toElem + '_frame').src = u;
		}, 500);
	});
}

function setTitle(x) {
	document.title = x + ' - Nikata Consultancy';
}

function enableNiceScroll(x) {
	// $(x).niceScroll({
	// cursorwidth: "10px",
	// smoothscroll: true,
	// cursorcolor: "#2a3542",
	// hidecursordelay: 1000,
	// autohidemode: false
	// });
}

function manageOrder(opr, o_id, t_id, idnum) {
	if (opr == 1) {
		swal({
			title : "Are you sure?",
			text : "The order will be canceled!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, Cancel it!",
			closeOnConfirm : false
		}, function() {
			var URL = appendContext('/merchant/cancelOrder?orderId=' + o_id)
			j_loader_DELETE(URL, 'html', 'header_' + idnum + ',headerDetail_'
					+ idnum + '');
			swal({
				title : "Order Canceled!",
				text : "Order has been cancelled",
				timer : 500,
				showConfirmButton : false
			});
		});
	} else if (opr == 2) {
		window.location.href = appendContext('/merchant/generateBill?orderId='
				+ o_id);
	} else if (opr == 3) {
		bitCheck = 999;
		hideElem('appendOrderBtnWrap_' + idnum);
		showElem('appendOrderActionWrap_' + idnum);
		var URL = appendContext('/merchant/appendOrder?o_id=' + o_id+'&t_id='+t_id);
		j_loader_GET(URL, 'html', 'appendOrder_' + idnum);
	} else if (opr == 4) {
		showElem('appendOrderBtnWrap_' + idnum);
		hideElem('appendOrderActionWrap_' + idnum);
		emptyElem('appendOrder_' + idnum);
	}else if(opr==5){
		printRows(t_id,o_id,idnum);
	}
	scrollTo('downAppend_'+t_id);
}

function printRows(t_id,o_id,idnum) {
	//var menus=$("input[name=nameGoesHere]").val();
	var list=[];
	var remove=[];
	var totalTR='';
	var menus=$('#menu-tools').val();
	for(var i=0;i<menus.length;i++){
		var id=menus[i];
		var qty=$('#qty_'+id).val();
		var name=$('#mhidden_name_'+id).val();
		var amt=getPrice(id);
		var frstAmt=amt;
		var oldqty=0;
		var oldamt=0;
		var singlePrice=0;
		
		var num=0;
		$('#'+t_id+'_'+id).children('td').each(function(i) { 
			if(num==1)
			oldqty=$(this).text().trim();
			if(num==2)
			oldamt=$(this).text().trim();
			
			num++;
		});
		
		if(num!=0){
		singlePrice=oldamt/oldqty;
		qty=parseInt(oldqty)+parseInt(qty);
		amt=singlePrice*qty;
		remove.push(t_id+'_'+id);
		}
		
		var tr='<tr><td>'+name+'</td><td>'+qty+'</td><td>'+amt+'.0</td></tr>';
		
		var order = {};
		order["order_id"]=o_id;
		order["table_id"]=t_id;
		order["menu_id"]=id;
		order["menu_name"]=name;
		order["quantity"]=qty;
		order["amount"]=amt;
		order["status"]="ACTIVE";
		order["merchant_id"]=$('#merchant_id_global').val();
		
		list.push(order);
		
		totalTR+=tr;
	}
	//console.log(JSON.stringify(list));

	if (list.length > 0) {
		var dataJSON = {};
		dataJSON["orders"] = list;
		var req = $.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			url : appendContext('/merchant/appendOrder'),
			data : JSON.stringify(dataJSON),
			beforeSend : function(request) {
				request.setRequestHeader("SID", getSID());
			},
			type : "POST",
			dataType : "json"
		});

		req.done(function(data) {
			console.log(JSON.stringify(data));
			for (var a = 0; a < remove.length; a++) {
				removeElem(remove[a]);
			}
			var rows='';
			for(var i=0;i<data.orders.length;i++){
				var elem=data.orders[i];
				var tr='<tr id="'+t_id+'_'+elem.menu_id+'"><td>'+elem.menu_name+'</td><td>'+elem.quantity+'</td><td>'+elem.amount+'</td></tr>';
				rows=rows+tr;
			}
			
			$('#' + t_id+'_tbody').html(rows);
			showElem('appendOrderBtnWrap_' + idnum);
			hideElem('appendOrderActionWrap_' + idnum);
			emptyElem('appendOrder_' + idnum);
		});

		req.fail(function(jqXHR, textStatus) {
			sweetAlert("Oops...", "Something went wrong, Try reloading page!",
					"error");
		});
	}
}

function getPrice(id) {
	var price=0;
	for(var a=0;a<dataAppend.length;a++){
		var item=dataAppend[a];
		if(item.id==id){
			price=item.amount;
			break;
		}
	}
	return price;
}

function scrollTo(x) {
	$('html, body').animate({
		scrollTop : $("#"+x).offset().top
	}, 2000);
}

function updateCat(id,sname,fname,sorder,mid,key) {
	$('#u_id'+key).val(id);
	$('#u_shortName'+key).val(sname);
	$('#u_fullName'+key).val(fname);
	$('#u_sortorder'+key).val(sorder);
	$('#u_merchant_id'+key).val(mid);
	$('#addDiv'+key).hide();
	$('#updateDiv'+key).show();
	if(key=='')
	j_loader_GET(appendContext('/merchant/manageCategory?levelcode='+sorder+'&leveldata='+fname+'&levelno=2'), 'html', 'subcategories');
	scrollTo('updateDiv'+key);
}
function canUpdate(key) {
	$('#u_id'+key).val('');
	$('#u_shortName'+key).val('');
	$('#u_fullName'+key).val('');
	$('#u_sortorder'+key).val('');
	$('#u_merchant_id'+key).val('');
	$('#addDiv'+key).show();
	$('#updateDiv'+key).hide();
	if(key==''){
	$('#subcategories').html('');
	$('#subcategories').hide();
	}
}

function appendOptions(id,code) {
	var arr=code.split('|');
	var subcode=arr[1].substring(0,2);
	var cateList=JSON.parse($('#subJSON').html());
	var options="<option value='0'>Select Sub-Category</option>";
	for(var i=0;i<cateList.length;i++){
		var cat=cateList[i];
		var sort=cat.sortorder;
		if(sort.substring(0,2)==subcode){
		var opt="<option value='"+cat.id+"|"+cat.sortorder+"'>"+cat.fullName+"</option>";
		options=options+opt;
		}
	}
	$('#'+id).html(options);
}

function appendOptions_sub(id,code,sub) {
	var arr=code.split('|');
	var subcode=arr[1].substring(0,2);
	var cateList=JSON.parse($('#subJSON').html());
	var selected='';
	var options="<option value='0'>Select Sub-Category</option>";
	for(var i=0;i<cateList.length;i++){
		var cat=cateList[i];
		var sort=cat.sortorder;
		if(sub==cat.id){
			selected=' selected ';
		}
		if(sort.substring(0,2)==subcode){
		var opt="<option "+selected+" value='"+cat.id+"|"+cat.sortorder+"'>"+cat.fullName+"</option>";
		options=options+opt;
		}
	}
	$('#'+id).html(options);
}