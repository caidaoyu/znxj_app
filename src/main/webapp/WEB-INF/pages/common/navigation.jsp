<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="col-sm-12 col-lg-12">
<div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <img alt="Charisma Logo" src="/charisma/img/index-icon.png" class="hidden-xs" style="width: 250px;margin-left: 50px;"/>
            <span style="font-size: 26px;color: white;font-family:Times New Roman;margin-left: 25px;position:absolute; bottom:20px; text-align:center;">${SYSTEMVERSION}</span>
            <div class="btn-group pull-right" style="color: #ffffff;padding-right: 60px">
                <span class="hidden-sm hidden-xs" > ${userInfo.username}</span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="logout" style="color: #ffffff">退出</a>
                <%--<button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${userInfo.username}</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="logout">退出</a></li>
                </ul>--%>
            </div>
        </div>
    </div></div>