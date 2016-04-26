
<!DOCTYPE html >
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script>
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-53648946-2', 'auto');
ga('send', 'pageview');
</script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Feedback</title>
<!--  JS -->
<c:url value="/resources/js/ext/jquery.min.js" var="jqueryJS" />
<script src="${jqueryJS}"></script>
<c:url value="/resources/js/ext/jquery.easing.min.js" var="jqueryEasingJS" />
<script src="${jqueryEasingJS}"></script>
<c:url value="/resources/js/ext/jquery-ui.js" var="jqueryuiJS" />
<script src="${jqueryuiJS}"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
crossorigin="anonymous"></script>
<!--  CSS  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<c:url value="/resources/css/ext/ie10-viewport-workaround.css" var="ie10CSS" />
<link href="${ie10CSS}" rel="stylesheet">
<c:url value="/resources/css/shared.css" var="sharedCSS" />
<link href="${sharedCSS}" rel="stylesheet">
<!-- Fonts / Images -->
<c:url value="/resources/images/favicon.png" var="favIcon" />
<link id="favicon" rel="icon" href="${favIcon}">
<c:url value="/resources/images/splash-logo.png" var="logo" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<c:url value="/resources/css/create.css" var="createCSS" />
<link href="${createCSS}" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="container"><form:form method="POST" id="form" action="createConfirm">
			<div class="row">
				<div class="form-group col-md-6"><label class="secondary-text">First Name</label> <form:input type="text" class="form-control" id="firstname" placeholder="First Name" path="firstname" /></div>
				<div class="form-group col-md-6"><label class="secondary-text">Last Name</label> <form:input type="text" class="form-control" id="lastname" placeholder="Last Name" path="lastname" /></div>
			</div>
			<div class="row">
				<div class="form-group col-md-6"><label class="secondary-text">Email / Username</label> <form:input type="text" class="form-control" id="email" placeholder="Email Address" path="username" /></div>
				<div class="form-group col-md-6"><label class="secondary-text">Field of Interest</label> <form:select id="interest" name="interest" class="form-control" path="interest">
						<option value="0">Any Industry</option>
						<option value="1">Art / Design</option>
						<option value="2">Construction / Labor</option>
						<option value="3">Customer Service</option>
						<option value="4">Education</option>
						<option value="5">Engineering</option>
						<option value="6">Finance</option>
						<option value="7">Food Service</option>
						<option value="8">Government / Politics</option>
						<option value="9">Health Services</option>
						<option value="10">Legal / Law Enforcement</option>
						<option value="11">News / Social Media</option>
						<option value="12">Retail</option>
						<option value="13">Science / Technology</option>
						<option value="14">Transportation</option>
						<option value="15">Volunteer / Charity</option>
						<option value="16">Other Industries</option>
					</form:select></div>
			</div>
			<div class="row">
				<div class="form-group col-md-6"><label class="secondary-text">Password<i class="fa fa-question" onclick="$('#password-tooltip').fadeToggle();"></i><font id="password-tooltip" class="help-tooltip minor-text">Must
							be 7-32 characters</font></label> <form:input type="password" placeholder="Password" class="form-control password" id="password" path="password" /></div>
				<div class="form-group col-md-6"><label class="secondary-text">Confirm Password</label> <input type="password" placeholder="Confirm Password" class="form-control password" id="password_confirmation" /></div>
			</div>
			<div class="row">
				<div class="form-group col-md-4"><label class="secondary-text">City</label> <form:input type="text" class="form-control" id="city" placeholder="City" path="city" /></div>
				<div class="form-group col-md-4"><label class="secondary-text">State</label> <form:input type="text" class="form-control" id="state" placeholder="State" path="state" /></div>
				<div class="form-group col-md-4"><label class="secondary-text">ZIP Code<i class="fa fa-question" onclick="$('#zip-tooltip').fadeToggle();"></i><font id="zip-tooltip" class="help-tooltip minor-text">5-digit ZIP</font></label> <form:input type="text" class="form-control" id="zipcode" placeholder="Zipcode" path="zipcode" size="5" maxlength="5" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></div>
			</div>
			<hr />
			<div id="notice">All fields are required.</div>
			<div id="firstname-error" class="error">First Name must have between 2-30 valid characters</div>
			<div id="lastname-error" class="error">Last Name must have between 2-30 valid characters</div>
			<div id="password_confirmation-error" class="error">Passwords must match</div>
			<div id="password-error" class="error">Password must be between 7-32 characters</div>
			<div id="email-error" class="error">Email format is not valid</div>
			<div id="email-taken-error" class="error">Email has already been taken</div>
			<div id="interest-error" class="error">Interest is not valid</div>
			<div id="city-error" class="error">City must have at least 2 valid characters</div>
			<div id="state-error" class="error">State must have at least 2-20 valid characters</div>
			<div id="zipcode-error" class="error">US Zip code format required</div>
			<c:choose>
				<c:when test="${not empty results}">
					<div id="server-error" class="error">${results}</div>
				</c:when>
			</c:choose>
			<div class="centered"><input class="btn btn-default" id="feedback-submit" type="submit" value="Submit" /></div>
		</form:form></div>
	<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span></button> <c:url value="/" var="index" /> <a class="navbar-brand" href="${index}">SplashFocus</a>
		</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:url value="/feedback" var="feedback" />
					<li><a id="feedback-buton" href="${feedback}">FEEDBACK</a></li>
					<c:choose>
						<c:when test="${not empty pageContext.request.remoteUser}">
							<li><a href="<c:url value="/dashboard" />">DASHBOARD</a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />">LOGOUT</a></li>
						</c:when>
						<c:otherwise>
							<c:url value="/login" var="login" />
							<li><a href="${login}">LOGIN</a></li>
						</c:otherwise>
					</c:choose>
			</ul>
		</div>
	</div>
</div>
</body>
<c:url value="/resources/js/create.js" var="createJS" />
<script src="${createJS}"></script>
</html>
