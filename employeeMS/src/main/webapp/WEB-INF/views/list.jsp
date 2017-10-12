<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>员工信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 引入bootstrap样式 -->
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- 引入jquery -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>
</head>

<body>
	<!--搭建显示界面  -->
	<div class="container">
		<!--标题  -->
		<div class="row">
			<div class="col-md-12">
				<h3>员工信息</h3>
			</div>
		</div>
		<!--按钮  -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-info">添加</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--数据表格  -->
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th>#</th>
					<th>name</th>
					<th>email</th>
					<th>gender</th>
					<th>deptName</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageInfo.list}" var="emp">
					<tr>
						<td>${emp.empId }</td>
						<td>${emp.empName }</td>
						<td>${emp.gender=="m"?"男":"女" }</td>
						<td>${emp.email }</td>
						<td>${emp.department.deptName }</td>
						<td>
							<button class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								编辑
							</button>
							<button class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								删除
							</button></td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<!--分页信息  -->
		<div class="row">
			<div class="col-md-6">第${pageInfo.pageNum }页，总${pageInfo.pages
				}共页，总${pageInfo.total }共条记录</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><a href="${APP_PATH }/emps?pn=1">首页</a> <c:if
							test="${pageInfo.hasPreviousPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if> <c:forEach items="${pageInfo.navigatepageNums }" var="num">
							<c:if test="${num ==pageInfo.pageNum }">
								<li class="active"><a href="#">${num }</a>
								</li>
							</c:if>
							<c:if test="${num !=pageInfo.pageNum }">
								<li><a href="${APP_PATH }/emps?pn=${num }">${num }</a>
								</li>
							</c:if>

						</c:forEach> <c:if test="${pageInfo.hasNextPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1}" aria-label="Next"> <span
									aria-hidden="true">&raquo;</span> </a></li>
						</c:if>
					<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">尾页</a>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>
