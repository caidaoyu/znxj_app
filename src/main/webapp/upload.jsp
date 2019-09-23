<%--&lt;%&ndash;--%>
<%--Created by IntelliJ IDEA.--%>
<%--User: MrD--%>
<%--Date: 2017/4/10--%>
<%--Time: 15:07--%>
<%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>智能巡检系统</title>--%>
<%--</head>--%>

<%--<body>--%>
<%--<form action="/file/upload" enctype="multipart/form-data" method="post">--%>
<%--上传用户：--%>
<%--<input type="text" name="username"><br/>--%>
<%--上传文件1：<input type="file" name="file1"><br/>--%>
<%--上传文件2：<input type="file" name="file2"><br/>--%>
<%--<input type="submit" value="提交">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript" src="js/formatdate.js"></script>
    <style>
        .selected{background: #35A7E7;
            color: white;}
    </style>
    <script type="text/javascript">
        $(function(){
            var aa=JSON.parse($("#reportcontent").val());
            var first = $("#table1 tr:first");
            $("#table1").empty();
            $("#table1").append(first);

            var operationstate;
            var state;
            var reportstate;
            var error;
            var errortext;
            for(var i=0;i<aa.length;i++){
                var image=aa[i].img;
                var audio=aa[i].audio;
                var video=aa[i].video;
                reportstate=aa[i].reportstate;
                errortext=aa[i].errcontent;
                if(reportstate==0){
                    error="-";
                }else if(reportstate==1){
                    /*if(errortext!=null&&errortext!=""){
                     var errorarry = JSON.parse(errortext);
                     error=errorarry[2];
                     }*/
                    if($("#reporttype").val()==2){
                        var errorarry = JSON.parse(errortext);
                        error=errorarry[2];
                    }else
                        error=errortext;
                }
                /*判断区域和设备是否跳过*/
                var html = "";
                var areanaame=aa[i].areaname;
                var skiparea;
                var equipname=aa[i].equipname;
                var skipequipname;
                /*设置巡检项的状态*/
                var checktype=aa[i].checktype;
                var checktype2;
                if(checktype==1){
                    checktype2="状态项";
                }else{
                    checktype2="记录项";
                }
                /*设置最低值和最高值*/
                var normalmin=aa[i].normalmin;
                var normalmin1;
                if(normalmin=='' || normalmin==undefined){
                    normalmin1="-";
                }else{
                    normalmin1=normalmin;
                }
                var normalmax=aa[i].normalmax;
                var normalmax1;
                if(normalmax=='' || normalmax==undefined){
                    normalmax1="-";
                }else{
                    normalmax1=normalmax;
                }

                var lowerwarning=aa[i].lowerwarning;
                var lowerwarning1;
                if(lowerwarning=='' || lowerwarning==undefined){
                    lowerwarning1="-";
                }else{
                    lowerwarning1=lowerwarning;
                }
                var upperwarning=aa[i].upperwarning;
                var upperwarning1;
                if(upperwarning=='' || upperwarning==undefined){
                    upperwarning1="-";
                }else{
                    upperwarning1=upperwarning;
                }
                var operationtime=aa[i].operationtime;
                var operatetime;
                if(operationtime=='' || operationtime==undefined){
                    operatetime="-";
                }else{
                    operatetime=new Date(operationtime).format("yyyy-MM-dd hh:mm:ss");
                }

                if(aa[i].areaskip=="1"){
                    if(aa[i].areaskipdesc==undefined){
                        skiparea=areanaame;
                    }else{
                        skiparea=areanaame+"("+aa[i].areaskipdesc+")";
                    }
                    if(aa[i].equipmentskip=="1"){
                        if(aa[i].equipmentskipdesc==undefined){
                            skipequipname=equipname;
                        }else{
                            skipequipname=equipname+"("+aa[i].equipmentskipdesc+")";
                        }
                        html="<tr><td style='color:red;'>"+skiparea+
                                "</td><td style='color:red;'>"+skipequipname+"</td><td>"+aa[i].checkname+
                                "</td><td>"+checktype2+"</td><td>"+operatetime+"</td><td>"+normalmin1+
                                "</td><td>"+normalmax1+"</td><td>"+lowerwarning1+"</td><td>"+upperwarning1+"</td><td>"+(aa[i].numvalue==''?'-':aa[i].numvalue)+
                                "</td><td>"+(aa[i].recordname==''?'-':aa[i].recordname)+"</td><td>"+(aa[i].unitname==''?'-':aa[i].unitname)+
                                "</td><td><a href='javascript:void(0)' onclick=findbystate('"+error+"','"+image+"','"+audio+"','"+video+"')>"+error+"</a></td></tr>";
                    }else {
                        skipequipname=equipname;
                        html = "<tr><td style='color:red;'>" + skiparea +
                                "</td><td>" + skipequipname + "</td><td>" + aa[i].checkname +
                                "</td><td>"+checktype2+"</td><td>"+operatetime+"</td><td>"+normalmin1+
                                "</td><td>"+normalmax1+"</td><td>"+lowerwarning1+"</td><td>"+upperwarning1+"</td><td>" + (aa[i].numvalue == '' ? '-' : aa[i].numvalue) +
                                "</td><td>" + (aa[i].recordname == '' ? '-' : aa[i].recordname) + "</td><td>" + (aa[i].unitname == '' ? '-' : aa[i].unitname) +
                                "</td><td><a href='javascript:void(0)' onclick=findbystate('" + error + "','" + image + "','" + audio + "','" + video + "')>" + error + "</a></td></tr>";
                    }
                }else{
                    skiparea=areanaame;
                    if(aa[i].equipmentskip=="1"){
                        skipequipname=equipname+"("+aa[i].equipmentskipdesc+")";
                        html="<tr><td>"+skiparea+
                                "</td><td style='color:red;'>"+skipequipname+"</td><td>"+aa[i].checkname+
                                "</td><td>"+checktype2+"</td><td>"+operatetime+"</td><td>"+normalmin1+
                                "</td><td>"+normalmax1+"</td><td>"+lowerwarning1+"</td><td>"+upperwarning1+"</td><td>"+(aa[i].numvalue==''?'-':aa[i].numvalue)+
                                "</td><td>"+(aa[i].recordname==''?'-':aa[i].recordname)+"</td><td>"+(aa[i].unitname==''?'-':aa[i].unitname)+
                                "</td><td><a href='javascript:void(0)' onclick=findbystate('"+error+"','"+image+"','"+audio+"','"+video+"')>"+error+"</a></td></tr>";
                    }else {
                        skipequipname=equipname;
                        html = "<tr><td>" + skiparea +
                                "</td><td>" + skipequipname + "</td><td>" + aa[i].checkname +
                                "</td><td>"+checktype2+"</td><td>"+operatetime+"</td><td>"+normalmin1+
                                "</td><td>"+normalmax1+"</td><td>"+lowerwarning1+"</td><td>"+upperwarning1+"</td><td>" + (aa[i].numvalue == '' ? '-' : aa[i].numvalue) +
                                "</td><td>" + (aa[i].recordname == '' ? '-' : aa[i].recordname) + "</td><td>" + (aa[i].unitname == '' ? '-' : aa[i].unitname) +
                                "</td><td><a href='javascript:void(0)' onclick=findbystate('" + error + "','" + image + "','" + audio + "','" + video + "')>" + error + "</a></td></tr>";
                    }
                }
                $("#table1").append(html);
            }
        })
        function findbystate(val,val2,val4,val5){
            //val是否有异常  val2图片  val3文字  val4音频
            if("-"==val){
                alert("无异常");
                return false;
            }else{
                var img=new Array();
                img=val2.split(",");
                var audio=new Array();
                audio=val4.split(",");
                var video=new Array();
                video=val5.split(",");

                var errorul=$("#errorul");
                errorul.empty();
                errorul.append("<li class='list-group-item'  onclick=showtext(this,'"+val+"')>描述</li>");
                if(val2!=null&&val2!="undefined"){
                    for(var i=0;i<img.length;i++){
                        var j=parseInt(i)+1;
                        errorul.append("<li class='list-group-item'  onclick=showimg(this,'"+img[i]+"')>图片"+j+"</li>");
                    }
                }
                if(val4!=null&&val4!="undefined"){
                    for(var m=0;m<audio.length;m++){
                        var n=parseInt(m)+1;
                        errorul.append("<li class='list-group-item'  onclick=showaudio(this,'"+audio[m]+"')>音频"+n+"</li>");
                    }
                }
                if(val5!=null&&val5!="undefined"){
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
//            au.loop="loop";
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
//            video.loop="loop";
            video.width="400";
            video.height="210";

            $("#pic")[0].appendChild(video);
        }
        function refer(){
            var type=$("#reporttype").val();
            window.location="showallreport1?page=1&tasktype="+type;
        }
    </script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <input type="hidden" id="reporttype" value="${type}">
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <div style="float: left;">
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i>
                                    <c:if test="${type==0}">日常巡检任务--列表--</c:if>
                                    <c:if test="${type==1}">计划巡检任务--列表--</c:if>
                                    <c:if test="${type==2}">HSE隐患排查--列表--</c:if>
                                    <c:if test="${type==3}">视频巡检任务--列表--</c:if>
                                    ${fn:substring(taskreportinfo.taskcode,0,taskreportinfo.taskcode.indexOf('-'))}(${taskreportinfo.taskcode})
                                </h2>
                            </div>
                            <div style="float: right;">
                                <a href="aa?id=${taskreportinfo.id}" class="btn btn-primary" style="margin-top: -6px;">导出Excel</a>
                            </div>
                            <div class="clearfix"></div>



                        </div>
                        <div class="box-content">
                            <div  style="height: 500px;overflow: auto;">
                                <input type="hidden" name="content" id="reportcontent" value='${taskreportinfo.content}'>
                                <table class="table table-striped table-bordered table-hover responsive dataTable" id="table1">
                                    <tr style="text-align: center">
                                        <th>区域</th>
                                        <th>设备</th>
                                        <th>巡检项</th>
                                        <th>巡检项类型</th>
                                        <th>执行时间</th>
                                        <th>最低值</th>
                                        <th>最高值</th>
                                        <th>下线警告值</th>
                                        <th>上限警告值</th>

                                        <th>报告值</th>
                                        <th>数据名称</th>
                                        <th>单位</th>
                                        <th>异常描述</th>
                                    </tr>
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
                        <div id="pic" style="border: 1px solid #F3F3F3; width: 400px;height: 100%;float: left;padding-left: 25px;">

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
