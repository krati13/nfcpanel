<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.margin{
margin-top:200px !important;
}
.ui-tooltip, .arrow:after {
	background: #777;
	border: 0px solid #eee;
}

.ui-tooltip {
	padding: 5px 10px;
	color: white;
	border-radius: 4px;
	box-shadow: 0 0 11px #eee;
	font-size:10px;
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
	box-shadow: 0px 2px 20px #777;
	-webkit-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	transform: rotate(45deg);
}

.arrow.top:after {
	bottom: -20px;
	top: auto;
}
</style>
<div style="margin-top: 30px"></div>
<div>
	<form id='${o_id}' method="POST">
		<div style='text-align: left'>
			<h4>Choose Menu Item</h4>
			<p style='font-size:10px'>You can also adjust item's quantity. To remove any selected item, click on its green label and press backspace.</p>
			<div id='quantityAppend'></div>
			<div style='height: 30px;' id='quantityHidden'></div>
			<div class="control-group">
				<select onchange='findChanges()' id="menu-tools"
					placeholder="Pick an item..."></select>
			</div>
			<div id='ap_${o_id}'></div>
			<script>
				$('#menu-tools').selectize({
					maxItems : null,
					valueField : 'id',
					labelField : 'name',
					searchField : 'name',
					options : dataAppend,
					create : false
				});
			</script>
		</div>
	</form>
</div>
<div class='margin_1' style="margin-top: 50px"></div>
<script>
	function findChanges() {
		var divs = '<div>';
		var num = 0;
		$('#menu-tools')
				.find("option:selected")
				.each(
						function() {
							var val = $(this).val();
							var qty = 1;
							if (document.getElementById('mhidden_' + val)) {
								qty = $('#mhidden_' + val).val();
							} else {
								var hidden = '<input type="hidden" id="mhidden_'+val+'" value="1"/>';
								$("#quantityHidden").append(hidden);
							}
							var padding = '10px';
							var div = '<div class="qtyAppend" style="display:inline-block;margin-right:'+padding+';margin-top:10px;"><div style="font-size:10px;width:120px;overflow:hidden">'+$(this).text()+'</div><div><input title="Change quantity for '
									+ $(this).text()
									+ '" type="number" min="1" style="width:120px" id="qty_'
									+ val
									+ '" onchange="setValue(this.value,'
									+ val
									+ ');" name="quantity" value="'
									+ qty
									+ '"/></div><input type="hidden" id="mhidden_name_'+val+'" value="'+$(this).text()+'"/></div>';
							divs = divs + div;
							num++;
						});
		divs = divs + '</div>';
		$('#quantityAppend').html(divs);
		$(".qtyAppend").tooltip(
				{
					show : null,
					position : {
						my : "center bottom-20",
						at : "center top",
						using : function(position, feedback) {
							$(this).css(position);
							$("<div>").addClass("arrow").addClass(
									feedback.vertical).addClass(
									feedback.horizontal).appendTo(this);
						}
					},
					open : function(event, ui) {
						ui.tooltip.animate({
							top : ui.tooltip.position().top + 10
						}, "fast");
					}
				});
	}

	function setValue(v, val) {
		$('#mhidden_' + val).val(v);
	}
	
	$('.selectize-input').click(function() {
		$('.margin_1').addClass('margin');
		scrollTo('downAppend_${t_id}');
	});
	$('.selectize-input').blur(function() {
		$('.margin_1').toggleClass('margin');
	});
</script>