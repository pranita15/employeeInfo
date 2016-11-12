<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<spring:url value="/resources/css/jqGrid/jqGridStyle.css"
	var="jqGridCss" />
<spring:url value="/resources/js/empGrid.js" var="empGridJs" />

<script src="http://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type='text/javascript'
	src="http://trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type='text/javascript'
	src="http://trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>

<link href="${jqGridCss}" rel="stylesheet" />
<script src="${empGridJs}"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#addBtn").click(function() {
			$("#addEmp").show();
			$("#gridDiv").hide();
			$("#updateBtn").hide();

		})
	});
	
	
	$(document).ready(function() {
		$("#appBtn").click(function() {
			$("#gridDiv").show();
			$("#grid").show();
			$("#addEmp").hide();
			$("#updateBtn").hide();
		})
	});
	function save() {
		var empName = $("#empName").val();
		var dept = $("#dept").val();
		var designation = $("#designation").val();
		var salary = $("#salary").val();
		var joiningDate = $("#joiningDate").val();
		$.ajax({
			type : "POST",
			encoding : "UTF-8",
			url : 'saveEmpData',
			dataType : 'json',
			data : {
				empName : empName,
				dept : dept,
				designation : designation,
				salary : salary,
				joiningDate : joiningDate
			},
			success : function(data) {
				alert("Successfully Saved");
				console.log("SUCCESS: ", data);
				window.location.reload(true);
			},
			error : function(e) {
				console.log("ERROR: ", e);

			}
		});
	}

	function update() {
		var empId = $("#empId").val();

		var empName = $("#empName").val();
		var dept = $("#dept").val();
		var designation = $("#designation").val();
		var salary = $("#salary").val();
		var joiningDate = $("#joiningDate").val();

		$.ajax({

			type : "POST",
			encoding : "UTF-8",
			url : "updateEmpData",
			datatype : 'json',
			data : {
				empId : empId,
				empName : empName,
				dept : dept,
				designation : designation,
				salary : salary,
				joiningDate : joiningDate,
			},
			success : function(data) {
	
				console.log("SUCCESS: ", data);
				$("#addEmp").hide();
				$("#gridDiv").show();
				$("#grid").show();
				$("#gridDiv").trigger("reloadGrid");
				$("#grid").trigger("reloadGrid");
			},
			error : function(e) {
				console.log("ERROR: ", e);

			}
		});
	}
	
</script>
</head>
<body>
	<button id="appBtn">App</button>

	<button id="addBtn">Add</button>
	<div id="addEmp" style="display: none;">

		<table align="center" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td style="width: 50%"><label for="empName"><b>Employee
							Name</b></label><br /> <input name="empName" id="empName" type="text"
					maxlength="50" style="width: 260px" /> <input type="hidden"
					id="empId" /></td>
			<tr>
				<td colspan="2"><label for="dept"><b>Department</b></label><br />
					<input name="dept" id="dept" type="text" maxlength="100"
					style="width: 535px" /></td>
			</tr>
			<tr>
				<td colspan="2"><label for="designation"><b>Designation</b></label><br />
					<input name="designation" id="designation" type="text"
					maxlength="100" style="width: 535px" /></td>
			</tr>
			<tr>
				<td><label for="salary"><b>Salary</b></label><br /> <input
					name="salary" id="salary" type="text" maxlength="50"
					style="width: 260px" /></td>
				<td><label for="joiningDate"><b>joiningDate</b></label><br />
					<input name="joiningDate" id="joiningDate" type="date"
					maxlength="50" style="width: 260px" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button id="saveBtn" onclick="save();">Save</button>
					<button id="updateBtn" onclick="update();">Update</button>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<div id="gridDiv" style="display: none;">
		<table id="grid">
			<div id="pagingDiv"></div>
		</table>
	</div>
	
	
</body>
</html>