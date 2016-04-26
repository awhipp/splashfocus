
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
		<title><c:out value="${user.lastname}"/>&nbsp;|&nbsp;Profile</title>
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
					<c:url value="/resources/css/view.css" var="viewCSS"></c:url>
					<link href="${viewCSS}" rel="stylesheet"></link>
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
										<b><c:out value="${user.firstname}"/>&nbsp;<c:out value="${user.lastname}"/></b>
									</div>
									<hr>
										<div id="tagline" class="secondary-text"><c:out value="${user.tagline}"/></div>
										<div class="minor-text">
											Interested in <span id="industry"><c:out value="${user.interest}"/></span>
										</div>
									</div>
								</div>
								<div id="bottom-content">
									<div class="container">
										<div class="row primary-text body-text">
											<div class="col-md-12" id="address"><c:out value="${user.address}"/></div>
											<div class="col-md-12 active" id="activity">Last active: 13
												days ago</div>
											</div>
										</div>

										<div class="heading">
											<div class="container">
												<div class="row primary-text">ABOUT
													<c:out value="${fn:toUpperCase(user.firstname)}"/></div>
												</div>
											</div>

											<div class="container">
												<div class="row secondary-text body-text">A graduate from the
													University of Maryland, Alexander's academic and professional career
													are evidence of his multifaceted interests and skill-sets. Primarily
													focused in the field of software development and deployment, he also
													earned a second degree in Psychology with a minor concentration in
													Business Analytics. He strives to be technologically savvy and
													competent in business to serve as a valuable asset between the
													desires of management and a development team's capabilities.</div>
												</div>

												<c:if test="${not empty user.experiences}">
													<div class="heading">
														<div class="container">
															<div class="row primary-text">PREVIOUS EXPERIENCE</div>
														</div>
													</div>

													<c:forEach var="experience" items="${user.experiences}">
														<div class="container experience">
															<div class="row body-text">
																<div class="primary-text experience-position"><c:out value="${experience.position}"/></div>
																<div class="primary-text experience-company"><c:out value="${experience.company}"/></div>
																<div class="secondary-text experience-description"><c:out value="${experience.description}"/></div>
																<div class="secondary-text experience-location"><c:out value="${experience.city}"/>,
																	<c:out value="${experience.state}"/></div>
																	<div class="secondary-text experience-time"><c:out value="${experience.start_date}"/>-to-<c:out value="${experience.end_date}"/></div>

																</div>
															</div>
														</c:forEach>
													</c:if>

													<div class="buttons">
														<div class="selector primary-text" id="ignore">
															<i class="fa fa-times"></i> IGNORE
														</div>
														<div class="selector primary-text" id="connect">
															<i class="fa fa-check"></i> CONNECT
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
												<c:url value="/resources/js/shared.js" var="sharedJS"></c:url>
												<script src="${sharedJS}"></script>
												<c:url value="/resources/js/view.js" var="viewJS"></c:url>
												<script src="${viewJS}"></script>
											</body>
										</html>
