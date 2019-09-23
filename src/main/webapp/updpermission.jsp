<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2017/5/4
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
</head>
<body>
<%--<form action="" method="post">
    <table class="">
        <tr>
            <td style="display: none"><input type="hidden" value="${roles.roleid}" name="roleId" /></td>
            <th>角色名</th>
            <td>${roles.rolename}&nbsp;</td>
            <th>状态</th>
            <td><c:choose>
                <c:when test="${roles.rolestate==0 }">
                    正常
                </c:when>
                <c:when test="${roles.rolestate==1 }">
                    不正常
                </c:when>
            </c:choose></td>
        </tr>
    </table>
    <table>
        <tr>
            <td><b style="font-size: 20px;">&nbsp;&nbsp;选择权限</b></td>
        </tr>
        <tr>
            <td class="input_content" colspan="10">
                <c:if test="${not empty(allpermission)}">
                    <c:forEach var="permissions" items="${allpermission}">
                        <div>${permissions.permissionname }
                            <input id="${permissions.permissionid}" name="selectedpowers"
                                type="checkbox"  value="${permissions.permissionid }"
                                    <c:forEach var="permission" items="${permissionlist }">
                                        <c:if test="${permission.permissionid eq permissions.permissionid }">checked="checked"
                                        </c:if>
                                    </c:forEach>/>
                            <br />
                        </div>
                    </c:forEach>
                </c:if>
            </td>
        </tr>
    </table>
    <input type="submit" value="确定" class="btn btn-primary"/>
</form>--%>


<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
        <div id="content" class="col-lg-10 col-sm-10">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2><i class="glyphicon glyphicon-globe"></i> 选择权限</h2>
                        </div>
                        <div class="box-content">
                            <form action="addpermission" method="post">
                                <table class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="display: none"><input type="hidden" value="${roles.roleid}" name="roleid" /></td>
                                        <th>角色名</th>
                                        <td>${roles.rolename}&nbsp;</td>
                                        <th>状态</th>
                                        <td><c:choose>
                                            <c:when test="${roles.rolestate==0 }">
                                                正常
                                            </c:when>
                                            <c:when test="${roles.rolestate==1 }">
                                                不正常
                                            </c:when>
                                        </c:choose></td>
                                    </tr>
                                </table>
                                <table class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td>选择权限</td>
                                    </tr>
                                    <tr>
                                        <td class="input_content" colspan="10">
                                            <c:if test="${not empty(allpermission)}">
                                                <c:forEach var="permissions" items="${allpermission}">
                                                    <div>${permissions.permissionname }
                                                        <input id="${permissions.permissionid}" name="selectedpowers"
                                                            type="checkbox"  value="${permissions.permissionid }"
                                                                <c:forEach var="permission" items="${permissionlist }">
                                                                    <c:if test="${permission.permissionid eq permissions.permissionid }">checked="checked"
                                                                    </c:if>
                                                                </c:forEach>/>
                                                        <br />
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                    </tr>
                                </table>
                                <input type="submit" value="确定" class="btn btn-primary"/>
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
