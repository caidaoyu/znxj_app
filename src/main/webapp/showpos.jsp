<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script>
        function delpos(id,posname){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delpos?id=" + id+"&posname="+posname,
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
            window.location="showposition?page="+$("#page").val();
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
                                    <i class="glyphicon glyphicon-globe"></i> 岗位列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:pos"><a href="addpos.jsp" id="button" class="btn btn-primary" style="margin-top: -6px;">添加岗位</a></shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>岗位名称</th>
                                    <th>岗位描述</th>
                                </tr>
                                <tbody id="tbUser2">
                                <c:forEach items="${pageBean.list}" var="pos">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:pos">
                                                <a href="selectbyposid?id=${pos.id}&page=${pageBean.currentPage}"> <i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission >
                                            <shiro:hasPermission name="del:pos">
                                                <a href="#" onclick="delpos(${pos.id},'${pos.positionname}')"> <i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                            <a href="queryposdetailbyid?id=${pos.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                        </td>
                                        <td>${pos.positionname}</td>
                                        <td>${pos.positiondesc}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div style="height: 50px;width: 500px;margin-left: 300px;">
                                <a href="showposition?page=1">第一页</a>
                               <c:if test="${pageBean.currentPage>1}">
                                   <a href="showposition?page=${pageBean.currentPage-1}">上一页</a>
                               </c:if>

                               <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                   <a href="showposition?page=${pageBean.currentPage+1}">下一页</a>
                               </c:if>

                               <a href="showposition?page=${pageBean.totalPage}">最后一页</a>

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
