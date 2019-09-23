<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function sitechange(){
            var siteid = $("#siteid option:selected")[0].value;
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
                                <i class="glyphicon glyphicon-globe"></i> 设备--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updequipment" method="post">
                                <input type="hidden" name="id" value="${equipmentinfo.id}">
                                <input type="hidden" name="page" value="${page}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">编号ID:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="customid" value="${equipmentinfo.customid}" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="name" value="${equipmentinfo.name}"required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd1">类型:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd1" name="type" value="${equipmentinfo.type}"required >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">GPS精度:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="longitude" value="${equipmentinfo.longitude}"required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd3">GPS纬度:</label>
                                            <input type="text" class="form-control" style="width: 300px;"  id="pwd3" name="latitude" value="${equipmentinfo.latitude}"required placeholder="请输入整数或小数" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="desccontent">设备描述:</label>
                                            <textarea rows="3" cols="80" name="desccontent"id="desccontent" class="form-control" required="required">${equipmentinfo.desccontent}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="nfc2">NFC标签:</label>
                                            <select  id="nfc2" name="nfcid">
                                               <option selected="selected" value="${equipmentinfo.nfcid}">${equipmentinfo.nfc.customid} ${equipmentinfo.nfc.desccontent}</option>
                                                <c:forEach items="${nfcinfos}" var="nfc">
                                                    <option value="${nfc.id}">${nfc.customid} ${nfc.desccontent}</option>
                                                </c:forEach>
                                                <option ${equipmentinfo.nfcid eq ''?'selected':''} value=""></option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="siteid">厂区:</label>
                                            <select class="form-control" id="siteid" name="siteid" onchange="sitechange()">
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option ${equipmentinfo.areainfo.plant eq site.id ?"selected":""} value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="control-label" for="area1">选择区域:</label>
                                            <select  id="area1" name="areaid">
                                                <c:forEach items="${areainfos}" var="area">
                                                    <option ${equipmentinfo.areaid eq area.id?'selected':''} value="${area.id}">${area.customid}</option><%--
                                                    <option ${equipmentinfo.areaid eq area.id ? 'selected' : ''} value="${area.id}">${area.customid}</option>--%>
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
</div>
</body>
</html>
