<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.google.gson.Gson"%>

<%@ page import="java.util.List"%>
<%@ page import="model.Violations"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" charset="UTF-8">
<title>Drone Data</title>
<link rel="stylesheet" href="../css/style.css" />

</head>
<style>
</style>
<body>
	<div class="main" id="main">
		<br>
		<h1>Drone Violations</h1>
		<br>
		<form class="sign-up-form" id="myForm">
			<div id="table-container">
				<table class="table  " id="myTable">
					<thead class="tablehead">
						<tr style="border: solid;">
							<th style="font-size: 22px;">Observation Time</th>
							<th style="font-size: 22px;">Drone Serial Number</th>
							<th style="font-size: 22px;">Model</th>
							<th style="font-size: 22px;">Closest Distance</th>
							<th style="font-size: 22px;">Name</th>
							<th style="font-size: 22px;">Phone Number</th>
							<th style="font-size: 22px;">Email</th>
						</tr>
					</thead>
					<tbody id="table-body">

					</tbody>
				</table>
			</div>
			<br>
		</form>
	</div>
</body>
<script src="../js/helpers.js"></script>
</html>