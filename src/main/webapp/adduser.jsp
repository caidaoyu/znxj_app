<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function changesite(){
            var siteid = $("#site option:selected")[0].value;
            $.ajax({
                url:"queryclassbysiteid",
                type:"post",
                data:{
                    siteid:siteid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var classselect =  document.getElementById("classid");
                        classselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var classop = document.createElement("option");
                            classop.innerHTML = data[i].customid;
                            classop.value = data[i].id;
                            classselect.appendChild(classop);
                        }
                        return true;
                    }else{
                        alert("该厂区暂时没有班组");
                        return false;
                    }
                }
            });
        }

        $(function () {
            $("#username").blur(function(){
                var username = $("#username").val();
                var classid = $("#classid").val();
                $.ajax({
                    url:"isUserExist",
                    type:"post",
                    data:{
                        userName:username,
//                        classid:classid,
                        id:null,
//                        oldClassId:null
                    },
                    dataType:"json",
                    success:function(data){
                        if(data >0){
                            alert("已存在登录名为"+username+"的用户");
                            $("input[type='submit']").attr("disabled",true);
                        }else{
                            $("input[type='submit']").attr("disabled",false);
                        }
                    }
                })
            });
        });


    </script>
</head>
<body>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

       <%-- <%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 用户--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="adduser" method="post">
                                <input type="hidden" name="type" value="0">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="site">所属厂区:</label>
                                            <select class="form-control" name="siteareaid" id="site" onchange="changesite()">
                                                <c:forEach items="${siteareainfos}" var="sites">
                                                    <option value="${sites.id}">${sites.customid}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="control-label">班组:</label>
                                            <select name="classid" class="form-control" id="classid">
                                                <c:forEach items="${classinfos}" var="c" >

                                                    <option value="${c.id}">${c.customid}</option>

                                                </c:forEach>
                                            </select>
                                        </td>
                                            <td class="form-inline" colspan="2">
                                                <label class="control-label" for="pwd6">所属岗位:</label>
                                                <select name="positionid" class="form-control" id="pwd6">
                                                    <c:forEach items="${positioninfos}" var="pos" >

                                                        <option value="${pos.id}">${pos.positionname}</option>

                                                    </c:forEach>
                                                </select>
                                            </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">登录名:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="username" required>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">登录密码:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="password" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">真实姓名:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="realname" required>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">性别:</label>
                                            <input type="radio"  id="pwd3" name="sex" value="1">男
                                            <input type="radio" checked  name="sex" value="0">女
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd4">生日:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd4" name="birthdate" onClick="WdatePicker()" required>
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd7">常用终端:</label>
                                            <select name="commonterminalid" id="pwd7" class="form-control" >
                                                <c:forEach items="${terminalinfos}" var="ter">
                                                    <option value="${ter.id}">${ter.customid}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd8">账号状态:</label>
                                            <input type="radio" checked  id="pwd8" name="state" value="1">正常
                                            <input type="radio"   name="state" value="0">停用
                                        </td>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd9">当前状态:</label>
                                            <input type="radio" checked  id="pwd9" name="userstate" value="1">在线
                                            <input type="radio"  name="userstate" value="0">离线
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2"><input type="submit" class="btn btn-primary" value="保存">
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
