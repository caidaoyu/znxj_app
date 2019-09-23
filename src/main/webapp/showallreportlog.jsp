<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function dellog(id){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "deltasklog?id=" + id,
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
            window.location="gettaskreportlog?page="+$("#page").val();
        }
        function getreportlog(id){
            $("#reportlog").empty();
            $.ajax({
                url: "getreportlog?id=" + id,
                type: "post",
                datatype: "json",
                success: function (data) {
                    if (data.logcat==null) {
                        alert("该报告暂时没有任务日志");
                        return false;
                    } else {
                        var logcat=JSON.parse(data.logcat);
                        for(var i=0;i<logcat.length;i++){
                            $("#reportlog").append("<p>"+logcat[i]+"</p>");
                        }
                        $("#myModal").modal("show");
                    }
                }
            })

        }
    </script>
</head>
<body>
<input type="hidden" value="${allreport.pageNum}" id="page">
<div class="ch-container">
    <div class="row">
    <%--    <%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">

                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 任务日志列表
                                </h2>
                        </div>
                        <div class="box-content">
                            <form action="gettaskreportlog?page=1" method="post">
                                <label class="control-label" for="name2">任务名：</label>
                                <input type="text" style="width: 200px;" id="name2" name="taskname" value="${param.taskname}">
                                <label class="control-label" >任务完成时间：</label>
                                <input type="text" name="time" onClick="WdatePicker()" readonly value="${param.time}">
                                <input type="submit" class="btn btn-primary" value="搜索" style="margin-left: 30px;">
                            </form>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>厂区</th>
                                    <th>任务名称</th>
                                    <th>报告编号</th>
                                    <th>报告类型</th>
                                    <th>工人</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${allreport.list}" var="reportlog">
                                    <tr>
                                        <td>${reportlog.task.site.customid}</td>
                                        <td>${fn:substring(reportlog.taskcode,0,reportlog.taskcode.indexOf('-'))}</td>
                                        <td>${reportlog.taskcode}</td>
                                        <td>
                                            <c:if test="${reportlog.tasktype==0}">日常巡检</c:if>
                                            <c:if test="${reportlog.tasktype==1}">计划巡检</c:if>
                                            <c:if test="${reportlog.tasktype==2}">隐患排查</c:if>
                                            <c:if test="${reportlog.tasktype==3}">视频巡检</c:if>
                                            <c:if test="${reportlog.tasktype==4}">临时任务</c:if>
                                        </td>
                                        <td>${reportlog.worker}</td>
                                        <td><sdf:formatDate value="${reportlog.starttime}" pattern="yyyy-MM-dd HH:mm"></sdf:formatDate></td>
                                        <td><sdf:formatDate value="${reportlog.endtime}" pattern="yyyy-MM-dd HH:mm"></sdf:formatDate></td>
                                        <td>
                                            <a class="btn btn-primary" href="javascript:void(0)" onclick="getreportlog(${reportlog.id})">
                                                <i class="glyphicon glyphicon-zoom-in icon-white"></i>查看日志</a>
                                            <shiro:hasPermission name="del:tasklog">
                                                <a href="#" onclick="dellog(${reportlog.id})" class="btn btn-danger"> <i class="glyphicon glyphicon-trash icon-white"></i>删除</a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="gettaskreportlog?page=1">第一页</a>
                                <c:if test="${allreport.pageNum>1}">
                                    <a href="gettaskreportlog?page=${allreport.pageNum-1}">上一页</a>
                                </c:if>

                                <c:if test="${allreport.pageNum<allreport.pages}">
                                    <a href="gettaskreportlog?page=${allreport.pageNum+1}">下一页</a>
                                </c:if>

                                <a href="gettaskreportlog?page=${allreport.pages}">最后一页</a>

                                第${allreport.pageNum}页/共${allreport.pages}页
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="z-index: 0">
    <div class="modal-dialog" style="z-index: 9999">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>显示任务日志</h3>
            </div>
            <div class="modal-body">
                <div id="reportlog"style="height: 30%;overflow:auto;">

                </div>
            </div>
            <div class="modal-footer">
                <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
