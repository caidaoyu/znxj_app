<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>

</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<%--<!-- topbar ends -->--%>
<div class="ch-container">
    <div class="row">

        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 区域--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updarea" method="post" id="form2">
                                <input type="hidden" name="id" value="${areainfo.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" value="${areainfo.customid}"required="required">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">GPS精度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="longitude" value="${areainfo.longitude}"required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">GPS纬度:</label>
                                            <input type="text" class="form-control" id="pwd3" name="latitude" value="${areainfo.latitude}" required="required" placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd4">GPS覆盖半径:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd4" name="radiusnumber" value="${areainfo.radiusnumber}" required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="area">区域描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="area" name="desccontent" value="${areainfo.desccontent}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc2">NFC标签:</label>

                                            <select  id="nfc2" name="nfctag">
                                                <option value="${areainfo.nfctag}">${areainfo.nfc.customid} ${areainfo.nfc.desccontent}</option>
                                                <c:forEach items="${nfcinfos}" var="nfc">
                                                    <option value="${nfc.id}">${nfc.customid} ${nfc.desccontent}</option>
                                                </c:forEach>
                                                <option ${areainfo.nfctag eq ''?'selected':''} value=""></option>
                                            </select>

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
</div>
</body>
</html>
