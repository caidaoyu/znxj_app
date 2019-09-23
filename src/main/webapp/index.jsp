<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="/WEB-INF/pages/common/header.jsp" />
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>智能巡检系统</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
<div class="col-sm-2 col-lg-2">
    <div class="sidebar-nav">
        <div class="nav-canvas">
            <div class="nav-sm nav nav-stacked">
            </div>
            <ul class="nav nav-pills nav-stacked main-menu">
                <li class="accordion" id="adminManager">
                    <a href="#"><i class="glyphicon glyphicon-globe"></i><span> 管理员管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li id="userInfo">
                            <shiro:hasPermission name="item:admin">
                                <a href="showadmin?page=1" target="center"><%--<i class="glyphicon glyphicon-user"></i>--%>管理员管理</a>
                            </shiro:hasPermission>
                        </li>
                        <li><shiro:hasPermission name="item:roles"><a href="showroles?page=1"target="center"><%--<i class="glyphicon glyphicon-adjust"></i>--%>角色管理</a></shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion" id="baseData">
                    <a href="#"><i class="glyphicon glyphicon-folder-open"></i><span> 基础数据</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:system"><a href="showsystem?type=1"  target="center"><%--<i class="glyphicon glyphicon-home"></i>--%>系统设置</a></shiro:hasPermission></li>
                        <%--<li><a href="showdaterecord?page=1">可读数据管理</a></li>--%>
                        <li><shiro:hasPermission name="item:site"><a href="showsite?page=1"  target="center"><%--<i class="glyphicon glyphicon-road"></i>--%>厂区管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:area"><a href="showallarea?page=1"  target="center"><%--<i class="glyphicon glyphicon-print"></i>--%>区域管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:equip"><a href="showallequipment?page=1"  target="center"><%--<i class="glyphicon glyphicon-inbox"></i>--%>设备管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:ter"><a href="showallterm?page=1"  target="center"><%--<i class="glyphicon glyphicon-stop"></i>--%>智能终端管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:nfc"><a href="showallnfc?page=1"  target="center"><%--<i class="glyphicon glyphicon-barcode"></i>--%>NFC标签管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:check"><a href="showallcheck?page=1"  target="center"><%--<i class="glyphicon glyphicon-list-alt"></i>--%>巡检项管理</a></shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion" id="userManager">
                    <a href="#"><i class="  glyphicon glyphicon-globe"></i><span> 用户管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:user"><a href="showallusers?page=1" target="center"><%--<i class="glyphicon glyphicon-user"></i>--%>用户管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:pos"><a href="showposition?page=1" target="center"><%--<i class=" glyphicon glyphicon-wrench"></i>--%>岗位管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:class"><a href="showallclass?page=1" target="center"><%--<i class="glyphicon glyphicon-fullscreen"></i>--%>班组管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:contact"><a href="showallcont?page=1" target="center"><%--<i class="glyphicon glyphicon-user"></i>--%>联系人管理</a></shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion" id="taskManager">
                    <a href="#"><i class=" glyphicon glyphicon-tasks"></i><span> 任务管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:usualtask"><a href="showtaskplan?page=1&type=0" target="center"><%--<i class="glyphicon glyphicon-cog"></i>--%>日常巡检任务</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:plantask"><a href="showtaskplan?page=1&type=1"  target="center"><%--<i class="glyphicon glyphicon-zoom-in"></i>--%>计划巡检任务</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:errortask"><a href="showtaskplan?page=1&type=2" target="center"><%--<i class="glyphicon glyphicon-warning-sign"></i>--%>HSE隐患排查</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:videotask"><a href="showtaskplan?page=1&type=3" target="center"><%--<i class="glyphicon glyphicon-picture"></i>--%>视频巡检任务</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:quick"><a href="showQuickReport?page=1" target="center"><%--<i class="glyphicon glyphicon-camera"></i>--%>即拍即传</a></shiro:hasPermission></li>
                    </ul>
                </li>

                <li class="accordion" id="knowManager">
                    <a href="#"><i class=" glyphicon glyphicon-calendar"></i><span> 知识库管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li>
                            <shiro:hasPermission name="item:knowledge">
                                <a href="showknowledge?page=1" target="center"><%--<i class="glyphicon glyphicon-folder-open"></i>--%>知识管理</a>
                            </shiro:hasPermission>
                        </li>
                        <li>
                            <shiro:hasPermission name="item:knowtype">
                                <a href="showknowledgetype?page=1" target="center"><%--<i class="glyphicon glyphicon-headphones"></i>--%>知识类别</a>
                            </shiro:hasPermission>
                        </li>
                        <%--<li><a href="testPage?page=1">poss</a></li>--%>
                    </ul>
                </li>
                <li class="accordion" id="logsManager">
                    <a href="#"><i class=" glyphicon glyphicon-calendar"></i><span> 日志管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li>
                            <shiro:hasPermission name="item:operatelog">
                                <a href="showlog?page=1" target="center"><%--<i class="glyphicon glyphicon-edit"></i>--%>操作日志</a>
                            </shiro:hasPermission></li>
                        <li>
                            <shiro:hasPermission name="item:taskreportlog">
                            <a href="gettaskreportlog?page=1"target="center"><%--<i class=" glyphicon glyphicon-list-alt"></i>--%>任务日志</a>
                            </shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion" id="countManger">
                    <a href="#"><i class=" glyphicon glyphicon-pencil"></i><span> 统计查询</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li>
                            <shiro:hasPermission name="item:taskfinal">
                                <%--<a href="reportlist?page=1&type=0" target="center"><i class="glyphicon glyphicon-ok-circle"></i>任务完成情况</a>--%>
                                <a href="toreportlist?type=0" target="center"><%--<i class="glyphicon glyphicon-ok-circle"></i>--%>任务完成情况</a>
                            </shiro:hasPermission>
                        </li>
                        <li>
                            <shiro:hasPermission name="item:equipstate">
                                <%--<a href="equipstateinfo?page=1" target="center"><i class="glyphicon glyphicon-cog"></i>设备状态情况</a>--%>
                                <a href="toequipstateinfo" target="center"><%--<i class="glyphicon glyphicon-cog"></i>--%>设备状态情况</a>
                            </shiro:hasPermission>
                        </li>
                        <li>
                            <shiro:hasPermission name="item:taskfinaltime">
                                <%--<a href="reportlist?page=1&type=1&tablestate=0" target="center"><i class="glyphicon glyphicon-time"></i>任务完成时间</a>--%>
                                <a href="toreportlist?type=1" target="center"><%--<i class="glyphicon glyphicon-time"></i>--%>任务完成时间</a>
                            </shiro:hasPermission>
                        </li>
                    </ul>
                </li>
                <li class="accordion" id="emailManager">
                    <a href="#"><i class=" glyphicon glyphicon-envelope"></i><span> 邮件推送</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li>
                            <shiro:hasPermission name="add:sendemail">
                                <a href="sendpersion" target="center"><%--<i class=" glyphicon glyphicon-plus"></i>--%>新增推送内容</a>
                            </shiro:hasPermission>
                        </li>
                        <li>
                            <%--<shiro:hasPermission name="item:sendemail">--%>
                                <a href="showsendemaillist?page=1" target="center"><%--<i class="glyphicon glyphicon-eye-open"></i>--%>查看推送列表</a>
                            <%--</shiro:hasPermission>--%>
                        </li>
                    </ul>
                </li>
                <li class="accordion" id="exportManager">
                    <a href="#"><i class=" glyphicon glyphicon-export"></i><span> 数据导出</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li>
                            <shiro:hasPermission name="item:exportInfo">
                                <a href="getPageExportInfo?page=1" target="center"><%--<i class="glyphicon glyphicon-log-out"></i>--%>数据导出列表</a>
                            </shiro:hasPermission>
                        </li>
                        <li>
                        </li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>

<div class="col-sm-10 col-lg-10" style="float: right ;margin-top: -10px; " >
    <iframe  height="90%" width="100%" frameborder="0" name="center" src="iframe.jsp">

    </iframe>
</div>
<script type="text/javascript">
    $('ul.main-menu li a').each(function () {
        if ($($(this))[0].href == String()){
            $(this).parent().addClass('active');
        }
    });
    $('.accordion > a').click(function (e) {
//        e.preventDefault();
        var li = $(".accordion > a");
//        alert(li.length);
        var $ul = $(this).siblings('ul');
        var $li = $(this).parent();
        for (var i = 0;i<li.length;i++){
            var liId = $(li[i]).parent();
            if(liId.is($li)){
                if ($ul.is(':visible')){
                    $li.removeClass('active');
                }
                else{
                    $li.addClass('active');
                }
                $ul.slideToggle();
            }else{
                var ul = $(li[i]).siblings('ul');
                if (ul.is(':visible')) {
                    ul.attr("display", "none");
                    liId.removeClass('active');
                    ul.slideToggle();
                }
            }
        }
    });
    $('.accordion li.active:first').parents('ul').slideDown();
   /* $('ul.main-menu li a').each(function () {
        if ($($(this))[0].href == String()){
            $(this).parent().addClass('active');
        }
    });
    $('.accordion > a').click(function (e) {
        e.preventDefault();
        var $ul = $(this).siblings('ul');
        var $li = $(this).parent();
        if ($ul.is(':visible')){
            $li.removeClass('active');
        }
        else{
            $li.addClass('active');
        }
        $ul.slideToggle();
    });
    $('.accordion li.active:first').parents('ul').slideDown();*/
</script>
</body>
</html>
