<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script>
        function dellog(id){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "dellog?id=" + id,
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
            window.location="showlog?page="+$("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" value="${pageBean.pageNum}" id="page">
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 操作日志列表
                            </h2>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="showlog?page=1" method="post">
                                    <label class="control-label" for="name2">操作人：</label>
                                    <input type="text" style="width: 200px;" id="name2" name="username" value="${param.username}">
                                    <label class="control-label" >操作时间：</label>
                                    <input type="text" name="time" onClick="WdatePicker()" readonly value="${param.time}">
                                    <input type="submit" class="btn btn-primary" value="搜索" style="margin-left: 30px;">
                                </form>
                            </div>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>操作人</th>
                                    <th>操作时间</th>
                                    <th>操作内容</th>
                                </tr>
                                <c:forEach items="${pageBean.list}" var="logs">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="del:operatelog">
                                                <a href="#" onclick="dellog(${logs.logid})"> <i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                        </td>
                                        <td>${logs.username}</td>
                                            <td><sdf:formatDate value="${logs.time}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>
                                        <td>${logs.operate}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;margin-left: 300px;">
                                <a href="/showlog?page=1&username=${uname}&time=${time}">第一页</a>
                                <c:if test="${pageBean.pageNum>1}">
                                    <a href="showlog?page=${pageBean.pageNum-1}&username=${uname}&time=${time}">上一页</a>
                                </c:if>

                                <c:if test="${pageBean.pageNum<pageBean.pages}">
                                    <a href="showlog?page=${pageBean.pageNum+1}&username=${uname}&time=${time}">下一页</a>
                                </c:if>

                                <a href="showlog?page=${pageBean.pages}&username=${uname}&time=${time}">最后一页</a>

                                第${pageBean.pageNum}页/共${pageBean.pages}页
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
