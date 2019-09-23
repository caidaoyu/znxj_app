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
    <meta name="renderer" content="webkit" />
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
        .modal-footer{
            border-top: 1px  #ffffff !important;
        }
    </style>
   <%-- <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script>
        function findbystate(val2,val4,val5){
//            debugger;
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
                $("#myModal1").modal("show");
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
    </script>--%>
</head>
<body>
<div id="div">
    <h3 style="text-align: center">${identifyingid} -- 异常报告</h3>

    <table  border="1" cellspacing="0" class="table2">
        <caption><h4>异常详情</h4></caption>
        <tr>
            <th>报告编号</th>
            <th>执行时间</th>
            <th>工人</th>
            <th>区域</th>
            <th>设备</th>
            <th>异常项</th>
            <th>描述</th>
            <th>异常记录链接</th>
        </tr>

        <c:forEach items="${courseList}" var="content">
            <tr>
                <td>${content.report.taskcode}</td>
                <td>${content.operationtime}</td>
                <td>${content.report.worker}</td>
                <td>${content.areaname}</td>
                <td>${content.equipname}</td>
                <td>${content.checkname}</td>
                <td>${content.errcontent}</td>
                <td>
                    <c:if test="${content.img != 'null' or content.audio != 'null' or content.video != 'null'}">
                        <a href='http://${ip}/toException?img=${content.img}&audio=${content.audio}&video=${content.video}'  target="_Blank">查看异常详情</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="height: 50px;width: 500px;text-align: center;margin-left: 500px;">
        <a href="http://${ip}/exceptionReport?page1=1&reportId=${reportId}">第一页</a>
        <c:if test="${temppagebean.curPage>1}">
            <a href="http://${ip}/exceptionReport?page1=1&reportId=${reportId}">上一页</a>
        </c:if>

        <c:if test="${temppagebean.curPage<temppagebean.pageCount}">
            <a href="http://${ip}/exceptionReport?page1=1&reportId=${reportId}">下一页</a>
        </c:if>

        <a href="http://${ip}/exceptionReport?page1=1&reportId=${reportId}">最后一页</a>

        第${temppagebean.curPage}页/共${temppagebean.pageCount}页
    </div>
</div>

<%--<div aria-hidden="false" hidden aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal1" class="modal fade in" style="z-index: 0">
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
                        <div id="pic" style="border: 1px solid #F3F3F3; width: 400px;height: 300px;float: left;">

                        </div>
                        &lt;%&ndash;<div class="clearfix" style="display: none;"></div>&ndash;%&gt;
                    </div>
                </div>
                <div class="modal-footer">
                    <a data-dismiss="modal" style="margin-top:20px" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>--%>
</body>
</html>
