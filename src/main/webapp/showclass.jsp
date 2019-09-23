<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function countuser(id,classname){
            $.ajax({
                url: "countuser?classid=" + id,
                type: "post",
                datatype: "json",
                success: function (data) {
                    if (data > 0) {
                        var isDel=confirm("该班组暂时拥有"+data+"人，不建议删除");
                        if (isDel!=true) {
                            showtask();
                        }else {
                            showtask();
                        }

                    } else {
                        delclass(id,classname);
                    }
                }
            });
        }
        function delclass(id,classname){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delclass?id=" + id+"&classname="+classname,
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
            window.location="showallclass?page="+$("#page").val();
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
                                    <i class="glyphicon glyphicon-globe"></i> 班组管理--列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:class">
                                    <a href="toaddclass" id="button" class="btn btn-primary" style="margin-top: -6px;">添加班组</a>
                                </shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>编号</th>
                                    <th>描述</th>
                                    <th>班组负责人</th>
                                    <th>所属厂区</th>
                                    <th>上岗时间</th>
                                    <th>下岗时间</th>

                                </tr>
                                <tbody id="tbUser2">
                               <c:forEach items="${pageBean.list}" var="c">
                                   <tr>
                                       <td>
                                           <shiro:hasPermission name="upd:class">
                                                <a href="selectbyclassid?id=${c.id}&page=${pageBean.currentPage}"> <i class="glyphicon glyphicon-edit red "></i></a>
                                           </shiro:hasPermission>
                                           <shiro:hasPermission name="del:class">
                                                <a href="javascript:void(0)" onclick="countuser(${c.id},'${c.customid}')"><i class="glyphicon glyphicon-trash"></i></a>
                                           </shiro:hasPermission>
                                           <a href="queryclassdetailbyid?id=${c.id}"> <i class="glyphicon glyphicon-info-sign blue "></i></a>

                                       </td>
                                       <td>${c.customid}</td>
                                       <td>${c.classdesc}</td>
                                       <td>${c.userinfo.realname}</td>
                                       <td>${c.site.customid}</td>
                                       <td>
                                          <sdf:formatDate value="${c.workstarttime}" pattern="yyyy-MM-dd hh:mm"></sdf:formatDate>
                                       </td>
                                       <td>
                                           <sdf:formatDate value="${c.workendtime}" pattern="yyyy-MM-dd hh:mm"></sdf:formatDate>
                                       </td>
                                   </tr>
                               </c:forEach>
                                </tbody>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;font: 18px;">
                                <a href="showallclass?page=1">第一页</a>
                                <c:if test="${pageBean.currentPage>1}">
                                    <a href="showallclass?page=${pageBean.currentPage-1}">上一页</a>
                                </c:if>

                                <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                    <a href="showallclass?page=${pageBean.currentPage+1}">下一页</a>
                                </c:if>

                                <a href="showallclass?page=${pageBean.totalPage}">最后一页</a>

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
