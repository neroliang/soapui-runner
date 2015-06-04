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
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/skeleton.css">
<link rel="stylesheet" href="css/custom.css">
</head>
<body>
	<div class="container">
		<section class="header">
		<h2 class="title">SoapUI Runner</h2>
		</section>
		<div class="navbar-spacer"></div>
		<nav class="navbar">
		<div class="container">
			<ul class="navbar-list">
				<li class="navbar-item"><a class="navbar-link"
					href="<c:url value="/" />">Home</a></li>
			</ul>
		</div>
		</nav>
		<div class="docs-section">
			<h6 class="docs-header">Run a Service Uptime Report</h6>
			<c:url value="/processEnvironmentSelection" var="url" />
			<div class="docs-example docs-example-forms">
				<form:form commandName="environment" method="GET" action="${url}">
					<div class="row">
						<div class="six columns">
							<label for="environmentInput">Environments</label>
							<form:select className="u-full-width" path="selection">
								<form:option value="uat">User Acceptance Testing</form:option>
								<form:option value="prod">Production</form:option>
							</form:select>
						</div>
					</div>
					<input class="button-primary" value="Submit" type="submit">
				</form:form>
			</div>
		</div>

		<div class="docs-section">
			<h6 class="docs-header">Results</h6>
			<table class="u-full-width">
				<thead>
					<tr>
						<th>Name</th>
						<th>Success Flag</th>
						<th>Log</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${report.resultList}" var="element"
						varStatus="loop">
						<tr>
							<td>${element.name}</td>
							<td>${element.successFlag}</td>
							<td><c:if test="${not empty element.log}">
									<a href="<c:url value="/log/${loop.index}" />">View</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>