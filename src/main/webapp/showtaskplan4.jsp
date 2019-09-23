<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
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
    <script type="text/javascript">
        function querywarningdetail(id){
            $.ajax({
                url:"querywarningdetail?id="+id,
                type:"post",
                datatype:"json",
                success: function(data) {
                    var imgs;
                    var audios;
                    var videos;
                    if(""!=data.img && data.img!=null){
                        imgs=JSON.parse(data.img);
                    }else{
                        imgs="";
                    }

                    if(""!=data.audio && data.audio!=null){
                        audios=JSON.parse(data.audio);
                    }else{
                        audios="";
                    }
                    if(""!=data.video && data.video!=null){
                        videos=JSON.parse(data.video);
                    }else{
                        videos="";
                    }
                    if(imgs==""&&audios==""&&videos==""){
                        alert("暂时没有其他异常信息");
                        return;
                    }else{
                        showquickdetail(imgs,data.content,audios,videos);
                    }
                }
            })
        }
        function showquickdetail(imgs,val3,audios,videos){
            // val2图片  val3文字  val4音频
            var errorul=$("#errorul");
            errorul.empty();
           /* errorul.append("<li class='list-group-item'  onclick=showtext(this,'"+val3+"')>文字</li>");*/
            if (imgs != null && "" != imgs) {
                for (var i = 0; i < imgs.length; i++) {
                    var j = parseInt(i) + 1;
                    errorul.append("<li class='list-group-item'  onclick=showimg(this,'" + imgs[i] + "')>图片" + j + "</li>");
                }
                showimg(this, imgs[0]);
            }
            if (audios != null && "" != audios) {
                for (var m = 0; m < audios.length; m++) {
                    var n = parseInt(m) + 1;
                    errorul.append("<li class='list-group-item'  onclick=showaudio(this,'" + audios[m] + "')>音频" + n + "</li>");
                }
                if (imgs == null && imgs == undefined) {
                    showaudio(this, audios[0]);
                }
            }
            if (videos != null && "" != videos) {
                for (var a = 0; a < videos.length; a++) {
                    var b = parseInt(a) + 1;
                    errorul.append("<li class='list-group-item'  onclick=showvideo(this,'" + videos[a] + "')>视频" + b + "</li>");
                }
                if (imgs == null && imgs == undefined && audios == null && audios == undefined) {
                    showvideo(this, videos[0]);
                }
            }
            $("#myModal").modal("show");
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

            $(pic).css("width","auto");
            $(pic).css("height","auto");
            $(pic).css("max-width","100%");
            $(pic).css("max-height","100%");
            $("#pic")[0].appendChild(pic);
        }
        /*显示文字*/
        /*function showtext(obj,val){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
            $("#pic").empty();
            $("#pic")[0].innerHTML=val;
        }*/
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
        function delbyquickid(id){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delbyquickid?id=" + id,
                    type: "post",
                    datatype: "json",
                    success: function (data) {
                        if (data > 0) {
                            alert("删除成功");
                            showtask();
                        } else {
                            alert("删除失败");
                            return false;
                        }
                    }
                });
            }
        }
        function showtask(){
            var type=$("#type").val();
            window.location="showQuickReport1?page=1&qtype=1&type="+type;
        }
        $(function(){
            var errorlen=$("#errorlen").val();
            console.log(errorlen);
            for(var i=0; i<parseInt(errorlen); i++){
                var reporttype=JSON.parse($("#report"+i).val());
                var type=$("#type"+i);
                var level=$("#level"+i);
                var content=$("#content"+i);
                for(var j=0;j<reporttype.length;j++){
                    if (reporttype[0] == null) {
                        reporttype[0] = "";
                    }
                    if (reporttype[1] == null) {
                        reporttype[1] = "";
                    }
                    if (reporttype[2] == null) {
                        reporttype[2] = "";
                    }
                    type.text(reporttype[0]);
                    level.text(reporttype[1]);
                    content.text(reporttype[2]);
                }
            }
        })
        function refer(){
            location.reload();
        }
    </script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
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
                            <%--<li><a href="showstoptask?page=1&state=3&type=${type}">终止任务</a></li>--%>
                            <li ><a href="showallreport1?page=1&tasktype=${type}" >任务报告</a></li>
                            <c:if test="${type==2}">
                                <li class="active"><a href="#report"data-toggle="tab" style="background: #35A7E7;color: white;"  onclick="refer()">立即上报</a></li>
                            </c:if>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i> 隐患排查立即上报任务报告 - 列表
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <input type="hidden" id="type" value="${type}">
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr>
                                            <th>厂区</th>
                                            <th>任务报告编号</th>
                                            <th>上传人</th>
                                            <th>班组</th>
                                            <th>报告类型</th>
                                            <th>报告等级</th>
                                            <th>文字异常</th>
                                            <th>上传时间</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach items="${pageBean.list}" var="report" varStatus="status">
                                            <input type="hidden" id="report${status.index}" value='${report.content}'>
                                            <tr>
                                                <td>${report.site.customid}</td>
                                                <td>${report.reportcode}</td>
                                                <td>${report.u.realname}</td>
                                                <td>${report.group.customid}</td>
                                                <td id="type${status.index}"></td>
                                                <td id="level${status.index}"></td>
                                                <td id="content${status.index}"></td>
                                                <td><sdf:formatDate value="${report.uploadtime}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                                <td>
                                                    <%--<shiro:hasPermission name="item:reportquickdetail">--%>
                                                        <a class="btn btn-primary" href="javascript:void(0)" onclick="querywarningdetail(${report.id})">
                                                            <i class="glyphicon glyphicon-zoom-in icon-white"></i>详情
                                                        </a>
                                                   <%-- </shiro:hasPermission>--%>
                                                    <shiro:hasPermission name="del:quick">
                                                        <a class="btn btn-danger" href="javascript:void(0)" onclick="delbyquickid(${report.id})"><i class="glyphicon glyphicon-trash icon-white"></i>删除</a>
                                                    </shiro:hasPermission>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <input type="hidden" id="errorlen" value="${pageBean.list.size()}">
                                    </table>
                                    <div style="height: 50px;width: 500px;margin-left: 300px;">
                                        <a href="showQuickReport1?page=1&qtype=1&type=${type}">第一页</a>
                                        <c:if test="${pageBean.currentPage>1}">
                                            <a href="showQuickReport1?page=${pageBean.currentPage-1}&qtype=1&type=${type}">上一页</a>
                                        </c:if>

                                        <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                            <a href="showQuickReport1?page=${pageBean.currentPage+1}&qtype=1&type=${type}">下一页</a>
                                        </c:if>
                                        <a href="showQuickReport1?page=${pageBean.totalPage}&qtype=1&type=${type}">最后一页</a>

                                        第${pageBean.currentPage}页/共${pageBean.totalPage}页
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <hr>


</div>


<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="z-index: 0">
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
