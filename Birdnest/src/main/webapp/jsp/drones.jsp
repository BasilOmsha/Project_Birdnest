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
<link rel="stylesheet" href="../css/signup-style.css" />

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

<!-- <script>
	//Define a function that makes an AJAX request to your servlet to fetch the updated data
	function refreshData() {
		// Make an AJAX request to your servlet to fetch the updated data
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {

			}
		}
		xhr.open("GET", "../obs", true);
		xhr.send();
	}
	setInterval(refreshData, 2000);

	setInterval(function() {
		// Make an AJAX request to the servlet to get the updated data
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				console.log('checking the respones:', xhr.responseText)
				// Parse the JSON response
				var data = JSON.parse(xhr.responseText);
				// Call the updateTable function with the data
				updateTable(data);
			}
		}
		xhr.open("GET", "../refreshData", true);
		xhr.send();
	}, 2000); // 2000 milliseconds = 2 seconds

	function updateTable(data) {
		console.log('checking the JS:', data);
		// Get the tbody element of the table
		var tbody = document.getElementById('table-body');
		// Clear the contents of the tbody
		tbody.innerHTML = '';
		// Loop through the data array
		for (var i = 0; i < data.length; i++) {
			// Get the current item
			var item = data[i];
			// Create a new tr element
			var tr = document.createElement('tr');
			// Create td elements for each property of the item
			var snapshotTimestampTd = document.createElement('td');
			var snapshotTimestamp = document.createElement('input');
			snapshotTimestamp.setAttribute('type', 'text');
			snapshotTimestamp.setAttribute('id', 'all');
			snapshotTimestamp.setAttribute('value', item.snapshotTimestamp);
			snapshotTimestamp.setAttribute('readonly', true);
			snapshotTimestampTd.appendChild(snapshotTimestamp);

			var serialNumberTd = document.createElement('td');
			var serialNumber = document.createElement('input');
			serialNumber.setAttribute('type', 'text');
			serialNumber.setAttribute('id', 'all');
			serialNumber.setAttribute('value', item.serialNumber);
			serialNumber.setAttribute('readonly', true);
			serialNumberTd.appendChild(serialNumber);

			var modelTd = document.createElement('td');
			var model = document.createElement('input');
			model.setAttribute('type', 'text');
			model.setAttribute('id', 'all');
			model.setAttribute('value', item.model);
			model.setAttribute('readonly', true);
			modelTd.appendChild(model);

			var distanceTd = document.createElement('td');
			var distance = document.createElement('input');
			distance.setAttribute('type', 'text');
			distance.setAttribute('id', 'all');
			distance.setAttribute('value', item.distance);
			distance.setAttribute('readonly', true);
			distanceTd.appendChild(distance);

			var nameTd = document.createElement('td');
			var name = document.createElement('input');
			name.setAttribute('type', 'text');
			name.setAttribute('id', 'all');
			name.setAttribute('value', item.firstName + ' ' + item.lastName);
			name.setAttribute('readonly', true);
			nameTd.appendChild(name);

			var phoneNumberTd = document.createElement('td');
			var phoneNumber = document.createElement('input');
			phoneNumber.setAttribute('type', 'text');
			phoneNumber.setAttribute('id', 'all');
			phoneNumber.setAttribute('value', item.phoneNumber);
			phoneNumber.setAttribute('readonly', true);
			phoneNumberTd.appendChild(phoneNumber);

			var emailTd = document.createElement('td');
			var email = document.createElement('input');
			email.setAttribute('type', 'text');
			email.setAttribute('id', 'all');
			email.setAttribute('value', item.email);
			email.setAttribute('readonly', true);
			emailTd.appendChild(email);
			// Append the tr element to the tbody
			tr.appendChild(snapshotTimestampTd);
			tr.appendChild(serialNumberTd);
			tr.appendChild(modelTd);
			tr.appendChild(distanceTd);
			tr.appendChild(nameTd);
			tr.appendChild(phoneNumberTd);
			tr.appendChild(emailTd);

			tbody.appendChild(tr);
		}
	}
</script> -->
<script src="../js/helpers.js"></script>
</html>