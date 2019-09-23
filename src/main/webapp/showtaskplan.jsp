<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function deltask(id,taskname){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "deltask?id=" + id+"&taskname="+taskname,
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
            var tasktype=$("#tasktype").val();
            window.location="showtaskplan?page="+$("#page").val()+"&type="+tasktype;
        }
        function refer(){
            location.reload();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <input type="hidden" value="${type}" id="tasktype">
            <div class="row">
                <div class="box col-md-12">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li class="active" >
                                <a href="#home" data-toggle="tab" style="background: #35A7E7;color: white;" onclick="refer()">任务列表</a>
                            </li>
                            <%--<li><a href="showstoptask?page=1&state=3&type=${type}">终止任务</a></li>--%>
                            <li>
                                <shiro:hasPermission name="item:taskreport">
                                    <a href="toshowreport?page=1&tasktype=${type}">任务报告</a>
                                </shiro:hasPermission>
                            </li>
                            <c:if test="${type==2}">
                                <li>
                                    <shiro:hasPermission name="item:reportquick">
                                        <a href="showQuickReport1?page=1&type=${type}&qtype=1">立即上报</a></li>
                                    </shiro:hasPermission>
                            </c:if>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i>
                                        <c:if test="${type==0}">日常巡检任务--列表</c:if>
                                        <c:if test="${type==1}">计划巡检任务--列表</c:if>
                                        <c:if test="${type==2}">HSE隐患排查--列表</c:if>
                                        <c:if test="${type==3}">视频巡检任务--列表</c:if>
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <div class="form-inline" style="margin-bottom: 20px;">
                                        <form action="showtaskplan?page=1&type=${type}" method="post">
                                            <label class="control-label" for="name2">任务号：</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="name2" name="identifyingid" value="${param.identifyingid}">
                                            <label class="control-label" for="status">状态：</label>
                                            <select class="form-control" id="status" name="state">
                                                <option ${state eq '' ? 'selected' : ''} value="">所有</option>
                                                <option ${state eq '1' ? 'selected' : ''} value="1">运行中</option>
                                                <option ${state eq '0' ? 'selected' : ''} value="0">停止运行</option>
                                            </select>
                                            <input type="submit" class="btn btn-primary" value="搜索">
                                            <c:if test="${type==0}">
                                                <shiro:hasPermission name="add:usualtask">
                                                    <a href="toaddtaskplan?type=${type}" style="margin-left:250px;">
                                                        <input type="button" class="btn btn-primary" value="新任务"></a>
                                                </shiro:hasPermission>
                                            </c:if>
                                            <c:if test="${type==1}">
                                                <shiro:hasPermission name="add:plantask">
                                                    <a href="toaddtaskplan?type=${type}" style="margin-left:250px;">
                                                        <input type="button" class="btn btn-primary" value="新任务"></a>
                                                </shiro:hasPermission>
                                            </c:if>
                                            <c:if test="${type==2}">
                                                <shiro:hasPermission name="add:errortask">
                                                    <a href="toaddtaskplan?type=${type}" style="margin-left:250px;">
                                                        <input type="button" class="btn btn-primary" value="新任务"></a>
                                                </shiro:hasPermission>
                                            </c:if>
                                            <c:if test="${type==3}">
                                                <shiro:hasPermission name="add:videotask">
                                                    <a href="toaddtaskplan?type=${type}" style="margin-left:250px;">
                                                        <input type="button" class="btn btn-primary" value="新任务"></a>
                                                </shiro:hasPermission>
                                            </c:if>
                                        </form>

                                    </div>
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr style="text-align: center">
                                            <th>操作</th>
                                            <th>所属厂区</th>
                                            <th>任务号</th>
                                            <th>任务名</th>
                                            <th>任务描述</th>
                                            <th>负责人</th>
                                            <th>是否顺序执行</th></th>
                                           <%-- <th>是否单次执行</th>--%>
                                            <th>状态</th>
                                            <th>创建时间</th>
                                            <th>批准时间</th>
                                        </tr>
                                        <c:forEach items="${pageBean.list}" var="task">
                                            <tr>
                                                <td>
                                                    <c:if test="${type==0}">
                                                        <shiro:hasPermission name="upd:usualtask">
                                                            <a href="querybytaskid?id=${task.id}&type=${task.type}&page=${pageBean.currentPage}">
                                                                <i class="glyphicon glyphicon-edit red "></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                        <shiro:hasPermission name="del:usualtask">
                                                            <a href="javascript:void(0)" onclick="deltask(${task.id},'${task.customid}')">
                                                                <i class="glyphicon glyphicon-trash"></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                    </c:if>
                                                    <c:if test="${type==1}">
                                                        <shiro:hasPermission name="update:plantask">
                                                            <a href="querybytaskid?id=${task.id}&type=${task.type}&page=${pageBean.currentPage}">
                                                                <i class="glyphicon glyphicon-edit red "></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                        <shiro:hasPermission name="del:plantask">
                                                            <a href="javascript:void(0)" onclick="deltask(${task.id},'${task.customid}')">
                                                                <i class="glyphicon glyphicon-trash"></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                    </c:if>
                                                    <c:if test="${type==2}">
                                                        <shiro:hasPermission name="update:hsetask">
                                                            <a href="querybytaskid?id=${task.id}&type=${task.type}&page=${pageBean.currentPage}">
                                                                <i class="glyphicon glyphicon-edit red "></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                        <shiro:hasPermission name="del:hsetask">
                                                            <a href="javascript:void(0)" onclick="deltask(${task.id},'${task.customid}')">
                                                                <i class="glyphicon glyphicon-trash"></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                    </c:if>
                                                    <c:if test="${type==3}">
                                                        <shiro:hasPermission name="update:videotask">
                                                            <a href="querybytaskid?id=${task.id}&type=${task.type}&page=${pageBean.currentPage}">
                                                                <i class="glyphicon glyphicon-edit red "></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                        <shiro:hasPermission name="del:videotask">
                                                            <a href="javascript:void(0)" onclick="deltask(${task.id},'${task.customid}')">
                                                                <i class="glyphicon glyphicon-trash"></i>
                                                            </a>
                                                        </shiro:hasPermission>
                                                    </c:if>

                                                    <a href="taskplandetail?id=${task.id}&type=${task.type}">
                                                        <i class="glyphicon glyphicon-info-sign blue "></i>
                                                    </a>

                                                </td>
                                                <td>${task.site.customid}</td>
                                                <td>${task.identifyingid}</td>
                                                <td>${task.customid}</td>
                                                <td>${task.taskdesc}</td>
                                                <td>${task.username}</td>
                                                <td>
                                                    <c:if test="${task.issequentially ==0}">否</c:if>
                                                    <c:if test="${task.issequentially ==1}">是</c:if>
                                                    <c:if test="${task.issequentially ==2}">否</c:if>
                                                    <c:if test="${task.issequentially ==3}">是</c:if></td>
                                            <%--<td>
                                                    <c:if test="${task.issingletime ==0}">否</c:if>
                                                    <c:if test="${task.issingletime ==1}">是</c:if></td>--%>
                                                <td>
                                                    <c:if test="${task.state ==0}">停止运行</c:if>
                                                    <c:if test="${task.state ==1}">运行中</c:if>
                                                </td>
                                                <td><sdf:formatDate value="${task.createtime}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                                <td><sdf:formatDate value="${task.approvetime}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <div style="height: 50px;width: 500px;margin-left: 300px;">
                                        <a href="showtaskplan?page=1&type=${type}&identifyingid=${identifyingid}&state=${state}">第一页</a>
                                        <c:if test="${pageBean.currentPage>1}">
                                            <a href="showtaskplan?page=${pageBean.currentPage-1}&type=${type}&identifyingid=${identifyingid}&state=${state}">上一页</a>
                                        </c:if>

                                        <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                            <a href="showtaskplan?page=${pageBean.currentPage+1}&type=${type}&identifyingid=${identifyingid}&state=${state}">下一页</a>
                                        </c:if>

                                        <a href="showtaskplan?page=${pageBean.totalPage}&type=${type}&identifyingid=${identifyingid}&state=${state}">最后一页</a>

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
