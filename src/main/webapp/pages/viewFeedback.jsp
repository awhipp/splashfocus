
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
<title>User Feedback</title>
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
	<link href='https://fonts.googleapis.com/css?family=Raleway:500,700,900' rel='stylesheet' type='text/css'>
</head>
<style>
.container {
	background-color: #336699;
	color: white;
	min-height: 100%;
}

.col-md-3 {
	text-align: right;
}

.btn-default {
	width: 100%;
}

h1 {
	text-align: center;
}
</style>
<body>
	<div class="container">
		<h1>User Feedback</h1>
	<hr>
		<div class="feedback"><c:forEach var="feedback" items="${allFeedback}">
				<div class="row">
					<div class="col-md-3">Name</div>
					<div class="col-md-9"><c:out value='${feedback.name}'/></div>
				</div>
				<div class="row">
					<div class="col-md-3">Location</div>
					<div class="col-md-9"><c:out value='${feedback.location}'/></div>
				</div>
				<div class="row">
					<div class="col-md-3">Email/Phone</div>
					<div class="col-md-9"><c:out value='${feedback.email}'/></div>
				</div>
				<div class="row">
					<div class="col-md-3">Feedback</div>
					<div class="col-md-9"><c:out value='${feedback.feedback}'/></div>
				</div>
				<hr />
			</c:forEach> <c:url value="/index" var="index" />
			<form action="${index}"><input class="btn btn-default" type="submit" value="Back to Splash Page"></form></div>
</div>
</body>
</html>
