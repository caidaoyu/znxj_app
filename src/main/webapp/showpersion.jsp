<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delcont(id,contname){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delcont?id=" + id+"&contname="+contname,
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
            window.location="showallcont?page="+$("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container">
    <div class="row">

        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <%--<div style="float: left;">--%>
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 联系人管理--列表
                                </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                                <%--<shiro:hasPermission name="add:contact">--%>
                                    <%--<a href="addpersion.jsp" id="button" class="btn btn-primary" style="margin-top: -6px;">添加联系人</a>--%>
                                <%--</shiro:hasPermission>--%>
                                    <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="" method="post">
                                    <label class="control-label" for="name2">姓名：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2" name="name" value="${param.name}">
                                    <label class="control-label" for="status">角色：</label>
                                    <select class="form-control" id="status" name="roletype">
                                        <option ${roletype eq '' ? 'selected' : ''} value="">所有</option>
                                        <option ${roletype eq '0' ? 'selected' : ''} value="0">处长</option>
                                        <option ${roletype eq '1' ? 'selected' : ''} value="1">主任</option>
                                        <option ${roletype eq '2' ? 'selected' : ''} value="2">经理</option>
                                        <option ${roletype eq '3' ? 'selected' : ''} value="3">员工</option>

                                    </select>
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:contact">
                                        <a href="addpersion.jsp" id="button" class="btn btn-primary" style="margin-left:250px;">添加联系人</a>
                                    </shiro:hasPermission>
                                </form>

                            </div>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <th>操作</th>
                                    <th>角色</th>
                                    <th>姓名</th>
                                    <th>固定电话</th>
                                    <th>移动电话</th>
                                    <th>电子邮件</th>
                                    <%--<th>邮件群组</th>--%>
                                </tr>
                                <c:forEach items="${pageBean.list}" var="cont">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:contact">
                                                <a href="queryById?id=${cont.id}&page=${pageBean.currentPage}"><i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:contact">
                                                <a href="javascript:void(0)" onclick="delcont(${cont.id},'${cont.name}')"><i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                            <%--<shiro:hasPermission name="item:contactdetail">--%>
                                                <a href="queryBycontId?id=${cont.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                            <%--</shiro:hasPermission>--%>
                                        </td>
                                        <td>
                                            <c:if test="${cont.roletype==0}">处长</c:if>
                                            <c:if test="${cont.roletype==1}">主任</c:if>
                                            <c:if test="${cont.roletype==2}">经理</c:if>
                                            <c:if test="${cont.roletype==3}">员工</c:if>
                                        </td>
                                        <td>${cont.name}</td>
                                        <td>${cont.phone}</td>
                                        <td>${cont.mobilephone}</td>
                                        <td>${cont.email}</td>
                                        <%--<td>--%>
                                            <%--<c:if test="${cont.groupid.indexOf('0')!=-1}">任务简报群组</c:if>--%>
                                            <%--<c:if test="${cont.groupid.indexOf('1')!=-1}">任务日报群组</c:if>--%>
                                            <%--<c:if test="${cont.groupid.indexOf('2')!=-1}">任务日报群组</c:if>--%>
                                        <%--</td>--%>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showallcont?page=1&roletype=${roletype}&name=${name}">第一页</a>
                                <c:if test="${pageBean.currentPage>1}">
                                    <a href="showallcont?page=${pageBean.currentPage-1}&roletype=${roletype}&name=${name}">上一页</a>
                                </c:if>

                                <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                    <a href="showallcont?page=${pageBean.currentPage+1}&roletype=${roletype}&name=${name}">下一页</a>
                                </c:if>

                                <a href="showallcont?page=${pageBean.totalPage}&roletype=${roletype}&name=${name}">最后一页</a>

                                第${pageBean.currentPage}页/共${pageBean.totalPage}页
                            </div>
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
