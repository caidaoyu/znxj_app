<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function validateForm(){
	        var pwd=$("#pwd").val();
	        //固定电话
	        var reg=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
	        if(!reg.test(pwd)){
	            alert("密码必须包含字母、数字和特殊符号");
	            return false;
	        }else {
	            return true;
	        }
	    }
	</script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 管理员--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updateadmin" method="post" onsubmit="return validateForm()">
                                <input type="hidden" name="id" value="${ad.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="rol">角色:</label>
                                            <select class="form-control" id="rol" style="width: 150px;" name="roleid">
                                                <c:forEach items="${roles}" var="role">
                                                    <option ${ad.roleid eq role.roleid?'selected':''} value="${role.roleid}">${role.rolename}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="site">厂区:</label>
                                            <select name="siteid" id="site" class="form-control">
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option ${ad.siteid eq site.id?'selected':''} value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                                <option ${ad.siteid eq null?'selected':''} value="">所有厂区</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="name2">登录账号:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="name2" name="username" value="${ad.username}" readonly="readonly">
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">登录密码:</label>
                                            <input type="password" class="form-control" style="width: 300px;" id="pwd" name="password" value="${ad.password}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="date">有效期:</label>
                                            <input type="text" onClick="WdatePicker()" class="form-control" style="width: 300px;" id="date" name="expirydate" value="<sdf:formatDate value='${ad.expirydate}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                        </td>
                                        <td class="form-inline">
                                            <span>状态:</span>
                                            <input type="radio" name="state" value="0" ${ad.state eq '0'?'checked':''}>禁用
                                            <input type="radio" name="state" value="1" ${ad.state eq '1'?'checked':''}>启用
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  colspan="2"><input type="submit" class="btn btn-primary" value="修改">
                                            <input type="reset" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <hr>


</div>

</body>
</html>
