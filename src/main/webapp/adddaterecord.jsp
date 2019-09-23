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
                                <i class="glyphicon glyphicon-globe"></i> 可读数据项--添加

                                <script type="text/javascript">
                                    $(function () {
                                        $("#datename").blur(function(){
                                            var dateName = $("#datename").val();
                                            // var recordType = $("#record").val();
                                            // alert(dateName);
                                            $.ajax({
                                                url:"isNameExist",
                                                type:"post",
                                                data:{
                                                    dateName:dateName,
                                                    id:null

                                                },
                                                dataType:"json",
                                                success:function(data){
                                                    if(data == "1"){
                                                        $("#existuser").css("display","block");
                                                        $("input[name='addTypeName']").attr("disabled",true);
                                                    }else{
                                                        $("#existuser").css("display","none");
                                                        $("input[name='addTypeName']").attr("disabled",false);
                                                    }
                                                }
                                            })
                                        });
                                    });
                                    function changeRecordItem() {
                                        var data = $("#record").val();
                                        var label = document.getElementById("measure");
                                        if (data == 2) {
                                            label.innerHTML = '枚举项:';
                                            $("#add").show();
                                        }else{
                                            label.innerHTML = '计量单位:';
                                            $("#add").hide();
                                        }
                                    }
                                    function addStateItem() {
                                        var inputText = $("#name2").val();
                                        if(inputText==null||inputText==''||inputText==undefined){alert('动态状态项为空!');return;};
                                        $("#dynamicItem").append("<label id='sub' name='state' style='color: red;padding-left: 35px';-web-kit-appearance:none;-moz-appearance: none;>"+inputText+"</label>" +
                                            "<input  onclick='subStateItem(this)'  style='margin-left: 5px ' type='button' value=' - '/><input type='hidden' name='state' value='"+inputText+"' />");
                                    }
                                    function checkLength(){
                                        if($("#record").val() == 2 && $("label[name='state']").length <2){
                                            alert("枚举项不能低于2个哟！");
                                        }else{
                                            $.ajax({
                                                url:"addrecord",
                                                type:"post",
                                                data:$("#form").serialize(),
                                                success: function(data){
                                                    if(data>0){
                                                        alert("添加成功");
                                                        showCheckItem();
                                                    }else{
                                                        alert("添加失败");
                                                        return false;
                                                    }
                                                },
                                            });
                                        }
                                    }
                                    function subStateItem(o) {
                                        $(o).prev().remove();
                                        $(o).next().remove();
                                        $(o).remove();
                                    }
                                    function showCheckItem(){
                                        window.location="showdaterecord?page=1";
                                    }

                                </script>
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="" method="post" id="form">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <Td class="form-inline">
                                            <label class="control-label" for="datename">类型:</label>
                                            <select name="recordtype" id="record" class="form-control" onchange="changeRecordItem()">
                                                <option  value="1">记录项</option>
                                                <option  value="2">枚举项</option>
                                            </select>
                                        </Td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="datename">名称:</label>
                                           <input type="text" name="name" class="form-control" id="datename" required>
                                            <span style="display: none;color: red;" id="existuser">该类型名称已存在！！！</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline" id="dynamicItem">
                                            <label class="control-label" for="name2" id="measure">计量单位:</label>
                                            <input type="text" class="form-control" id="name2" name="unitname" required>
                                            <input style="font-size: 20px" type="button" id="add" value=" + " hidden onclick="addStateItem()"/>
                                            <%--<input type="button" id="sub" value=" - " hidden/>--%>
                                        </td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td class="form-inline">--%>
                                            <%--<label class="control-label" for="pwd">默认值:</label>--%>
                                            <%--<input type="text" class="form-control" id="pwd" name="defaultvalue" required>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <tr>
                                        <td><input type="button" class="btn btn-primary" name="addTypeName" value="保存" onclick="checkLength()">
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
