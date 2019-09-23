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
            goPage(1,5);
            linechart();
        });
        //分页
        function goPage(pno,psize){
            var itable = document.getElementById("table");
            var num = itable.rows.length;//表格所有行数(所有记录数)
            var totalPage = 0;//总页数
            var pageSize = psize;//每页显示行数
            //总共分几页
            if(num/pageSize > parseInt(num/pageSize)){
                totalPage=parseInt(num/pageSize)+1;
            }else{
                totalPage=parseInt(num/pageSize);
            }
            var currentPage = pno;//当前页数
            var startRow = (currentPage - 1) * pageSize+1;//开始显示的行  31
            var endRow = currentPage * pageSize;//结束显示的行   40
            endRow = (endRow > num)? num : endRow;
            //遍历显示数据实现分页
            for(var i=1;i<(num+1);i++){
                var irow = itable.rows[i-1];
                if(i>=startRow && i<=endRow){
                    irow.style.display = "table-row";
                }else{
                    irow.style.display = "none";

                }
            }
            var pageEnd = document.getElementById("pageEnd");
            var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
            if(currentPage>1){
                tempStr += "<a href=\"#\" onClick=\"goPage("+(1)+","+psize+")\">首页</a>";
                tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage-1)+","+psize+")\"><上一页</a>"
            }else{
                tempStr += "首页";
                tempStr += "<上一页";
            }

            if(currentPage<totalPage){
                tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage+1)+","+psize+")\">下一页></a>";
                tempStr += "<a href=\"#\" onClick=\"goPage("+(totalPage)+","+psize+")\">尾页</a>";
            }else{
                tempStr += "下一页>";
                tempStr += "尾页";
            }

            document.getElementById("barcon").innerHTML = tempStr;
            var statelen=$("#statelen").val();

        }
        //厂区改变
        function sitechange(){
            var siteid = $("#siteid option:selected")[0].value;
            $.ajax({
                url:"queryareabysiteid",
                type:"post",
                data:{
                    siteid:siteid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var areaselect =  document.getElementById("areaid");
                        areaselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var areaop = document.createElement("option");
                            areaop.innerHTML = data[i].customid;
                            areaop.value = data[i].id;
                            areaselect.appendChild(areaop);
                        }
                        return true;
                    }else{
                        alert("该厂区暂时没有区域");
                        return false;
                    }
                }
            });
        }
        //区域改变
        function changearea(){
            var areaid = $("#areaid option:selected")[0].value;
            $.ajax({
                url:"queryequipment",
                type:"post",
                data:{
                    areaid:areaid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var equipidselect =  document.getElementById("equipid");
                        equipidselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var equipidop = document.createElement("option");
                            equipidop.innerHTML = data[i].name;
                            equipidop.value = data[i].id;
                            equipidselect.appendChild(equipidop);
                        }
                        return true;
                    }else{
                        alert("该区域暂时没有设备");
                        return false;
                    }
                }
            });
        }
        //设备改变
        function changeequip(){
            var equipid = $("#equipid option:selected")[0].value;
            $.ajax({
                url:"getchecktask",
                type:"post",
                data:{
                    equipid:equipid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var checkselect =  document.getElementById("checkname");
                        checkselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var checkidop = document.createElement("option");
                            checkidop.innerHTML = data[i].itemname;
                            checkidop.value = data[i].id;
                            checkselect.appendChild(checkidop);
                        }
                        return true;
                    }else{
                        alert("该区域暂时没有设备");
                        return false;
                    }
                }
            });
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

            var equipid = $("#equipid option:selected")[0].value;
            var equipname=$("#equipid option:selected")[0].text;

            var checkid = $("#checkname option:selected")[0].value;
            var checkname=$("#checkname option:selected")[0].text;

            var monthstr=$("#month option:selected")[0].value;
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
                    checkid:checkid,
                    checkname:checkname,
                    monthstr:monthstr
                },
                success : function(data){
                    console.log(data);
                    if(data!=null && data!=""){
                        zhixian(data.series,data.monthes);
                    }
 //                   else{
//                        var monthstr=$("#monthstr").val();
//                        var checkname2=$("#checkname2").val();
//                        alert(checkname2+"巡检项"+monthstr+"月暂无信息");
//                    }
                }
            });
        }
        function getreport(){
            var siteid = $("#siteid option:selected")[0].value;
            var sitename=$("#siteid option:selected")[0].text;

            var areaid = $("#areaid option:selected")[0].value;
            var areaname=$("#areaid option:selected")[0].text;

            var equipid = $("#equipid option:selected")[0].value;
            var equipname=$("#equipid option:selected")[0].text;

            var checkid = $("#checkname option:selected")[0].value;
            var checkname=$("#checkname option:selected")[0].text;

            var monthstr=$("#month option:selected")[0].value;
            window.location.href="equipstateinfo?page=1&checkid="+checkid+"&monthstr="+monthstr+
                    "&siteid="+siteid+"&areaid="+areaid+"&equipid="+equipid+"&sitename="+sitename+
                    "&areaname="+areaname+"&equipname="+equipname+"&checkname="+checkname+"";
        }
    </script>
</head>
<body>
<%--<input type="hidden" id="tablestate" value="${tablestate}">--%>
<%--<input type="hidden" id="monthstr" value="${monthstr}">--%>
<%--<input type="hidden" id="checkname2" value="${checkname}">--%>

<!-- topbar ends -->
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
                                        <option value="">所有区域</option>
                                        <c:forEach items="${areainfos}" var="area">
                                            <option ${areaid eq area.id?'selected':''} value="${area.id}">${area.customid}</option>
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
                                        <option value="">所有巡检项</option>
                                        <c:forEach items="${checkiteminfos}" var="checks">
                                            <option ${checkid eq checks.id?'selected':''} value="${checks.id}">${checks.itemname}</option>
                                        </c:forEach>
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
                                   </select>

                                    <input type="button" id="btn" class="btn btn-primary" value="搜索" style="margin-left: 50px;" onclick="getreport()">
                                </form>

                            </div>
                            <%--<c:forEach items="${equipstates.list}" var="equipstates"  varStatus="status">--%>
                                <%--<input type="hidden" name="content" id="reportcontent${status.index}" value='${equipstates.content}'>--%>
                            <%--</c:forEach>--%>
                            <%--<input type="hidden" id="statelen" value="${fn:length(equipstates.list)}">--%>
                            <table id="table1"  class="table table-striped table-bordered table-hover ">
                                <tr>
                                    <%--<th>厂区</th>--%>
                                    <th style="width: 12.5%">区域</th>
                                    <th style="width: 12.5%">设备</th>
                                    <th style="width: 12.5%">巡检项</th>
                                    <th style="width: 12.5%">巡检项类型</th>
                                    <th style="width: 12.5%">执行时间</th>
                                    <th style="width: 12.5%">报告值</th>
                                    <th style="width: 12.5%">数据名称</th>
                                    <th style="width: 12.5%">单位</th>
                                </tr>
                            </table>
                            <table id="table" class="table table-striped table-bordered table-hover" style="margin-top: -20px;">
                                <c:forEach items="${equipstates}" var="equipstates">
                                    <tr>
                                        <td style="width: 12.5%">${equipstates.areaname}</td>
                                        <td style="width: 12.5%">${equipstates.equipname}</td>
                                        <td style="width: 12.5%">${equipstates.checkname}</td>
                                        <td style="width: 12.5%">${equipstates.checktype}</td>
                                        <td style="width: 12.5%">${equipstates.operationtime}" pattern="yyyy-MM-dd HH:mm"></sdf:formatDate></td>
                                        <td style="width: 12.5%">${equipstates.numvalue==""?"-":equipstates.numvalue}</td>
                                        <td style="width: 12.5%">${equipstates.recordname}</td>
                                        <td style="width: 12.5%">${equipstates.unitname}</td>
                                    </tr>
                                </c:forEach>
                                <input type="hidden" id="statelen" value="${fn:length(equipstates)}">
                            </table>
                            <div style="height: 50px;width: 500px;margin-left: 300px;">
                            </div>
                            <table id="table2" style="margin-top: -70px;"  class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr><td><div id="barcon" name="barcon"></div></td></tr>
                            </table>
                            <div id="container" style="display:inline; width:50%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
