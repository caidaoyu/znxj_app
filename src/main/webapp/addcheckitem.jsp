<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
<script type="text/javascript">
    $(function(){
        var op = $("#pwd2 option:selected");
        var state=$("#pwd2").val();
        if(op.val()==1){
            $("#type")[0].style.display="none";
            $("#type2")[0].style.display="none";
        }else if(op.val()==2){
            $("#type")[0].style.display="none";
            $("#type2")[0].style.display="block";
        }else if(op.val()==3){
            $("#type")[0].style.display="none";
            $("#type2")[0].style.display="block";
        }
    })
    function change(){
        var op = $("#pwd2 option:selected");
        var state=$("#pwd2").val();
        if(op.val()==1){
            $("#type")[0].style.display="none";
            $("#type2")[0].style.display="none";

        }else if(op.val()==2){
            $("#type")[0].style.display="none";
            $("#type2")[0].style.display="block";
            $("#type2").find('label').text('记录类型：');
            $.ajax({
                url:"queryRecordByType",
                type:"post",
                data:{
                    recordType:1
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var dynamicStateItem =  document.getElementById("jilutype");
                        dynamicStateItem.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var item = document.createElement("option");
                            item.innerHTML = data[i].name+"&nbsp;&nbsp;("+data[i].unitname+")";
                            item.value = data[i].id;
                            dynamicStateItem.appendChild(item);
                        }
                        return true;
                    }else{
                        alert("没有数据。");
                        return false;
                    }
                }
            });

        }
        else if(op.val()!=1){
            $("#type")[0].style.display="none";
            $("#type2")[0].style.display="block";
            $("#type2").find('label').text('枚举类型：');
            $.ajax({
                url:"queryRecordByType",
                type:"post",
                data:{
                    recordType:2
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var dynamicStateItem =  document.getElementById("jilutype");
                        dynamicStateItem.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var item = document.createElement("option");
                            item.innerHTML = data[i].name+"&nbsp;&nbsp;("+data[i].state+")";
                            item.value = data[i].id;
                            dynamicStateItem.appendChild(item);
                        }
                        return true;
                    }else{
                        alert("没有数据。");
                        return false;
                    }
                }
            });
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
                                <i class="glyphicon glyphicon-globe"></i> 巡检项--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addcheck" method="post">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="idtype">编号ID:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="idtype" name="customid" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="itemname" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">关键点描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="keyword" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">类型:</label>
                                           <select id="pwd2" name="type" onchange="change()">
                                               <option value="1">状态项</option>
                                               <option value="2">记录项</option>
                                               <option value="3">枚举项</option>
                                           </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline" id="type2">
                                            <label class="control-label" for="jilutype">记录类型:</label>
                                            <select id="jilutype" name="recordid">
                                                <option value="" selected>请选择</option>
                                                <c:forEach items="${daterecordinfos}" var="daterecord">
                                                    <%--<c:if test="${daterecord.unitname != null}">--%>
                                                        <%--<option value="${daterecord.id}">${daterecord.name}(${daterecord.unitname})</option>--%>
                                                    <%--</c:if>--%>
                                                    <%--<c:if test="${daterecord.state != null}">--%>
                                                        <%--<option value="${daterecord.id}">${daterecord.name}(${daterecord.state})</option>--%>
                                                    <%--</c:if>--%>
                                                    <option value="${daterecord.id}">${daterecord.name}(${daterecord.unitname})</option>
                                                </c:forEach>
                                            </select>
                                        </td>

                                    </tr>
                                    <%--<tr>--%>
                                    <%--<td class="form-inline" id="type3">--%>
                                        <%--<label class="control-label" for="dynamicStateType">枚举类型:</label>--%>
                                        <%--<select id="dynamicStateType" name="recordid">--%>
                                            <%--<option value="" selected>请选择</option>--%>
                                            <%--&lt;%&ndash;<c:forEach items="${daterecordinfos}" var="daterecord">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<option value="${daterecord.id}">${daterecord.name}&nbsp;${daterecord.unitname}</option>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                                        <%--</select>--%>
                                    <%--</td>--%>
                                    <%--</tr>--%>
                                    <tr id="type">
                                        <td>
                                            <span>范围:</span>
                                            <input type="text" name="normalmin">- <input type="text" name="normalmax"><br/>

                                            <span style="margin-top: 25px;">上限警告:</span><input type="text" name="upperwarning"style="margin-top: 25px;">
                                            <span style="margin-top: 25px;">下限警告:</span><input type="text" name="lowerwarning" style="margin-top: 25px;"><br/>
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
