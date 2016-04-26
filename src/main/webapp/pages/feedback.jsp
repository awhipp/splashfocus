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
<c:url value="/resources/css/feedback.css" var="feedbackCSS" />
<link href="${feedbackCSS}" rel="stylesheet">
<!-- Fonts / Images -->
<c:url value="/resources/images/favicon.png" var="favIcon" />
<link id="favicon" rel="icon" href="${favIcon}">
<c:url value="/resources/images/splash-logo.png" var="logo" />
	<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="container primary-text"><form:form method="POST" id="form" action="feedbackConfirm">
			<div class="form-group"><label for="name">Name <font style="color: red;">*</font></label> <form:input type="text" class="form-control secondary-text" id="name" placeholder="Name" path="name" /></div>
			<div class="form-group"><label for="location">Location</label> <form:input type="text" class="form-control secondary-text" id="location" placeholder="Location" path="location" /></div>
			<div class="form-group"><label for="email">Email or Phone <font style="color: red;">*</font></label> <form:input type="text" class="form-control secondary-text" id="email" placeholder="Email or Phone"
					path="email" /></div>
			<div class="form-group"><label for="feedback">Feedback</label> <form:textarea id="feedback" placeholder="Any comments or questions" path="feedback" class="form-control secondary-text"
					onkeyup="textAreaAdjust(this)" /></div>
			<hr />
			<p class="minor-text"><font style="color: red;">*</font> Important fields so that we reach out should we have any follow up questions to better our service.</p>
			<p id="warning" class="minor-text"><font> Please fill out all required fields. </font></p>
			<div class="centered"><input class="btn btn-default" id="feedback-submit" type="submit" value="Submit" /></div>
		</form:form></div>
	<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span></button> <c:url value="/" var="index" /> <a class="navbar-brand" href="${index}">SplashFocus</a>
		</div>
			<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar">
					<c:if test="${empty pageContext.request.remoteUser}">
							<c:url value="/create" var="create" />
							<li><a href="${create}">CREATE ACCOUNT</a></li>
					</c:if>
						<c:url value="/nearbyJobs" var="nearbyJobs"></c:url>
						<li><a href="${nearbyJobs}">FIND JOBS</a></li>
						<c:url value="/nearbyJobSeekers" var="nearbyPeople"></c:url>
						<li><a href="${nearbyPeople}">FIND PEOPLE</a></li>
					</ul>
				<ul class="nav navbar-nav navbar-right">
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
			</ul></div>
	</div>
</div>
</body>
<c:url value="/resources/js/feedback.js" var="feedbackJS" />
<script src="${feedbackJS}"></script>
<c:url value="/resources/js/shared.js" var="sharedJS" />
<script src="${sharedJS}"></script>
</html>
