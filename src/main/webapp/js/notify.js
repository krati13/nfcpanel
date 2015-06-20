/**
 * Author : Jitendra | Created for getting notification from restaurant
 */
var previousOrder = '';
var previousTxn = '';
function getNewOrders() {
	if ($('#roleId').val() == 2) {
		var targetURL = $('#notifyURL').val();
		var eventSource = new EventSource(targetURL);
		eventSource.addEventListener('orders', function(event) {
			var order = event.data;
			order = order.trim();
			if (order != '' && order != previousOrder) {
				console.log(order);
				previousOrder = order;
				$('#orderNotifyRowContainer').html('');
				$('#orderCountNotify').show();
				var orderList = JSON.parse(order);
				var count = orderList.length;
				for (var i = 0; i < count; i++) {
					var o_id = orderList[i].order_id;
					var t_id = orderList[i].table_id;
					var details = buildNotifyDetails(o_id, t_id);
					$('#orderNotifyRowContainer').append(details);
				}
				$('#orderCountNotify').html(count);
				enableNiceScroll('.notificationDetailsWrap');
			}

			if (order == '' && previousOrder=='') {
				var details = buildNotifyDetails(0, 0);
				$('#orderNotifyRowContainer').html(details);
			}
		}, false);

		eventSource.addEventListener('transactions', function(event) {
			var txn = event.data;
			txn = txn.trim();
			if (txn != '' && txn != previousTxn) {
				previousTxn = txn;
				$('#txnNotifyRowContainer').html('');
				$('#txnCountNotify').show();
				var txnList = JSON.parse(txn);
				var count = txnList.length;
				for (var i = 0; i < count; i++) {
					var txn_id = txnList[i].txn_id;
					var t_id = txnList[i].table_id;
					var details = buildNotifyDetails_txn(txn_id, t_id);
					$('#txnNotifyRowContainer').append(details);
				}
				$('#txnCountNotify').html(count);
				enableNiceScroll('.notificationDetailsWrap');
			}

			if (txn == '' && previousTxn=='') {
				var details = buildNotifyDetails_txn(0, 0);
				$('#txnNotifyRowContainer').html(details);
			}
		}, false);
	}
}

function buildNotifyDetails(o_id, t_id) {
	if (o_id == 0 && t_id == 0) {
		var data = "<div class='rows'><span>no new orders placed</span></div>";
		return data;
	} else {
		var data = "<div class='rows'><span>Order from Table No. " + t_id
				+ "</span>" + buildButton(o_id, t_id) + "</div>";
		return data;
	}
}

function buildButton(o_id, t_id) {
	var btn = "<div class='downBtnWrap'><input id='menuaddbtn' style='padding: 3px;color:#2e6da4' type='button' class='btn btn-link btn-block animateBtn' onclick='view()' value='View order' /></div>";
	return btn;
}

function buildNotifyDetails_txn(o_id, t_id) {
	if (o_id == 0 && t_id == 0) {
		var data = "<div class='rows'><span>no new transaction</span></div>";
		return data;
	} else {
		var data = "<div class='rows'><span>Trnsaction from Table No. " + t_id
				+ "</span>" + buildButton_txn(o_id, t_id) + "</div>";
		return data;
	}
}

function buildButton_txn(o_id, t_id) {
	var btn = "<div class='downBtnWrap'><input id='menuaddbtn' style='padding: 3px;color:#2e6da4' type='button' class='btn btn-link btn-block animateBtn' onclick='view()' value='View transaction' /></div>";
	return btn;
}

function showPopUp(x, y) {
	quickHidePopup(x, y);
	$('#' + x).slideToggle(300);
	$('#' + y).toggleClass('ahover_notifyActive');
	$('#modal_lay_notify_1').toggle();
}

function hidePopup() {
	$('.notificationDetailsWrap').slideUp(200);
	$('.ahover_notify').removeClass('ahover_notifyActive');
	$('#modal_lay_notify_1').hide();
}

function quickHidePopup(x, y) {
	$('.notificationDetailsWrap').each(function() {
		var id = $(this).attr('id');
		if (id != x) {
			$(this).hide();
		}
	});
	$('.ahover_notify').each(function() {
		var id = $(this).attr('id');
		if (id != y) {
			$(this).removeClass('ahover_notifyActive');
		}
	});
	$('#modal_lay_notify_1').hide();
}
