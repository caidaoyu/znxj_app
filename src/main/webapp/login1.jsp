<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <style>
        #body{
            background: url("/charisma/img/bg.jpg");
        }
    </style>
</head>
<body id="body">
<div class="ch-container">
    <div class="row">
        <div class="row">
            <div  class="col-md-12 center login-header" style="color:white;font-size: 4ch;margin-top: 20px">智能巡检平台管理系统</div>
            <div class="col-md-12 center login-header">
                <h2 style="color: white;">用户登录</h2>
            </div>
        </div>
        <div class="row">
            <div class="well col-md-5 center login-box">

                <form class="form-horizontal" action="/admin/login" method="post" autocomplete="off">
                    <%--<fieldset>--%>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                            <input type="text" class="form-control"  placeholder="用户名" name="username" >
                        </div>
                        <div class="clearfix"></div><br>

                        <div class="input-group input-group-lg" style="margin-top: 20px;">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                            <input type="password" class="form-control" placeholder="密码" name="password">
                        </div>
                        <label style="color: red;font-size: 20px;margin-top: 25px;">${error}</label>
                        <div class="clearfix"></div>
                        <p class="center col-md-5" style="margin-top: 25px;">
                            <button type="submit" class="btn btn-primary">登录</button>
                        </p>
                    <%--</fieldset>--%>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
