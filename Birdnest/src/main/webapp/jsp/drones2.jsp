<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%><%-- <%@ page import="com.app.Drones2"%> --%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drone Data</title>
</head>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	background-color: var(- -lightgrey2);
	color: var(- -darkgrey);
	direction: ltr;
	line-height: 1.34;
	margin: 0;
	font-family: Helvetica;
}

input {
	width: 200px;
	height: 50px;
	margin-right: 1335px;
}

table {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 80%;
	background-color: rgb(199, 199, 199);
}

.table td, th {
	border: 1px solid #ddd;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: rgb(199, 199, 199);
}

tr:hover {
	background-color: #ddd;
	cursor: pointer;
}

.table th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #04AA6D;
	color: white;
}
</style>
<body>
	<table class="table" id="myTable">
    <tr style="border: solid;">
        <th>Observation</th>
        <th>Serial Number</th>
        <th>Model</th>
        <th>Manufacturer</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone Number</th>
        <th>Violation Captured At</th>
        <th>Email</th>
        <th>Firmware</th>
        <th>Position Y</th>
        <th>Position X</th>
        <th>Distance</th>
        <th>NDZStatus</th>
    </tr>
    <c:forEach var="observation" items="${observations}">
        <c:forEach var="drone" items="${observation.drones}">
            <tr>
                <td>Observation</td>
                <td>${drone.serialNumber}</td>
                <td>${drone.model}</td>
                <td>${drone.manufacturer}</td>
                <td>${pilotsMap[drone.serialNumber].firstName}</td>
                <td>${pilotsMap[drone.serialNumber].lastName}</td>
                <td>${pilotsMap[drone.serialNumber].phoneNumber}</td>
                <td>${observation.snapshotTimestamp}</td>
                <td>${pilotsMap[drone.serialNumber].email}</td>
                <td>${drone.firmware}</td>
                <td>${drone.positionY}</td>
                <td>${drone.positionX}</td>
                <td>${drone.distance}</td>
                <td>${drone.NDZStatus}</td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>