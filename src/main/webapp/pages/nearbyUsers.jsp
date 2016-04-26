<!DOCTYPE html >
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>Local Job Seekers</title>
<c:url value="/resources/css/ext/jquery-ui.css" var="jqueryuiCSS" />
<link href="${jqueryuiCSS}" rel="stylesheet">
<!--  JavaScript -->
<c:url value="/resources/js/ext/jquery.min.js" var="jqueryJS" />
<script src="${jqueryJS}"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
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
<!-- Fonts / Images -->
<c:url value="/resources/images/favicon.png" var="favIcon" />
<link id="favicon" rel="icon" href="${favIcon}">
<c:url value="/resources/images/splash-logo.png" var="logo" />
<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900'
	rel='stylesheet' type='text/css'>
<c:url value="/login" var="login" />
<c:url value="/index" var="index" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<c:url value="/resources/css/board.css" var="boardCSS" />
<link href="${boardCSS}" rel="stylesheet">
</head>
<body>
	<div class="options">
		<div class="container">
			<div class="dropdown">
				<form method="post" action="nearbyJobSeekers" id="resubmitSeekers">
					<div class="row address-box title container">
						<label for="name" class="row">Find users near</label>
						<div class="row search-box">
							<input id="input-address" class="dropdown-toggle" type="text"
								name="address" data-toggle="dropdown" value="<c:out value='${address}'/>"
								placeholder="City, State, or ZIP code" autocomplete="off"
								spellcheck="false">
							<div class="btn btn-default search" id="searchSeekers">
								<i class="fa fa-search"></i>
							</div>
						</div>
						<a class="primary-text adv-search">Advanced Search</a>
						<ul class="dropdown-menu secondary-text">
							<li><p>
									<select class="industry_dropdown" name="interest">
										<option value="0"
											<c:if test = "${industry == '0'}">selected</c:if>>Any
											Industry</option>
										<option value="1"
											<c:if test = "${industry == '1'}">selected</c:if>>Art
											/ Design</option>
										<option value="2"
											<c:if test = "${industry == '2'}">selected</c:if>>Construction
											/ Labor</option>
										<option value="3"
											<c:if test = "${industry == '3'}">selected</c:if>>Customer
											Service</option>
										<option value="4"
											<c:if test = "${industry == '4'}">selected</c:if>>Education</option>
										<option value="5"
											<c:if test = "${industry == '5'}">selected</c:if>>Engineering</option>
										<option value="6"
											<c:if test = "${industry == '6'}">selected</c:if>>Finance</option>
										<option value="7"
											<c:if test = "${industry == '7'}">selected</c:if>>Food
											Service</option>
										<option value="8"
											<c:if test = "${industry == '8'}">selected</c:if>>Government
											/ Politics</option>
										<option value="9"
											<c:if test = "${industry == '9'}">selected</c:if>>Health
											Services</option>
										<option value="10"
											<c:if test = "${industry == '10'}">selected</c:if>>Legal
											/ Law Enforcement</option>
										<option value="11"
											<c:if test = "${industry == '11'}">selected</c:if>>News
											/ Social Media</option>
										<option value="12"
											<c:if test = "${industry == '12'}">selected</c:if>>Retail</option>
										<option value="13"
											<c:if test = "${industry == '13'}">selected</c:if>>Science
											/ Technology</option>
										<option value="14"
											<c:if test = "${industry == '14'}">selected</c:if>>Transportation</option>
										<option value="15"
											<c:if test = "${industry == '15'}">selected</c:if>>Volunteer
											/ Charity</option>
										<option value="16"
											<c:if test = "${industry == '16'}">selected</c:if>>Other
											Industries</option>
									</select> <input name="latitude" value="0" class="hidden">
								</p></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="bottom">
		<div class="container">
			<div class="content" id="load-list">
				<c:forEach var="user" items="${users}">
					<c:url value="/viewProfile/${user.username}/" var="url" />
					<div class="row object" onclick="window.location.pathname='${url}'">
						<div class="item">
							<div class="row primary-text"><c:out value='${user.firstname}'/>&nbsp;<c:out value='${user.lastname}'/></div>
							<div class="row secondary-text"><c:out value='${user.tagline}'/></div>
							<div class="row minor-text subheading">
								<div class="interest"><c:out value='${user.interest}'/>&nbsp;-&nbsp;<c:out value='${user.city}'/>,&nbsp;<c:out value='${user.state}'/>&nbsp;<c:out value='${user.zipcode}'/></div>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:if test="${not empty address}">
				<c:if test="${fn:length(users) lt 20}">
					<div class="row object banner primary-text">
						No more users in that area.
						<br />
						We are still growing! <a href="#" onclick="$('#input-address').focus();">Try another area?</a>
					</div>
				</c:if>
				
				</c:if>
				<c:if test="${not empty users}">
					<div class="row object">
						<c:if test="${min gt 20}">
							<div class="prev-page btn btn-default"
								onclick="window.history.back();">
								<i class="fa fa-angle-double-left"></i> Back
							</div>
						</c:if>
						<c:if test="${fn:length(users) gt 19}">
							<div class="next-page btn btn-default"
								onclick="$('#seekers_form').submit();">
								Next <i class="fa fa-angle-double-right"></i>
							</div>
						</c:if>
					</div>
				</c:if>
			</div>
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
					<c:url value="/beta" var="beta" />
					<li><a href="#" onclick="switchToJobs();">SWITCH TO NEARBY
							JOBS</a></li>
					<li><a href="#"
						onclick="$('html, body').animate({ scrollTop: 0 }, 'fast');">BACK
							TO TOP</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:url value="/feedback" var="feedback" />
					<li><a id="feedback-button" href="${feedback}">FEEDBACK</a></li>
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
	<form method="post" action="nearbyJobs" class="hidden" id="jobs_form">
		<input type="text" name="address" value="<c:out value='${address}'/>"> <select
			name="industry">
			<option id="jobs_form_value" value="0"></option>
		</select> <input name="latitude" value="0"><input type="submit">
	</form>
	<form method="post" action="nearbyJobSeekers" class="hidden"
		id="seekers_form">
		<input type="text" name="address" value="<c:out value='${address}'/>"> <select
			name="interest">
			<option id="seekers_form_value" value="0"></option>
		</select>
		<c:choose>
			<c:when test="${not empty min}">
				<input name="latitude" value="${min}">
			</c:when>
			<c:otherwise>
				<input name="latitude" value="0">
			</c:otherwise>
		</c:choose>
		<input type="submit">
	</form>
	<c:url value="/resources/js/board.js" var="boardJS" />
	<script src="${boardJS}"></script>
	<c:url value="/resources/js/shared.js" var="sharedJS" />
	<script src="${sharedJS}"></script>
</body>
</html>
