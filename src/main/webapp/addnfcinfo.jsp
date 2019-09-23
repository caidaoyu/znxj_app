<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function validate_form(){
            var date=$("#date").val();
            if(date==""){
                alert("启用时间必须填写");
                return false;
            }
        }
    </script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

<%--
        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> NFC标签--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addnfc" method="post" onsubmit="return validate_form(this)">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" required>
                                        </td>
                                    </tr>
                                    <tr>
                                            <td class="form-inline">
                                            <label class="control-label" for="nfc">NFC串码:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="nfc" name="unitcode" required placeholder="该项必须填写">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc1">GPS经度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="nfc1" name="longitude" required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">GPS纬度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="latitude" required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="area">标签描述:</label>
                                            <textarea class="form-control" id="area" required name="desccontent" rows="5" cols="50" placeholder="该项必须填写"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="date">启用时间:</label>
                                            <input type="text" class="form-control" style="width: 300px;" placeholder="该项必须填写" required="required" id="date" name="enabletime" onClick="WdatePicker()" readonly="readonly">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="status">状态:</label>
                                            <select id="status" name="state" required="required">
                                                <option value="0">失效</option>
                                                <option value="1">正常</option>
                                                <option value="2">遗失</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="remark">备注:</label>
                                            <textarea class="form-control" id="remark" name="remark" rows="5" cols="50" ></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" class="btn btn-primary" value="保存">
                                            <input type="button" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
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
