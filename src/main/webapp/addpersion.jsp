<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <script type="text/javascript">
        function validate_form(){
            var tel=$("#tel").val();
            var tel2=$("#te2").val();
            //固定电话
            var phone=/^0\d{2,3}-?\d{7,8}$/;
            //移动电话
            var reg=/(1[3-9]\d{9}$)/;

            if(!phone.test(tel)){
                alert("固定电话格式错误");
                return false;
            }else {
                return true;
            }
            if(!reg.test(tel2)){
                alert("请输入正确格式的手机号码");
                return false;
            }else {
                return true;
            }
        }
    </script>
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
                                <i class="glyphicon glyphicon-globe"></i> 联系人--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addcont" method="post" onsubmit="return validate_form()" >
                                <input type="hidden" name="groupid" value="0">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="role1">岗位名称:</label>
                                            <select class="form-control" id="role1" style="width: 150px;" name="roletype">
                                                <option value="0">处长</option>
                                                <option value="1">主任</option>
                                                <option value="2">经理</option>
                                                <option selected="selected" value="3">员工</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">姓名:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd"name="name" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="tel">固定电话:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="tel"name="phone" required onblur="checktel(value)">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="tel2">移动电话:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="tel2"name="mobilephone" required onblur="checktel2(value)">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="tel3">电子邮件:</label>
                                            <input type="email" class="form-control" style="width: 300px;" id="tel3"name="email" required>
                                        </td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td class="form-inline">--%>
                                            <%--<label class="control-label">群组邮件:</label>--%>
                                            <%--<input type="checkbox" name="selectedgroup" value="0">任务日报群组--%>
                                            <%--<input type="checkbox" name="selectedgroup" value="1">任务周报群组--%>
                                            <%--<input type="checkbox" name="selectedgroup" value="2">任务月报群组--%>
                                            <%--<select class="form-control" id="tel4" style="width: 150px;" name="groupid">--%>
                                                <%--<option value="0">任务日报群组</option>--%>
                                                <%--<option value="1">任务周报群组</option>--%>
                                                <%--<option value="2">任务月报群组</option>--%>
                                            <%--</select>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
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
</div>

</body>
</html>
