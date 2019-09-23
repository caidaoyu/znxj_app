<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function validate_form(){
            var tel=$("#tel").val();
            var tel2=$("#te2").val();
            //固定电话
            var phone=/^0\d{2,3}-?\d{7,8}$/;
            //移动电话
            var reg=/(1[3-9]\d{9}$)/;
            if(!phone.test(tel)){
                alert("请输入正确格式的手机号码");
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
                                <i class="glyphicon glyphicon-globe"></i> 联系人--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updcont" method="post" onsubmit="return validate_form()">
                                <input type="hidden" name="id" value="${contactinfo.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="role1">角色:</label>
                                            <select class="form-control" id="role1" style="width: 150px;" name="roletype">
                                                <option ${contactinfo.roletype eq '' ? 'selected' : ''} value="">所有</option>
                                                <option ${contactinfo.roletype eq '0' ? 'selected' : ''} value="0">处长</option>
                                                <option ${contactinfo.roletype eq '1' ? 'selected' : ''} value="1">主任</option>
                                                <option ${contactinfo.roletype eq '2' ? 'selected' : ''} value="2">经理</option>
                                                <option ${contactinfo.roletype eq '3' ? 'selected' : ''} value="3">员工</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">姓名:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd"name="name" required value="${contactinfo.name}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="tel">固定电话:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="tel"name="phone" required value="${contactinfo.phone}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="tel2">移动电话:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="tel2"name="mobilephone" required value="${contactinfo.mobilephone}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="tel3">电子邮件:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="tel3"name="email" required value="${contactinfo.email}">
                                        </td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td class="form-inline">--%>
                                            <%--<label class="control-label">群组邮件:</label>--%>
                                            <%--<input ${contactinfo.groupid.indexOf('0')!=-1?'checked':''} type="checkbox" name="selectedgroup" value="0">任务日报群组--%>
                                            <%--<input ${contactinfo.groupid.indexOf('1')!=-1?'checked':''} type="checkbox" name="selectedgroup" value="1">任务周报群组--%>
                                            <%--<input ${contactinfo.groupid.indexOf('2')!=-1?'checked':''} type="checkbox" name="selectedgroup" value="2">任务月报群组--%>
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
