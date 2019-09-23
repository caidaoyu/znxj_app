<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2017/5/12
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>智能巡检系统</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/highcharts.src.js"></script>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var chart;
        var chart2;
        $(function(){
            pie();
            linechart();
            tasknum();
        })
        function pie(){
            chart = new Highcharts.Chart({
                //常规图表选项设置
                chart: {
                    renderTo: 'machineRate',        //在哪个区域呈现，对应HTML中的一个元素ID
                    plotBackgroundColor: null,    //绘图区的背景颜色
                    plotBorderWidth: null,        //绘图区边框宽度
                    plotShadow: false            //绘图区是否显示阴影
                },
                //图表的主标题
                title: {
                    text: '饼图'
                },
                //当鼠标经过时的提示设置
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
                    percentageDecimals: 1
                },
                plotOptions: {
                    //饼状图
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            formatter: function() {
                                //Highcharts.numberFormat(this.percentage,2)格式化数字，保留2位精度
                                return '<b>'+ this.point.name +'</b>: '+Highcharts.numberFormat(this.percentage,2) +' %';
                            }
                        },showInLegend: true
                    }
                },
                //图表要展现的数据
                series: [{
                    type: 'pie',
                    name: '操作状态占比'
                }]

            });
            $.ajax({
                type : "GET",
                url : "/getStaticMachineRateByReport",
                data:$('#form').serialize(),
                success : function(res){
                    var data=res.machines;
                    //定义一个数组
                    browsers = [],
                    $.each(data,function(i,d){
                                var str = "";
                                switch (d.operationstate) {
                                    case 0:
                                        str = "未执行";
                                        break;
                                    case 1:
                                        str = "漏检";
                                        break;
                                    case 2:
                                        str = "跳检";
                                        break;
                                    case 3:
                                        str = "正常";
                                        break;
                                    case 4:
                                        str="超时";
                                        break;
                                    case undefined:
                                        str="其他";
                                        break;
                                    default:
                                        break;
                                }
                                browsers.push([str,d.count]);
                            });
                    //设置数据
                    chart.series[0].setData(browsers);
                    var sites=res.sites;
                    $("#name2").empty();
                    $("#name2").append("<option value='' selected>所有厂区</option>");
                    for(var i=0;i<sites.length;i++){
                        if(res.siteid==sites[i].id){
                            $("#name2").append("<option value='"+sites[i].id+"' selected>"+sites[i].customid+"</option>");
                        }else
                            $("#name2").append("<option value='"+sites[i].id+"'>"+sites[i].customid+"</option>");
                    }
                },
            });
        }
        function zhixian(data,months){
            chart2 = new Highcharts.Chart('container', {
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
                        text: '情况'
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
            var series = new Array();
            $.ajax({
                type : "GET",
                url : "/linechart",
                data:$('#form').serialize(),
                success : function(data){
                    var str="";
                    var months=new Array();
                    var s1=new Array();
                    var s2=new Array();
                    var s3=new Array();
                    var s4=new Array();
                    var s5=new Array();
                    for(var i=0;i<data.length;i++){
                        switch (data[i].operationstate) {
                            case 0:
                                str = "未执行";
                                s1.push(data[i].count);
                                break;
                            case 1:
                                str = "漏检";
                                s2.push(data[i].count);
                                break;
                            case 2:
                                str = "跳检";
                                s3.push(data[i].count);
                                break;
                            case 3:
                                str = "正常";
                                s4.push(data[i].count);
                                break;
                            case 4:
                                str="超时";
                                s5.push(data[i].count);
                                break;
                           /* case undefined:
                                str="其他";
                                break;*/
                            default:
                                break;
                        }
                        if(months.toString().indexOf(data[i].time)>-1){
                            continue;
                        }else{
                            months.push(data[i].time);
                            continue;
                        }
                    }
                   /* series.push({"name":"未执行", "data": s1},
                            {"name":"漏检", "data": s2},
                            {"name":"跳检", "data": s3},
                            {"name":"正常", "data": s4},
                            {"name":"超时", "data": s5});*/
                    series.push({"name":"正常", "data": s4},
                            {"name":"超时", "data": s5},
                            {"name":"跳检", "data": s3},
                            {"name":"未执行", "data": s1},
                            {"name":"漏检", "data": s2}
                            );
                    zhixian(series,months);
                }
            });
        }
        function search(){
            pie();
            linechart();
        }
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
                            $("#taskno").append("<option  value='"+task[i].id+"'>"+task[i].identifyingid+"</option>");
                        }
                    }
                }
            })
        }
    </script>
</head>
<body>
<input type="hidden" id="taskid" value="${taskid}">
<div class="ch-container">
    <div class="row">
        <%-- <%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li >
                                <a href="toreportlist?type=0" >任务情况列表</a>
                            </li>
                            <li class="active"><a href="taskfinalline.jsp"data-toggle="tab" style="background: #35A7E7;">任务情况报表</a></li>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i> 任务完成情况 - 报表
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <div class="form-inline" style="margin-bottom: 20px;">
                                        <form action="" method="post" id="form">
                                            <label class="control-label" for="name2">厂区：</label>
                                            <select id="name2" class="form-control" name="siteid" onchange="tasknum()">
                                                <option value="">所有厂区</option>
                                                <c:forEach items="${aa}" var="site">
                                                    <option value="${site.id}">${site.customid}</option>
                                                </c:forEach>

                                            </select>
                                            <label class="control-label" for="status">任务类型：</label>
                                            <select class="form-control" id="status" name="tasktype" onchange="tasknum()">
                                                <option value="">所有任务</option>
                                                <option value="0">日常巡检</option>
                                                <option value="1">计划巡检</option>
                                                <option value="2">隐患排查</option>
                                                <option value="3">视频巡检</option>
                                                <option value="4">临时任务</option>
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
                                            <input type="button" class="btn btn-primary" value="搜索" style="margin-left: 50px;" onclick="search()">
                                        </form>
                                    </div>
                                    <div id="machineRate"  title="饼图" style="display:inline; width:50%;"></div>
                                    <div id="container" style="display:inline; width:90%;height: 90%"></div>
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
