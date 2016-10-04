<%@page import="today.SortUtil"%>
<%@page import="today.Event"%>
<%@page import="java.util.List"%>
<%@page import="today.EventDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="today.ico">
<link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<link
	href="http://cdn.bootcss.com/bootstrap-datepicker/1.6.0/css/bootstrap-datepicker3.standalone.css"
	rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="http://cdn.bootcss.com/bootstrap-datepicker/1.6.0/js/bootstrap-datepicker.min.js"></script>
<script
	src="http://cdn.bootcss.com/bootstrap-datepicker/1.6.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<title>历史上的今天</title>



<script type="text/javascript">
	$(function() {

		$('#date_selector').datepicker({
			format : "mm月dd日",
			weekStart : 0,
			todayBtn : "linked",
			language : "zh-CN"
		});

		//选择日期
		$('#date_selector').on("changeDate", function(date) {
			var date = $('#date_selector').datepicker('getFormattedDate');
			window.location.href = "date?d=" + date;
		});

	});

	function toggle(id) {
		var v = $('#' + id);
		if (v.is(':hidden')) {
			v.show();
		} else {
			v.hide();
		}
	}
</script>


</head>

<%
	String date = (String) request.getAttribute("date");
	if (null == date) {
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		date = sdf.format(new Date());
	}

	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日");
	String today = sdf2.format(new Date());

	EventDao dao = new EventDao();
	List<Event> list = dao.getEventByDate(date);

	List<Event> listEvent = SortUtil.getEventByType(list, "0");
	List<Event> listBirth = SortUtil.getEventByType(list, "1");
	List<Event> listDeath = SortUtil.getEventByType(list, "2");
%>




<body>
	<div class="page-header" align="center">
		<h1>
			<small>历史上的 </small><%=date%>
		</h1>
	</div>


	<div class="container">
		<div class="row clearfix">
			<div class="col-md-9 column">
				<div class="list-group">
					<h4 class="list-group-item active" onclick="toggle('event');">大事记</h4>
					<div id="event">
						<%
							for (Event event : listEvent) {
						%>
						<div class="list-group-item">
							<h5 class="list-group-item-heading"><%=event.getYear()%></h5>
							<p class="list-group-item-text"><%=event.getMessage()%></p>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<div class="list-group">
					<h4 class="list-group-item active" onclick="toggle('birth');">出生</h4>
					<div id="birth">
						<%
							for (Event event : listBirth) {
						%>
						<div class="list-group-item">
							<h5 class="list-group-item-heading"><%=event.getYear()%></h5>
							<p class="list-group-item-text"><%=event.getMessage()%></p>
						</div>
						<%
							}
						%>
					</div>
				</div>
				<div class="list-group">
					<h4 class="list-group-item active" onclick="toggle('death');">逝世</h4>
					<div id="death">
						<%
							for (Event event : listDeath) {
						%>
						<div class="list-group-item">
							<h5 class="list-group-item-heading"><%=event.getYear()%></h5>
							<p class="list-group-item-text"><%=event.getMessage()%></p>
						</div>
						<%
							}
						%>
					</div>
				</div>

			</div>
			<div class="col-md-3 column">
				<div align="center" class="page-header">
					<h4>今天是</h4>
					<h3><%=today%></h3>
				</div>
				<div id="date_selector" align="center" data-date="<%=date%>"></div>
			</div>


		</div>
	</div>
	</div>

</body>
</html>