<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <%--<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>--%>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content"class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 智能终端--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="username">终端名称:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" required="required" value="${terminalinfo.customid}" readonly="readonly">
                                    </td>
                                    <td class="form-inline">
                                        <label class="control-label" for="nfc">硬件型号:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="nfc" name="hardwaremodel" required="required"value="${terminalinfo.hardwaremodel}"readonly="readonly">
                                    </td>
                                </tr>

                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="nfc1">软件版本号:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="nfc1" name="softversion" required="required"value="${terminalinfo.softversion}"readonly="readonly">
                                    </td>
                                    <td class="form-inline">
                                        <label class="control-label" for="area">所数单位/部门:</label>
                                        <input type="text" name="department" id="area" required="required" class="form-control"value="${terminalinfo.department}"readonly="readonly">
                                    </td>
                                </tr>


                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="date">终端授权码:</label>
                                        <input type="text" class="form-control" style="width: 300px;" required="required" id="date" name="authcode" value="${terminalinfo.authcode}"readonly="readonly">
                                    </td>
                                    <td class="form-inline">
                                        <label class="control-label" for="status">状态:</label>
                                        <c:if test="${terminalinfo.state ==0}"><input type="text" class="form-control" id="status" value="离线" readonly="readonly"></c:if>
                                        <c:if test="${terminalinfo.state ==1}"><input type="text" class="form-control" id="status" value="在用" readonly="readonly"></c:if>
                                        <c:if test="${terminalinfo.state ==2}"><input type="text" class="form-control" id="status" value="故障" readonly="readonly"></c:if>
                                        <c:if test="${terminalinfo.state ==3}"><input type="text" class="form-control" id="status" value="丢失" readonly="readonly"></c:if>
                                        <c:if test="${terminalinfo.state ==4}"><input type="text" class="form-control" id="status" value="更换" readonly="readonly"></c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="date1">启用时间:</label>
                                        <input type="text" class="form-control" style="width: 300px;"
                                               value="<sdf:formatDate value='${terminalinfo.enabletime}' pattern='yyyy-MM-dd'></sdf:formatDate>"
                                               id="date1" name="enabletime" onClick="WdatePicker()" readonly="readonly">
                                    </td>
                                    <td class="form-inline">
                                        <label class="control-label" for="date2">到期时间:</label>
                                        <input type="text" class="form-control" style="width: 300px;"
                                               id="date2" name="unenabletime" onClick="WdatePicker()"
                                               readonly="readonly" value="<sdf:formatDate value='${terminalinfo.unenabletime}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline" colspan="2">
                                        <label class="control-label" for="pwd2">终端描述:</label>
                                        <textarea class="form-control" cols="100" rows="5" id="pwd2" name="desccontent" readonly="readonly">${terminalinfo.desccontent}</textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <td  colspan="2">
                                        <input type="reset" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
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
