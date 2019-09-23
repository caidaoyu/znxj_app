<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
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
                                <i class="glyphicon glyphicon-globe"></i> 管理员--详情
                            </h2>
                        </div>
                        <div class="box-content">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="name2">登录账号:</label>
                                            <input type="text"  class="form-control" style="width: 300px;" readonly="readonly" id="name2" name="username" value="${adm.username}" readonly="readonly">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">登录密码:</label>
                                            <input type="text" class="form-control" style="width: 300px;" readonly="readonly" id="pwd" name="password" value="${adm.password}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="date">有效期:</label>
                                            <input type="text" onClick="WdatePicker()" readonly="readonly" class="form-control" style="width: 300px;" id="date" name="expirydate" value="<sdf:formatDate value='${adm.expirydate}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="reset" class="btn btn-primary white" value="返回" onclick="javascript:history.go(-1);"></td>
                                    </tr>
                                </table>
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
