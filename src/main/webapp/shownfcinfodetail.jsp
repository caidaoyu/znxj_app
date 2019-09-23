<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <i class="glyphicon glyphicon-globe"></i> NFC标签--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="username">编号ID:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" value="${nfcinfo.customid}" readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="nfc">NFC串码:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="nfc" name="unitcode" value="${nfcinfo.unitcode}" readonly="readonly">
                                    </td>
                                </tr>

                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="nfc1">GPS经度:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="nfc1" name="longitude" value="${nfcinfo.longitude}" readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd2">GPS纬度:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="latitude" value="${nfcinfo.latitude}" readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="area">标签描述:</label>
                                        <textarea class="form-control" id="area" name="desccontent" rows="5" cols="50" readonly="readonly"> ${nfcinfo.desccontent}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="date">启用时间:</label>
                                        <input type="text" readonly="readonly" class="form-control" style="width: 300px;" id="date" name="enabletime" value="<sdf:formatDate value='${nfcinfo.enabletime}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                        </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="status">状态:</label>
                                        <c:if test="${nfcinfo.state==0}"><input type="text" value="失效"class="form-control" id="status"readonly="readonly"></c:if>
                                        <c:if test="${nfcinfo.state==1}"><input type="text" value="正常"class="form-control" id="status"readonly="readonly"></c:if>
                                        <c:if test="${nfcinfo.state==2}"><input type="text" value="遗失"class="form-control" id="status"readonly="readonly"></c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="remark">备注:</label>
                                        <textarea class="form-control" id="remark" name="remark" rows="5" cols="50" readonly="readonly"> ${nfcinfo.remark}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="reset" class="btn btn-primary white" value="返回" onclick="javascript:history.go(-1);"></td></tr>
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
