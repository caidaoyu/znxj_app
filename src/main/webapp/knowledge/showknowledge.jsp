<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function delknowledge(id,title){
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "delknowledge?id=" + id+"&title="+title,
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
            window.location="/showknowledge?page=1";
        }
        $(function(){
            var errorlen=$("#errorlen").val();
            var files=new Array();
            for(var i=0; i<parseInt(errorlen); i++){
                files=JSON.parse($("#allattachs"+i).val());

                $("#attach"+i).text(files.length);
               /* var istr = allattach.length;
                var str1 = allattach.replace(",","");
                var istr1 = str1.length;
                var size=parseInt(istr)-parseInt(istr1);
               console.log(parseInt(size));
                if(parseInt(size)==0){
                    $("#attach"+i).text(1);
                }else{
                    $("#attach"+i).text(parseInt(size)+1);
                }*/

            }
        })
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
                            <%--<div style="float: left;">--%>
                                <h2>
                                    <i class="glyphicon glyphicon-globe"></i> 知识管理列表
                                </h2>
                            <%--</div>--%>
                            <%--<div style="float: right;">--%>
                                <%--<shiro:hasPermission name="add:knowledge">--%>
                                    <%--<a href="toaddknowledge" id="button" class="btn btn-primary" style="margin-top: -6px;">添加知识</a>--%>
                                <%--</shiro:hasPermission>--%>
                            <%--</div>--%>
                            <%--<div class="clearfix"></div>--%>
                        </div>
                        <div class="box-content">
                            <div class="form-inline" style="margin-bottom: 20px;">
                                <form action="showknowledge?page=1" method="post">
                                    <label class="control-label" for="name2">标题：</label>
                                    <input type="text" class="form-control" style="width: 300px;" id="name2" name="title"value="${param.title}">
                                    <input type="submit" class="btn btn-primary" value="搜索">
                                    <shiro:hasPermission name="add:knowledge">
                                        <a href="toaddknowledge" id="button" class="btn btn-primary" style="margin-left:250px;">添加知识</a>
                                    </shiro:hasPermission>
                                </form>
                            </div>
                            <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                <tr>
                                    <th>操作</th>
                                    <th>知识类别</th>
                                    <th>知识标题</th>
                                    <th>内容简述</th>
                                    <th>关联设备</th>
                                    <th>附件文档</th>
                                </tr>
                                <c:forEach items="${info.list}" var="knowledge" varStatus="status">
                                    <tr>
                                        <td>
                                            <shiro:hasPermission name="upd:knowledge">
                                                <a href="getknowledgebyid?id=${knowledge.id}"> <i class="glyphicon glyphicon-edit red "></i></a>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="del:knowledge">
                                            <a href="javascript:void(0)" onclick="delknowledge(${knowledge.id},'${knowledge.title}')"><i class="glyphicon glyphicon-trash"></i></a>
                                            </shiro:hasPermission>
                                            <a href="getknowledgedetail?id=${knowledge.id}"><i class="glyphicon glyphicon-info-sign blue "></i></a>
                                        </td>
                                        <td>${knowledge.ktype.typename}</td>
                                        <td>${knowledge.title}</td>
                                        <td>${knowledge.descontent}</td>
                                        <td>${knowledge.equip.name}</td>
                                        <td id="attach${status.index}"></td>
                                        <input type="hidden" value='${knowledge.attachment}' id="allattachs${status.index}">
                                    </tr>
                                </c:forEach>

                                <input type="hidden" id="errorlen" value="${info.list.size()}">
                            </table>
                            <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                <a href="showknowledge?page=1&title=${title}">第一页</a>
                                <c:if test="${info.pageNum>1}">
                                    <a href="showknowledge?page=${info.pageNum-1}&title=${title}">上一页</a>
                                </c:if>

                                <c:if test="${info.pageNum<info.pages}">
                                    <a href="showknowledge?page=${info.pageNum+1}&title=${title}">下一页</a>
                                </c:if>

                                <a href="showknowledge?page=${info.pages}&title=${title}">最后一页</a>

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
