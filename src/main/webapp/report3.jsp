<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp" %>
    <style>
        .selected {
            background: #35A7E7;
            color: white;
        }

        .fontcenter {
            text-align: center !important;
            vertical-align: middle;
        }

        #testalert {
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            width: 200px;
        }
    </style>
    <%--<script src="/js/jquery-1.11.0.min.js"></script>--%>
    <script type="text/javascript">
        var count;
        //浮点数值
        function CheckInputIntFloat(oInput) {
            if('' != oInput.value.replace(/-?\d{1,}\.{0,1}\d{0,}/,'')) {
                oInput.value = oInput.value.match(/-?\d{1,}\.{0,1}\d{0,}/) == null ? '' :oInput.value.match(/-?\d{1,}\.{0,1}\d{0,}/);
            }
        }
        $(function () {
            var x = document.getElementById('table').rows[0].cells;
            var len = $("#statuslen").val();
            for (var i = 0; i < len; i++) {
                var oldErrorContent = $("#error"+i).text();
                var imgContent = new Array();
                imgContent=$("#img"+i).val();
                var audioContent =  new Array();
                audioContent=$("#audio"+i).val();
                var videoContent =  new Array();
                videoContent=$("#video"+i).val();
                var newErrorContent = "";
                if(oldErrorContent!="-"){
                    $("#error"+i).empty();
                    if(oldErrorContent.indexOf("/") > 0 ){
                        var ss = oldErrorContent.substring(0,oldErrorContent.indexOf("/"));
                        var ff = oldErrorContent.substring(oldErrorContent.indexOf("/")+1,oldErrorContent.length);
                        $("#error"+i).append("<span style='color: red;'>"+ss+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style='color: blue;'>"+ff+"</span>");
                    }else {
                        $("#error"+i).append("<a href='javascript:void(0);' onclick=showimg('"+imgContent+"','"+audioContent+"','"+videoContent+"')>"+oldErrorContent+"</a>");
                    }

                }
            }
            var y = document.getElementById('table').rows;
            var reportid = $("#reportid").val();
            $.ajax({
                url: "getReportContentByReportId",
                type: "post",
                data: {
                    taskreportid: reportid,
                },
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        for (var i = 0; i < data.length; i++) {
                            var presentNumValue = data[i].numvalue;
                            var nowNumValue = $("#numvalue" + i).text();
//                            nowNumValue / presentNumValue < (1-fluctudte)
//                            nowNumValue / presentNumValue > (1+fluctudte)
//                            if (nowNumValue != "") {
//                                var fluctudte = $("#maxval").val();
//                                if (Math.abs(nowNumValue - presentNumValue) / Math.abs(presentNumValue) > fluctudte) {
//                                    if ((nowNumValue - presentNumValue) > 0)
//                                        $("#error" + i).append("<a href='javascript:void(0)' onmouseover='nnn(this)'><span style='color:red;'>" + "↑↑" + "</span></a>");
//                                    else
//                                        $("#error" + i).append("<a href='javascript:void(0)'><span style='color:red;'>" + "↓↓" + "</span></a>");
//                                } else
//                                    $("#error" + i).append("正常");
//                            }
                            if(data[i].checkvalue==null||data[i].checkvalue==''){
                                $("#first" + i).text("-");
                            }else
                                $("#first" + i).text(data[i].checkvalue);
//                            }
                        }
                        return true;
                    } else {
                        alert("出错了");
                        return false;
                    }
                }
            });
        })
        //        function exportexcel(taskid,type){
        //            var taskname=$("#taskname").val();
        //            var donetime=$("#donetime").val();
        //            window.location="exportExcel?type="+type+"&taskid="+taskid+"&taskname="+taskname+"&donetime="+donetime;
        //        }
        /*var imgs=new Array();
        var audios=new Array();
        var videos=new Array();
        imgs=JSON.parse(img);
        if (imgs.length > 0) {

        }*/
        function showimg(img,audio,video){
            var imgs=new Array();
            var audios=new Array();
            var videos=new Array();
            if(""!=img && img!="undefined"&&img!="null"&&img!="[]"){
                imgs=JSON.parse(img);
            }else{
                imgs="";
            }

            if(""!=audio && audio!="undefined"&&audio!="null" && audio!="[]"){
                audios=JSON.parse(audio);
            }else{
                audios="";
            }
            if(""!=video && video!="undefined" &&video!="null" && video!="[]"){
                videos=JSON.parse(video);
            }else{
                videos="";
            }
            var errorul=$("#errorul");
            errorul.empty();
            if(imgs==""&&audios==""&&videos=="" ){
                alert("暂时没有其他异常信息");
                return;
            }
            if(imgs!=null && ""!=imgs) {
                for (var i = 0; i < imgs.length; i++) {
                    var j = parseInt(i) + 1;
                    errorul.append("<li id ='imgLiLJJ"+i+"' class='list-group-item'  onclick=showimg(this,'" + imgs[i] + "')>图片" + j + "</li>");
                }
                var firstImgLi = document.getElementById("imgLiLJJ0");
                showimg(firstImgLi, imgs[0]);
            }
            if(audios!=null && ""!=audios){
                for (var m = 0; m < audios.length; m++) {
                    var n = parseInt(m) + 1;
                    errorul.append("<li id ='audioLiLJJ"+m+"' class='list-group-item'  onclick=showaudio(this,'" + audios[m] + "')>音频" + n + "</li>");
                }
                if(img ==null && img == undefined) {
                    var firstAudioLi = document.getElementById("audioLiLJJ0");
                    showaudio(firstAudioLi, audios[0]);
                }
            }
            if(videos!=null && ""!=videos){
                for (var a = 0; a < videos.length; a++) {
                    var b = parseInt(a) + 1;
                    errorul.append("<li id ='videoLiLJJ"+a+"' class='list-group-item'  onclick=showvideo(this,'" + videos[a] + "')>视频" + b + "</li>");
                }
                if(img ==null && img == undefined && audio ==null && audio == undefined) {
                    var firstVideoLi = document.getElementById("videoLiLJJ0");
                    showvideo(firstVideoLi, videos[0]);
                }
            }
            $("#myModal3").modal("show");
//            $("#arealist").css("display","block");
//            $("#dotime").css("display","none");

        }
        function showimg(obj,imgs){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
            $("#pic").empty();
            var pic = document.createElement("img");
            pic.src="report"+imgs;

            $(pic).css("width","auto");
            $(pic).css("height","auto");
            $(pic).css("max-width","100%");
            $(pic).css("max-height","100%");

            $("#pic")[0].appendChild(pic);
        }
        function showaudio(obj, audioval) {
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);

            $("#pic").empty();
            var au = document.createElement('audio');
            au.controls = true;
            au.preload = "auto";
//            au.loop="loop";
            var source = document.createElement('source');
            au.appendChild(source);
            source.src = "report" + audioval;
            source.type = "audio/mpeg";
            $("#pic")[0].appendChild(au);
        }
        function showvideo(obj, videoval) {
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
            $("#pic").empty();
            var video = document.createElement('video');
            video.src = "report" + videoval;
            video.controls = true;
            video.preload = "auto";
            $(video).css("width","100%");
            $(video).css("height","100%");
//            $(video).css("max-width","100%");
//            $(video).css("max-height","100%");

//            video.loop="loop";
//            video.width = "600";
//            video.height = "400";

            $("#pic")[0].appendChild(video);
        }
        function ObjStory(id, checkvalue) {
            this.id = id;
            this.checkvalue = checkvalue;
        }
        function toValidate() {
            $("#myModal").modal("show");
            $("#dotime").css("display", "none");
            $("#examvalidate").css("display", "block");
//            $("#toexam").css("display", "none");
            $("#examuser").val("");
            $("#exampwd").val("");
        }
        function submitValidate() {
            $("#myModal2").modal("hide");
            var examuser = $("#examuser").val();
            var exampwd = $("#exampwd").val();

            if (examuser == "") {
                alert("用户名不能为空");
                return false;
            }
            if (exampwd == "") {
                alert("密码不能为空");
                return false;
            }
            if (!examuser == "" && !exampwd == "") {
                $.ajax({
                    url: "/queryUserPower?examUser=" + examuser + "&examPwd=" + exampwd,
                    type: "get",
                    dataType:"json",
//                    scriptCharset: 'utf-8',
//                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (data) {
                        $("#myModal").modal("hide");
                        if (data.success) {
                            $("#myModal2").modal("show");
                            $("#toexam").css("display", "block");
                            $("#examuser2").val(data.data);
                        } else {
                            alert(data.message);
                            return false;
                        }
                    }
                });
            }
        }
        function check() {
            $("#myModal2").modal("hide");
            var examuser = $("#examuser2").val();
//            alert(examuser);
            if (examuser == ''){
                alert("复核人不能为空");
                return false;
            }
            var Context = "";
            var table = $("#table");
            var reportcontents = [];
            table.find("tr").each(function () {
                if ($(this)[0].rowIndex != 0) {
                    var arrtd = $(this).children();
                    reportcontents.push(new ObjStory(arrtd.eq(0).text(), $(this).children().last().children().val()));
                }
            })
            var json = JSON.stringify(reportcontents);
            $.ajax({
                url: "updReportContent",
                type: "post",
                data: {
                    reportData: json,
                    reportId: $("#oldreportid").val(),
                    examuser: examuser,
                    taskcode:$("#taskcode").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data > 0) {
                        alert("复核成功");
                        showReportDetail($("#oldreportid").val(), $("#oldtype").val());
                        return true;
                    } else {
                        alert("复核失败");
                        return false;
                    }
                }
            });
        }
        function showReportDetail(id, type) {
            window.location = "querytaskreportdetail?id=" + id + "&type=" + type+"&page="+$("#page").val()+"&type2=1";
        }

        function exportexcel() {
            $("#myModal").modal("show");
            $("#dotime").css("display", "block");
            $("#examvalidate").css("display", "none");
//            $("#toexam").css("display", "none");
        }
        function exportexce2(id) {
            $("#myModal").modal("hide");
            var exportcontentlist = "";
            var temp = "";
            var exportcontent = document.getElementsByName("exportcontent");
            for (var i = 0; i < exportcontent.length; i++) {
                if (exportcontent[i].checked) {
                    temp = exportcontent[i].value;
                    exportcontentlist = exportcontentlist + "," + temp;
                }
            }
            var checkedexportcontent = exportcontentlist.substring(1, exportcontentlist.length);
            if (checkedexportcontent == "") {
                alert("请选择导出的字段");
                return false;
            } else {
                window.location = "aa?id=" + id + "&checkcontent=" + checkedexportcontent;
                return true;
            }
        }
        function selectAll() {
            var isCheck = $("#sel_1").is(':checked');  //获得全选复选框是否选中
            $("input[type='checkbox']").each(function () {
                this.checked = isCheck;       //循环赋值给每个复选框是否选中
            });
        }
        function nnn(obj) {
            obj.title = "报告值与前次复核列的值波动超过20% (绝对值(报告值 - 前次复核列) / 报告值 > 20%)";
        }
//        function mmm(obj) {
//            obj.title = "报告值与前次复核列的值波动超过20% (绝对值(报告值 - 前次复核列) / 报告值 > 20%)";
//        }
    </script>
</head>
<body>
<%--<div id='testalert'>--%>
<%--aaaaaa--%>
<%--</div>--%>
<input type="hidden" value="${taskreport.id}" id="reportid">
<input type="hidden" value="${taskreportid}" id="oldreportid">
<input type="hidden" value="${type}" id="oldtype">
<input type="hidden" value="${FLUCTUATE}" name="FLUCTUATE" id="maxval">
<input type="hidden" value="${page}" name="page" id="page">
<input type="hidden" value="${taskcode}"  id="taskcode">
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li>
                                <a href="showtaskplan?page=1&type=${type}">任务列表</a>
                            </li>
                            <li class="active"><a href="#report" data-toggle="tab" style="background: #35A7E7;"
                                                  onclick="refer()">任务报告</a></li>
                            <c:if test="${type==2}">
                                <li><a href="showQuickReport1?page=1&type=${type}&qtype=1">立即上报</a></li>
                            </c:if>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="report">
                            <div class="box-inner" style="height: 700px !important;overflow: scroll">
                                <div class="box-header well" data-original-title="">
                                    <div style="float: left;">
                                        <h2>
                                            <i class="glyphicon glyphicon-globe"></i>
                                            <c:if test="${type==0}">日常巡检任务--列表--</c:if>
                                            <c:if test="${type==1}">计划巡检任务--列表--</c:if>
                                            <c:if test="${type==2}">HSE隐患排查--列表--</c:if>
                                            <c:if test="${type==3}">视频巡检任务--列表--</c:if>
                                            ${fn:substring(taskcode,0,taskcode.indexOf('-'))}(${taskcode})
                                        </h2>
                                    </div>
                                    <div style="float: right;">
                                        <c:if test="${type2==1}">
                                            <a href="/firstPage?page=${page}" style="margin-top: -6px;mar0gin-right: 10px;"
                                               class="btn btn-primary">返回</a>
                                        </c:if>
                                        <c:if test="${type2==2}">
                                            <a href="javascript:void(0)" class="btn btn-primary" style="margin-top: -6px;"
                                               onclick="javascript:history.go(-1);">返回</a>
                                        </c:if>
                                        <a href="javascript:void(0)" class="btn btn-primary" style="margin-top: -6px;"
                                           onclick="exportexcel()">导出Excel</a>

                                        <c:if test="${examtime==0}">
                                            <%--<shiro:hasPermission name="item:reportexam">--%>
                                                <a href="javascript:void(0)" class="btn btn-primary"
                                                   style="margin-top: -6px;" onclick="toValidate()">复核</a>
                                            <%--</shiro:hasPermission>--%>
                                        </c:if>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="box-content">
                                    <table id="table" class="table table-striped table-bordered table-hover ">
                                        <tr>
                                            <c:if test="${areaname==1}">
                                                <th class="fontcenter">区域</th>
                                            </c:if>
                                            <c:if test="${equipname==1}">
                                                <th class="fontcenter">设备</th>
                                            </c:if>
                                            <c:if test="${checkname==1}">
                                                <th class="fontcenter">巡检项</th>
                                            </c:if>
                                            <c:if test="${checktype==1}">
                                                <th class="fontcenter">巡检项类型</th>
                                            </c:if>
                                            <%--<c:if test="${unitname==1}">--%>
                                                <th class="fontcenter">单位</th>
                                            <%--</c:if>--%>
                                            <c:if test="${operationtime==1}">
                                                <th class="fontcenter">执行时间</th>
                                            </c:if>
                                            <c:if test="${normalmin==1}">
                                                <th class="fontcenter">低值</th>
                                            </c:if>
                                            <c:if test="${normalmax==1}">
                                                <th class="fontcenter">高值</th>
                                            </c:if>
                                            <c:if test="${lowerwarning==1}">
                                                <th class="fontcenter">告警下限</th>
                                            </c:if>
                                            <c:if test="${upperwarning==1}">
                                                <th class="fontcenter">告警上限</th>
                                            </c:if>
                                            <c:if test="${numvalue==1}">
                                                <th class="fontcenter">报告值</th>
                                            </c:if>
                                            <c:if test="${errcontent==1}">
                                                <th class="fontcenter">异常描述</th>
                                            </c:if>
                                            <c:if test="${recordname==1}">
                                                <th class="fontcenter">数据名称</th>
                                            </c:if>
                                            <c:if test="${unitname==1}">
                                                <th class="unitname">单位</th>
                                            </c:if>
                                            <c:if test="${firstval==1}">
                                                <c:if test="${taskreport==null}">
                                                    <th class="fontcenter">前次复核值</th>
                                                </c:if>
                                                <c:if test="${taskreport.examstate==0}">
                                                    <th class="fontcenter">前次复核值</th>
                                                </c:if>
                                                <c:if test="${taskreport.examstate==1}">
                                                    <th class="fontcenter">前次复核值(人工)</th>
                                                </c:if>
                                                <c:if test="${taskreport.examstate==2}">
                                                    <th class="fontcenter">前次复核值(自动)</th>
                                                </c:if>
                                                <%--<th class="fontcenter">前次复核值</th>--%>
                                            </c:if>
                                            <c:if test="${checkvalue==1}">
                                                <th class="fontcenter">复核值</th>
                                            </c:if>
                                        </tr>
                                        <c:forEach items="${reportinfos}" var="reports" varStatus="status">
                                            <input type="hidden" id="img${status.index}" value='${reports.img}'>
                                            <input type="hidden" id="audio${status.index}" value='${reports.audio}'>
                                            <input type="hidden" id="video${status.index}" value='${reports.video}'>
                                            <tr>
                                                <td style="display: none">${reports.id}</td>
                                                <c:if test="${areaname==1}">
                                                    <td class="fontcenter">
                                                        <c:if test="${reports.areaskip==1}">
                                                            <span style="color: red">${reports.areaname}(${reports.areaskipdesc})</span>
                                                        </c:if>
                                                        <c:if test="${reports.areaskip==0}">
                                                            <span>${reports.areaname}</span>
                                                        </c:if>
                                                    </td>
                                                </c:if>
                                                <c:if test="${equipname==1}">
                                                    <td class="fontcenter">
                                                        <c:if test="${reports.equipmentskip==1}">
                                                            <span style="color: red">${reports.equipname}(${reports.equipmentskipdesc})</span>
                                                        </c:if>
                                                        <c:if test="${reports.equipmentskip==0}">
                                                            <span>${reports.equipname}</span>
                                                        </c:if>
                                                    </td>
                                                </c:if>
                                                <c:if test="${checkname==1}">
                                                    <td class="fontcenter">
                                                        ${reports.checkname}
                                                    </td>
                                                </c:if>

                                                <c:if test="${checktype==1}">
                                                    <td class="fontcenter">${reports.checktype}</td>
                                                </c:if>
                                                <%--<c:if test="${unitname == 1}">--%>
                                                    <td class="fontcenter">${reports.unitname}</td>
                                                <%--</c:if>--%>
                                                <c:if test="${operationtime==1}">
                                                    <td class="fontcenter">${reports.operationtime}</td>
                                                </c:if>
                                                <c:if test="${normalmin==1}">
                                                    <td class="fontcenter">${reports.normalmin}</td>
                                                </c:if>
                                                <c:if test="${normalmax==1}">
                                                    <td class="fontcenter">${reports.normalmax}</td>
                                                </c:if>
                                                <c:if test="${lowerwarning==1}">
                                                    <td class="fontcenter">${reports.lowerwarning}</td>
                                                </c:if>
                                                <c:if test="${upperwarning==1}">
                                                    <td class="fontcenter">${reports.upperwarning}</td>
                                                </c:if>
                                                <c:if test="${numvalue==1}">
                                                    <td class="fontcenter"id="numvalue${status.index}">
                                                        <c:if test="${reports.checktype == '枚举项'}">
                                                            <c:if test="${reports.enumitem == ''}">-</c:if>
                                                            <c:if test="${reports.enumitem != ''}">${reports.enumitem}</c:if>
                                                        </c:if>
                                                        <c:if test="${reports.checktype == '记录项'}">
                                                            <c:if test="${reports.numvalue == ''}">-</c:if>
                                                            <c:if test="${reports.numvalue != ''}">${reports.numvalue}</c:if>
                                                        </c:if>
                                                        <c:if test="${reports.checktype == '状态项'}">
                                                            <c:if test="${reports.areaskipdesc != null or reports.equipmentskipdesc != null}">
                                                                -
                                                                <%--<c:if test="${reports.reportstate == ''}">-</c:if>--%>
                                                            </c:if>

                                                            <c:if test="${reports.areaskipdesc == null and reports.equipmentskipdesc == null}">
                                                                <c:if test="${reports.reportstate == ''}">-</c:if>
                                                                <c:if test="${reports.reportstate != ''}">
                                                                    <c:if test="${reports.reportstate == 1}">
                                                                        异常
                                                                    </c:if>
                                                                    <c:if test="${reports.reportstate == 0}">
                                                                        正常
                                                                    </c:if>
                                                                </c:if>
                                                            </c:if>

                                                        </c:if>

                                                    </td>
                                                </c:if>
                                                <c:if test="${errcontent==1}">
                                                    <td class="fontcenter" id="error${status.index}">${reports.errcontent}</td>
                                                        <%--<a href="javascript:void(0);" onclick="showimg('${reports.imgs}','${reports.audio}','${reports.video}')">${reports.errcontent}--%>
                                                        <%--</a>--%>

                                                </c:if>
                                                <c:if test="${recordname==1}">
                                                    <td class="fontcenter">${reports.recordname}</td>
                                                </c:if>
                                                <c:if test="${unitname==1}">
                                                    <td class="fontcenter">${reports.unitname}</td>
                                                </c:if>
                                                <c:if test="${firstval==1}">
                                                    <td class="fontcenter" id="first${status.index}">
                                                        <c:if test="${taskreport==null}">-</c:if>
                                                    </td>
                                                </c:if>
                                                <c:if test="${checkvalue==1}">
                                                    <td class="fontcenter">
                                                        <c:if test="${reports.checktype=='记录项'}">
                                                            <c:if test="${taskreportinfo.examstate==0}">
                                                                <input type="text" onkeyup="javascript:CheckInputIntFloat(this);" style="width: 50px"
                                                                       value="${reports.numvalue}">
                                                            </c:if>
                                                            <c:if test="${taskreportinfo.examstate==1}">
                                                                <c:if test="${reports.checkvalue!=reports.numvalue}">
                                                                    <span style="color: white"><input type="number" style="width: 50px;background:red;color: white;text-align: center;border:0;" value="${reports.checkvalue}" readonly></span>
                                                                </c:if>
                                                                <c:if test="${reports.checkvalue==reports.numvalue}">
                                                                    <input type="number" style="width: 50px;text-align: center;background:#f9f9f9;border:0;"
                                                                           value="${reports.checkvalue}" readonly>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${taskreportinfo.examstate==2}">
                                                                <input type="number" style="width: 50px;text-align: center;background:#f9f9f9;border:0;"
                                                                           value="${reports.checkvalue}" readonly>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${reports.checktype=='状态项'}">-</c:if>
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                        <input type="hidden" id="statuslen" value="${fn:length(reportinfos)}">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in"
     style="z-index: 0">
    <div class="modal-dialog" style="z-index: 9999">
        <div class="modal-content" style="overflow: auto;">
            <div id="dotime">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>选择导出字段</h3>
                </div>
                <div class="modal-body">
                    选择导出字段:<br/>
                    <input id="sel_1" onchange="selectAll()" type="checkbox" value="1"/>全选/全不选<br>
                    任务名称<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="任务名称">
                    任务编号<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="任务编号">
                    工人<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="工人">
                    <c:if test="${areaname==1}">
                        区域<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="区域">
                    </c:if>
                    <%--区域<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="区域">--%>
                    <c:if test="${equipname==1}">
                        设备<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="设备">
                    </c:if>
                    <%--设备<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="设备"><br>--%>
                    <c:if test="${checkname==1}">
                        巡检项<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="巡检项"><br>
                    </c:if>
                    <%--巡检项<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="巡检项">--%>
                    <c:if test="${checktype==1}">
                        巡检项类型<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="巡检项类型">
                    </c:if>
                    <%--巡检项类型<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="巡检项类型">--%>
                    <c:if test="${normalmin==1}">
                        正常最低值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="正常最低值">
                    </c:if>
                    <%--正常最低值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="正常最低值">--%>
                    <c:if test="${normalmax==1}">
                        正常最高值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="正常最高值">
                    </c:if>
                    <%--正常最高值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="正常最高值">--%>
                    <c:if test="${lowerwarning==1}">
                        下限警告值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="下限警告值"><br>
                    </c:if>
                    <%--下限警告值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"--%>
                                <%--value="下限警告值"><br>--%>
                    <c:if test="${upperwarning==1}">
                        上限警告值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;"value="上限警告值">
                    </c:if>
                    <%--上限警告值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="上限警告值">--%>
                    <c:if test="${numvalue==1}">
                        报告值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="报告值">
                    </c:if>
                    <%--报告值<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="报告值">--%>
                    <c:if test="${errcontent==1}">
                        异常描述<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="异常描述">
                    </c:if>
                    <%--异常描述<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="异常描述">--%>
                    <c:if test="${recordname==1}">
                        数据名称<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="数据名称">
                    </c:if>
                    <c:if test="${unitname==1}">
                        单位<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="单位">
                    </c:if>
                    <c:if test="${firstval==1}">
                        <c:if test="${taskreport==null}">
                            前次复核值 <input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="前次复核列">
                        </c:if>
                        <c:if test="${taskreport.examstate==0}">
                            前次复核值 <input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="前次复核列">
                        </c:if>
                        <c:if test="${taskreport.examstate==1}">
                            前次复核值(人工) <input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="前次复核值(人工)">
                        </c:if>
                        <c:if test="${taskreport.examstate==2}">
                            前次复核值(自动) <input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="前次复核值(自动)">
                        </c:if>
                    </c:if>
                    <c:if test="${checkvalue==1}">
                        复核列<input type="checkbox" name="exportcontent" style="width: 17px;height: 17px;" value="复核列">
                    </c:if>


                </div>
                <div class="modal-footer">
                    <input type="button" id="btn2" value="导出" class="btn btn-primary"
                           onclick="exportexce2(${taskreportid})">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
            <div id="examvalidate">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>复核员认证</h3>
                </div>
                <div class="modal-body">
                    复核员：<input type="text" name="examuser" id="examuser"> 密码：<input type="password" name="exampwd"
                                                                                    id="exampwd">
                </div>
                <div class="modal-footer">
                    <input type="button" id="btn3" value="验证" class="btn btn-primary" onclick="submitValidate()">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal2" class="modal fade in"
     style="z-index: 1">
    <div class="modal-dialog" style="z-index: 11000">
        <div class="modal-content" style="overflow: auto;">
            <div id="toexam">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>复核确认</h3>
                </div>
                <div class="modal-body">
                    确定复核吗？<input type="text" id="examuser2">
                </div>
                <div class="modal-footer">
                    <input type="button" id="btn4" value="确定" class="btn btn-primary" onclick="check()">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal3" class="modal fade in" style="z-index: 0">
    <div class="modal-dialog" style="z-index: 9999;width: 60%;">
        <div class="modal-content" style="overflow: auto;">
            <div id="arealist">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>显示异常</h3>
                </div>
                <div class="modal-body">
                    <div id="showerror"style="height: 60%;">
                        <div style="width: 150px;float: left;height: 100%;;overflow:auto;">
                            <ul class="list-group" id="errorul">

                            </ul>
                        </div>
                        <div id="pic" style="border: 1px solid #F3F3F3; width: 600px;height: 100%;float: left;">

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
