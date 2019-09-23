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
                                <i class="glyphicon glyphicon-globe"></i> NFC标签--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updatenfc" method="post">
                                <input type="hidden" name="id" value="${nfcinfo.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" value="${nfcinfo.customid}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc">NFC串码:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="nfc" name="unitcode" value="${nfcinfo.unitcode}" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc1">GPS经度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="nfc1" name="longitude" value="${nfcinfo.longitude}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">GPS纬度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="latitude" value="${nfcinfo.latitude}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="area">标签描述:</label>
                                            <textarea class="form-control" id="area" name="desccontent" rows="5" cols="50"> ${nfcinfo.desccontent}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="date">启用时间:</label>
                                            <input type="text" onClick="WdatePicker()" class="form-control" style="width: 300px;" id="date" name="enabletime" value="<sdf:formatDate value='${nfcinfo.enabletime}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="status">状态:</label>
                                            <select id="status" name="state">
                                                <option ${nfcinfo.state eq 0 ? 'selected' : ''} value="0">失效</option>
                                                <option ${nfcinfo.state eq 1 ? 'selected' : ''}  value="1">正常</option>
                                                <option ${nfcinfo.state eq 2 ? 'selected' : ''}  value="2">遗失</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="remark">备注:</label>
                                            <textarea class="form-control" id="remark" name="remark" rows="5" cols="50"> ${nfcinfo.remark}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" class="btn btn-primary" value="修改">
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
