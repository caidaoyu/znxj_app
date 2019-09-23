<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <%--<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>--%>
    <link type="text/css" rel='stylesheet' href="charisma/css/bootstrap-datetimepicker.min.css">
    <script src="js/bootstrap-datetimepicker.js" type="text/javascript"></script>
    <script src="js/bootstrap-datetimepicker.fr.js" type="text/javascript" charset="UTF-8"></script>
    <script type="text/javascript" src="charisma/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $(".form_datetime").datetimepicker({
                language:'zh-CN',
                format: "yyyy-mm-dd hh:ii",
                autoclose: true,
                todayBtn: true,
                pickerPosition:"bottom-left",
                minuteStep: 10
            });

            //判断是否存在同名班组
            $("#pwd4").change(function () {
                var customid = $("#idc").val();
                var siteareaid = $("#pwd4").val();
                var id = $("#id").val();
                $.ajax({
                    url:"isClassIdExist",
                    type:"post",
                    data:{
                        customName:customid,
                        siteareaid:siteareaid,
                        id:id
                    },
                    dataType:"json",
                    success:function(data){
                        if(data >0){
                            alert("该厂区下已存在班组名为"+customid+"的班组");
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
                                <i class="glyphicon glyphicon-globe"></i> 班组--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updclass" method="post">
                                <input type="hidden" name="id" id="id" value="${classinfo.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="idc">编组编号:</label>
                                            <input type="text" class="form-control" required style="width: 300px;" id="idc" name="customid" value="${classinfo.customid}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">班组描述:</label>
                                            <textarea type="text" class="form-control" rows="3" cols="35" id="username" name="classdesc" required>${classinfo.classdesc}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">上岗时间:</label>
                                            <input type="text" class="form_datetime form-control" style="width: 300px;" id="pwd" name="workstarttime" required value="<sdf:formatDate value='${classinfo.workstarttime}' pattern='yyyy-MM-dd HH:mm:ss'></sdf:formatDate>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">下岗时间:</label>
                                            <input type="text" class="form_datetime form-control" style="width: 300px;" id="pwd2" name="workendtime" required value="<sdf:formatDate value='${classinfo.workendtime}' pattern='yyyy-MM-dd  HH:mm:ss'></sdf:formatDate>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">班组负责人:</label>
                                           <%-- <input type="text" class="form-control" style="width: 300px;" name="directorid" value="${classinfo.userinfo.realname}" id="pwd3">--%>
                                            <select class="form-control" name="directorid" id="pwd3">
                                                <c:forEach items="${userinfos}" var="users">
                                                    <option ${classinfo.userinfo.realname eq users.realname ? 'selected' : ''} value="${users.id}">${users.realname}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td class="form-inline">--%>
                                            <%--<label class="control-label" for="pwd3">复核责任人:</label><br>--%>
                                            <%--<c:forEach items="${checkUserList}" var="user" varStatus="status">--%>
                                                <%--&lt;%&ndash;<input value="${user.check}">&ndash;%&gt;--%>
                                                <%--<span style="display:inline-block;width: 155px;margin-left: 0px;margin-top: 20px;">--%>
                                                    <%--<input type="checkbox" ${user.check eq true ? 'checked' : ''} name="checkuserid" value="${user.userinfo.id}" style="height: 15px">${user.userinfo.realname}--%>
                                                <%--</span>--%>
                                                <%--<c:if test="${status.count%8==0}">--%>
                                                    <%--<br>--%>
                                                <%--</c:if>--%>
                                            <%--</c:forEach>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd4">所属厂区:</label>
                                            <%--<input type="text" class="form-control" style="width: 300px;" name="siteareaid" value="${classinfo.site.customid}" id="pwd4">--%>
                                            <select class="form-control" name="siteareaid" id="pwd4">
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option ${classinfo.site.customid eq site.customid ? 'selected' : ''} value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" class="btn btn-primary" value="修改">
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
