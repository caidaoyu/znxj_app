<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function selectallrole(obj,checkboxname){
            if($(obj).prop("checked")){
                $("input:checkbox[name='"+checkboxname+"']").each(function() {
                    $(this).prop("checked",true);
                });
            }else{
                $("input:checkbox[name='"+checkboxname+"']").each(function() {
                    $(this).prop("checked",false);
                });
            }
        }
    </script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
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
                                <i class="glyphicon glyphicon-globe"></i> 角色--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updroles" method="post">
                                <input type="hidden" class="form-control" style="width: 300px;" name="roleid" required="required" value="${roles.roleid}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="rolename">角色名称:</label>
                                            <input type="text" name="rolename" class="form-control" id="rolename" value="${roles.rolename}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <span>角色状态:</span>
                                            <input type="radio" name="rolestate" ${roles.rolestate eq "0"?'checked':''} value="0" >可用
                                            <input type="radio" name="rolestate" ${roles.rolestate eq "1"?'checked':''} value="1" >不可用
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>选择权限:<input type="checkbox" name="role" id="role" onclick="selectallrole(this,'selectedpowers')" style="width: 16px;height: 16px;"> 全选/全不选</h4><br/>
                                            <c:if test="${not empty(allpermission)}">
                                                <c:forEach var="permissions" items="${allpermission}" varStatus="status">
                                                    <span style="text-align: left;display:inline-block; width:200px;height:30px;">
                                                        <input id="${permissions.permissionid}" name="selectedpowers"
                                                               type="checkbox"  value="${permissions.permissionid }" style="width: 16px;height: 16px;"
                                                                <c:forEach var="permission" items="${permissionlist }">
                                                                    <c:if test="${permission.permissionid eq permissions.permissionid }">checked="checked"
                                                                    </c:if>
                                                                </c:forEach>/>${permissions.permissionname }
                                                    </span>
                                                    <c:if test="${status.count%6==0}">
                                                    <br>
                                                </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2"><input type="submit" class="btn btn-primary" value="保存">
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





<tr>

    <c:forEach items="${listBabys}" var="itemx"  varStatus="status">

    <td>

        <a href="javascript:recselectAct(${itemx.id});">${itemx.babyname}</a>
    </td>
    <c:if test="${status.count%6==0}">
</tr>
<tr>
    </c:if>

    </c:forEach>
</tr>
</body>
</html>
