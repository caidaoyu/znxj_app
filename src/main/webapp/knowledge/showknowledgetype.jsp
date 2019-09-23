<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delknowledgetype(id,typename){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delknowledgetype?typeid=" + id+"&typename="+typename,
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
            window.location="/showknowledgetype?page=1";
        }
    </script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
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
                                    <i class="glyphicon glyphicon-globe"></i> 知识类别管理列表
                                </h2>
                            </div>
                            <div style="float: right;">
                                <shiro:hasPermission name="add:knowtype">
                                    <a href="knowledge/addknowledgetype.jsp" id="button" class="btn btn-primary" style="margin-top: -6px;">添加类别</a>
                                </shiro:hasPermission>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="box-content">

                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>类别名称</th>
                                    <th>类别描述</th>
                                </tr>
                                <c:forEach items="${info.list}" var="type" >
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:knowtype">
                                                <a href="getknowledgetypebyid?typeid=${type.typeid}"> <i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:knowtype">
                                                <a href="javascript:void(0)" onclick="delknowledgetype(${type.typeid},'${type.typename}')"><i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                        </td>
                                        <td>${type.typename}</td>
                                        <td>${type.typedesc}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showknowledgetype?page=1">第一页</a>
                                <c:if test="${info.pageNum>1}">
                                    <a href="showknowledgetype?page=${info.pageNum-1}">上一页</a>
                                </c:if>

                                <c:if test="${info.pageNum<info.pages}">
                                    <a href="showknowledgetype?page=${info.pageNum+1}">下一页</a>
                                </c:if>

                                <a href="showknowledgetype?page=${info.pages}">最后一页</a>

                                第${info.pageNum}页/共${info.pages}页
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
