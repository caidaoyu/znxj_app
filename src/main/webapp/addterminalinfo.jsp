<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function validate_form(){
            var date1=$("#date1").val();
            var date2=$("#date2").val();
            if(date1==""){
                alert("启用时间必须填写");
                return false;
            }
            if(date2==""){
                alert("到期时间必须填写");
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

     <%--   <%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 智能终端--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addterminal" method="post" onsubmit="return validate_form(this)">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">终端名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" required="required">
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc">硬件型号:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="nfc" name="hardwaremodel" required="required">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc1">软件版本号:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="nfc1" name="softversion" required="required">
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="area">所属单位/部门:</label>
                                            <input type="text" name="department" id="area" required="required" class="form-control">
                                        </td>
                                    </tr>


                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="date">终端授权码:</label>
                                            <input type="text" class="form-control" style="width: 300px;" required="required" id="date" name="authcode" >
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="status">状态:</label>
                                            <select id="status" name="state" required="required">
                                                <option value="0">离线</option>
                                                <option value="1">在用</option>
                                                <option value="2">故障</option>
                                                <option value="3">丢失</option>
                                                <option value="4">更换</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="date1">启用时间:</label>
                                            <input type="text" class="form-control" style="width: 300px;" required="required" id="date1" name="enabletime" onClick="WdatePicker()" readonly="readonly">
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="date2">到期时间:</label>
                                            <input type="text" class="form-control" style="width: 300px;" required id="date2" name="unenabletime" onClick="WdatePicker()" readonly="readonly" placeholder="该项为必填项">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline" colspan="2">
                                            <label class="control-label" for="pwd2">终端描述:</label>
                                            <textarea class="form-control" cols="100" rows="5" id="pwd2" name="desccontent" ></textarea>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td  colspan="2"><input type="submit" class="btn btn-primary" value="保存">
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
