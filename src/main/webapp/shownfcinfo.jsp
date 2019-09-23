<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delnfc(id,nfc){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delnfc?id=" + id+"&nfc="+nfc,
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
            window.location="showallnfc?page="+$("#page").val();
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
                            <%--<div style="float: left;">--%>
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> NFC标签管理列表
                                </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                                <%--<shiro:hasPermission name="add:nfc">--%>
                                    <%--<a href="addnfcinfo.jsp" id="button" class="btn btn-primary" style="margin-top: -6px;">添加NFC标签</a>--%>
                                <%--</shiro:hasPermission>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>

                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="showallnfc?page=1" method="post">
                                    <label class="control-label" for="name2">名称：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2" name="customid" value="${param.customid}">
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:nfc">
                                        <a href="addnfcinfo.jsp" id="button" class="btn btn-primary" style="margin-left:250px;">添加NFC标签</a>
                                    </shiro:hasPermission>
                                </form>
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                            <tr style="text-align: center">
                                <th>操作</th>
                                <th>名称</th>
                                <th>NFC串码</th>
                                <th>描述</th>
                                <th>GPS经度</th>
                                <th>GPS维度</th>
                                <th>启用时间</th>
                                <th>状态</th>
                                <th>备注</th>
                            </tr>
                            <c:forEach items="${pageBean.list}" var="nfc">
                                <tr>
                                    <td>
                                        <shiro:hasPermission name="upd:nfc">
                                            <a href="querybynfcid?id=${nfc.id}&page=${pageBean.pageNum}"><i class="glyphicon glyphicon-edit red "></i></a>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="del:nfc">
                                            <a href="javascript:void(0)" onclick="delnfc(${nfc.id},'${nfc.customid}')"><i class="glyphicon glyphicon-trash"></i></a>
                                        </shiro:hasPermission>
                                        <%--<shiro:hasPermission name="item:nfcdetail">--%>
                                            <a href="querynfcdetail?id=${nfc.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                        <%--</shiro:hasPermission>--%>
                                    </td>
                                    <td>${nfc.customid}</td>
                                    <td>${nfc.unitcode}</td>
                                    <td>${nfc.desccontent}</td>
                                    <td>${nfc.longitude}</td>
                                    <td>${nfc.latitude}</td>
                                    <td><sdf:formatDate value="${nfc.enabletime}" pattern="yyyy-MM-dd"></sdf:formatDate></td>
                                    <td>
                                        <c:if test="${nfc.state==0}">失效</c:if>
                                        <c:if test="${nfc.state==1}">正常</c:if>
                                        <c:if test="${nfc.state==2}">遗失</c:if>
                                    </td>
                                    <td>${nfc.remark}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                            <a href="showallnfc?page=1&customid=${customid}">第一页</a>
                            <c:if test="${pageBean.pageNum>1}">
                                <a href="showallnfc?page=${pageBean.pageNum-1}&customid=${customid}">上一页</a>
                            </c:if>

                            <c:if test="${pageBean.pageNum<pageBean.pages}">
                                <a href="showallnfc?page=${pageBean.pageNum+1}&customid=${customid}">下一页</a>
                            </c:if>

                            <a href="showallnfc?page=${pageBean.pages}&customid=${customid}">最后一页</a>

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
