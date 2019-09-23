<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2017/4/26
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%--<%@ include file="/WEB-INF/pages/common/header.jsp"%>--%>
    <style>
        #div{
            width: 1500px;
        }
        .table1{
            width: 1200px;
            height: 50px;
            margin-left: 150px;
        }
        table tr{
            height: 50px;
        }
        table tr td{
            line-height: 20px;
            text-align: center;
            vertical-align: middle;
        }
        .table2{
            width: 1200px;
            height: 20px;
            margin-left:150px;
        }
    </style>
    <script>
        function findbystate(val2,val4,val5){
            if(val2=="[]"&&val4=="[]"&&val5=="[]"){
                alert("暂无其他异常信息");
            }else{
                var img=new Array();
                img=JSON.parse(val2);
                var audio=new Array();
                audio=JSON.parse(val4);
                var video=new Array();
                video=JSON.parse(val5);

                var errorul=$("#errorul");
                errorul.empty();
                if(val2!=null&&val2!=undefined&&img!=null){
                    for(var i=0;i<img.length;i++){
                        var j=parseInt(i)+1;
                        errorul.append("<li class='list-group-item'  onclick=showimg(this,'"+img[i]+"')>图片"+j+"</li>");
                    }
                }
                if(val4!=null&&val4!=undefined&&audio!=null){
                    for(var m=0;m<audio.length;m++){
                        var n=parseInt(m)+1;
                        errorul.append("<li class='list-group-item'  onclick=showaudio(this,'"+audio[m]+"')>音频"+n+"</li>");
                    }
                }
                if(val5!=null&&val5!=undefined&&video!=null){
                    for(var a=0;a<video.length;a++){
                        var b=parseInt(a)+1;
                        errorul.append("<li class='list-group-item'  onclick=showvideo(this,'"+video[a]+"')>视频"+b+"</li>");
                    }
                }
                $("#myModal").modal("show");
            }

        }
        /*显示图片*/
        function showimg(obj,imgs){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);

            $("#pic").empty();
            var pic = document.createElement("img");
            pic.src="report"+imgs;

            pic.width="400";
            pic.height="208";
            $("#pic")[0].appendChild(pic);
        }
        /*显示文字*/
        function showtext(obj,val){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
            $("#pic").empty();
            $("#pic")[0].innerHTML=val;
        }
        /*显示音频*/
        function showaudio(obj,audioval){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
            $("#pic").empty();
            var au = document.createElement('audio');
            au.controls = true;
            au.preload="auto";
            au.loop="loop";
            var source = document.createElement('source');
            au.appendChild(source);
            source.src="report"+audioval;
            source.type="audio/mpeg";
            $("#pic")[0].appendChild(au);

        }
        function showvideo(obj,videoval){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
            $("#pic").empty();
            var video = document.createElement('video');
            video.src = "report"+videoval;
            video.controls = true;
            video.preload="auto";
            video.loop="loop";
            video.width="400";
            video.height="210";

            $("#pic")[0].appendChild(video);
        }
    </script>
</head>
<body>
<div id="div">
    <h3 style="text-align: center">月报(${identifyingid})</h3>
    <table border="1" cellspacing="0" class="table1">
        <caption><h4>当月任务完成情况</h4></caption>
        <tr>
            <td>任务总数：${num0}</td>
            <td>待上传数：${num9}</td>
            <td>正常完成数：${num1}</td>
            <td>未正常完成数：${num8}</td>
            <td>漏检数：${num2}</td>
            <td>超时数：${num3}</td>
        </tr>
        <tr>
            <td colspan="3">周期内平均耗时：${avgTimes}</td>
            <td colspan="3">检查的点位数：${nfcCount}</td>
        </tr>
    </table>
    <table border="1" cellspacing="0" class="table2">
        <caption><h4>非正常任务详情</h4></caption>
        <tr>
            <th>任务编号</th>
            <th>任务状态</th>
            <th>计划开始时间</th>
            <th>责任人</th>
        </tr>
        <c:forEach items="${errortasklist}" var="temp">
            <tr>
                <td>${temp.taskcode}</td>
                <td>
                    <c:if test="${temp.state==0}">
                        未执行
                    </c:if>
                    <c:if test="${temp.operationstate==1}">
                        漏检
                    </c:if>
                    <c:if test="${temp.operationstate==2}">
                        跳检
                    </c:if>
                    <c:if test="${temp.operationstate==4}">
                        <c:if test="${temp.stopstate==0}">
                            超时
                        </c:if>
                        <c:if test="${temp.stopstate==2}">
                            超时(被动终止)
                        </c:if>
                    </c:if>
                    <c:if test="${temp.stopstate==1}">
                        终止(主动)
                    </c:if>
                </td>
                <td><sdf:formatDate value="${temp.executetime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>
                <td>${temp.user.realname}</td>
            </tr>
        </c:forEach>
    </table>
    <div style="height: 50px;width: 500px;text-align: center;margin-left: 500px;">
        <a href="http://${ip}/weekreport?page2=1&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">第一页</a>
        <c:if test="${taskpagebean.curPage>1}">
        <a href="http://${ip}/weekreport?page2=${taskpagebean.curPage-1}&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">上一页</a>
        </c:if>

        <c:if test="${taskpagebean.curPage<taskpagebean.pageCount}">
        <a href="http://${ip}/weekreport?page2=${taskpagebean.curPage+1}&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">下一页</a>
        </c:if>

        <a href="http://${ip}/weekreport?page2=${taskpagebean.pageCount}&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">最后一页</a>

        第${taskpagebean.curPage}页/共${taskpagebean.pageCount}页
    </div>
    <table  border="1" cellspacing="0" class="table1">
        <caption><h4>巡检内容完成情况</h4></caption>
        <tr>
            <td>检查项总数：${num5}</td>
            <td>已完成数：${num6}</td>
            <td>未完成数：${num7}</td>
            <td colspan="2">异常数：${num4}</td>
        </tr>
    </table>

    <table  border="1" cellspacing="0" class="table2">
        <caption><h4>异常详情</h4></caption>
        <tr>
            <th>报告编号</th>
            <th>执行时间</th>
            <th>工人</th>
            <th>异常项</th>
            <th>描述</th>
            <th>异常记录链接</th>
        </tr>
        <c:forEach items="${courseList}" var="content">
            <tr>
                <th colspan="6">发现异常数量：${exceptionCount}</th>
            </tr>
            <tr>
                <td>${content.report.taskcode}</td>

                <td>${content.operationtime}</td>
                <td>${content.report.worker}</td>
                <td>${content.checkname}</td>
                <td>${content.errcontent}</td>
                <td>
                    <c:if test="${content.img != 'null' and content.audio != 'null' and content.video != 'null'}">
                        <a href='http://${ip}/toException?img=${content.img}&audio=${content.audio}&video=${content.video}'  target="_Blank">查看异常详情</a>
                    </c:if>
                    <c:if test="${content.img eq 'null' or content.audio eq 'null' or content.video eq 'null'}">
                        ---
                    </c:if>
                    <%--<a href="javascript:void(0);" onclick=findbystate('${content.img}','${content.audio}','${content.video}')>查看异常详情</a>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="height: 50px;width: 500px;text-align: center;margin-left: 500px;">
        <a href="http://${ip}/weekreport?page1=1&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}&page2=${page2}">第一页</a>
        <c:if test="${temppagebean.curPage>1}">
        <a href="http://${ip}/weekreport?page1=${temppagebean.curPage-1}&date1=${date1}&page2=${page2}&date2=${date2}&type=0&taskidstr=${taskidstr}">上一页</a>
        </c:if>

        <c:if test="${temppagebean.curPage<temppagebean.pageCount}">
        <a href="http://${ip}/weekreport?page1=${temppagebean.curPage+1}&date1=${date1}&page2=${page2}&date2=${date2}&type=0&taskidstr=${taskidstr}">下一页</a>
        </c:if>

        <a href="http://${ip}/weekreport?page1=${temppagebean.pageCount}&date1=${date1}&page2=${page2}&date2=${date2}&type=0&taskidstr=${taskidstr}">最后一页</a>

        第${temppagebean.curPage}页/共${temppagebean.pageCount}页
    </div>
</div>
<%--<div class="ch-container">--%>
    <%--<div class="row">--%>
        <%--&lt;%&ndash;<%@ include file="/WEB-INF/pages/common/menu.jsp"%>&ndash;%&gt;--%>
        <%--<input type="hidden" value="${type}" id="type">--%>
        <%--<div id="content" class="col-lg-12 col-sm-12">--%>
            <%--<div class="row">--%>
                <%--<div class="box col-md-12">--%>
                    <%--<div class="box-inner">--%>
                        <%--<div class="box-header well" data-original-title="">--%>
                            <%--<h2>--%>
                                <%--<i class="glyphicon glyphicon-globe"></i>月报--%>
                            <%--</h2>--%>
                        <%--</div>--%>
                        <%--<div class="box-content">--%>
                            <%--<table class="table table-striped table-bordered table-hover">--%>
                                <%--<caption><h4>当日任务完成情况</h4></caption>--%>
                                <%--<tr>--%>
                                    <%--<td>任务总数：${num0}</td>--%>
                                    <%--<td>正常完成数：${num1}</td>--%>
                                    <%--<td>漏检数：${num2}</td>--%>
                                    <%--<td>超时数：${num3}</td>--%>
                                <%--</tr>--%>
                            <%--</table>--%>
                            <%--<table class="table table-striped table-bordered table-hover">--%>
                                <%--<caption><h4>非正常任务详情</h4></caption>--%>
                                <%--<tr>--%>
                                    <%--<th>任务编号</th>--%>
                                    <%--<th>计划开始时间</th>--%>
                                    <%--<th>责任人</th>--%>
                                <%--</tr>--%>
                                <%--<c:forEach items="${errortasklist}" var="temp">--%>
                                    <%--<tr>--%>
                                        <%--<td>${temp.task.identifyingid}</td>--%>
                                        <%--<td><sdf:formatDate value="${temp.executetime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>--%>
                                        <%--<td>${temp.user.realname}</td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>
                            <%--</table>--%>
                            <%--<div style="height: 50px;width: 500px;text-align: center;margin-left: 500px;">--%>
                                <%--<a href="weekreport?page2=1&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">第一页</a>--%>
                                <%--<c:if test="${taskpagebean.curPage>1}">--%>
                                    <%--<a href="weekreport?page2=${taskpagebean.curPage-1}&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">上一页</a>--%>
                                <%--</c:if>--%>

                                <%--<c:if test="${taskpagebean.curPage<taskpagebean.pageCount}">--%>
                                    <%--<a href="weekreport?page2=${taskpagebean.curPage+1}&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">下一页</a>--%>
                                <%--</c:if>--%>

                                <%--<a href="weekreport?page2=${taskpagebean.pageCount}&page1=${page1}&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}">最后一页</a>--%>

                                <%--第${taskpagebean.curPage}页/共${taskpagebean.pageCount}页--%>
                            <%--</div>--%>
                            <%--<table class="table table-striped table-bordered table-hover">--%>
                                <%--<caption><h4>巡检内容完成情况</h4></caption>--%>
                                <%--<tr>--%>
                                    <%--<td>检查项总数：${num5}</td>--%>
                                    <%--<td>已完成数：${num6}</td>--%>
                                    <%--<td>未完成数：${num7}</td>--%>
                                    <%--<td colspan="2">异常数：${num4}</td>--%>
                                <%--</tr>--%>
                            <%--</table>--%>

                            <%--<table class="table table-striped table-bordered table-hover">--%>
                                <%--<caption><h4>异常详情</h4></caption>--%>
                                <%--<tr>--%>
                                    <%--<th>报告编号</th>--%>
                                    <%--<th>执行时间</th>--%>
                                    <%--<th>工人</th>--%>
                                    <%--<th>异常项</th>--%>
                                    <%--<th>描述</th>--%>
                                    <%--<th>异常记录链接</th>--%>
                                <%--</tr>--%>
                                <%--<c:forEach items="${courseList}" var="content">--%>
                                    <%--<tr>--%>
                                        <%--<td>${content.report.taskcode}</td>--%>
                                        <%--<td>${content.operationtime}</td>--%>
                                        <%--<td>${content.report.worker}</td>--%>
                                        <%--<td>${content.checkname}</td>--%>
                                        <%--<td>${content.errcontent}</td>--%>
                                        <%--<td><a href="javascript:void(0);" onclick=findbystate('${content.img}','${content.audio}','${content.video}')>查看异常详情</a></td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>
                            <%--</table>--%>
                            <%--<div style="height: 50px;width: 500px;text-align: center;margin-left: 500px;">--%>
                                <%--<a href="weekreport?page1=1&date1=${date1}&date2=${date2}&type=0&taskidstr=${taskidstr}&page2=${page2}">第一页</a>--%>
                                <%--<c:if test="${temppagebean.curPage>1}">--%>
                                    <%--<a href="weekreport?page1=${temppagebean.curPage-1}&date1=${date1}&page2=${page2}&date2=${date2}&type=0&taskidstr=${taskidstr}">上一页</a>--%>
                                <%--</c:if>--%>

                                <%--<c:if test="${temppagebean.curPage<temppagebean.pageCount}">--%>
                                    <%--<a href="weekreport?page1=${temppagebean.curPage+1}&date1=${date1}&page2=${page2}&date2=${date2}&type=0&taskidstr=${taskidstr}">下一页</a>--%>
                                <%--</c:if>--%>

                                <%--<a href="weekreport?page1=${temppagebean.pageCount}&date1=${date1}&page2=${page2}&date2=${date2}&type=0&taskidstr=${taskidstr}">最后一页</a>--%>

                                <%--第${temppagebean.curPage}页/共${temppagebean.pageCount}页--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="z-index: 0">--%>
    <%--<div class="modal-dialog" style="z-index: 9999">--%>
        <%--<div class="modal-content" style="overflow: auto;">--%>
            <%--<div id="arealist">--%>
                <%--<div class="modal-header">--%>
                    <%--<button data-dismiss="modal" class="close" type="button">×</button>--%>
                    <%--<h3>显示异常</h3>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>
                    <%--<div id="showerror"style="height: 30%;">--%>
                        <%--<div style="width: 150px;float: left;height: 300px;overflow:auto;">--%>
                            <%--<ul class="list-group" id="errorul">--%>

                            <%--</ul>--%>
                        <%--</div>--%>
                        <%--<div id="pic" style="border: 1px solid #F3F3F3; width: 400px;height: 100%;float: left;padding-left: 25px;">--%>

                        <%--</div>--%>
                        <%--<div class="clearfix" style="display: none;"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<a data-dismiss="modal" class="btn btn-default" href="#">取消</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>



</body>
</html>
