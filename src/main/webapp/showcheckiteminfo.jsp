<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp" %>
    <script type="text/javascript">
        function delcheck(id, name) {
            $.ajax({
                url: 'selectUsrCheckItem?id=' + id ,
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data > 0) {
                        alert("有" + data + "个任务与该巡检项关联，不能删除此巡检项.");
                    } else {
                        var isDel = confirm('确定删除吗？');
                        if (isDel != true) {
                            return false;
                        } else {
                            $.ajax({
                                url: "delcheck?id=" + id + "&checkname=" + name,
                                type: "post",
                                datatype: "json",
                                success: function (data) {
                                    debugger;
                                    if (data == 1) {
                                        alert("删除成功");
                                        showtask();
                                    } else if (data == 0) {
                                        alert("删除失败");
                                        return false;
                                    } else if (data == 2) {  //2018年7月16日16:46:27  新加
                                        alert("删除失败，该巡检项已被使用，不能删除");
                                        return false;
                                    }
                                }
                            });
                        }
                    }
                }
            });

        }
        function showtask() {
            window.location = "showallcheck?page=" + $("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container">
    <div class="row">
        <%-- <%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <%--<div style="float: left;">--%>
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 巡检项管理列表
                            </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                            <%--<shiro:hasPermission name="add:check">--%>
                            <%--<a href="toaddcheck" id="button" class="btn btn-primary" style="margin-top: -6px;">添加巡检项</a>--%>
                            <%--</shiro:hasPermission>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="showallcheck?page=1" method="post">
                                    <label class="control-label" for="name2">名称：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2"
                                           name="itemname" value="${param.itemname}">
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:check">
                                        <a href="toaddcheck" id="button" class="btn btn-primary"
                                           style="margin-left:250px;">添加巡检项</a>
                                    </shiro:hasPermission>
                                </form>
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                            <tr style="text-align: center">
                                <th>操作</th>
                                <th>编号</th>
                                <th>名称</th>
                                <th>关键点描述</th>
                                <th>类型</th>
                                <th>名称</th>
                                <th>可读数据名称</th>
                                <%-- <th>正常低值</th>
                                 <th>正常高值</th>
                                 <th>上限警告值</th>
                                 <th>下限警告值</th>--%>
                            </tr>
                            <c:forEach items="${pageBean.list}" var="check">
                                <tr>
                                    <td>
                                        <shiro:hasPermission name="upd:check">
                                            <a href="querybycheckid?id=${check.id}&page=${pageBean.currentPage}&type=${check.type}"><i
                                                    class="glyphicon glyphicon-edit red "></i></a>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="del:check">
                                            <a href="javascript:void(0)"
                                               onclick="delcheck(${check.id},'${check.customid}')"><i
                                                    class="glyphicon glyphicon-trash"></i></a>
                                        </shiro:hasPermission>
                                            <%--<shiro:hasPermission name="item:checkdetail">--%>
                                        <a href="querycheckdetail?id=${check.id}"><i
                                                class="glyphicon glyphicon-info-sign blue "></i></a>
                                            <%--</shiro:hasPermission>--%>
                                    </td>
                                    <td>${check.customid}</td>
                                    <td>${check.itemname}</td>
                                    <td>${check.keyword}</td>
                                    <td>
                                        <c:if test="${check.type==1}">状态项</c:if>
                                        <c:if test="${check.type==2}">记录项</c:if>
                                        <c:if test="${check.type==3}">枚举项</c:if>
                                    </td>
                                    <td>${check.daterecord.name}</td>
                                    <td>
                                        <c:if test="${check.type==2}">${check.daterecord.unitname}</c:if>
                                        <c:if test="${check.type==3}">${check.daterecord.state}</c:if>
                                    </td>

                                        <%--  <td>${check.normalmin}</td>
                                          <td>${check.normalmax}</td>
                                          <td>${check.upperwarning}</td>
                                          <td>${check.lowerwarning}</td>--%>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                            <a href="showallcheck?page=1&itemname=${itemname}">第一页</a>
                            <c:if test="${pageBean.currentPage>1}">
                                <a href="showallcheck?page=${pageBean.currentPage-1}&itemname=${itemname}">上一页</a>
                            </c:if>

                            <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                <a href="showallcheck?page=${pageBean.currentPage+1}&itemname=${itemname}">下一页</a>
                            </c:if>

                            <a href="showallcheck?page=${pageBean.totalPage}&itemname=${itemname}">最后一页</a>

                            第${pageBean.currentPage}页/共${pageBean.totalPage}页
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
