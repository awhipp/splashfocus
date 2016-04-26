
<!DOCTYPE html >
<html lang="en">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<head>
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
		  ga('create', 'UA-53648946-2', 'auto');
		  ga('send', 'pageview');
	  	</script>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
		<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
		<meta name="description" content=""></meta>
		<meta name="author" content=""></meta>
		<title>Your Dashboard | SplashFocus</title>
		<!--  JS -->
		<c:url value="/resources/js/ext/jquery.min.js" var="jqueryJS"></c:url>
		<script src="${jqueryJS}"></script>
		<c:url value="/resources/js/ext/jquery.easing.min.js"
			var="jqueryEasingJS"></c:url>
			<script src="${jqueryEasingJS}"></script>
			<c:url value="/resources/js/ext/jquery-ui.js" var="jqueryuiJS"></c:url>
			<script src="${jqueryuiJS}"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
			integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
			crossorigin="anonymous"></script>
			<!--  CSS  -->
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
				integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
				crossorigin="anonymous">
			<c:url value="/resources/css/ext/ie10-viewport-workaround.css"
				var="ie10CSS"></c:url>
				<link href="${ie10CSS}" rel="stylesheet"></link>
				<c:url value="/resources/css/shared.css" var="sharedCSS"></c:url>
				<link href="${sharedCSS}" rel="stylesheet"></link>
				<c:url value="/resources/css/dashboard.css" var="dashboardCSS"></c:url>
				<link href="${dashboardCSS}" rel="stylesheet"></link>
				<!-- Fonts / Images -->
				<c:url value="/resources/images/favicon.png" var="favIcon"></c:url>
				<link id="favicon" rel="icon" href="${favIcon}"></link>
				<c:url value="/resources/images/splash-logo.png" var="logo"></c:url>
				<link rel="stylesheet"
					href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"></link>
					<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900'
						rel='stylesheet' type='text/css'></link>
					</head>
					<body>
						<div id="top-content">
							<div class="container">
								<div class="title">
									<b><c:out value='${user.firstname}'/>&nbsp;<c:out value='${user.lastname}'/></b>
								</div>
								<hr>
									<div id="tagline" class="secondary-text"><c:out value='${user.tagline}'/></div>
								</div>
							</div>
							<div id="bottom-content">

								<div class="heading">
									<div class="container">
										<div class="row primary-text">What would you like to do
											<c:out value='${user.firstname}'/>?</div>
										</div>
									</div>

									<div class="container">
										<div class="options row">
											<div class="option-wrapper col-md-4">
												<div class="btn btn-default primary-text" onclick="updatedashboard()">Update Profile</div>
											</div>
											<div class="option-wrapper col-md-4">
												<c:url value="/postJob" var="postJob"/>
												<div class="btn btn-default primary-text" onclick="window.location.href='${postJob}'">Post a Job</div>
											</div>
											<div class="option-wrapper col-md-4">
												<div class="btn btn-default primary-text" onclick="viewMessages()">View Messages</div>
											</div>
										</div>
										<hr/>
									</div>
									<div class="updateProfile"></div>
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
												<c:url value="/nearbyJobs" var="nearbyJobs"></c:url>
												<li><a href="${nearbyJobs}">FIND JOBS</a></li>
												<c:url value="/nearbyJobSeekers" var="nearbyPeople"></c:url>
												<li><a href="${nearbyPeople}">FIND PEOPLE</a></li>
											</ul>
											<ul class="nav navbar-nav navbar-right">
												<c:url value="/feedback" var="feedback" />
												<li><a id="feedback-button" href="${feedback}">FEEDBACK</a></li>
												<c:choose>
													<c:when test="${not empty pageContext.request.remoteUser}">
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
								<c:url value="/resources/js/shared.js" var="sharedJS"></c:url>
								<script src="${sharedJS}"></script>
								<c:url value="/resources/js/dashboard.js" var="dashboardJS"></c:url>
								<script src="${dashboardJS}"></script>
							</body>
						</html>
