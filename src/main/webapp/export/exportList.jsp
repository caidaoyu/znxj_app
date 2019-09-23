<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/4/24
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据导出列表</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript" src="/js/PopDrag.js"></script>
    <link href='/charisma/css/popDrag.css' rel='stylesheet'>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container">
    <div class="row">
        <div id="content"class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <%--<div style="float: left;">--%>
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 数据导出列表
                            </h2>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:exportInfo">
                                <a href="/toAddExport" id="addbutton" class="btn btn-primary" style="margin-top: -6px;">添加导出模板</a>
                                </shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="getPageExportInfo?page=1" method="post">
                                    <label class="control-label" for="name">表头名称：</label>
                                    <input type="text" class="form-control"  style="width: 300px;" id="name" name="name" value="${param.name}">
                                    &nbsp;&nbsp;&nbsp;
                                    <input type="submit" class="btn btn-primary" value="搜索">
<%--
                                    <input type="button" onclick="WdatePicker()" class="btn btn-primary" value="日期">

                                    <input type="button" onclick="prompt('')" class="btn btn-primary" value="输入框">--%>

                                </form>
                            </div>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <th style="text-align: center">操作</th>
                                    <th style="text-align: center">表头名称</th>
                                    <th style="text-align: center">任务类型</th>
                                    <th style="text-align: center">任务编号</th>
                                    <th style="text-align: center">创建时间</th>

                                </tr>
                                <c:forEach items="${pageInfo.list}" var="exportList">
                                    <tr>

                                        <td style="text-align: center">
                                            <shiro:hasPermission name="export:exportInfo">
                                                <a href="#" onclick="showDialog(this)" id="${exportList.exportinfos.id}"><i class="glyphicon glyphicon-log-out blue "></i></a>
                                            </shiro:hasPermission>
                                            <a href="javascript:void(0)" onclick="deleteExport(this,${exportList.exportinfos.id})"><i class="glyphicon glyphicon-trash red"></i></a>
                                                <%--<a href=""><i class="glyphicon glyphicon-edit "></i></a>--%>

                                        </td>
                                        <td style="text-align: center">${exportList.exportinfos.exportname}</td>
                                        <td style="text-align: center">
                                            <c:if test="${exportList.exportinfos.tasktype == 0}">
                                                日常巡检任务
                                            </c:if>
                                            <c:if test="${exportList.exportinfos.tasktype == 1}">
                                                计划巡检任务
                                            </c:if>
                                            <c:if test="${exportList.exportinfos.tasktype == 2}">
                                                HSE隐患排查
                                            </c:if>
                                            <c:if test="${exportList.exportinfos.tasktype == 3}">
                                                视频巡检任务
                                            </c:if>
                                        </td>
                                        <td style="text-align: center">${exportList.taskplaninfo.identifyingid}</td>
                                        <td style="text-align: center">
                                            <sdf:formatDate value="${exportList.exportinfos.createtimestamp}" pattern="yyyy-MM-dd"/>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="getPageExportInfo?page=1">第一页</a>
                                <c:if test="${pageInfo.prePage>1}">
                                    <a href="getPageExportInfo?page=${pageInfo.prePage-1}">上一页</a>
                                </c:if>

                                <c:if test="${pageInfo.prePage<pageInfo.pages}">
                                    <a href="getPageExportInfo?page=${pageInfo.prePage+1}">下一页</a>
                                </c:if>

                                <a href="getPageExportInfo?page=${pageInfo.pages}">最后一页</a>

                                第${pageInfo.prePage}页/共${pageInfo.pages}页
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="textPopBox" class="popBox" >
        <h3 class="popBoxHandle">导出任务时间段</h3>
        <form action="" method="post">
            <table width="100%" cellspacing="5">
                <tr>
                    <td>
                        输入时间：<input id="start" type="text" onclick="WdatePicker()" name="startTime" style="width:150px"/>
                    </td>
                    <td align="left">至&nbsp&nbsp<input id="end" type="text" onclick="WdatePicker()" name="endTime" style="width:150px"/></td>
                </tr>


                <tr>
                    <td style="text-align:right">
                        <input id='cancel' type="button" class="button green" value=" 取消 " />
                    </td>
                    <td style="text-align:left">
                        <input id='ok' type="button" class="button blue" value=" 导出" />
                    <td/>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    function deleteExport(obj,id){
        layer.confirm("确定删除吗？",function (index) {
            $.ajax({
                url: "deleteExportInfo?id=" + id,
                type: "post",
                datatype: "json",
                success: function (data) {
                    if(data >0){
                        $(obj).parents("tr").remove();
                        layer.msg('删除成功！',{icon:1,time:2000});
                    }else{
                        layer.msg('删除失败',{icon:2,time:2000});
                        return false;
                    }
                }
            });
        })
    }

    //初始化弹出框
    var p = new PopUp({
        //isScroll:'disabled'
        //enableDrag:'disabled'
        //enableShadow:'disabled'
        //id为你自己自定义的弹出框div的id
        id:"textPopBox"
    });

    //缩放窗口时重新定位弹出框及遮罩层的宽度和高度
    EventUtil.addEvent(window,'resize', function() {
        p.setPosition();
        //如果开启遮罩层，遮罩层元素存在，则重新定位遮罩层
        p.enableShadow && p.enableShadow.shadow && p.enableShadow.setProperty(p.enableShadow.shadow);
    });
    var exportinfoId;
    function showDialog(event) {
        exportinfoId=event.id;
        p.show({//定义坐标，如果缺省则居中显示
//            x:100,
//            y:100
        });
    };
    EventUtil.addEvent(EventUtil._$('ok'),'click', function(event) {
        //自定义点击确定按钮之后得操作
        var start =$("#start").val();
        var end =$("#end").val();
        if((start == "" || start == null || start == undefined)||(end == "" || end == null || end == undefined)){ // "",null,undefined
            alert("时间段不能为空!");
            return;
        }
        $.ajax({
            url:"selectExport2?exportinfoId=" + exportinfoId + "&startTime=" + start+"&endTime="+end,
            type:"post",
            success: function(data) {
                if(data!=null&&data=="0"){
//                    $("#loading").modal("hide");
                    alert("选择的时间段没有导出报告");
                }else {
                    window.location = "selectExport?exportinfoId=" + exportinfoId + "&startTime=" + start+"&endTime="+end;
                }
                //隐藏弹出框
                p.hide();

            }
            /* complete:function(XMLHttpRequest){
             $("#myModal").modal("hide");
             $("#equipmentlist")[0].style.display = "none";
             $("#arealist")[0].style.display = "none";
             $("#checklist")[0].style.display = "none";
             $("#dotime")[0].style.display="none";
             $("#loading")[0].style.display="none";
             }*/
        })
//        window.location = "selectExport?exportinfoId=" + exportinfoId + "&startTime=" + start+"&endTime="+end;
//        //隐藏弹出框
//        p.hide();
    });
    EventUtil.addEvent(EventUtil._$('cancel'),'click', function() {
        //自定义点击取消按钮之后得操作

        //隐藏弹出框
        p.hide();
    });

    //当按下ESC键时关闭弹出框
    //    EventUtil.addEvent(document,'keyup',function(e) {
    //        e = e || window.event;
    //        e.keyCode == 27 && p.hide();
    //    });

    EventUtil.addEvent(window,'load',function() {
        if((isIE6 || isOpera) && p.isScroll != null && p.isScroll == 'enabled') {
            EventUtil.addEvent(window,'scroll', function() {
                setTimeout(function() {
                    p.setPosition();
                },100);
            });
        }
    });

</script>
</body>
</html>
