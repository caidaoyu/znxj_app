<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <jsp:include page="/WEB-INF/pages/common/header.jsp" />
<script type="text/javascript">
    function deladmin(id,name){
        var isDel=confirm('确定删除吗？');
        if (isDel!=true) {
            return false;
        }else {
            $.ajax({
                url: "deladmin?id=" + id+"&username="+name,
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
        window.location="showadmin?page="+$("#page").val();
    }
</script>
</head>
<body style="overflow-x:hidden;">
<input type="hidden" id ="page" value="${admininfos.pageNum}">
    <div class="row">
       <%-- <jsp:include page="/WEB-INF/pages/common/menu.jsp" />--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <div style="float: left;">
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 管理员列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:admin">
                                <a href="toaddadmin" id="addbutton" class="btn btn-primary" style="margin-top: -6px;">添加管理员</a>
                                </shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">

                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>登录名</th>
                                    <th>角色</th>
                                    <th>厂区</th>
                                    <th>有效期至</th>
                                    <th>创建时间</th>
                                </tr>
                                <c:forEach items="${admininfos.list}" var="a">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:admin"><a href="selectbyid?id=${a.id}&page=${admininfos.pageNum}"> <i class="glyphicon glyphicon-edit red "></i></a></shiro:hasPermission>
                                            <shiro:hasPermission name="del:admin"><a href="javascript:void(0)" onclick="deladmin(${a.id},'${a.username}','${admininfos.pageNum}')"><i class="glyphicon glyphicon-trash"></i></a></shiro:hasPermission>
                                            <a href="selectbyadminid?id=${a.id}"> <i class="glyphicon glyphicon-info-sign blue "></i></a>
                                        </td>
                                        <td>${a.username}</td>
                                        <td>${a.roles.rolename}</td>
                                        <td><c:if test="${a.site.customid==null}">所有厂区</c:if>${a.site.customid}</td>
                                        <td><sdf:formatDate value="${a.expirydate}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                        <td><sdf:formatDate value="${a.createdate}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showadmin?page=1">第一页</a>
                                <c:if test="${admininfos.pageNum>1}">
                                    <a href="showadmin?page=${admininfos.pageNum-1}">上一页</a>
                                </c:if>

                                <c:if test="${admininfos.pageNum<admininfos.pages}">
                                    <a href="showadmin?page=${admininfos.pageNum+1}">下一页</a>
                                </c:if>

                                <a href="showadmin?page=${admininfos.pages}">最后一页</a>

                                第${admininfos.pageNum}页/共${admininfos.pages}页
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--</div>--%>
<script type="text/javascript">

</script>
</body>
</html>
