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
                /* $("#type")[0].style.visibility="hidden";*/
                 $("#type2")[0].style.visibility="hidden";
             }else if(op.val()==2){
                /* $("#type")[0].style.visibility="hidden";*/
                 $("#type2")[0].style.visibility="visible";

                 $("#type2").find('label').text('记录类型：');

             }else if (op.val() == 3){
                 $("#type2")[0].style.visibility="visible";

                 $("#type2").find('label').text('枚举类型：');
             }

         })

        function change(){
            var op = $("#pwd2 option:selected");
            var state=$("#pwd2").val();
            if(op.val()==1){
                /*$("#type")[0].style.visibility="hidden";*/
                $("#type2")[0].style.visibility="hidden";
            }else if(op.val()==2){
               /* $("#type")[0].style.visibility="hidden";*/
                $("#type2")[0].style.visibility="visible";
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
            }else if(op.val()==3){
                /* $("#type")[0].style.visibility="hidden";*/
                $("#type2")[0].style.visibility="visible";
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
                                <i class="glyphicon glyphicon-globe"></i> 巡检项--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updcheck" method="post">
                                <input type="hidden" name="id" value="${checkiteminfo.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="idtype">编号ID:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="idtype" name="customid" value="${checkiteminfo.customid}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="itemname"value="${checkiteminfo.itemname}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">关键点描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="keyword" value="${checkiteminfo.keyword}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">类型:</label>
                                            <select name="type" id="pwd2" onchange="change()">
                                                <option ${checkiteminfo.type eq '1'?'selected':''} value="1">状态项</option>
                                                <option ${checkiteminfo.type eq '2'?'selected':''} value="2">记录项</option>
                                                <option ${checkiteminfo.type eq '3'?'selected':''} value="3">枚举项</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <%--<c:if test="${checkiteminfo.type != 1}">--%>
                                        <td class="form-inline" id="type2">
                                            <label class="control-label" for="jilutype">记录类型:</label>
                                                <%--<input type="text" value="${checkiteminfo.daterecord.name}&nbsp;${checkiteminfo.daterecord.unitname}" readonly class="form-control">--%>
                                            <select id="jilutype" name="recordid" readonly="readonly">
                                                <c:forEach var="daterecord" items="${daterecordinfos}">
                                                    <c:if test="${checkiteminfo.type ==2}">
                                                        <option ${checkiteminfo.recordid eq daterecord.id ? 'selected' : ''} value="${daterecord.id}">${daterecord.name} &nbsp;&nbsp;&nbsp;${daterecord.unitname}</option>
                                                    </c:if>
                                                    <c:if test="${checkiteminfo.type ==3}">
                                                        <option ${checkiteminfo.recordid eq daterecord.id ? 'selected' : ''} value="${daterecord.id}">${daterecord.name} &nbsp;&nbsp;&nbsp;${daterecord.state}</option>
                                                    </c:if>
                                                </c:forEach>

                                            </select>
                                        </td>
                                        <%--</c:if>--%>
                                    </tr>


                                    <%--<tr id="type">
                                        <td>
                                            <span>范围:</span>
                                            <input type="text" name="normalmin" value="${checkiteminfo.normalmin}">- <input type="text" name="normalmax" value="${checkiteminfo.normalmax}">
                                            <br/>

                                            <span style="margin-top: 25px;">上限警告:</span><input type="text" name="upperwarning"value="${checkiteminfo.upperwarning}" style="margin-top: 25px;">

                                            <span style="margin-top: 25px;">下限警告:</span><input type="text" name="lowerwarning"value="${checkiteminfo.lowerwarning}" style="margin-top: 25px;">
                                        </td>
                                    </tr>--%>


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
