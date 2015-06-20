<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />

<div class='dashContainer'>
	<div style='background:white' class='dashWrap'>
		<div style="margin-top: 100px;"></div>
		<div style='display:inline-block'>
			<iframe id='piedata_frame' scrolling="no" frameborder="0" style='width:400px;height:400px;border:0px solid transparent;'></iframe>
		</div>
		<div style='display:inline-block'>
			<iframe id='bardata_frame' scrolling="no" frameborder="0" style='width:400px;margin-left:50px;height:400px;border:0px solid transparent;'></iframe>
		</div>
<!-- 		<div style='display:inline-block'> -->
<!-- 			<iframe id='linedata_frame' style='width:400px;margin-left:50px;height:400px;border:0px solid transparent;'></iframe> -->
<!-- 		</div> -->
		<input type='hidden' id='piedata'/>
		<input type='hidden' id='bardata'/>
		<input type='hidden' id='linedata'/>
	</div>
</div>
<script>
loadJSON('${context}/merchant/graph/txn_graph','bardata','/views/charts/bar.html');
loadJSON('${context}/merchant/graph/menu_graph','piedata','/views/charts/pie.html');
//loadJSON('${context}/merchant/graph/footfall_graph','linedata','/views/charts/line.html');
</script>