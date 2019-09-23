<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delbytempid(tempid){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "deltemp?tempid=" + tempid,
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
            var tasktype=$("#tasktype").val();
            window.location="showstoptask?page=1&state=3&type="+tasktype;
        }
    </script>
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
                    <input type="hidden" value="${type}" id="tasktype">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li>
                                <a href="showtaskplan?page=1&type=${type}">任务列表</a>
                            </li>
                            <li class="active"><a href="#stop" style="background: #35A7E7;color: white;"  data-toggle="tab">终止任务</a></li>
                            <li ><a href="showallreport1?page=1&tasktype=${type}" >任务报告</a></li>
                           <c:if test="${type==2}">
                                <li><a href="showQuickReport1?page=1&type=${type}&qtype=1">立即上报</a></li>
                            </c:if>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="stop">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i>终止任务 - 列表
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr>
                                            <th>任务号</th>
                                            <th>报告编号</th>
                                            <th>任务状态</th>
                                            <th>终止原因</th>
                                            <th>终止描述</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach items="${info.list}" var="stop1">
                                            <tr>
                                                <td>${fn:substring(stop1.tem.taskcode,0,stop1.tem.taskcode.indexOf('-'))}</td>
                                                <td>${stop1.tem.taskcode}</td>
                                                <td>
                                                    <c:if test="${stop1.tem.state ==3}">已终止</c:if>
                                                </td>
                                                <td>${stop1.reason}</td>
                                                <td>${stop1.content}</td>
                                                <td>
                                                    <%--<shiro:hasPermission name="del:quick">--%>
                                                        <a class="btn btn-danger" href="javascript:void(0)" onclick="delbytempid(${stop1.tasktempid})">
                                                            <i class="glyphicon glyphicon-trash icon-white"></i>删除
                                                        </a>
                                                    <%--</shiro:hasPermission>--%>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                   <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                        <a href="showstoptask?page=1&state=3&type=${type}">第一页</a>
                                        <c:if test="${info.pageNum>1}">
                                            <a href="showstoptask?page=${info.pageNum-1}&state=3&type=${type}">上一页</a>
                                        </c:if>

                                        <c:if test="${info.pageNum<info.pages}">
                                            <a href="showstoptask?page=${info.pageNum+1}&state=3&type=${type}">下一页</a>
                                        </c:if>

                                        <a href="showstoptask?page=${info.pages}&state=3&type=${type}">最后一页</a>
                                        第${info.pageNum}页/共${info.pages}页

                                    </div>
                                </div>
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
