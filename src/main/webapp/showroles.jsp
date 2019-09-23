<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
<script type="text/javascript">
    function admincount(id,rname){
        $.ajax({
            url: "admincount?roleid=" + id,
            type: "post",
            datatype: "json",
            success: function (data) {
                if(data>0){
                    var isDel=confirm("有"+data+"个用户在使用该角色，暂时不能删除？");
                    if (isDel!=true) {
                        return false;
                        showtask();
                    }else {
                        showtask();
                    }
                }else{
                    delrole(id,rname);
                }
            }
        });
    }

    function delrole(id,rname){
        var isDel=confirm('确定删除吗？');
        if (isDel!=true) {
            return false;
        }else {
            $.ajax({
                url: "delrole?id=" + id+"&rname="+rname,
                type: "post",
                datatype: "json",
                success: function (data) {
                    if (data > 0) {
                        alert("删除成功");
                        showtask();
                    } else {
                        alert("删除失败");
                        return false;
                    }
                }
            });
        }
    }
    function showtask(){
        window.location="showroles?page="+$("#page").val();
    }
</script>
</head>
<body>
<input type="hidden" id="page" value="${res.pageNum}">
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <div style="float: left;">
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 角色管理列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:roles">
                                    <a href="toaddroles" id="button" class="btn btn-primary" style="margin-top: -6px;">添加角色</a>
                                </shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">

                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>角色名称</th>
                                    <th>拥有的权限</th>
                                    <th>角色状态</th>
                                    <%--<th>权限</th>--%>
                                </tr>
                                <c:forEach items="${res.list}" var="roleres" varStatus="status">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:roles">
                                            <a href="getrolebyroleid?roleid=${roleres.roles.roleid}&page=${res.pageNum}"> <i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:roles">
                                            <a href="javascript:void(0)" onclick="admincount(${roleres.roles.roleid},'${roleres.roles.rolename}')"><i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                        </td>
                                        <td>${roleres.roles.rolename}</td>
                                        <td style="width: 900px;">${roleres.permissionstr}
                                        </td>
                                        <td>
                                            <c:if test="${roleres.roles.rolestate==0}">可用</c:if>
                                            <c:if test="${roleres.roles.rolestate==1}">不可用</c:if>
                                        </td>
                                        <%--<td><a href="showpromiss?roleid=${roleres.roles.roleid}">选择权限</a></td>--%>
                                    </tr>
                                   <%-- <c:if test="${status.count%8==0}">
                                        <br>
                                    </c:if>--%>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showadmin?page=1">第一页</a>
                                <c:if test="${res.pageNum>1}">
                                    <a href="showroles?page=${res.pageNum-1}">上一页</a>
                                </c:if>

                                <c:if test="${res.pageNum<res.pages}">
                                    <a href="showroles?page=${res.pageNum+1}">下一页</a>
                                </c:if>

                                <a href="showroles?page=${res.pages}">最后一页</a>

                                第${res.pageNum}页/共${res.pages}页
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
