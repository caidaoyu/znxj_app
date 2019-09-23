<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function classbyuserid(id,uname){
            $.ajax({
                url: "classbyuserid?directorid=" + id,
                type: "post",
                datatype: "json",
                success: function (data) {
                    if(data.length>0){
                        var classname=""
                        for(var i=0;i<data.length;i++){
                            classname+=data[i].customid+",";
                        }
                        var director=classname.substring(0,classname.length-1);
                        var isDel=confirm("该用户是班组："+director+"的负责人，不建议删除");
                        if (isDel!=true) {
                            showtask();
                        }else {
                            showtask();
                        }
                    }else{
                        deluser(id,uname);
                    }
                }
            });
        }
        function deluser(id,uname){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "deluser?id=" + id+"&uname="+uname,
                    type: "post",
                    datatype: "json",
                    success: function (data) {
                        if (data > 0) {
                            alert("删除成功,");
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
            window.location="showallusers?page="+$("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content"class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <%--<div style="float: left;">--%>
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 用户管理
                                </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                                <%--<shiro:hasPermission name="add:user">--%>
                                    <%--<a href="toadd" id="button" class="btn btn-primary" style="margin-top: -6px;">添加用户</a>--%>
                                <%--</shiro:hasPermission>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="showallusers?page=1" method="post">
                                    <label class="control-label" for="name2">名称：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2" name="realname" value="${param.realname}">

                                   <%-- <label class="control-label" for="type">用户类型：</label>
                                    <select class="form-control" id="type" style="width: 150px;" name="type">
                                        <option ${type eq '' ? 'selected' : ''} value="">所有</option>
                                        <option ${type eq '0' ? 'selected' : ''} value="0">操作员</option>
                                        <option ${type eq '1' ? 'selected' : ''} value="1">监督员</option>
                                        <option ${type eq '2' ? 'selected' : ''} value="2">现场经理</option>
                                    </select>--%>
                                    <label class="control-label" for="status">当前状态：</label>
                                    <select class="form-control" id="status" name="userstate">
                                        <option ${userstate eq '' ? 'selected' : ''} value="">所有</option>
                                        <option ${userstate eq '1' ? 'selected' : ''} value="1">在线</option>
                                        <option ${userstate eq '0' ? 'selected' : ''} value="0">离线</option>
                                    </select>
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:user">
                                        <a href="toadd" id="button" class="btn btn-primary" style="margin-left:250px;">添加用户</a>
                                    </shiro:hasPermission>

                                </form>
                            </div>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <th>操作</th><%--
                                    <th>类型</th>--%>
                                    <th>登录名</th>
                                    <th>真实姓名</th>
                                    <th>性别</th>
                                    <th>班组</th>
                                    <th>所属岗位</th>
                                    <th>終端</th>
                                    <th>当前状态</th>
                                    <th>状态</th>
                                </tr>
                                <c:forEach items="${pageBean.list}" var="user">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:user">
                                                <a href="querybyUserId?id=${user.id}&page=${pageBean.currentPage}"><i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:user">
                                                <a href="javascript:void(0)" onclick="classbyuserid(${user.id},'${user.realname}')"><i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                            <a href="queryuserdetail?id=${user.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                        </td>
                                        <%--<td>
                                            <c:if test="${user.type==0}">操作员</c:if>
                                            <c:if test="${user.type==1}">监督员</c:if>
                                            <c:if test="${user.type==2}">现场经理</c:if>
                                        </td>--%>
                                        <td>${user.username}</td>
                                        <td>${user.realname}</td>
                                        <td>
                                            <c:if test="${user.sex==0}">女</c:if>
                                            <c:if test="${user.sex==1}">男</c:if>
                                        </td>
                                        <td>${user.group.customid}</td>
                                        <td>${user.pos.positionname}</td>
                                        <td>${user.ter.customid}</td>
                                        <td>
                                            <c:if test="${user.userstate==0}">离线</c:if>
                                            <c:if test="${user.userstate==1}">在线</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${user.state==0}">禁用</c:if>
                                            <c:if test="${user.state==1}">可用</c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showallusers?page=1&realname=${realname}&type=${type}&userstate=${userstate}">第一页</a>
                                <c:if test="${pageBean.currentPage>1}">
                                    <a href="showallusers?page=${pageBean.currentPage-1}&realname=${realname}&type=${type}&userstate=${userstate}">上一页</a>
                                </c:if>

                                <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                    <a href="showallusers?page=${pageBean.currentPage+1}&realname=${realname}&type=${type}&userstate=${userstate}">下一页</a>
                                </c:if>

                                <a href="showallusers?page=${pageBean.totalPage}&realname=${realname}&type=${type}&userstate=${userstate}">最后一页</a>

                                第${pageBean.currentPage}页/共${pageBean.totalPage}页
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
