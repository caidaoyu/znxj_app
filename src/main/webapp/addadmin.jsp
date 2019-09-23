<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
   <%@ include file="/WEB-INF/pages/common/header.jsp"%>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
    function queryname(uname){
        $.ajax({
            url:"queryexistname?username="+uname,
            type:"post",
            dataType:"json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success:function(data){
                if(data=="0"){
                    $("#existuser").css("display","none");
                }else{
                    $("#existuser").css("display","block");
                }
            }
        })
    }
    
    function validateForm(){
        var pwd=$("#pwd").val();
        //固定电话
        var reg=/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
        if(!reg.test(pwd)){
            alert("密码必须包含字母、数字和特殊符号");
            return false;
        }else {
            return true;
        }
    }
</script>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        <%--<jsp:include page="/WEB-INF/pages/common/menu.jsp" />--%>
        <div id="content"class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 管理员--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form id="sub" action="addadmin" method="post" onsubmit="return validateForm()">
                                <input type="hidden" class="form-control" style="width: 300px;" name="createuser" required="required" value="${userInfo.id}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="rol">角色:</label>
                                            <select class="form-control" id="rol" style="width: 150px;" name="roleid">
                                                <c:forEach items="${roles}" var="role">
                                                    <option value="${role.roleid}">${role.rolename}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="site">厂区:</label>
                                            <select name="siteid" id="site" class="form-control">
                                                <option value="">所有厂区</option>
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline" id="uname">
                                            <label class="control-label" for="name2">登录账号:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="name2" name="username" required="required" onblur="queryname(value)">
                                            <span style="display: none;" id="existuser">该用户已存在！！</span>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">登录密码:</label>
                                            <input type="password" class="form-control" style="width: 300px;" id="pwd" name="password" required="required">
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="user">过期时间:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="user" name="expirydate" onClick="WdatePicker()" required="required">
                                        </td>
                                        <td class="form-inline">
                                            <span>状态:</span>
                                            <input type="radio" name="state" value="0">禁用
                                            <input type="radio" name="state" value="1" checked>启用
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><img src="http://localhost:8080/report/images/2017/10/10/092bf524-5e57-4829-b34a-7f5a667c6fb7_IMG_20171010_105354.jpg"></td>
                                    </tr>

                                    <tr>
                                        <td colspan="2"><input id="saveUser" type="submit" class="btn btn-primary" value="保存" >
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
<script type="text/javascript">
</script>
</body>
</html>
