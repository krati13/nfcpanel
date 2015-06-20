<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />

<c:if test="${!empty user.menu}">
	<c:redirect url="home"></c:redirect>
</c:if>
<!DOCTYPE html>
<html lang="en" class="lockscreen">
<head>
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Login - Nikata Consultancy</title>
<meta name="generator" content="Bootply" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>

<!-- HTML code from Bootply.com editor -->
<body style='color:white !important'>
	<div class='jbackground'></div>
	<div style="background: transparent;" class="container">
		<div class="flip">
			<div class="card">
				<div class="face front">
					<div id="loginbox" style="margin-top: 100px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
						<div style="background: transparent;" class="panel">
							<div style="text-align: center;color: red;">
									${SPRING_SECURITY_LAST_EXCEPTION.message}
							</div>

							<div style="padding-top: 30px" class="panel-body">
								<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>
								<form action="<c:url value='j_spring_security_check'/>"
								method="post">
								<div class="center">
									<div class="headline text-center" id="time"></div>
									<div class="lockscreen-name">Sign In</div>
									<div class="lockscreen-item">
										<div class="lockscreen-image">
											<img src="images/nikata.jpg" alt="user image">
										</div>
										<div class="lockscreen-credentials">
											<div class="input-group">
												<input style="width:200px;outline:none" id="login-username" type="text" required="required"
													class="form-control" name="j_username" value=""
													placeholder="Username"> <input style="width:200px;outline:none" id="login-password"
													required="required" type="password" class="form-control"
													name="j_password" placeholder="Password">
												<div onclick="document.getElementById('iSubmit').click();"
													style="background: green" class="input-group-btn">
													<button style="background: green; color: white"
														class="btn btn-flat">Go</button>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12 control">
											<div style="border-top: 1px solid #888; padding-top: 25px; font-size: 85%;margin-top: 20px;">
												Problem signing in? <a href="#" id='signupbtn'> Recover password </a>
											</div>
										</div>
									</div>
								</div>
								<input type="submit" id='iSubmit' value="Submit"
									style="display: none">
							</form>
							</div>
						</div>
					</div>
				</div>
				<div class="face back">
					<div id="signupbox" style="margin-top: 100px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
						<div style="background: transparent;" class="panel">
							<div class="panel-heading">
								<div class="panel-title">Recover your password</div>
																<div style="float: right; font-size: 85%; position: relative; top: -10px">
									<a id="signinlink" href="#">Sign In</a>
								</div>
								
							</div>
							<div class="panel-body">
								<form id="signupform" class="form-horizontal" role="form">
									<div id="signupalert" style="display: none" class="alert alert-danger">
										<p>Error:</p>
										<span></span>
									</div>

									<div class="form-group">
										<label for="email" class="col-md-3 control-label">Email</label>
										<div class="col-md-9">
											<input type="text" class="form-control" name="email" placeholder="Email Address">
										</div>
									</div>

									<div class="form-group">
										<!-- Button -->
										<div class="col-md-offset-3 col-md-9">
											<button id="btn-signup" type="button" class="btn btn-info">
												<i class="icon-hand-right"></i>Recover
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$(function() {
	$('#signupbtn,#signinlink').click(function() {
		$('.card').toggleClass('flipped');
	});
	startTime();
});

/*  */
function startTime() {
	var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();

	// add a zero in front of numbers<10
	m = checkTime(m);
	s = checkTime(s);

	// Check for PM and AM
	var day_or_night = (h > 11) ? "PM" : "AM";

	// Convert to 12 hours system
	if (h > 12)
		h -= 12;

	// Add time to the headline and update every 500 milliseconds
	$('#time').html(h + ":" + m + ":" + s + " " + day_or_night);
	setTimeout(function() {
		startTime()
	}, 1000);
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}
</script>
</html>