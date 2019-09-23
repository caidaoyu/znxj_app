<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="col-sm-2 col-lg-2">
    <div class="sidebar-nav">
        <div class="nav-canvas">
            <div class="nav-sm nav nav-stacked">
            </div>
            <ul class="nav nav-pills nav-stacked main-menu">
                <li>
                    <a class="ajax-link" href="showallusers?page=1">
                        <i class="glyphicon glyphicon-home"></i><span> 首页</span></a>
                </li>

                <li class="accordion">
                    <a href="#"><i class="glyphicon glyphicon-user"></i><span> 权限管理</span></a>
                   <ul class="nav nav-pills nav-stacked">
                        <li id="userInfo"><shiro:hasPermission name="item:admin"><a href="showallusers?page=1" target="center">管理员管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:roles"><a href="showroles?page=1">角色管理</a></shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion">
                    <a href="#"><i class="glyphicon glyphicon-book"></i><span>基础数据</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:system"><a href="showsystem">系统设置</a></shiro:hasPermission></li>
                        <%--<li><a href="showdaterecord?page=1">可读数据管理</a></li>--%>
                        <li><shiro:hasPermission name="item:site"><a href="showsite?page=1">厂区管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:area"><a href="showallarea?page=1">区域管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:equip"><a href="showallequipment?page=1">设备管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:ter"><a href="showallterm?page=1">智能终端管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:nfc"><a href="showallnfc?page=1">NFC标签管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:check"><a href="showallcheck?page=1">巡检项管理</a></shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion">
                    <a href="#"><i class=" glyphicon glyphicon-th-list"></i><span> 用户管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:user"><a href="showallusers?page=1">用户管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:pos"><a href="showposition?page=1">岗位管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:class"><a href="showallclass?page=1">班组管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:contact"><a href="showallcont?page=1">联系人管理</a></shiro:hasPermission></li>
                    </ul>
                </li>
                <li class="accordion">
                    <a href="#"><i class=" glyphicon glyphicon-th-list"></i><span> 任务管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:usualtask"><a href="showtaskplan?page=1&type=0">日常巡检任务</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:plantask"><a href="showtaskplan?page=1&type=1" >计划巡检任务</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:errortask"><a href="showtaskplan?page=1&type=2">HSE隐患排查</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:videotask"><a href="showtaskplan?page=1&type=3">视频巡检任务</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:quick"><a href="showQuickReport?page=1&qtype=0">即拍即传</a></shiro:hasPermission></li>
                    </ul>
                </li>

                <li class="accordion">
                    <a href="#"><i class=" glyphicon glyphicon-calendar"></i><span> 知识库管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><shiro:hasPermission name="item:knowledge"><a href="showknowledge?page=1">知识管理</a></shiro:hasPermission></li>
                        <li><shiro:hasPermission name="item:knowtype"><a href="showknowledgetype?page=1">知识类别</a></shiro:hasPermission></li>
                        <%--<li><a href="testPage?page=1">poss</a></li>--%>
                    </ul>
                </li>
                <li class="accordion">
                    <a href="#"><i class=" glyphicon glyphicon-calendar"></i><span> 日志管理</span></a>
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="#">操作日志</a></li>
                        <li><shiro:hasPermission name="item:taskreportlog"><a href="gettaskreportlog?page=1">任务日志</a></shiro:hasPermission></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>
<iframe height="100%" width="100%" frameborder="0" name="center" src="">
</iframe>

<script type="text/javascript">

       $('ul.main-menu li a').each(function () {
            if ($($(this))[0].href == String(window.location))
                $(this).parent().addClass('active');
        });
        $('.accordion > a').click(function (e) {
            e.preventDefault();
            var $ul = $(this).siblings('ul');
            var $li = $(this).parent();
            if ($ul.is(':visible')) $li.removeClass('active');
            else   $li.addClass('active');
            $ul.slideToggle();
        });
        $('.accordion li.active:first').parents('ul').slideDown();
</script>

