<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8" />
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
</head>
<body id="body">
<div class="allContent">
    <div class="head">
        <div class="top">
            <img src="/charisma/img/login/daohang.png">
        </div>
    </div>
    <div class="paddingBottom" style="background-color: #146597">
        <div class="min">
            <div class="mid">
                <div class="left">
                    <img src="/charisma/img/login/bg.png">
                </div>
                <div class="right" style="background-color: #ffffff">
                    <div class="right_min">
                        <p class="xitong">系统登录</p>
                        <div class="midder">
                            <form action="/admin/login" method="post" autocomplete="off">
                                <div>
                                    <p style="padding-bottom: 8px;">用户名：</p><input type="text"  class="userName"name="username">
                                </div>
                                <div>
                                    <p class="mima" style="padding-bottom: 8px;">密码：</p><input type="password" name="password"  class="userPassword">
                                </div>
                                <div style="padding-top: 7px">
                                    <label style="color: red;font-size: 18px;">${error}</label>
                                </div>

                              <%--  <div class="mimajz">
                                    <input type="checkbox">
                                    <span>记住用户名？</span>
                                    <span class="cur">忘记密码</span>
                                </div>--%>
                                <div class="loding">
                                    <%--<div class="lod">--%>
                                        <p>
                                            <button type="submit" class="lod" style=" border: none;color: #ffffff;text-align: center;font-size: 22px;">登 录</button>
                                        </p>
                                    <%--</div>--%>

                                    <%--<a href="">
                                        <div class="lod">
                                            <p>登录</p>
                                        </div>
                                    </a>--%>
                                    <%--<p class="curr">没有账号去注册>>></p>--%>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="footer">
       <%-- <div class="bottom">
            <img src="/charisma/img/login/daohang.png">
        </div>--%>
        <p><img src="/charisma/img/login/logoh.png"></p>
        <p style="color: #c5c5c5;font-weight: bold">V ${SYSTEMVERSION}</p>
    </div>

</div>
</body>
</html>
