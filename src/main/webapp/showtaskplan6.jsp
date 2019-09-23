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
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function(){
            sitechange();
            linechart();
        });
        //厂区改变
        function sitechange(){
            var siteid=$("#siteid option:selected")[0].value;
            var returnareaid=$("#returnareaid").val();
            $("#areaid").empty();
            $("#areaid").append("<option  value='' selected>所有区域</option>");
            $.ajax({
                url:"queryareabysiteid",
                type:"post",
                data:{
                    siteid:siteid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        for(var i=0;i<data.length;i++){
                            if(data[i].id==returnareaid){
                                $("#areaid").append("<option  value='"+data[i].id+"' selected>"+data[i].customid+"</option>");
                            }else{
                                $("#areaid").append("<option  value='"+data[i].id+"'>"+data[i].customid+"</option>");
                            }

                        }
                        changearea();
                    }else{
                        alert("该厂区暂时没有区域");
                    }
                }
            });
        }
        //区域改变
        function changearea(){
            var areaid=$("#areaid option:selected")[0].value;
            var returnequipid=$("#returnequipid").val();
            $("#equipid").empty();
            $("#equipid").append("<option  value='' selected>所有设备</option>");

            $.ajax({
                url:"queryequipment",
                type:"post",
                data:{
                    areaid:areaid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        for(var i=0;i<data.length;i++){
                            if(data[i].id==returnequipid){
                                $("#equipid").append("<option  value='"+data[i].id+"' selected>"+data[i].name+"</option>");
                            }else{
                                $("#equipid").append("<option  value='"+data[i].id+"'>"+data[i].name+"</option>");
                            }

                        }
                        changeequip();

                    }else{
                        alert("该区域暂时没有设备");
                    }
                }
            });
        }
        //设备改变
        function changeequip(){
            var equipid=$("#equipid option:selected")[0].value;
            var returncheckid=$("#paramcheckname").val();
            var needline=$("#needline").val();
            if(equipid!=""){
                $("#checkname").empty();
                $("#checkname").append("<option  value='' selected>所有巡检项</option>");
                $.ajax({
                    url:"getchecktask",
                    type:"post",
                    data:{
                        equipid:equipid,
                    },
                    dataType:"json",
                    success:function(data){
                        if(data!=null){returncheckid
                            for(var i=0;i<data.length;i++){
                                if(data[i].itemname==returncheckid){
                                    $("#checkname").append("<option  value='"+data[i].id+"' selected>"+data[i].itemname+"</option>");
                                }else{
                                    $("#checkname").append("<option  value='"+data[i].id+"'>"+data[i].itemname+"</option>");
                                }

                            }
                            if(needline==1){
                                linechart();
                            }
                        }else{
                            alert("该区域暂时没有设备");
                        }
                    }
                });
            }

        }
        //折线图
        function zhixian(data,months){
            var chart2 = new Highcharts.Chart('container', {
                title: {
                    text: '折线图',
                    x: -20
                },
                xAxis: {
                    title: {
                        text: '天数'
                    },
                    categories: months
                },
                yAxis: {
                    title: {
                        text: '读数'
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
            var siteid = $("#siteid option:selected")[0].value;
            var sitename=$("#siteid option:selected")[0].text;

            var areaid = $("#areaid option:selected")[0].value;
            var areaname=$("#areaid option:selected")[0].text;
            if(areaid==""){
                areaid=$("#returnareaid").val();
            }
            if(areaname=="所有区域"){
                areaname=$("#returnareaname").val();
            }
            var equipid = $("#equipid option:selected")[0].value;
            var equipname=$("#equipid option:selected")[0].text;

//            var checkid = $("#checkname option:selected")[0].value;
            var checkname=$("#checkname option:selected")[0].text;

            // var monthstr=$("#month option:selected")[0].value;
            // var yearstr=$("#year option:selected")[0].value;
            var monthstr = $("#startTime").val();
            var yearstr = $("#endTime").val();
            if (checkname=="所有巡检项")
                checkname=$("#paramcheckname").val();
            if (monthstr=="")
                monthstr=$("#monthstr").val();
            if (yearstr=="")
                yearstr=$("#yearstr").val();
            $.ajax({
                url : "equipstatechart",
                type : "GET",
                data:{
                    page:0,
                    siteid:siteid,
                    sitename:sitename,
                    areaid:areaid,
                    areaname:areaname,
                    equipid:equipid,
                    equipname:equipname,
//                    checkid:checkid,
                    checkname:checkname,
                    startTime:monthstr,
                    endTime:yearstr
                },
                success : function(result){
                    // console.log(result.data);
                    if(result.data!=null && result.data!=""){
                        if(result.data.series.length>=0&&result.data.monthes.length>=0){
                            zhixian(result.data.series,result.data.monthes);
                        }else{
                            // var monthstr=$("#monthstr").val();
                            // var yearstr=$("#yearstr").val();
                            var checkname2=$("#paramcheckname").val();
                            alert(checkname2+"巡检项暂无信息");
                            return false;
                        }
                    }
                }
            });
        }
        function getreport(){
            var siteid = $("#siteid option:selected")[0].value;
            var sitename=$("#siteid option:selected")[0].text;

            var areaid = $("#areaid option:selected")[0].value;
            var areaname=$("#areaid option:selected")[0].text;
            if(areaname=="所有区域"){
                areaname="";
            }

            var equipid = $("#equipid option:selected")[0].value;
            var equipname=$("#equipid option:selected")[0].text;
            if(equipname=="所有设备"){
                equipname="";
            }
            var checkid = $("#checkname option:selected")[0].value;
            var checkname=$("#checkname option:selected")[0].text;
            if(checkname=="所有巡检项"){
                checkname="";
            }
           /* var monthstr=$("#month option:selected")[0].value;
            var yearstr=$("#year option:selected")[0].value;*/
           var monthstr = $("#startTime").val();
           var yearstr = $("#endTime").val();
            window.location.href="equipstateinfo?page=1&checkid="+checkid+"&startTime="+monthstr+"&endTime="+yearstr+
                    "&siteid="+siteid+"&areaid="+areaid+"&equipid="+equipid+"&sitename="+sitename+
                    "&areaname="+areaname+"&equipname="+equipname+"&checkname="+checkname+"&needline=1";
        }
    </script>
</head>
<body>
<input type="hidden" id="paramcheckname" value="${checkname}">
<input type="hidden" id="monthstr" value="${startTime}">
<input type="hidden" id="yearstr" value="${endTime}">
<input type="hidden" id="returnsiteid" value="${siteid}">
<input type="hidden" id="returnareaid" value="${areaid}">
<input type="hidden" id="returnequipid" value="${equipid}">
<input type="hidden" id="returncheckid" value="${checkid}">
<input type="hidden" id="needline" value="${needline}">
<input type="hidden" id="returnareaname" value="${areaname}">
<%--<input type="hidden" id="returncheckname" value="${checkname}">--%>
<div class="ch-container">
    <div class="row">
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 设备状态情况
                            </h2>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="" method="get" id="form">
                                    <label class="control-label" for="siteid">厂区：</label>
                                    <select class="form-control" id="siteid" name="siteid" onchange="sitechange()">
                                        <option value="">所有厂区</option>
                                        <c:forEach items="${sites}" var="site">
                                            <option ${siteid eq site.id?'selected':''} value="${site.id}">${site.customid}</option>
                                        </c:forEach>
                                    </select>
                                    <label class="control-label" for="areaid">区域：</label>
                                    <select class="form-control" id="areaid"  onchange="changearea()">
                                        <option ${areaid eq ""?"selected":""} value="">所有区域</option>
                                        <c:forEach items="${areainfos}" var="area">
                                            <option ${areaid eq area.id ? 'selected':''} value="${area.id}">${area.customid}</option>
                                        </c:forEach>
                                    </select>
                                    </select>
                                    <label class="control-label" for="equipid">设备：</label>
                                    <select class="form-control" id="equipid" name="equipid" onchange="changeequip()">
                                        <option value="">所有设备</option>
                                        <c:forEach items="${equipmentinfos}" var="equips">
                                            <option ${equipid eq equips.id?'selected':''} value="${equips.id}">${equips.name}</option>
                                        </c:forEach>
                                    </select>
                                    <label class="control-label" for="checkname">巡检项：</label>
                                    <select class="form-control" id="checkname" name="checkname">
                                        <%--<c:if test="${checkname eq ''}">--%>
                                            <%--<option value="">所有巡检项</option>--%>
                                        <%--</c:if>--%>
                                        <option value="${checkname}">${checkname}</option>
                                        <%--<c:forEach items="${checkiteminfos}" var="checks">--%>
                                            <%--<option ${checkname eq checks.itemname?'selected':''} value="${checks.itemname}">${checks.itemname}</option>--%>
                                        <%--</c:forEach>--%>
                                    </select>



                                    <label class="control-label">选择时间：</label>
                                    <%--<select  class="form-control" name="year" id="year">
                                        <option value="2018">2018</option>
                                        <option ${yearstr eq 2017?'selected':''}>2017</option>
                                    </select>

                                    <label class="control-label">月份：</label>
                                   <select  class="form-control" name="month" id="month">
                                       <option value="">所有月份</option>
                                       <option ${monthstr eq 1?'selected':''}>1</option><option ${monthstr eq 2?'selected':''}>2</option>
                                       <option ${monthstr eq 3?'selected':''}>3</option><option ${monthstr eq 4?'selected':''}>4</option>
                                       <option ${monthstr eq 5?'selected':''}>5</option><option ${monthstr eq 6?'selected':''}>6</option>
                                       <option ${monthstr eq 7?'selected':''}>7</option><option ${monthstr eq 8?'selected':''}>8</option>
                                       <option ${monthstr eq 9?'selected':''}>9</option><option ${monthstr eq 10?'selected':''}>10</option>
                                       <option ${monthstr eq 11?'selected':''}>11</option><option ${monthstr eq 12?'selected':''}>12</option>
                                   </select>--%>

                                    <input type="text"  class="jeinput" id="startTime" onClick="WdatePicker()" style="width:200px;height: 35px;" placeholder="年/月/日" name="startTime" value="${param.startTime}">&nbsp;&nbsp;至&nbsp;&nbsp;
                                    <input type="text"  class="jeinput" id="endTime" onClick="WdatePicker()" style="width:200px;height: 35px;" placeholder="年/月/日" name="endTime" value="${param.endTime}">

                                    <input type="button" id="btn" class="btn btn-primary" value="搜索" style="margin-left: 50px;" onclick="getreport()">
                                </form>

                            </div>
                            <table id="table" class="table table-striped table-bordered table-hover">
                                <tr>
                                    <th>区域</th>
                                    <th>设备</th>
                                    <th>巡检项</th>
                                    <th>巡检项类型</th>
                                    <th>执行时间</th>
                                    <th>报告值</th>
                                    <th>复核值</th>
                                    <th >数据名称</th>
                                    <th>单位</th>
                                </tr>
                                <c:forEach items="${equipstates.list}" var="equipstates">
                                    <tr>
                                        <td >${equipstates.areaname}</td>
                                        <td >${equipstates.equipname}</td>
                                        <td>${equipstates.checkname}</td>
                                        <td>${equipstates.checktype}</td>
                                        <td>${equipstates.operationtime}</td>
                                        <td >${equipstates.numvalue==""?"-":equipstates.numvalue}</td>
                                        <td >${equipstates.checkvalue==""?"0":equipstates.checkvalue}</td>
                                        <td>${equipstates.recordname}</td>
                                        <td>${equipstates.unitname}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;margin-left: 300px;">
                                <a href="equipstateinfo?page=1&siteid=${siteid}&sitename=${sitename}&areaid=${areaid}&areaname=${areaname}&equipid=${equipid}&equipname=${equipname}&checkname=${checkname}&startTime=${startTime}&endTime=${endTime}">第一页</a>
                                <c:if test="${equipstates.pageNum>1}">
                                    <a href="equipstateinfo?page=${equipstates.pageNum-1}&siteid=${siteid}&sitename=${sitename}&areaid=${areaid}&areaname=${areaname}&equipid=${equipid}&equipname=${equipname}&checkname=${checkname}&startTime=${startTime}&endTime=${endTime}">上一页</a>
                                </c:if>

                                <c:if test="${equipstates.pageNum<equipstates.pages}">
                                    <a href="equipstateinfo?page=${equipstates.pageNum+1}&siteid=${siteid}&sitename=${sitename}&areaid=${areaid}&areaname=${areaname}&equipid=${equipid}&equipname=${equipname}&checkname=${checkname}&startTime=${startTime}&endTime=${endTime}">下一页</a>
                                </c:if>

                                <a href="equipstateinfo?page=${equipstates.pages}&siteid=${siteid}&sitename=${sitename}&areaid=${areaid}&areaname=${areaname}&equipid=${equipid}&equipname=${equipname}&checkname=${checkname}&startTime=${startTime}&endTime=${endTime}">最后一页</a>

                                第${equipstates.pageNum}页/共${equipstates.pages}页
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
