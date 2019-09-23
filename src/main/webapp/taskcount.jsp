<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <style>
        .selected{background: #35A7E7;
            color: white;}
    </style>
    <script type="text/javascript">
        $(function(){
            var len=$("#statuslen").val();
            /*var first = $("#table1 tr:first");*/
            $("#table1").empty();
            /*追加表头*/
            $("#table1").append("<tr id='one'><th>区域</th><th>设备</th><th>巡检项</th><th>数据名称</th><th>单位</th><th>状态</th></tr>");
            for(var j=0;j<len;j++){
                var th=document.createElement("th");

                var taskcode=$("#code"+j).val();
                var tes = document.createTextNode(taskcode);
                th.appendChild(tes);
                $("#one")[0].appendChild(th);
            }
//            var value=new Array();
//            for(var q=0;q<len;q++){
//                value.push(JSON.parse($("#count"+q).val()).length);
//            }
//            var num=Math.max.apply(null, value);
//            var aa=$("#count"+num).val()
            var content1=JSON.parse($("#count"+0).val());
                for(var i=0;i<content1.length;i++){
                $("#table1").append("<tr id='bbb"+i+"' isnormal=''><td>"+content1[i].areaname+
                        "</td><td>"+content1[i].equipname+"</td><td>"+content1[i].checkname+
                        "</td><td>"+(content1[i].recordname==''?'-':content1[i].recordname)+"</td><td>"+(content1[i].unitname==''?'-':content1[i].unitname)+
                        "</td><td>"+content1[i].checktype+"</td><td>"+(content1[i].numvalue==''?'-':content1[i].numvalue)+"</td></tr>");
               if(content1[i].reportstate=="1"){
                    $("#bbb"+i).attr("isnormal",1);
                }else if(content1[i].reportstate=="0"){
                   $("#bbb"+i).attr("isnormal",0);
               }
            }

            for(var m=1;m<len;m++){
                var td;
                var taskcontent=JSON.parse($("#count"+m).val());
                var aa=taskcontent.length;
                for(var n=0;n<taskcontent.length;n++){
                    td=document.createElement("td");
                    if(taskcontent[n].numvalue!=undefined){
                        var value=taskcontent[n].numvalue==''?'-':taskcontent[n].numvalue;
                        var tdval = document.createTextNode(value);
                        td.appendChild(tdval);
                        $("#bbb"+n)[0].appendChild(td);
                    }else
                        continue;
                  /* if(taskcontent[n].reportstate=="1"){
                        $("#bbb"+n).attr("isnormal",1);
                    }else if(content1[i].reportstate=="0"){
                       $("#bbb"+i).attr("isnormal",0);
                   }*/
                }
            }
        });
        function datechange(obj) {
            var p1=$(obj).children('option:selected').val();//这就是selected的值
            var table =document.getElementById("table1");
            var rows = table.rows.length;
            switch(p1){
                case "":
                    console.log("选中所有");
                    location.reload();
                    break;
                case "0":
                    console.log("选中正常");
                    for(var i=0;i<rows;i++){
                        var value=$("#bbb"+i).attr("isnormal");
                        if(value==1){
                            $("#bbb"+i).addClass("hidden");
                        }else{
                            $("#bbb"+i).removeClass("hidden");
                        }
                    }
                    break;
                case "1":
                    console.log("选中异常 ");
                    for(var j=0;j<rows;j++){
                        var value=$("#bbb"+j).attr("isnormal");
                        if(value==0){
                            $("#bbb"+j).addClass("hidden");
                        }else{
                            $("#bbb"+j).removeClass("hidden");
                        }
                    }
                    break;
            }
        };
        function showdata(){
            var taskid=$("#taskidd").val();
            var donetime=$("#donetime").val();
            var type=$("#type").val();
            location.href="findbydonetime?taskid="+taskid+"&donetime="+donetime+"&type="+type;
        }
        function refer(){
            var type=$("#type").val();
            window.location="showallreport1?page=1&tasktype="+type;
        }
        function exportexcel(taskid,type){
           var taskname=$("#taskname").val();
            var donetime=$("#donetime").val();
            window.location="exportExcel?type="+type+"&taskid="+taskid+"&donetime="+donetime+"&taskname="+taskname;
        }
    </script>
</head>
<body>
<input id="taskname" type="hidden" value="${taskname}">
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <input type="hidden" value="${type}" id="type">
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li>
                                <a href="showtaskplan?page=1&type=${type}">任务列表</a>
                            </li>
                            <li class="active"><a href="#report" data-toggle="tab" style="background: #35A7E7;" onclick="refer()">任务报告</a></li>
                            <c:if test="${type==2}">
                                <li><a href="showQuickReport1?page=1&type=${type}&qtype=1">立即上报</a></li>
                            </c:if>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="report">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i>
                                        <c:if test="${type==0}">日常巡检任务报告--汇总</c:if>
                                        <c:if test="${type==1}">计划巡检任务报告--汇总</c:if>
                                        <c:if test="${type==2}">HSE隐患排查报告--汇总</c:if>
                                        <c:if test="${type==3}">视频巡检任务报告--汇总</c:if>
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <c:forEach items="${reportinfos}" var="reports" varStatus="status">
                                        <input type="hidden" id="count${status.index}" value='${reports.content}'>
                                        <input type="hidden" id="code${status.index}" value="${reports.taskcode}">
                                    </c:forEach>
                                    <input type="hidden" id="statuslen" value="${fn:length(reportinfos)}">

                                    <div style="height:500px;overflow: auto;">

                                        <input type="hidden" id="taskidd" name="taskid" value="${taskid}">
                                        <span style="font-size: 15px;margin-top: 20px;margin-bottom: 20px">报告完成时间：</span>
                                        <input type="text" name="donetime" id="donetime" readonly onClick="WdatePicker()" value="${donetime}"style="margin-top: 20px;margin-bottom: 20px">
                                        <input type="submit" value="搜索" class="btn btn-primary" onclick="showdata()">
                                        <span style="font-size:15px;margin-top: 20px;margin-bottom: 20px">操作状态:</span>
                                        <select id = "reportstate" name="reportstate" onchange="datechange(this)" style="margin-top: 20px;margin-bottom: 20px">
                                            <option id="option1" ${param.reportstate eq ''?'selected':''} value="">所有</option>
                                            <option id="option2" ${param.reportstate eq '0'?'selected':''} value="0">正常</option>
                                            <option id="option3" ${param.reportstate eq '1'?'selected':''} value="1">异常</option>
                                        </select>
                                        <input type="button" class="btn btn-primary" value="导出Excel" style="margin-left: 30px;" onclick="exportexcel(${taskid},${type})">

                                        <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable" id="table1">

                                        </table>
                                    </div>
                                    <input type="button" class="btn btn-primary" value="返回" onclick="javascript:history.go(-1);">
                                </div>
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
        <div class="modal-content" style="overflow: auto;">
            <div id="arealist">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>显示异常</h3>
                </div>
                <div class="modal-body">
                    <div id="showerror"style="height: 30%;">
                        <div style="width: 150px;float: left;height: 300px;overflow:auto;">
                            <ul class="list-group" id="errorul">

                            </ul>
                        </div>
                        <div id="pic" style="border: 1px solid #F3F3F3; width: 400px;height: 100%;float: left;">

                        </div>
                        <div class="clearfix" style="display: none;"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
