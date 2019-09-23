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
        function showdata(){
            var taskid=$("#taskidd").val();
            var type=$("#type").val();
            var operationtime=$("#donetime").val();
            var reportstate=$("#reportstate").val();
            location.href="reportcount?page=1&taskid="+taskid+"&operationtime="+operationtime+"&type="+type+"&reportstate="+reportstate+"&type2=0";
        }
        function refer(){
            var type=$("#type").val();
            window.location="showallreport1?page=1&tasktype="+type;
        }
        function exportexcel(taskid,type){
            var taskname=$("#taskname").val();
            window.location="exportExcel?type="+type+"&taskid="+taskid+"&taskname="+taskname;
        }
        function eee(val,val2,val4,val5){
            //val是否有异常  val2图片  val3文字  val4音频
            if("-"==val){
                alert("无异常");
                return false;
            }else{

                var img=new Array();
                img=JSON.parse(val2);
                var audio=new Array();
                audio=JSON.parse(val4);
                var video=new Array();
                video=JSON.parse(val5);

                var errorul=$("#errorul");
                errorul.empty();
                errorul.append("<li class='list-group-item'  onclick=showtext(this,'"+val+"')>文字</li>");
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
                                    <form action="" method="post">
                                        <input type="hidden" id="taskidd" name="taskid" value="${taskid}">
                                        <span style="font-size: 15px;margin-top: 20px;margin-bottom: 20px">报告完成时间：</span>
                                        <input type="text" name="operationtime" id="donetime" readonly onClick="WdatePicker()" value="${donetime}">
                                        <span style="font-size:15px;margin-top: 20px;margin-bottom: 20px">操作状态:</span>
                                        <select id = "reportstate" name="reportstate">
                                            <option id="option1" ${param.reportstate eq ''?'selected':''} value="">所有</option>
                                            <option id="option2" ${param.reportstate eq '0'?'selected':''} value="0">正常</option>
                                            <option id="option3" ${param.reportstate eq '1'?'selected':''} value="1">异常</option>
                                        </select>
                                        <input type="button" value="搜索" class="btn btn-primary" onclick="showdata()">
                                        <input type="button" class="btn btn-primary" value="导出Excel" style="margin-left: 30px;" onclick="exportexcel(${taskid},${type})">
                                    </form>
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr>
                                            <th>任务编号</th>
                                            <th>区域</th>
                                            <th>设备</th>
                                            <th>巡检项</th>
                                            <th>巡检项类型</th>
                                            <th>执行时间</th>
                                            <th>最低值</th>
                                            <th>最高值</th>
                                            <th>下限警告值</th>
                                            <th>上限警告值</th>
                                            <th>报告值</th>
                                            <th>数据名称</th>
                                            <th>单位</th>
                                            <th>异常描述</th>
                                        </tr>
                                        <c:forEach items="${reportinfos.list}" var="reports">
                                            <tr>
                                                <th>${reports.report.taskcode}</th>
                                                <td>${reports.areaname}</td>
                                                <td>${reports.equipname}</td>
                                                <td>${reports.checkname}</td>
                                                <td>${reports.checktype}</td>
                                                <td>${reports.operationtime}</td>
                                                <td>${reports.normalmin}</td>
                                                <td>${reports.normalmax}</td>
                                                <td>${reports.lowerwarning}</td>
                                                <td>${reports.upperwarning}</td>
                                                <td>
                                                    <c:if test="${!reports.numvalue eq '-'}">
                                                        <c:if test="${reports.numvalue < reports.normalmin}"><span style="color: red;">${reports.numvalue}↓↓</span></c:if>
                                                        <c:if test="${reports.numvalue > reports.normalmax}"><span style="color: red;">${reports.numvalue}↑↑</span></c:if>

                                                    </c:if>
                                                </td>
                                                <td>${reports.recordname}</td>
                                                <td>${reports.unitname}</td>
                                                <td>
                                                   <a href="javascript:void(0);" onclick=eee('${reports.errcontent}','${reports.img}','${reports.audio}','${reports.video}')>
                                                   ${reports.errcontent}
                                                   </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                        <a href="reportcount?page=1&taskid=${taskid}&type=${type}&taskname=${taskname}&donetime=${donetime}&type2=0">第一页</a>
                                        <c:if test="${reportinfos.pageNum>1}">
                                            <a href="reportcount?page=${reportinfos.pageNum-1}&taskid=${taskid}&type=${type}&taskname=${taskname}&donetime=${donetime}&type2=0">上一页</a>
                                        </c:if>

                                        <c:if test="${reportinfos.pageNum<reportinfos.pages}">
                                            <a href="reportcount?page=${reportinfos.pageNum+1}&taskid=${taskid}&type=${type}&taskname=${taskname}&donetime=${donetime}&type2=0">下一页</a>
                                        </c:if>

                                        <a href="reportcount?page=${reportinfos.pages}&taskid=${taskid}&type=${type}&taskname=${taskname}&donetime=${donetime}&type2=0">最后一页</a>

                                        第${reportinfos.pageNum}页/共${reportinfos.pages}页

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
