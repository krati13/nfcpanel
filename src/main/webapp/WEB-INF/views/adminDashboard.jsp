<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="<%=request.getContextPath()%>" />
<c:set var="SID" value="<%=session.getId()%>" />

<c:if test="${empty user.menu}">
	<c:redirect url="login"></c:redirect>
</c:if>

<!DOCTYPE html>
<html class="lockscreen">
<head>
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body class="bodyLogin">
	<div class='jbackground'></div>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page-container">
		<div id="mainContainer">
			
		</div>
		<div id='mainContainerLoading'>
			<div style="position: absolute;top:50%;margin-top: -50px;left:50%;margin-left: -40px;">
				<img src='${context}/images/orbit-loading.gif'	class="loader_gif" />
			</div>
		</div>
	</div>
	<input type="hidden" id='SID_TOKEN' value='${SID}' />
	<input type="hidden" id='CONTEXT' value='${context}' />
</body>
</html>