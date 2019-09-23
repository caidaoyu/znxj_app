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

<%--
        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
--%>
        <div id="content"class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 巡检项--详情
                            </h2>
                        </div>
                        <div class="box-content">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="idtype">编号ID:</label>
                                            <input type="text" class="form-control"  id="idtype" name="customid" value="${checkiteminfo.customid}"readonly="readonly">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="itemname"value="${checkiteminfo.itemname}" readonly="readonly">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">关键点描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="keyword" value="${checkiteminfo.keyword}"readonly="readonly">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">类型:</label>
                                            <c:if test="${checkiteminfo.type==1}"><input type="text" id="pwd2" value="状态项" readonly="readonly" class="form-control"></c:if>
                                            <c:if test="${checkiteminfo.type==2}"><input type="text" id="pwd2" value="记录项" readonly="readonly" class="form-control"></c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="name">可读数据名称:</label>
                                           <input type="text" id="name" value="${checkiteminfo.daterecord.name}" readonly="readonly" class="form-control">
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
