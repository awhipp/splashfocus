
<!DOCTYPE html >
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<c:url value="/resources/images/favicon.png" var="favIcon" />
<link rel="icon" href="${favIcon}">
<title>Splash Focus</title>
<!--  CSS  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<c:url value="/resources/css/ext/ie10-viewport-workaround.css"
	var="ie10CSS" />
<link href="${ie10CSS}" rel="stylesheet">
<c:url value="/resources/css/shared.css" var="sharedCSS" />
<link href="${sharedCSS}" rel="stylesheet">
<c:url value="/resources/css/index.css" var="indexCSS" />
<link href="${indexCSS}" rel="stylesheet">
<c:url value="/resources/css/ext/jquery-ui.css" var="jqueryuiCSS" />
<link href="${jqueryuiCSS}" rel="stylesheet">
<!--  JavaScript -->
<c:url value="/resources/js/ext/jquery.min.js" var="jqueryJS" />
<script src="${jqueryJS}"></script>
<c:url value="/resources/js/ext/jquery.easing.min.js"
	var="jqueryEasingJS" />
<script src="${jqueryEasingJS}"></script>
<c:url value="/resources/js/ext/jquery-ui.js" var="jqueryuiJS" />
<script src="${jqueryuiJS}"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<!-- Fonts / Images -->
<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900'
	rel='stylesheet' type='text/css'>
<body>
	<c:if test="${not empty message}">
		<div id="message">
			<div id="inner-message" class="alert alert-error primary-text">${message}</div>
		</div>
	</c:if>
	<div class="content container">
		<c:url value="/resources/images/splash-logo.png" var="splashLogo" />
		<img src="${splashLogo}" class="logo"></img>
		<div id="main-dialogue">
			<div class="row title" id="tagline">We seem to have lost focus.</div>
			<div class="row primary-text" id="tagline">Error 404 Page Not
				Found</div>
			<c:choose>
				<c:when test="${not empty pageContext.request.remoteUser}">
					<c:url value="/dashboard" var="url" />
					<div class="row secondary-text" id="subheading"
						onclick="window.location.href='${url}'">Get me out of here</div>
				</c:when>
				<c:otherwise>
					<c:url value="/index" var="url" />
					<div class="row secondary-text" id="subheading"
						onclick="window.location.href='${url}'">Get me out of here</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<c:url value="/" var="index" />
				<a class="navbar-brand" href="${index}">SplashFocus</a>
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
</html>
