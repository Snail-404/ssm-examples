<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工信息页面</title>
<!-- 引入jquery -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

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
				<button id="addEmp" class="btn btn-info">添加</button>
				<button id="delEmps" class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--数据表格  -->
		<div class="row">
			<table class="table table-hover" id="emps_tab">
				<thead>
					<tr>
						<th><input type="checkbox" id="check_all"></th>
						<th>#</th>
						<th>name</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<!--分页信息  -->
		<div class="row">
			<div id="page_info" class="col-md-6"></div>
			<div id="page_nav" class="col-md-6"></div>
		</div>
	</div>

	<!-- --------------------------------------------------------------------------- -->
	<!--添加信息  -->
	<div id="empAddModal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">员工信息添加</h4>
				</div>

				<form class="form-horizontal">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">empName</label>
						<div class="col-sm-6">
							<input name="empName" type="text" class="form-control"
								id="empName_add_input" placeholder="Tommmm"> <span
								class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">email</label>
						<div class="col-sm-6">
							<input name="email" type="text" class="form-control"
								id="email_add_input" placeholder="Password"><span
								class="help-block"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">gender</label>
						<div class="col-sm-10">
							<label class="radio-inline"> <input type="radio"
								name="gender" id="gender1_add_input" value="m" checked="checked">
								男 </label> <label class="radio-inline"> <input type="radio"
								name="gender" id="gender2_add_input" value="f"> 女 </label>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">deptName</label>
						<div class="col-sm-4">
							<select class="form-control" name="dId">

							</select>
						</div>
					</div>
				</form>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">Save
						changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
		<!-- --------------------------------------------------------------------------- -->
	<!--修改员工信息  -->
	<div id="empUpdateModal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">员工信息修改</h4>
				</div>

				<form class="form-horizontal">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">empName</label>
						<div class="col-sm-6">
							<p class="form-control-static" name="empName" id="empName_update_static"></p>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">email</label>
						<div class="col-sm-6">
							<input name="email" type="text" class="form-control"
								id="email_update_input" placeholder="Password"><span
								class="help-block"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">gender</label>
						<div class="col-sm-10">
							<label class="radio-inline"> <input type="radio"
								name="gender" id="gender1_update_input" value="m" checked="checked">
								男 </label> <label class="radio-inline"> <input type="radio"
								name="gender" id="gender2_update_input" value="f"> 女 </label>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">deptName</label>
						<div class="col-sm-4">
							<select class="form-control" name="dId">

							</select>
						</div>
					</div>
				</form>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_update_btn">修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	

	<script type="text/javascript">
	//设置全局变量记录员工总记录数,当前页码
		var empTotal,currentPage;
//------------------------------------------------------------------------------------------------------
		//删除员工信息
		
		$(document).on("click",".emp_del_btn",function(){
			var delName=$(this).parents("tr").find("td:eq(2)").text();
			if (confirm("确定要删除"+delName+"吗？")) {
				$.ajax({
					type:"delete",
					url:"emp/"+$(this).attr("del-id"),
					success:function(result){
						alert(result.msg);
						toPage(currentPage);
					}
				});
			}
		});
		
		//批量删除
		$("#delEmps").click(function(){
			var delNames="";
			var delIds="";
			//取要删除的用户名和id
			$.each($(".check-item:checked"),function(index,item){
				delNames+=$(item).parents("tr").find("td:eq(2)").text()+",";
				delIds+=$(item).parents("tr").find("td:eq(1)").text()+"-";
			});
			delNames=delNames.substring(0, delNames.length-1);
			delIds=delIds.substring(0, delIds.length-1);
			//alert(delIds);
			
			$.ajax({
				type:"delete",
				url:"emp/"+delIds,
				success:function(result){
					alert(result.msg);
					toPage(currentPage);
				}
			});
			
			
		});
		
		
		//设置全选&全不选
		//如果标题中选择则下面的全选
		$("#check_all").click(function(){
			$(".check-item").prop("checked",$(this).prop("checked"));
		});
		
		$(document).on("click",".check-item",function(){
			var flag=$(".check-item:checked").length==$(".check-item").length;
			$("#check_all").prop("checked",flag);
		});
		
		
//------------------------------------------------------------------------------------------------------
		//修改员工信息
		$(document).on("click",".emp_edit_btn",function(){
			get_depts("#empUpdateModal select");
			
			get_emp($(this).attr("edit-id"));
			
			$("#emp_update_btn").attr("update-id",$(this).attr("edit-id"));
			$("#empUpdateModal").modal({
				backdrop : "static"
			});
		});
		
		function get_emp(id){
			$.ajax({
				type:"get",
				url:"emp/"+id,
				
				success:function(result){
					//console.log(result);
					var empData=result.extend.emp;
					$("#empName_update_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					$("#empUpdateModal input[name=gender]").val([empData.gender]);
					$("#empUpdateModal select").val([empData.dId]);
				}
			});
		}
		
		$("#emp_update_btn").click(function(){
			//1.先验证邮箱信息格式
			var email = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				show_validate_msg("#email_update_input", "error", "邮箱格式不正确");
				return false;
			}else{
				show_validate_msg("#email_update_input", "success", "");
			}
			
			//2.将修改后的员工信息发送到后台
			$.ajax({
				type:"put",
				url:"emp/"+$(this).attr("update-id"),
				//如果type为post请求，则在数据后加上+"&_method=PUT"
				//data:$("#empUpdateModal form").serialize()+"&_method=PUT",
				
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					//1.关闭模态框
					$("#empUpdateModal").modal("hide");
					//2.返回修改信息所在页码
					toPage(currentPage);
				}
			});
		});
	
//------------------------------------------------------------------------------------------------------
		//添加员工信息
		
		$("#addEmp").click(function() {
			//清除表单数据
			reset_form("#empAddModal form");
			//$("#empAddModal form")[0].reset();
			get_depts("#empAddModal select");
			$("#empAddModal").modal({
				backdrop : "static"
			});
		});
		
		//清除表单数据及表单样式
		function reset_form(ele){
			$(ele)[0].reset();
			$(ele).find("*").removeClass("has-success has-error");
			$(ele).find(".help-block").text("");
		}

		function get_depts(ele) {
			$(ele).empty();
			$.ajax({
				type : "get",
				url : "depts",

				success : function(result) {
					//console.log(result);
					$.each(result.extend.depts, function(index, item) {
						var option = $("<option></option>").append(
								item.deptName).attr("value", item.deptId);
						$(ele).append(option);
					});
				}
			});
		}

		//对员工信息进行验证
		function validate_add_form() {
			//验证员工姓名
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if (!regName.test(empName)) {
				//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
				show_validate_msg("#empName_add_input", "error", "可以是2-5位中文或6-16位英文和数字的组合");
				return false;
			}else{
				show_validate_msg("#empName_add_input", "success", "");
			}
			//验证邮箱格式
			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				//alert("邮箱格式不正确");
				show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
				return false;
			}else{
				show_validate_msg("#email_add_input", "success", "");
			}
			return true;
		}

		function show_validate_msg(ele, status, msg) {
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if ("success" == status) {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			} else if ("error" == status) {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}

		}

		$("#emp_save_btn").click(function() {
			//1.将表单中的员工信息通过ajax传入服务器，存入数据库
			
			//先查看#emp_save_btn的ajax-va属性，若为error,则终止此次方法
			var va=$("#emp_save_btn").attr("ajax-va");
			if (va=="error") {
				return false;
			}
			
			//在提交之前需要先进行验证,如果返回false则终止方法进行
			if (!validate_add_form()) {
				return false;
			}
			$.ajax({
				type : "post",
				url : "emp",
				data : $("#empAddModal form").serialize(),

				success : function(result) {
					alert(result.msg);
					//2.存储成功后自动关闭模态框
					$("#empAddModal").modal("hide");
					//3.自动跳到员工列表的最后一页
					toPage(empTotal);

				}
			});

		});
		
		$("#empName_add_input").change(function(){
			var empName=$("#empName_add_input").val();
			console.log(empName);
			$.ajax({
				type:"POST",
				url:"checkUserName",
				data:{"empName":empName},
				
				success:function(result){
					if (result.code==100) {
						show_validate_msg("#empName_add_input", "success", "用户名可用");
						$("#emp_save_btn").attr("ajax-va","success");
					} else {
						show_validate_msg("#empName_add_input", "error", "用户名不可可用");
						$("#emp_save_btn").attr("ajax-va","error");
					}
				}
			});
		});

/*------------------------------------------------------------------------------------------  */
		//查询信息
		$(function() {
			toPage(1);
		});

		function toPage(pn) {
			$.ajax({
				type : "get",
				url : "emps",
				data : {
					pn : pn
				},
				success : function(result) {
					//console.log(result);
					//1.解析并显示雨员工信息
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);
					//3.解析并显示分页条信息
					build_page_nav(result);
				}
			});
		}

		//构建员工信息表
		function build_emps_table(result) {
			//#emps_tab tbody
			$("#emps_tab tbody").empty();
			var emps = result.extend.pageInfo.list;
			//遍历
			$.each(emps, function(index, item) {
				var checkTd=$("<td></td>").append($("<input></input>").addClass("check-item").prop("type","checkbox"));
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var gender = $("<td></td>").append(
						item.gender == "m" ? "男" : "女");
				var email = $("<td></td>").append(item.email);
				var deptName = $("<td></td>").append(item.department.deptName);

				var editBtn = $("<button></button>").addClass(
						"btn btn-primary btn-sm emp_edit_btn").attr("edit-id",item.empId).append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil").append("编辑"));
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm emp_del_btn").attr("del-id",item.empId).append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash").append(
										"删除"));
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(
						delBtn);
				$("<tr></tr>").append(checkTd).append(empIdTd).append(empNameTd).append(gender)
						.append(email).append(deptName).append(btnTd).appendTo(
								"#emps_tab tbody");
			});
		}

		//显示分页信息
		function build_page_info(result) {
			//page_info
			$("#page_info").empty();
			$("#page_info").append(
					"第" + result.extend.pageInfo.pageNum + "页，总共"
							+ result.extend.pageInfo.pages + "页，总共"
							+ result.extend.pageInfo.total + "条记录");
			empTotal = result.extend.pageInfo.total;
			currentPage=result.extend.pageInfo.pageNum;
		}

		//设置分页条
		function build_page_nav(result) {
			//page_nav
			$("#page_nav").empty();

			var nav = $("<nav></nav>");
			var ul = $("<ul></ul>").addClass("pagination");
			//首页
			//var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
			//前一页
			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));
			//设置点击事件
			if (result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				firstPageLi.click(function() {
					toPage(1);
				});
				prePageLi.click(function() {
					toPage(result.extend.pageInfo.pageNum - 1);
				});
			}

			ul.append(firstPageLi).append(prePageLi);
			//下一页
			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			//末页			
			var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页"));
			//设置点击事件
			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				lastPageLi.click(function() {
					toPage(result.extend.pageInfo.pages);
				});
				nextPageLi.click(function() {
					toPage(result.extend.pageInfo.pageNum + 1);
				});
			}

			//设置页码
			$.each(result.extend.pageInfo.navigatepageNums, function(index,
					item) {
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if (item == result.extend.pageInfo.pageNum) {
					numLi.addClass("active");
				}
				numLi.click(function() {
					toPage(item);
				});
				ul.append(numLi);
			});
			ul.append(nextPageLi).append(lastPageLi).appendTo(nav);
			$("#page_nav").append(nav);
		}
	</script>
</body>
</html>
