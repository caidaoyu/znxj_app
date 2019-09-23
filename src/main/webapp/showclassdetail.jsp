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

<%--
        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 班组管理--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="idc">编组编号:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="idc" name="customid" value="${detailclass.customid}" readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="username">班组描述:</label>
                                        <textarea type="text" class="form-control" rows="3" cols="35" id="username" name="classdesc" readonly="readonly">${detailclass.classdesc}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd">上岗时间:</label>
                                        <input type="text" class="form-control" style="width: 300px;" id="pwd" name="workstarttime" value="<sdf:formatDate value='${detailclass.workstarttime}' pattern='yyyy-MM-dd'></sdf:formatDate>" readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd2">下岗时间:</label>
                                        <input type="text" class="form-control" style="width: 300px;" name="workendtime" id="pwd2" value=" <sdf:formatDate value='${detailclass.workendtime}' pattern='yyyy-MM-dd'></sdf:formatDate>"  readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd3">班组负责人:</label>
                                        <input type="text" class="form-control" style="width: 300px;" name="workendtime" id="pwd3" value="${detailclass.userinfo.realname}"  readonly="readonly">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-inline">
                                        <label class="control-label" for="pwd4">所属厂区:</label>
                                        <input type="text" class="form-control" style="width: 300px;" name="workendtime" id="pwd4" value="${detailclass.site.customid}"  readonly="readonly">
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

