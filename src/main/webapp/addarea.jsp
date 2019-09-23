<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>


    <script type="text/javascript">

        $(function () {
            $("#pwd").change(function () {
                var customid = $("#pwd").val();
                if(customid == null || customid == undefined || customid == ''){
                    alert("您还没有选择厂区哟！！！");
                    $("input[type='submit']").attr("disabled",true);
                }else{
                    $("input[type='submit']").attr("disabled",false);
                }
                // alert(customid);
            });
        });
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
                                <i class="glyphicon glyphicon-globe"></i> 区域--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addarea" method="post" id="form2">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">厂区:</label>
                                           <select name="plant" id="pwd" required="required">
                                               <option value="">--请选择--</option>
                                               <c:forEach items="${siteareainfos}" var="site">

                                                   <option value="${site.id}">${site.customid}</option>
                                               </c:forEach>
                                           </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">GPS精度:</label>
                                            <input type="text" class="form-control" placeholder="只能输入整数或小数" style="width: 300px;" id="pwd2" name="longitude" required="required" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">GPS纬度:</label>
                                            <input type="text" class="form-control" placeholder="只能输入整数或小数"  id="pwd3" name="latitude" value="" required="required" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd4">GPS覆盖半径:</label>
                                            <input type="text" class="form-control" placeholder="只能输入整数或小数" style="width: 300px;" id="pwd4" name="radiusnumber" required="required" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="area">区域描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="area" name="desccontent">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc2">NFC标签:</label>
                                            <select  id="nfc2" name="nfctag">
                                                <option selected="selected" value=""></option>
                                                <c:forEach items="${nfcinfos}" var="nfc">
                                                    <option value="${nfc.id}">${nfc.customid} ${nfc.desccontent}</option>
                                                </c:forEach>
                                            </select><a href="javascript:void(0)" onclick="model()">创建NFC标签</a>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td><input type="submit" class="btn btn-primary" value="保存">
                                            <input type="button" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
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
    <div class="modal-dialog" style="z-index: 9999;width:800px">
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
                                  <label class="control-label" for="nfc">串码:</label>
                                  <input type="text" class="form-control" style="width: 200px;" id="nfc" name="unitcode">
                              </td>
                          </tr>

                          <tr>
                              <td class="form-inline">
                                  <label class="control-label" for="nfc1">GPS经度:</label>
                                  <input type="text" class="form-control" style="width: 200px;" id="nfc1" name="longitude" placeholder="只能输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                              </td>
                              <td class="form-inline">
                                  <label class="control-label" for="wd">GPS纬度:</label>
                                  <input type="text" class="form-control" style="width: 200px;" id="wd" name="latitude" placeholder="只能输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
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
                                      <option value="1" selected>正常</option>
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
