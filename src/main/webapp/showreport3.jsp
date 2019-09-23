<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>

</head>
<body>
<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
        <div id="content" class="col-lg-10 col-sm-10">
            <div class="row">
                <div class="box col-md-12">

                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li>
                                <a href="showtaskplan3?page=1&type=3">任务列表</a>
                            </li>
                            <li class="active"><a href="#report" data-toggle="tab" style="background: #35A7E7;">任务报告</a></li>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="report">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i> 计划巡检任务报告 - 列表
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <div class="form-inline" style="margin-bottom: 20px;">
                                        <form action="" method="post">
                                            <label class="control-label" for="name2">任务报告编号：</label>
                                            <input type="text" style="width: 200px;" id="name2" name="taskid" value="${param.taskid}">
                                            <label class="control-label" for="status">状态：</label>
                                            <select class="form-control" id="status" name="reportstate">
                                                <option ${param.reportstate eq '' ? 'selected' : ''} value="">所有</option>
                                                <option ${param.reportstate eq 1 ? 'selected' : ''} value="1">正常</option>
                                                <option ${param.reportstate eq 2 ? 'selected' : ''} value="2">异常</option>
                                            </select>
                                            <label class="control-label" >日期：</label>
                                            <input type="text" name="time1" onClick="WdatePicker()" readonly value="${param.time1}">--<input type="text" name="time2" onClick="WdatePicker()" readonly value="${param.time2}">
                                            <input type="submit" class="btn btn-primary" value="搜索" style="margin-left: 30px;">

                                        </form>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr>
                                            <th>操作</th>
                                            <th>任务号</th>
                                            <th>报告编号</th>
                                            <th>报告完成时间</th>
                                            <th>工人</th>
                                            <th>终端</th>
                                            <th>操作状态</th>
                                            <th>有无异常项</th>
                                            <th>责任人</th>
                                            <th>数据提交时间</th>
                                        </tr>
                                        <c:forEach items="${pageBean.list}" var="report">
                                            <tr>
                                                <td>
                                                    <a href="#"><i class="glyphicon glyphicon-trash"></i></a>
                                                    <a href="#"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                                </td>
                                                <td>${report.taskid}</td>
                                                <td>${report.id}</td>
                                                <td><sdf:formatDate value="${report.donetime}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                                <td>${report.worker}</td>
                                                <td>${report.equip.name}</td>
                                                <td>
                                                    <c:if test="${report.operationstate ==0}">正常</c:if>
                                                    <c:if test="${report.operationstate ==1}">漏检</c:if>
                                                    <c:if test="${report.operationstate ==2}">跳检</c:if>
                                                    <c:if test="${report.operationstate ==3}">未执行</c:if>
                                                    <c:if test="${report.operationstate ==4}">超时</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${report.reportstate ==0}">无</c:if>
                                                    <c:if test="${report.reportstate ==1}">有</c:if>
                                                </td>
                                                <td>${report.user.realname}</td>
                                                <td><sdf:formatDate value="${report.uploadtime}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                        <a href="showallreport?page=1&tasktype=0">第一页</a>
                                        <c:if test="${pageBean.currentPage>1}">
                                            <a href="showallreport?page=${pageBean.currentPage-1}&tasktype=0">上一页</a>
                                        </c:if>

                                        <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                            <a href="showallreport?page=${pageBean.currentPage+1}&tasktype=0">下一页</a>
                                        </c:if>

                                        <a href="showallreport?page=${pageBean.totalPage}"&tasktype=0>最后一页</a>

                                        第${pageBean.currentPage}页/共${pageBean.totalPage}页
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
