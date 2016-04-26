
<!DOCTYPE html >
<html lang="en">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		<title><c:out value='${job.position}'/></title>
		<!--  JS -->
		<c:url value="/resources/js/ext/jquery.min.js" var="jqueryJS"></c:url>
		<script src="${jqueryJS}"></script>
		<c:url value="/resources/js/ext/jquery.easing.min.js" var="jqueryEasingJS"></c:url>
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
			<c:url value="/resources/css/ext/ie10-viewport-workaround.css" var="ie10CSS"></c:url>
			<link href="${ie10CSS}" rel="stylesheet"></link>
			<c:url value="/resources/css/shared.css" var="sharedCSS"></c:url>
			<link href="${sharedCSS}" rel="stylesheet"></link>
			<c:url value="/resources/css/view.css" var="viewCSS"></c:url>
			<link href="${viewCSS}" rel="stylesheet"></link>
			<!-- Fonts / Images -->
			<c:url value="/resources/images/favicon.png" var="favIcon"></c:url>
			<link id="favicon" rel="icon" href="${favIcon}"></link>
			<c:url value="/resources/images/splash-logo.png" var="logo"></c:url>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"></link>
			<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900' rel='stylesheet' type='text/css'></link>
			<c:url value="/" var="index"></c:url>
			<c:url value="/feedback" var="feedback"></c:url>
			<c:url value="/login" var="login"></c:url>
		</head>
		<body>
			<div id="top-content">
				<div class="container">
					<div class="title"><b><c:out value='${job.position}'/></b></div>
					<div class="primary-text"><c:out value='${job.company}'/></div><hr>
					<div id="tagline" class="secondary-text"><c:out value='${job.tagline}'/></div>
					<div class="minor-text"><span id="industry"><c:out value='${job.industry}'/></span> Industry</div>
				</div>
			</div>
			<div id="bottom-content">
				<div class="container">
					<div class="row primary-text body-text">
						<div class="col-md-12" id="address"><c:out value='${job.address}'/></div>
						<div class="col-md-12 active" id="activity">Last active: 13 days ago</div>
					</div>
				</div>

				<div class="heading">
					<div class="container">
						<div class="row primary-text">
							POSITION DESCRIPTION
						</div>
					</div>
				</div>

				<div class="container">
					<div class="row secondary-text body-text">
						<c:out value='${job.description}'/>
					</div>
				</div>
			</div>

			<div class="buttons">
				<div class="selector primary-text" id="ignore">
					<i class="fa fa-times"></i> IGNORE
				</div>
				<div class="selector primary-text" id="connect">
					<i class="fa fa-check"></i> CONNECT
				</div>
			</div>
			<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span></button>  <a class="navbar-brand" href="${index}">SplashFocus</a>
					</div>
					<div class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a id="feedback-button" href="${feedback}">FEEDBACK</a></li>
							<c:choose>
								<c:when test="${not empty pageContext.request.remoteUser}">
									<li><a href="<c:url value="/dashboard" />">DASHBOARD</a></li>
									<li><a href="<c:url value="/j_spring_security_logout" />">LOGOUT</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${login}">LOGIN</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
			<c:url value="/resources/js/shared.js" var="sharedJS"></c:url>
			<script src="${sharedJS}"></script>
			<c:url value="/resources/js/view.js" var="viewJS"></c:url>
			<script src="${viewJS}"></script>

		</body>
	</html>
