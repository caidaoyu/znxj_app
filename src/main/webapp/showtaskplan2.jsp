<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

        $(function () {
//            var tablestate=$("#tablestate").val();
//            if(tablestate==0){
//                $("#table1").css("display","none");
//                $("#table2").css("display","none");
//            }else {
//                $("#table1").css("display","table");
//                $("#table2").css("display","table");
//            }
            tasknum();
        })

        function tasknum(){
            var type = $("#status option:selected")[0].value;
            var siteid = $("#name2 option:selected")[0].value;
            var taskid=$("#taskid").val();
            $("#taskno").empty();
            $("#taskno").append("<option  value='' selected>所有任务</option>");
            $.ajax({
                type : "GET",
                url : "taskbysiteandtype?type="+type+"&siteid="+siteid,
                success : function(task){
                    for(var i=0;i<task.length;i++){
                        if(task[i].id==taskid){
                            $("#taskno").append("<option  value='"+task[i].id+"' selected>"+task[i].identifyingid+"</option>");
                        }else{
                            if(task[i].identifyingid!=undefined){
                                $("#taskno").append("<option  value='"+task[i].id+"' >"+task[i].identifyingid+"</option>");
                            }
                        }
                    }
                }
            })
        }
    </script>
</head>
<body>
<input type="hidden" id="taskid" value="${taskid}">
<%--<input type="hidden" id="tablestate" value="${tablestate}">--%>
<%--
<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">
       <%-- <%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active">
                                <a href="#home" data-toggle="tab"style="background: #35A7E7;">任务情况列表</a>
                            </li>
                            <li><a href="taskfinalline.jsp">任务情况报表</a></li>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i> 任务完成情况
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <div class="form-inline" style="margin-bottom: 20px;">
                                        <form action="reportlist?page=1&type=0" method="post">
                                            <label class="control-label" for="name2">厂区：</label>
                                            <select id="name2" class="form-control" name="siteid" onchange="tasknum()">
                                                <option value="">所有厂区</option>
                                                <c:forEach items="${sites}" var="site">
                                                    <option ${siteid eq site.id?'selected':''} value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="control-label" for="status">任务类型：</label>
                                            <select class="form-control" id="status" name="tasktype"  onchange="tasknum()">
                                                <option ${tasktype eq ''?'selected':''} value="">所有任务</option>
                                                <option ${tasktype eq '0'?'selected':''} value="0">日常巡检</option>
                                                <option ${tasktype eq '1'?'selected':''} value="1">计划巡检</option>
                                                <option ${tasktype eq '2'?'selected':''} value="2">隐患排查</option>
                                                <option ${tasktype eq '3'?'selected':''} value="3">视频巡检</option>
                                                <option ${tasktype eq '4'?'selected':''} value="4">临时任务</option>
                                            </select>
                                            <label class="control-label" for="taskno">任务编号：</label>
                                            <select class="form-control" id="taskno" name="taskid">
                                                <option value="" selected>所有任务</option>
                                            </select>
                                            <label class="control-label" for="status">执行人：</label>
                                            <input type="text" class="form-control" name="worker"><br/>
                                            <label class="control-label">时间：</label>
                                            <input type="text" style="margin-top: 20px;" name="time1" onClick="WdatePicker()" value="${param.time1}">：
                                            <input type="text" style="margin-top: 20px;" name="time2" onClick="WdatePicker()" value="${param.time2}">
                                            <input type="submit" class="btn btn-primary" value="搜索" style="margin-left: 50px;">
                                        </form>

                                    </div>
                                    <table id="table1" class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr style="text-align: center">
                                            <th>厂区</th>
                                            <th>任务类型</th>
                                            <th>任务编号</th>
                                            <th>执行状态</th>
                                            <th>计划开始时间</th>
                                            <th>实际开始时间</th>
                                            <th>任务完成时间</th>
                                            <th>执行人</th>
                                        </tr>
                                        <c:forEach items="${reports.list}" var="task">
                                            <tr>
                                                <td>${task.task.site.customid}</td>
                                                <td>
                                                    <c:if test="${task.temp.type ==0}">日常巡检 </c:if>
                                                    <c:if test="${task.temp.type ==1}">计划巡检 </c:if>
                                                    <c:if test="${task.temp.type ==2}">隐患排查 </c:if>
                                                    <c:if test="${task.temp.type ==3}">视频巡检</c:if>
                                                    <c:if test="${task.temp.type ==4}">临时任务</c:if>
                                                </td>
                                                <%--<td><a href="querytaskreportdetail?id=${task.id}&type=${task.tasktype}&type2=1">${task.taskcode}</a></td>--%>
                                                <td>
                                                    <c:if test="${task.id==null}">${task.temp.taskcode}</c:if>
                                                    <c:if test="${task.id!=null}">
                                                        <a href="querytaskreportdetail?id=${task.id}&type=${task.temp.type}&page=1&type2=2">${task.temp.taskcode}</a>
                                                    </c:if>

                                                </td>
                                                <td>
                                                    <c:if test="${task.temp.state ==0}">未执行 </c:if>
                                                    <c:if test="${task.temp.state ==1}">进行中 </c:if>
                                                    <c:if test="${task.temp.state ==2}">
                                                        <c:if test="${task.temp.operationstate==1}">已漏检</c:if>
                                                        <c:if test="${task.temp.operationstate==2}">已跳检</c:if>
                                                        <c:if test="${task.temp.operationstate==3}">已完成</c:if>
                                                    </c:if>
                                                    <c:if test="${task.temp.state ==3}">
                                                        <%--<c:if test="${task.temp.operationstate==4}">已超时</c:if>--%>
                                                        <%--<c:if test="${task.temp.operationstate==3}">--%>
                                                            <%--已终止--%>
                                                        <%--</c:if>--%>
                                                        <c:if test="${task.temp.stopstate==2}">已超时</c:if>
                                                        <c:if test="${task.temp.stopstate==1}">
                                                            <a href="javascript:void(0)" onclick="showstop(${report.temp.id})">已终止</a>
                                                        </c:if>
                                                    </c:if>
                                                </td>
                                                <td><sdf:formatDate value="${task.temp.executetime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>
                                                <td><sdf:formatDate value="${task.starttime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>
                                                <td><sdf:formatDate value="${task.donetime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>
                                                <td>${task.worker}</td>
                                            </tr>
                                       </c:forEach>
                                    </table>
                                    <div style="height: 50px;width: 500px;margin-left: 300px;" id="table2">
                                        <a href="reportlist?page=1&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&taskid=${taskid}&time1=${time1}&time2=${time2}&type=0">第一页</a>
                                        <c:if test="${reports.pageNum>1}">
                                            <a href="reportlist?page=${reports.pageNum-1}&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&taskid=${taskid}&time1=${time1}&time2=${time2}&type=0">上一页</a>
                                        </c:if>

                                        <c:if test="${reports.pageNum<reports.pages}">
                                            <a href="reportlist?page=${reports.pageNum+1}&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&taskid=${taskid}&time1=${time1}&time2=${time2}&type=0">下一页</a>
                                        </c:if>

                                        <a href="reportlist?page=${reports.pages}&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&taskid=${taskid}&time1=${time1}&time2=${time2}&type=0">最后一页</a>

                                        第${reports.pageNum}页/共${reports.pages}页
                                    </div>
                                </div>
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
