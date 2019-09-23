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
                                <i class="glyphicon glyphicon-globe"></i> 用户--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="upduser" method="post">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                       <%-- <td class="form-inline">
                                            <label class="control-label" for="idtype">账号类型:</label>
                                            <c:if test="${userinfo.type==0}">
                                                <input type="text" name="type" class="form-control" value="操作员" id="idtype" readonly>
                                            </c:if>
                                            <c:if test="${userinfo.type==1}">
                                                <input type="text" name="type" class="form-control" value="监督员" id="idtype" readonly>
                                            </c:if>
                                            <c:if test="${userinfo.type==2}">
                                                <input type="text" name="type" class="form-control" value="现场经理" id="idtype" readonly>
                                            </c:if>
                                        </td>--%>
                                        <td class="form-inline">
                                            <label class="control-label" for="classname">班组:</label>
                                            <input type="text" class="form-control" id="classname" value="${userinfo.group.customid}" readonly>
                                        </td>
                                        <td class="form-inline" colspan="2">
                                               <label class="control-label" for="pwd6">所属岗位:</label>
                                               <input type="text" id="pwd6" class="form-control" value="${userinfo.pos.positionname}" readonly>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">登录名:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="username" value="${userinfo.username}" readonly>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">登录密码:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="password" value="${userinfo.password}" readonly>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">真实姓名:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="realname" value="${userinfo.realname}" readonly>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">性别:</label>
                                            <c:if test="${userinfo.sex==1}"><input type="text" id="pwd3" value="男" class="form-control" readonly></c:if>
                                            <c:if test="${userinfo.sex==0}"><input type="text" id="pwd3" value="女" class="form-control" readonly></c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd4">生日:</label>
                                            <input type="text" class="form-control" readonly style="width: 300px;" id="pwd4"  name="birthdate"  value="<sdf:formatDate value='${userinfo.birthdate}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd7">常用终端:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd7"
                                                   name="commonterminalid"  value="${userinfo.ter.customid}" readonly>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd8">账号状态:</label>
                                            <c:if test="${userinfo.state==1}"><input type="text" id="pwd8" class="form-control"  value="正常" readonly></c:if>
                                            <c:if test="${userinfo.state==0}"><input type="text" id="pwd8" class="form-control" value="停用" readonly></c:if>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd9">当前状态:</label>
                                            <c:if test="${userinfo.userstate==1}"><input type="text" id="pwd9" class="form-control" value="在线" readonly></c:if>
                                            <c:if test="${userinfo.userstate==0}"><input type="text" id="pwd9" class="form-control" value="离线" readonly></c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
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
