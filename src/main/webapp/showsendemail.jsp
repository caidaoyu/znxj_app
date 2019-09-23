<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp" %>
    <script>
        function delsendemail(id) {
            var isDel = confirm('确定删除吗？');
            if (isDel != true) {
                return false;
            } else {
                $.ajax({
                    url: "delsendemail?id=" + id,
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
        function showtask() {
            window.location = "showsendemaillist?page=" + $("#page").val();
        }
        $(function () {
            var send = $("#send").val();
            for (var i = 0; i < send; i++) {
                var contact = $("#contact" + i).val();
                var name = "";
                $.ajax({
                    url: "showsendname",
                    type: "post",
                    data: {contactid: contact},
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        for (var j = 0; j < data.length; j++) {
                            name += data[j].name + ",";
                        }
                    }
                })
                var connames = name.substring(0, name.length - 1);
                $("#conname" + i).text(connames);
            }
        })
    </script>

</head>
<body>
<input type="hidden" id="page" value="${pageInfo.pageNum}">
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <div style="float: left;">
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 推送列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <a href="sendpersion" id="addbutton" class="btn btn-primary" style="margin-top: -6px;">新增推送内容</a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>邮件推送任务名</th>
                                    <th>类型</th>
                                    <th>巡检任务编号</th>
                                    <th>联系人列表</th>
                                    <th>状态</th>
                                    <th>发送人邮箱</th>
                                    <th>发送人邮箱授权码</th>
                                    <th>异常触发</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${pageInfo.list}" var="send" varStatus="status">
                                    <input type="hidden" id="contact${status.index}" value="${send.contactid}">
                                    <tr>
                                        <td>
                                                ${send.task.customid}
                                            <c:if test="${send.task.type == 0}">日常巡检</c:if>
                                            <c:if test="${send.type == 1}">计划巡检</c:if>
                                            <c:if test="${send.task.type == 2}">隐患排查</c:if>
                                            <c:if test="${send.type == 3}">视频巡检</c:if>
                                            <c:if test="${send.type == 0}">日报</c:if>
                                            <c:if test="${send.type == 1}">周报</c:if>
                                            <c:if test="${send.type == 2}">月报</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${send.type == 0}">日报</c:if>
                                            <c:if test="${send.type == 1}">周报</c:if>
                                            <c:if test="${send.type == 2}">月报</c:if>
                                        </td>
                                        <td>${send.task.identifyingid}</td>
                                        <td id="conname${status.index}">${send.contactid}</td>
                                        <td>
                                            <c:if test="${send.state == 0}">激活</c:if>
                                            <c:if test="${send.state == 1}">暂停</c:if>
                                        </td>
                                        <td>${send.email}</td>
                                        <td>${send.pwd}</td>
                                        <td>
                                            <c:if test="${send.sendexception == 0}">否</c:if>
                                            <c:if test="${send.sendexception == 1}">是</c:if>
                                        </td>
                                        <td>
                                            <shiro:hasPermission name="upd:sendemail">
                                                <a class="btn btn-primary" href="querysendbyid?id=${send.id}">
                                                    <i class="glyphicon glyphicon-edit icon-white"></i>编辑
                                                </a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:sendemail">
                                                <a class="btn btn-danger" href="javascript:void(0);"
                                                   onclick="delsendemail(${send.id})">
                                                    <i class="glyphicon glyphicon-trash icon-white"></i>删除
                                                </a>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <input type="hidden" id="send" value="${fn:length(pageInfo.list)}">
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showsendemaillist?page=1">第一页</a>
                                <c:if test="${pageInfo.pageNum>1}">
                                    <a href="showsendemaillist?page=${pageInfo.pageNum-1}">上一页</a>
                                </c:if>

                                <c:if test="${pageInfo.pageNum<pageInfo.pages}">
                                    <a href="showsendemaillist?page=${pageInfo.pageNum+1}">下一页</a>
                                </c:if>

                                <a href="showsendemaillist?page=${pageInfo.pages}">最后一页</a>

                                第${pageInfo.pageNum}页/共${pageInfo.pages}页
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
