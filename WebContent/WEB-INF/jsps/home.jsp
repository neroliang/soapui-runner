<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SoapUI Runner</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//fonts.googleapis.com/css?family=Raleway:400,300,600"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="css/normalize.css">
<link rel="stylesheet"
	href="css/skeleton.css">
<link rel="stylesheet"
	href="css/custom.css">
</head>
<body>
	<div class="container">
		<section class="header">
		<h2 class="title">
			SoapUI Runner
			</h2>
		</section>
		<div class="navbar-spacer"></div>
		<nav class="navbar">
		<div class="container">
			<ul class="navbar-list">
				<li class="navbar-item"><a class="navbar-link"
					href="<c:url value="/" />">Home</a></li>
				<li class="navbar-item"><a class="navbar-link"
					href="<c:url value="/new-uptime-report" />">New Report</a></li>
				<li class="navbar-item"><a class="navbar-link"
					href="<c:url value="/run-custom-report" />">Run Custom Report</a></li>
			</ul>
		</div>
		</nav>
		<div class="docs-section">
			<p>To do</p>
		</div>
	</div>
</body>
</html>