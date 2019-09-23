<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/highcharts.src.js"></script>
    <script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            var reportcount=$("#reportcount").val();
            for(var i=0;i<reportcount;i++){
                    var starttime=$("#start"+i).val();
                    var endtime=$("#end"+i).val();
                    var createdate=$("#createdate"+i).val();
                    //判断开始时间是否大于结束日期
                    if(starttime>endtime)
                    {
                        alert("开始时间不能大于结束时间！");
                        return false;
                    }
                    //截取字符串，得到日期部分"2009-12-02",用split把字符串分隔成数组
                    var begin1=starttime.substr(0,10).split("-");
                    var end1=endtime.substr(0,10).split("-");
                    var createdate2=createdate.substr(0,10).split("-");
                    //将拆分的数组重新组合，并实例成化新的日期对象
                    var date1=new Date(begin1[1] + - + begin1[2] + - + begin1[0]);
                    var date2=new Date(end1[1] + - + end1[2] + - + end1[0]);
                   var createdate3=new Date(createdate2[1] + - + createdate2[2] + - + createdate2[0]);

                    //得到两个日期之间的差值m，以分钟为单位
                    //Math.abs(date2-date1)计算出以毫秒为单位的差值
                    //Math.abs(date2-date1)/1000得到以秒为单位的差值
                    //Math.abs(date2-date1)/1000/60得到以分钟为单位的差值
                    var m=parseInt(Math.abs(date2-date1)/1000/60);
                    var t=Math.abs(createdate3-date1)/1000/60;
                    //小时数和分钟数相加得到总的分钟数
                    //time1.substr(11,2)截取字符串得到时间的小时数
                    //parseInt(time1.substr(11,2))*60把小时数转化成为分钟
                    var min1=parseInt(starttime.substr(11,2))*60+parseInt(starttime.substr(14,2));
                    var min2=parseInt(endtime.substr(11,2))*60+parseInt(endtime.substr(14,2));
                    var t2=parseInt(createdate.substr(11,2))*60+parseInt(createdate.substr(14,2));

                    //两个分钟数相减得到时间部分的差值，以分钟为单位
                    var n=min2-min1;
                    var datetime4= t2-min1;

                    //将日期和时间两个部分计算出来的差值相加，即得到两个时间相减后的分钟数
                    var minutes=m+n;
                    var minutes2=t+datetime4;
                    $("#time"+i).text(minutes);
                    $("#datesub"+i).text(minutes2);
                }
            inittasknum();
        })
        function zhixian(data,months){
            var chart2 = new Highcharts.Chart('container', {
                title: {
                    text: '折线图',
                    x: -20
                },
                xAxis: {
                    title: {
                        text: '月份'
                    },
                    categories: months
                },
                yAxis: {
                    title: {
                        text: '平均耗时(单位：分钟)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                /* tooltip: {
                 valueSuffix: '°C'
                 },*/
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series:data
            });
        }
        function linechart(){
            $("#taskno").val($("#taskid").val());
            $.ajax({
                type : "GET",
                url : "/avgtimelinechart",
                async:true,
                data:$('#form').serialize(),
                success : function(data){
                    if(data.data!=null){
                        zhixian(data.data.series,data.data.monthes);
                    }else{
                        alert("暂无报表信息");
                        return false;
                    }
                }
            });
        }
        function tasknum(){
            var type = $("#status option:selected")[0].value;
            var siteid = $("#name2 option:selected")[0].value;
//            var taskid=$("#taskno option:selected")[0].value;
            var taskid=$("#taskid").val();
            $("#taskno").append("<option  value=''>所有任务</option>");
            $.ajax({
                type : "GET",
                url : "taskbysiteandtype?type="+type+"&siteid="+siteid,
                success : function(task){
                    $("#taskno").empty();

                    for(var i=0;i<task.length;i++){
                        if(parseInt(task[i].id)==parseInt(taskid)){
                            $("#taskno").append("<option  value='"+task[i].id+"' selected>"+task[i].identifyingid+"</option>");
                        }else{
                            $("#taskno").append("<option  value='"+task[i].id+"' >"+task[i].identifyingid+"</option>");
                        }
                    }
                }
            })
        }
        function inittasknum(){
            var type = $("#status option:selected")[0].value;
            var siteid = $("#name2 option:selected")[0].value;
//            var taskid=$("#taskno option:selected")[0].value;
            var taskid=$("#taskid").val();
            $.ajax({
                type : "GET",
                url : "taskbysiteandtype?type="+type+"&siteid="+siteid,
                success : function(task){
                    $("#taskno").empty();
                    $("#taskno").append("<option  value=''>所有任务</option>");
                    for(var i=0;i<task.length;i++){
                        if(parseInt(task[i].id)==parseInt(taskid)){
                            $("#taskno").append("<option  value='"+task[i].id+"' selected>"+task[i].identifyingid+"</option>");
                        }else{
                            $("#taskno").append("<option  value='"+task[i].id+"' >"+task[i].identifyingid+"</option>");
                        }
                    }
                    linechart();
                }
            })
        }

    </script>
</head>
<body>
<input type="hidden" id="taskid" value="${taskid}">
<%--<input type="hidden" id="tablestate" value="${tablestate}">--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 任务完成时间
                            </h2>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="reportlist?page=1&type=1" method="post" id="form">

                                    <label class="control-label" for="name2">厂区：</label>
                                    <select id="name2" class="form-control" name="siteid">
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
                                        <%--<option value="${taskid}"></option>--%>
                                    </select>
                                    <label class="control-label" for="status">执行人：</label>
                                    <input type="text" class="form-control" name="worker"><br/>
                                    <label class="control-label">时间：</label>
                                    <input type="text" style="margin-top: 20px;" name="time1" onClick="WdatePicker()" value="${param.time1}">：
                                    <input type="text" style="margin-top: 20px;" name="time2" onClick="WdatePicker()" value="${param.time2}">
                                    <input type="submit" id="btn" class="btn btn-primary" value="搜索" style="margin-left: 50px;">
                                </form>

                            </div>
                            <table id="table1" class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>任务编号</th>
                                    <th>任务类型</th>
                                    <th>任务开始时间偏差(单位：分钟)</th>
                                    <th>耗时(单位：分钟)</th>
                                </tr>
                                <c:forEach items="${reports.list}" var="report"  varStatus="status">
                                    <input type="hidden" id="start${status.index}"
                                           value="<sdf:formatDate value='${report.starttime}' pattern='yyyy-MM-dd HH:mm'></sdf:formatDate>">
                                    <input type="hidden" id="end${status.index}"
                                           value="<sdf:formatDate value='${report.endtime}' pattern='yyyy-MM-dd HH:mm'></sdf:formatDate>">
                                    <input type="hidden" id="createdate${status.index}"
                                           value="<sdf:formatDate value='${report.temp.executetime}' pattern='yyyy-MM-dd HH:mm'></sdf:formatDate>">
                                    <tr>
                                        <%--<td><a href="querytaskreportdetail?id=${report.id}&type=${report.tasktype}&type2=1">${report.taskcode}</a></td>--%>
                                        <td><a href="querytaskreportdetail?id=${report.id}&type=${report.tasktype}&page=1&type2=2">${report.taskcode}</a></td>
                                        <td>
                                            <c:if test="${report.tasktype ==0}">日常巡检 </c:if>
                                            <c:if test="${report.tasktype ==1}">计划巡检 </c:if>
                                            <c:if test="${report.tasktype ==2}">隐患排查 </c:if>
                                            <c:if test="${report.tasktype ==3}">视频巡检</c:if>
                                            <c:if test="${report.tasktype ==4}">临时任务</c:if>
                                        </td>
                                        <td id="datesub${status.index}">
                                            <%--<sdf:formatDate value="${task.starttime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate>--%>
                                        </td>
                                        <td id="time${status.index}">

                                        </td>
                                    </tr>
                                </c:forEach>
                                <input type="hidden" id="reportcount" value="${fn:length(reports.list)}">
                            </table>
                            <div style="height: 50px;width: 500px;margin-left: 300px;" id="table2">
                                <a href="reportlist?page=1&type=1&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&time1=${time1}&time2=${time2}&taskid=${taskid}">第一页</a>
                                <c:if test="${reports.pageNum>1}">
                                    <a href="reportlist?page=${reports.pageNum-1}&type=1&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&time1=${time1}&time2=${time2}&taskid=${taskid}">上一页</a>
                                </c:if>

                                <c:if test="${reports.pageNum<reports.pages}">
                                    <a href="reportlist?page=${reports.pageNum+1}&type=1&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&time1=${time1}&time2=${time2}&taskid=${taskid}">下一页</a>
                                </c:if>

                                <a href="reportlist?page=${reports.pages}&type=1&tasktype=${tasktype}&worker=${worker}&siteid=${siteid}&time1=${time1}&time2=${time2}&taskid=${taskid}">最后一页</a>

                                第${reports.pageNum}页/共${reports.pages}页
                            </div>
                            <div id="container" style="display:inline; width:90%;height: 90%"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
