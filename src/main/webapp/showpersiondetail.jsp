<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <i class="glyphicon glyphicon-globe"></i> 联系人管理--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="role1">角色:</label>
                                           <c:if test='${contactinfo.roletype==0}'><input type="text" value="员工"class="form-control" id="role1"readonly="readonly"></c:if>
                                               <c:if test='${contactinfo.roletype==1}'><input type="text" value="经理"class="form-control" id="role1"readonly="readonly"></c:if>
                                                <c:if test='${contactinfo.roletype==2}'><input type="text" value="主任"class="form-control" id="role1"readonly="readonly"></c:if>
                                                <c:if test='${contactinfo.roletype==3}'><input type="text" value="处长"class="form-control" id="role1"readonly="readonly"></c:if>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd">姓名:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="pwd"name="name" value="${contactinfo.name}" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="tel">固定电话:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="tel"name="phone" value="${contactinfo.phone}" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="tel2">移动电话:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="tel2"name="mobilephone"value="${contactinfo.mobilephone}" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="tel3">电子邮件:</label>
                                        <input type="text" readonly="readonly" class="form-control" style="width: 300px;" id="tel3"name="email"value="${contactinfo.email}">
                                    </td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<td class="form-inline">--%>
                                        <%--<label class="control-label" for="tel4">群组邮件:</label>--%>
                                        <%--<c:if test="${contactinfo.groupid==0}"><input type="text" value="任务简报群组"class="form-control" id="tel4" readonly="readonly"></c:if>--%>
                                        <%--<c:if test="${contactinfo.groupid==1}"><input type="text" value="任务日报群组"class="form-control" id="tel4"readonly="readonly"></c:if>--%>

                                    <%--</td>--%>
                                <%--</tr>--%>
                                <tr>
                                    <td>
                                        <input type="button" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

