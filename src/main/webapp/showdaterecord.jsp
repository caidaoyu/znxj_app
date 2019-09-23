<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp" %>
    <script type="text/javascript">
        function getChecknameByid(recordid, dataname) {
            $.ajax({
                        url: 'getChecknameByid?recordid=' + recordid,
                        type: 'post',
                        dataType: 'text',
                        success: function (data) {
                            /* alert("有"+data+"个巡检项正在使用该数据，还要继续删除吗？");*/
                            if (data > 0) {
                                alert("有" + data + "个巡检项与该数据关联，不能删除此数据.");
                            } else {
                                var isDel = confirm("确认删除此数据吗？");
                                if (isDel != true) {
                                    return false;
                                    showtask();
                                } else {
                                    $.ajax({
                                        url: "delrecord?id=" + parseInt(recordid) + "&dataname=" + dataname,
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
                        }
                    }
            )
            ;
        }
        function delrecord(recordid, dataname) {
            var isDelete = confirm('确定删除吗？');
            if (isDelete != true) {
                return false;
            } else {
                $.ajax({
                    url: "delrecord?id=" + parseInt(recordid) + "&dataname=" + dataname,
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
            window.location = "showdaterecord?page=" + $("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
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
                                    <i class="glyphicon glyphicon-globe"></i> 系统设置
                                </h2>
                            </div>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:data">
                                    <a href="adddaterecord.jsp" id="button" class="btn btn-primary"
                                       style="margin-top: -6px;">添加数据项</a>
                                </shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <ul id="myTab" class="nav nav-tabs">
                            <li><a href="showsystem?type=1">终端显示和报告缓存设置</a></li>
                            <li><a href="showsystem?type=2">一般性设置</a></li>
                            <%--<li><a href="showsystem?type=3">邮件服务器设置</a></li>--%>
                            <li><a href="showsystem?type=4">图片|视频|音频服务器设置</a></li>
                            <li><a href="showsystem?type=3">报告波动值设置</a></li>
                            <li class="active"><a href="#data">可读数据管理</a></li>
                            <li><shiro:hasPermission name="add:apk"><a href="showappversion?page=1">Android
                                APK更新</a></shiro:hasPermission></li>
                            <li><shiro:hasPermission name="item:warntype"><a
                                    href="showwarntype?page=1">隐患类型和终止原因设置</a></shiro:hasPermission></li>
                            <li><a href="showReportSetting">显示单次任务报告设置</a></li>
                            <li><a href="showDoubleReportSetting">显示任务报告汇总设置</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="data" style="margin-left: 15px;">
                                <div class="box-content">
                                    <div class="form-inline" style="margin-bottom: 20px;">
                                        <form action="showdaterecord?page=1" method="post">
                                            <label class="control-label" for="name2">名称：</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="name2"
                                                   name="name" value="${param.name}">
                                            <input type="submit" class="btn btn-primary" value="搜索">
                                        </form>
                                    </div>
                                </div>
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr style="text-align: center">
                                        <th>操作</th>
                                        <th>名称</th>
                                        <th>单位</th>
                                        <th>类型</th>
                                    </tr>
                                    <c:forEach items="${pageBean.list}" var="datarecord">
                                        <tr>
                                            <td>
                                                <shiro:hasPermission name="upd:data">
                                                    <a href="querybydateid?id=${datarecord.id}&page=${pageBean.currentPage}"><i
                                                            class="glyphicon glyphicon-edit red "></i></a>
                                                </shiro:hasPermission>
                                                <shiro:hasPermission name="del:data">
                                                    <a href="javascript:void(0)"
                                                       onclick="getChecknameByid(${datarecord.id},'${datarecord.name}')">
                                                        <i class="glyphicon glyphicon-trash"></i></a>
                                                </shiro:hasPermission>
                                            </td>
                                            <td>${datarecord.name}</td>
                                            <c:if test="${datarecord.recordtype==1}">
                                                <td>${datarecord.unitname}</td>
                                            </c:if>
                                            <c:if test="${datarecord.recordtype==2}">
                                                <td>${datarecord.state}</td>
                                            </c:if>
                                            <td>
                                                <c:if test="${datarecord.recordtype==0}">状态项</c:if>
                                                <c:if test="${datarecord.recordtype==1}">记录项</c:if>
                                                <c:if test="${datarecord.recordtype==2}">枚举项</c:if>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </table>
                                <div style="height: 50px;width: 500px;margin-left: 300px;">
                                    <a href="showdaterecord?page=1&name=${name}">第一页</a>
                                    <c:if test="${pageBean.currentPage>1}">
                                        <a href="showdaterecord?page=${pageBean.currentPage-1}&name=${name}">上一页</a>
                                    </c:if>

                                    <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                        <a href="showdaterecord?page=${pageBean.currentPage+1}&name=${name}">下一页</a>
                                    </c:if>

                                    <a href="showdaterecord?page=${pageBean.totalPage}&name=${name}">最后一页</a>

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
</div>
</body>
</html>
