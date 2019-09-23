<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function getequipcount(areaid,name){
            $.ajax({
                url:'getequipcount?areaid='+areaid,
                type:'post',
                dataType:'text',
                success:function(data){
                    /* alert("有"+data+"个巡检项正在使用该数据，还要继续删除吗？");*/
                    var isDel=confirm("有"+data+"个设备与该区域关联，还要继续删除吗？");
                    if (isDel!=true) {
                        return false;
                        showtask();
                    }else {
                        delarea(areaid,name);
                    }
                }
            });
        }
        function delarea(id,name){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delarea?id=" + id+"&name="+name,
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
            window.location="showallarea?page="+$("#page").val();
        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container" style="overflow: hidden">
    <div class="row">
        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <%--<div style="float: left;">--%>
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 区域管理列表
                                </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                                <%--<shiro:hasPermission name="add:area">--%>
                                    <%--<a href="toaddarea" id="button" class="btn btn-primary" style="margin-top: -6px;">添加区域</a>--%>
                                <%--</shiro:hasPermission>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        </div>
                        <div class="box-content">
                            <div class="form-inline">
                                <form action="showallarea?page=1" method="post">
                                    <label class="control-label" for="name2">名称：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2" name="name"value="${param.name}">
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:area">
                                        <a href="toaddarea" id="button" class="btn btn-primary"style="margin-left:250px;">添加区域</a>
                                    </shiro:hasPermission>
                                </form>
                            </div>
                        </div>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable" >
                                <tr style="text-align: center">
                                    <th>操作</th>
                                    <th>名称</th>
                                    <th>厂区</th>
                                    <th>描述</th>
                                    <th>GPS经度</th>
                                    <th>GPS维度</th>
                                    <th>NFC标签</th>
                                </tr>
                                <c:forEach items="${pageBean.list}" var="area">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:area">
                                                <a href="findbyareaid?id=${area.id}&page=${pageBean.currentPage}"><i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:area">
                                                <a href="javascript:void(0)" onclick="getequipcount(${area.id},'${area.customid}')"><i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                           <%-- <shiro:hasPermission name="add:area">--%>
                                                <a href="queryareadetail?id=${area.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                            <%--</shiro:hasPermission>--%>
                                        </td>
                                        <td>${area.customid}</td>
                                        <td>${area.site.customid}</td>
                                        <td>${area.desccontent}</td>
                                        <td>${area.longitude}</td>
                                        <td>${area.latitude}</td>
                                        <td>${area.nfc.unitcode}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                            <a href="showallarea?page=1&name=${name}">第一页</a>
                            <c:if test="${pageBean.currentPage>1}">
                                <a href="showallarea?page=${pageBean.currentPage-1}&name=${name}">上一页</a>
                            </c:if>

                            <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                <a href="showallarea?page=${pageBean.currentPage+1}&name=${name}">下一页</a>
                            </c:if>

                            <a href="showallarea?page=${pageBean.totalPage}&name=${name}">最后一页</a>

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
