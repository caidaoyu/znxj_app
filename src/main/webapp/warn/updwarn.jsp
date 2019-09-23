<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
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
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 编辑隐患类型
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="/updwarntype" method="post">
                                <input type="hidden" name="id" value="${warntype.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="type">类型:</label>
                                            <select id="type" name="type" class="form-control">
                                                <option ${warntype.type eq "0"?"selected":""} value="0">隐患类型</option>
                                                <option ${warntype.type eq "1"?"selected":""} value="1">隐患等级</option>
                                                <option ${warntype.type eq "2"?"selected":""} value="2">终止原因</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd"name="name" required value="${warntype.name}">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td><input type="submit" class="btn btn-primary" value="保存">
                                            <input type="reset" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <hr>


</div>

</body>
</html>
