<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
       $(function(){
           $("#manager").click(function () {
               $("#list :checkbox").prop("checked", true);
           })
           $("#admin").click(function () {
               $("#list :checkbox").prop("checked", false);
           })
       })
    </script>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
        <div id="content" class="col-lg-10 col-sm-10">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 权限列表
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="login1.jsp" method="post">
                                <input type="button" class="btn btn-primary" value="管理员" id="manager">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" class="btn btn-primary" value="系统维护员" id="admin">
                                <table id="list" class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" style="width: 500px;">
                                    <tr>
                                        <th>栏目</th>
                                        <th>权限</th>
                                    </tr>
                                    <tr>
                                        <td>管理员及权限</td>
                                        <td><input type="checkbox"></td>
                                    </tr>
                                    <tr>
                                        <td>用户管理</td>
                                        <td><input type="checkbox" checked="checked"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="checkbox" checked="checked"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="checkbox" checked="checked"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="checkbox" checked="checked"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><input type="checkbox" checked="checked"></td>
                                    </tr>
                                    <tr>
                                    <td></td>
                                    <td><input type="checkbox" checked="checked"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><input type="submit" class="btn btn-primary" value="保存">
                                    </tr>
                                </table>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
