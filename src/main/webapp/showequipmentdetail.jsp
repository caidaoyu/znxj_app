<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
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
                                <i class="glyphicon glyphicon-globe"></i> 设备--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="username">编号ID:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" value="${equipmentinfo.customid}" readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd">名称:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="pwd" name="name" value="${equipmentinfo.name}"readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd1">类型:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="pwd1" name="type" value="${equipmentinfo.type}"readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd2">GPS精度:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="longitude" value="${equipmentinfo.longitude}"readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd3">GPS纬度:</label>
                                        <input type="text" class="form-control" style="width: 300px;"  id="pwd3" name="latitude" value="${equipmentinfo.latitude}"readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="desccontent">设备描述:</label>
                                        <textarea rows="3" cols="80" name="desccontent"id="desccontent" class="form-control" readonly="readonly">${equipmentinfo.desccontent}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="nfc2">NFC标签:</label>
                                        <c:if test="${equipmentinfo.nfc.unitcode ==null}">暂无NFC标签</c:if>
                                        <c:if test="${equipmentinfo.nfc.unitcode !=null}">
                                            <input type="text" id="nfc2" value="${equipmentinfo.nfc.unitcode}" readonly="readonly">
                                        </c:if>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="area1">所属区域:</label>
                                        <c:if test="${equipmentinfo.areainfo.customid ==null}">暂无所属区域</c:if>
                                        <c:if test="${equipmentinfo.areainfo.customid !=null}">
                                            <input type="text" id="nfc2" id="area1" value="${equipmentinfo.areainfo.customid}" readonly="readonly">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="reset" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
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
