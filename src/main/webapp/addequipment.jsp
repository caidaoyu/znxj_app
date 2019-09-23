<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function model(){
            $("#myModal").modal("show");
        }
        $(function(){
            $("#btn").click(function(){
                $.ajax({
                    url:"addnfcajax",
                    type:"post",
                    data:$('#form1').serialize(),
                    dataType:"json",
                    success:function(data){
                        if(data>0){
                            $('#myModal').modal('hide');
                            shownfc();
                        }else{
                            alert("添加失败");
                        }
                    }
                })
            })


            $("input[type='submit']").click(function () {
                var siteName = $("#siteid").val();
                if(siteName == null || siteName == undefined || siteName == ''){
                    alert("您还没有选择厂区哟！！！");
                    $("input[type='submit']").attr("disabled",true);
                    $("#area1").empty();
                }else{
                    $("input[type='submit']").attr("disabled",false);
                }
            });


        })
        function shownfc(){
            $.ajax({
                url:"shownfcjax",
                type:"post",
                dataType:"json",
                success:function(data){
                    $("#nfc2").empty();
                    $("#nfc2").append("<option selected='selected' value=''></option>");
                    for(var i=0;i<data.length;i++){
                        $("#nfc2").append("<option value='"+data[i].id+"'>"+data[i].unitcode+"</option>");
                    }
                }
            })
        }
        function sitechange(){
            var siteid = $("#siteid option:selected")[0].value;
            var siteName = $("#siteid").val();
            if(siteName == null || siteName == undefined || siteName == ''){
                alert("您还没有选择厂区哟！！！");
                $("input[type='submit']").attr("disabled",true);
                $("#area1").empty();
            }else{
                $("input[type='submit']").attr("disabled",false);
                $.ajax({
                    url:"queryareabysiteid",
                    type:"post",
                    data:{
                        siteid:siteid,
                    },
                    dataType:"json",
                    success:function(data){
                        if(data!=null){
                            var areaselect =  document.getElementById("area1");
                            areaselect.innerHTML = "";
                            for(var i=0;i<data.length;i++){
                                var areaop = document.createElement("option");
                                areaop.innerHTML = data[i].customid;
                                areaop.value = data[i].id;
                                areaselect.appendChild(areaop);
                            }
                            return true;
                        }else{
                            alert("该厂区暂时没有区域");
                            return false;
                        }
                    }
                });
            }


        }
    </script>
</head>
<body>
<%--
<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
--%>
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
                                <i class="glyphicon glyphicon-globe"></i> 设备--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addequipment" method="post">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">编号ID:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="name"required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd1">类型:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd1" name="type"required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">GPS精度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="longitude" required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">GPS纬度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd3" name="latitude" required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="desccontent">设备描述:</label>
                                            <textarea rows="3" cols="80" name="desccontent"id="desccontent" class="form-control"required></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc2">NFC标签:</label>
                                            <select  id="nfc2" name="nfcid" class="form-control" >
                                                <option selected="selected" value=""></option>
                                                <c:forEach items="${nfcinfos}" var="nfc">
                                                    <option value="${nfc.id}">${nfc.customid} ${nfc.desccontent}</option>
                                                </c:forEach>
                                            </select><a href="javascript:void(0)" onclick="model()">创建NFC标签</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="siteid">厂区:</label>
                                            <select class="form-control" id="siteid" name="siteid" onchange="sitechange()">
                                                <option selected="selected" value="" >--请选择厂区--</option>
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select> &nbsp;&nbsp;&nbsp;
                                            <label class="control-label" for="area1">选择区域:</label>
                                            <select  id="area1" name="areaid" required class="form-control" >
                                                <option selected="selected" value="" >--请选择区域--</option>
                                                <c:forEach items="${areainfos}" var="area">
                                                    <option value="${area.id}">${area.customid}</option>
                                                </c:forEach>
                                            </select>
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

</div>
<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="z-index: 0">
    <div class="modal-dialog" style="z-index: 9999">
        <div class="modal-content">
            <form action="addnfcajax" method="post" id="form1">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>添加NFC标签</h3>
                </div>

                <div class="modal-body">
                    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable">
                        <tr>
                            <td class="form-inline">
                                <label class="control-label" for="bh">编号ID:</label>
                                <input type="text" class="form-control" style="width: 200px;" id="bh" name="customid">
                            </td>
                            <td class="form-inline">
                                <label class="control-label" for="nfc">NFC串码:</label>
                                <input type="text" class="form-control" style="width: 200px;" id="nfc" name="unitcode">
                            </td>
                        </tr>

                        <tr>
                            <td class="form-inline">
                                <label class="control-label" for="nfc1">GPS经度:</label>
                                <input type="text" class="form-control" style="width: 200px;" id="nfc1" name="longitude" placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                            </td>
                            <td class="form-inline">
                                <label class="control-label" for="wd">GPS纬度:</label>
                                <input type="text" class="form-control" style="width: 200px;" id="wd" name="latitude" placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                            </td>
                        </tr>

                        <tr>
                            <td class="form-inline" colspan="2">
                                <label class="control-label" for="desc">标签描述:</label>
                                <textarea class="form-control" id="desc" name="desccontent" rows="5" cols="70"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td class="form-inline">
                                <label class="control-label" for="date">启用时间:</label>
                                <input type="text" class="form-control" style="width: 200px;" id="date" name="enabletime" onClick="WdatePicker()" readonly="readonly">
                            </td>
                            <td class="form-inline">
                                <label class="control-label" for="status">状态:</label>
                                <select id="status" name="state">
                                    <option value="0">失效</option>
                                    <option value="1">正常</option>
                                    <option value="2">遗失</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td class="form-inline"colspan="2">
                                <label class="control-label" for="remark">备注:</label>
                                <textarea class="form-control" id="remark" name="remark" rows="5" cols="70"></textarea>
                            </td>
                        </tr>
                    </table>

                </div>
                <div class="modal-footer">
                    <input type="button" id="btn" value="添加" class="btn btn-primary">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
