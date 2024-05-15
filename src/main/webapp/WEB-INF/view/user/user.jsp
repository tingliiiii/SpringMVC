<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!-- JSTL 標籤 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User 首頁</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/3.0.2/css/buttons.bootstrap5.css">

<style>
body {
	font-family: 'Noto Sans TC';
	padding: 30px;
}

h2 {
	margin: 30px auto;
}

h3 {
	margin: 30px auto;
}
</style>

</head>
<body>
	<h2>User 資料維護</h2>
	<hr />
	<table class="table table-borderless">


		<tr>
			<!-- User 表單 -->
			<td><%@ include file="userform.jspf"%></td>
			<!-- User 圖表 -->
			<td><%@ include file="userstatistics.jspf"%>
			</td>
		</tr>
		<tr>
			<!-- User 列表 -->
			<td colspan="2"><%@ include file="userlist.jspf"%></td>
		</tr>
	</table>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
	<script
		src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/3.0.2/js/dataTables.buttons.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.bootstrap5.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.html5.min.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.print.min.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.colVis.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var ans = $('#_method').val();
			console.log(ans);
			let table = new DataTable('#myTable', {
				layout : {
					topStart : {
						buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ]
					}
				}
			});
		});
	</script>
</body>
</html>