<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delsite(id,sitename){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delsite?id=" + id+"&sitename="+sitename,
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
            window.location="showsite?page="+$("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.pageNum}">
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
                                    <i class="glyphicon glyphicon-globe"></i> 厂区列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <c:if test="${siteid==null}">
                                    <shiro:hasPermission name="add:site">
                                        <a href="addsite.jsp" id="button" class="btn btn-primary" style="margin-top: -6px;">添加厂区</a>
                                    </shiro:hasPermission>
                                </c:if>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>厂区名称</th>
                                    <th>厂区描述</th>
                                    <th>厂区备注</th>
                                </tr>
                                <c:forEach items="${pageBean.list}" var="site">
                                    <tr>
                                        <td>
                                            <c:if test="${siteid==null}">
                                            <shiro:hasPermission name="upd:site">
                                                <a href="findbysiteid?id=${site.id}&page=${pageBean.pageNum}"> <i class="glyphicon glyphicon-edit red "></i>
                                                </a>
                                            </shiro:hasPermission>
                                            </c:if>
                                            <c:if test="${siteid==null}">
                                            <shiro:hasPermission name="del:site">
                                                <a href="javascript:void(0)" onclick="delsite(${site.id},'${site.customid}')">
                                                    <i class="glyphicon glyphicon-trash"></i>
                                                </a>
                                            </shiro:hasPermission>
                                            </c:if>
                                            <%--<shiro:hasPermission name="item:sitedetail">--%>
                                                <a href="querysitedetail?id=${site.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                            <%--</shiro:hasPermission>--%>
                                        </td>
                                        <td>${site.customid}</td>
                                        <td>${site.desccontent}</td>
                                        <td>${site.remark}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;margin-left: 300px;">
                                <a href="showsite?page=1">第一页</a>
                                <c:if test="${pageBean.pageNum>1}">
                                    <a href="showsite?page=${pageBean.pageNum-1}">上一页</a>
                                </c:if>

                                <c:if test="${pageBean.pageNum<pageBean.pages}">
                                    <a href="showsite?page=${pageBean.pageNum+1}">下一页</a>
                                </c:if>

                                <a href="showsite?page=${pageBean.pages}">最后一页</a>

                                第${pageBean.pageNum}页/共${pageBean.pages}页
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
