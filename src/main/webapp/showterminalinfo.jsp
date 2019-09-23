<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delterminal(id,tername){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delterminal?id=" + id+"&tername="+tername,
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
            window.location="showallterm?page="+$("#page").val();
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
                                    <i class="glyphicon glyphicon-globe"></i> 智能终端管理列表
                                </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                                <%--<shiro:hasPermission name="add:ter">--%>
                                    <%--<a href="addterminalinfo.jsp" id="button" class="btn btn-primary" style="margin-top: -6px;">添加智能终端</a>--%>
                                <%--</shiro:hasPermission>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="" method="post">
                                    <label class="control-label" for="name2">名称：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2" name="name" value="${param.name}">
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:ter">
                                        <a href="addterminalinfo.jsp" id="button" class="btn btn-primary" style="margin-left:250px;">添加智能终端</a>
                                    </shiro:hasPermission>

                                </form>
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                            <tr style="text-align: center">
                                <th>操作</th>
                                <th>名称</th>
                                <th>硬件类型</th>
                                <th>软件版本</th>
                                <th>描述</th>
                                <th>单位/部门</th>
                                <th>授权码</th>
                                <th>到期时间</th>
                                <th>状态</th>
                            </tr>
                            <c:forEach items="${pageBean.list}" var="term">
                                <tr>
                                    <td>
                                        <shiro:hasPermission name="upd:ter">
                                            <a href="querybytermid?id=${term.id}&page=${pageBean.currentPage}"><i class="glyphicon glyphicon-edit red "></i></a>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="del:ter">
                                            <a href="javascript:void(0)" onclick="delterminal(${term.id},'${term.customid}')"><i class="glyphicon glyphicon-trash"></i></a>
                                        </shiro:hasPermission>
                                        <a href="querytermdetail?id=${term.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                    </td>
                                    <td>${term.customid}</td>
                                    <td>${term.hardwaremodel}</td>
                                    <td>${term.softversion}</td>
                                    <td>${term.desccontent}</td>
                                    <td>${term.department}</td>
                                    <td>${term.authcode}</td>
                                    <td>
                                        <sdf:formatDate value="${term.unenabletime}" pattern="yyyy-MM-dd"></sdf:formatDate>
                                    </td>
                                    <td>
                                        <c:if test="${term.state==0}">离线</c:if>
                                        <c:if test="${term.state==1}">在用</c:if>
                                        <c:if test="${term.state==2}">故障</c:if>
                                        <c:if test="${term.state==3}">丢失</c:if>
                                        <c:if test="${term.state==4}">更换</c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                            <a href="showallterm?page=1">第一页</a>
                            <c:if test="${pageBean.currentPage>1}">
                                <a href="showallterm?page=${pageBean.currentPage-1}">上一页</a>
                            </c:if>

                            <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                <a href="showallterm?page=${pageBean.currentPage+1}">下一页</a>
                            </c:if>

                            <a href="showallterm?page=${pageBean.totalPage}">最后一页</a>

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
