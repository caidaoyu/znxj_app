<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/4/24
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导出数据添加</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <%
        request.setAttribute("baseUrl",request.getContextPath());
    %>
</head>
<body>
<style>
    .span{
        display:inline-block;
        text-align: right;
        vertical-align:middle;
        width: 120px;height: 40px;
        margin-top: 19px;
    }
    .permission-list{ border:solid 1px #eee;}
    .permission-list1 > dt{background-color:#D3D3D3;padding:10px 10px}
    .permission-list > dl{ margin-bottom: -2px !important;}
    .permission-list > dt{ background-color:#D3D3D3;padding:5px 10px}
    .permission-list > dd{ padding:10px; padding-left:50px}
    .permission-list > dd > dl{ border-bottom:solid 1px #eee; padding:5px 0}
    .permission-list > dd > dl > dt{ display:inline-block;float:left;white-space:nowrap;width:120px}
    .permission-list > dd > dl > dd{ margin-left:120px;}
    .permission-list > dd > dl > dd > label{ padding-right:10px}
    label{
        font-weight: normal;
        font-size: 17px;
    }
</style>
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
                                <i class="glyphicon glyphicon-globe"></i> 数据导出--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form id="exportInfo" action="" method="post">
                                <input type="hidden" class="form-control" style="width: 300px;" name="createuser" required="required" value="${userInfo.id}">
                                <table cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="exportname">表头名称：</label>
                                            <input type="text" class="form-control" style="width: 450px;" id="exportName" name="exportname" required="required">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="siteid">选择厂区：</label>
                                            <select class="form-control"  id="siteid" style="width:450px;"  onchange="changeCheckItem()">
                                                <c:forEach items="${siteList}" var="sites">
                                                    <option value="${sites.id}">${sites.customid}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="type">任务类型：</label>
                                            <select class="form-control"  id="type" style="width:450px;"  name="tasktype" onchange="changeCheckItem()">
                                                <option value="0" ${tasktype == 0 ? "selected" : ""}>日常巡检任务</option>
                                                <option value="1" ${tasktype == 1 ? "selected" : ""}>计划巡检任务</option>
                                                <option value="2" ${tasktype == 2 ? "selected" : ""}>HSE隐患排查</option>
                                                <option value="3" ${tasktype == 3 ? "selected" : ""}>视频巡检任务</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="taskNumber">任务编号：</label>
                                            <select class="form-control"  id="taskNumber" style="width:450px;"  name="taskid" onchange="changeItem()">
                                                <c:forEach items="${taskPlanList}" var="tasknoList">
                                                    <%--<option value="${tasknoList.id}" name="taskPlanId">${tasknoList.id}</option>--%>
                                                    <option value="${tasknoList.id}">${tasknoList.identifyingid}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>

                                    <script type="text/javascript">
                                        function changeCheckItem(){
                                            var siteId = $("#siteid option:selected")[0].value;
                                            var type = $("#type option:selected")[0].value;

                                            $.ajax({
                                                url:"queryTaskNo",
                                                type:"post",
                                                data:{
                                                    type:type,
                                                    siteId:siteId,
                                                },
                                                dataType:"json",
                                                success:function(data){
                                                    if(data!=null){
                                                        var taskNum =  document.getElementById("taskNumber");
                                                        taskNum.innerHTML = "";
                                                        for(var i=0;i<data.length;i++){
                                                            var taskNumOp = document.createElement("option");
                                                            taskNumOp.innerHTML = data[i].identifyingid;
                                                            taskNumOp.value = data[i].id;
                                                            taskNum.appendChild(taskNumOp);
                                                        }
                                                        if(data.length == 0){
                                                            var taskNumOp1 = document.createElement("option");
                                                            taskNumOp1.innerHTML ="无";
                                                            taskNumOp1.value = "无";
                                                            taskNum.appendChild(taskNumOp1);
                                                        }
                                                        if(data.length == 0){
                                                            $("#taskContentDiv dl").remove();
                                                            $("#taskContentDiv h4").remove();
                                                            var taskContentDiv = document.getElementById("taskContentDiv");
                                                            var taskContentH4 = document.createElement("H4");
                                                            taskContentH4.innerHTML = "暂无对应的区域、设备、巡检项"
                                                            taskContentDiv.append(taskContentH4);

                                                        }
                                                        changeItem();
                                                        return true;
                                                    }else{
//                                                        alert("该厂区暂时没有班组");
                                                        return false;
                                                    }
                                                }
                                            });
                                        }
                                    </script>
                                    <tr>
                                        <td  style="display: inline-block;max-width: 100%;margin-bottom: 5px;font-weight: bold" >
                                            <%--
                                                                                        &nbsp;&nbsp;&nbsp; <label><input type="checkbox" name="computingtime">进入、退出设备时间</label>
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                                            <label><input type="checkbox"  name="consuming">计算区域耗时</label>
                                        </td>
                                    </tr>
                                </table>
                                <div style="padding-top: 10px;padding-left: 9px">
                                    <label class="control-label">选择导出内容：</label>
                                    <div style="margin-left: 45px;">
                                        <div id="taskContentDiv">
                                            <input hidden  name="taskcontent" value='${taskContents}' id="taskContentInput">
                                        </div>
                                    </div>
                                </div>
                                <input type="button" class="btn btn-inverse btn-primary " value="保存" style="margin-left: 100px;margin-top: 15px;" id="addExportInfo">
                                <input type="button" class="btn btn-inverse btn-primary" value="返回" style="margin-left: 50px;margin-top: 15px;" onclick="javascript:history.go(-1);">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    var baseData;
    var isFirst = true;
    var isHaveConsumption = false;
    $(function(){
        debugger;

        var taskContent = $("#taskContentInput").val();
        if(taskContent != null && taskContent != "" && taskContent != undefined){
            if (typeof taskContent == "string") {
                baseData = JSON.parse(taskContent);
            }
            else  {
                baseData = taskContent;
            }
            refreshUI();
        }else{
            baseData = null;
            return;
        }

    });
    function refreshUI() {
        $("#taskContentDiv dl").remove();
        $("#taskContentDiv h4").remove();
        var areaData = baseData.areas;
        if(isFirst){
            isHaveConsumption = false;
            for(var i=0;i<areaData.length;i++){
                var equipmentData = areaData[i].equipments;
                areaData[i].area["isChecked"] = false;
                for(var j=0;j<equipmentData.length;j++){
                    var checkItems = equipmentData[j].checkItems;
                    equipmentData[j].equipment["isChecked"] = false;
                    checkItems["isChecked"] = false;
                    checkItems["isAll"] = false;
                    for(var k=0;k<checkItems.length;k++){
                        checkItems[k]["isChecked"] = false;
                        if (checkItems[k].item.itemname == '输油状态') {
                            isHaveConsumption = true;
                        }
                    }
                }
            }
            isFirst = false;
        }
        if (isHaveConsumption) {
            $("input[name='consuming']").removeAttr("disabled");
        }
        else {
            $("input[name='consuming']").attr("disabled","false");
        }
        //区域
        for(var i=0;i<areaData.length;i++){
            var areaChecked = areaData[i].area["isChecked"]?"checked":"";
            var areaDL = $("<dl  class='permission-list'>" +
                "<dt class='areaDTClass' id='areaItemId"+i+"'>" +
                "<label>" +
                "<input "+areaChecked+" value='"+areaData[i].area.id+"' type='checkbox' "+" onclick='areaClick("+i+")' name='selectCheckItem'>"+areaData[i].area.customid +
                "</label>" +
                "</dt>" +
                "</dl>");
            $("#taskContentDiv").append(areaDL);
            //设备
            var equipmentData = areaData[i].equipments;
            for(var j=0;j<equipmentData.length;j++){
                var equipmentChecked = equipmentData[j].equipment["isChecked"]?"checked":"";
//                var checkItemsChecked = equipmentData[j].checkItems["isChecked"]?"checked":"";
                var checkItemsChecked = equipmentData[j].checkItems["isAll"]?"checked":"";
                var equipmentDt = $("<dt id='equipmentItemId"+i+"' style='background-color: #efefef;padding-left: 30px' class='permission-list1' >" +
                    "<label>" +
                    "<input "+equipmentChecked+" type='checkbox' onclick='equipmentClick("+i+","+j+")'  name='selectCheckItem' value='"+equipmentData[j].equipment.id+"'>"+equipmentData[j].equipment.name +
                    "</label></dt>" +
                    "<dd>" +
                    "<dl>" +//$("#id").is(":checked")
                    "<dt class='permission-list2'><label><input "+checkItemsChecked+" onclick='allItemsClick("+i+","+j+")' type='checkbox' value='' name='selectCheckItem' id='allCheckItems"+i+j+"'>所有巡检项</label></dt>" +
                    "<dd id='checkItemId"+i+j+"'></dd>"+
                    "</dl>" +
                    "</dd>");
                $("#areaItemId"+i+"").after(equipmentDt);
                //巡检项
                var checkItemsData = equipmentData[j].checkItems;
                for(var k=0;k<checkItemsData.length;k++){
                    var checkitemChecked = checkItemsData[k]["isChecked"]?"checked":"";
                    var checkDD = $("<label>" +
                        "<input  type='checkbox' "+checkitemChecked+" onclick='checkItemClick("+i+","+j+","+k+")' value='" +checkItemsData[k].item.id+ "' name='selectCheckItem'>"+checkItemsData[k].item.itemname +
                        "</label>");
                    $("#checkItemId"+i+j+"").append(checkDD);
                }
            }
        }
    }
    /**
     * 区域click
     * @param i
     */
    function areaClick(i) {
        var areaData = baseData.areas;
        var equipmentData = areaData[i].equipments;
        areaData[i].area["isChecked"] = !areaData[i].area["isChecked"];
        for(var j=0;j<equipmentData.length;j++){//如果地区选中了，说明是选中了所有的巡检项，反之则反，故所有的ischecked的值是与areaData中的一致
            var checkItems = equipmentData[j].checkItems;
            equipmentData[j].equipment["isChecked"] = areaData[i].area["isChecked"];
            checkItems["isChecked"] = areaData[i].area["isChecked"];
            checkItems["isAll"] = areaData[i].area["isChecked"];
            for(var k=0;k<checkItems.length;k++){
                checkItems[k]["isChecked"] = areaData[i].area["isChecked"];
            }
        }
        refreshUI();
    }
    /**
     * 设备click
     * @param i
     */
    function equipmentClick(i,j) {
        var areaData = baseData.areas;
        var equipmentData = areaData[i].equipments;
        equipmentData[j].equipment["isChecked"] = ! equipmentData[j].equipment["isChecked"];
        areaData[i].area["isChecked"] = equipmentData[j].equipment["isChecked"];
        var checkItems = equipmentData[j].checkItems;
        checkItems["isChecked"] = equipmentData[j].equipment["isChecked"];
        checkItems["isAll"] = equipmentData[j].equipment["isChecked"];
        for(var k=0;k<checkItems.length;k++){//如果设置选中了，说明是选中了所有的巡检项，反之则反，故所有的ischecked的值是与equipmentData中的一致
            checkItems[k]["isChecked"] = equipmentData[j].equipment["isChecked"];
        }
        refreshUI();
    }
    /**
     * 全部巡检项click
     * @param i
     */
    function allItemsClick(i,j) {
        var areaData = baseData.areas;
        var equipmentData = areaData[i].equipments;
        var checkItems = equipmentData[j].checkItems;
        var tempIsChecked = $("#allCheckItems"+i+j+"").is(":checked");//拿到所有巡检项的选中状态
        equipmentData[j].equipment["isChecked"] = tempIsChecked;
        areaData[i].area["isChecked"] = tempIsChecked;
        checkItems["isChecked"] = tempIsChecked;
        for(var k=0;k<checkItems.length;k++){
            checkItems[k]["isChecked"] = tempIsChecked;
        }
        checkItems["isAll"] = tempIsChecked;
        refreshUI();
    }
    /**
     * 设备click
     * @param i
     */
    function checkItemClick(i,j,z) {
        var areaData = baseData.areas;
        var equipmentData = areaData[i].equipments;
        var checkData = equipmentData[j].checkItems;
        //判断除了当前选中的那个设备之外，有没有选中的设备，如果有，则不改变其他的，如果没有，则说明已经没有选中的设备，那么其他的都为空
        var checkedCount = 0;//选中的数量
        for(var k=0;k<checkData.length;k++){
            var checkItem = checkData[k];
            if (k == z) {
                checkItem["isChecked"] = ! checkItem["isChecked"];
            }
            if (checkItem["isChecked"]) {
                checkedCount ++;
            }
        }
        if (checkedCount != checkData.length) {
            //如果没有一个是选中的状态
            checkData["isAll"] = false;
            if (checkedCount == 0) {
                equipmentData[j].equipment["isChecked"] = false;
                areaData[i].area["isChecked"] = false;
            }
            else  {
                equipmentData[j].equipment["isChecked"] = true;
                areaData[i].area["isChecked"] = true;
            }
        }
        else  {
            equipmentData[j].equipment["isChecked"] = true;
            areaData[i].area["isChecked"] = true;
            checkData["isAll"] = true;
        }
        refreshUI();
    }
    function changeItem(){
        var taskNo = $("#taskNumber option:selected")[0].value;
        $.ajax({
            url:"queryItemNo",
            type:"post",
            data:{
                taskNo:taskNo,
            },
            dataType:"json",
            success:function(data){
                if(data!= null){
                    baseData = JSON.parse(data.taskcontent);
                    isFirst = true;
                    refreshUI();
                }
            }
        });
    }
    /**
     *  点击了计算耗油时调用
     */
    function consumptionClick(isClickConsumption) {
        var areaData = baseData.areas;
        for (var i = 0; i < areaData.length; i ++) {
            var equipmentData = areaData[i].equipments;
            for (var j = 0;j < equipmentData.length; j ++) {
                var checkData = equipmentData[j].checkItems;
                for(var k=0;k<checkData.length;k++){
                    var checkItem = checkData[k];
                    if (checkItem.item.itemname == '输油状态') {
                        checkItem["isChecked"] = isClickConsumption;
                        equipmentData[j].equipment["isChecked"] = isClickConsumption;
                        areaData[i].area["isChecked"] = isClickConsumption;
                    }
                    else  {
                        checkItem["isChecked"] = false;
                    }
                }
                checkData["isAll"] = false;
            }
        }
        refreshUI();
    }

    $(function () {
        //添加
        $("#addExportInfo").click(function () {
            var areaData = baseData.areas;
            var tempAreaData = new Array();
            for(var i=0;i<areaData.length;i++) {
                var areaCheck = areaData[i].area["isChecked"];//区域选中的状态
                //判断是否是选中状态
                if (areaCheck) {//选中的话，判断设备是否选中
                    var equipmentData = areaData[i].equipments;
                    tempAreaData.push(areaData[i]);
                    var tempEquipment = new Array();
                    for (var j = 0; j < equipmentData.length; j++) {
                        var equipmentCheck = equipmentData[j].equipment["isChecked"];
                        if (equipmentCheck) {//选中的话，判断设备是否选中
                            var checkData = equipmentData[j].checkItems;
                            var tempCheckData = new Array();
                            for (var k = 0; k < checkData.length; k++) {
                                var checkItemCheck = checkData[k]["isChecked"];
                                if (checkItemCheck) {//已经选中
                                    tempCheckData.push(checkData[k]);
                                }
                                else {
//                                    checkData.splice(k, 1);
//                                    delete checkData[k];//如果没有选中的巡检项，就删除这个巡检项
                                }
                            }
                            if (tempCheckData.length) {//说明有选中的巡检项
                                tempEquipment.push({equipment:equipmentData[j].equipment, checkItems:tempCheckData});
                            }
                        }
                        else {
//                            equipmentData.splice(j, 1);
//                            delete equipmentData[j];//如果设备没有选中。就删除这个设备
                        }
                    }
                    if (tempEquipment.length) {
                        areaData[i].equipments = tempEquipment;
                    }
                }
            }
            if (tempAreaData.length == 0) {
                //什么都没有选择
                alert("请选择区域，设备，巡检项");
                return;
            }
            var exportname=$("#exportName").val();//表头名称
            if(exportname==""){
                alert("亲，表头名称必须要填写的哟");
                return false;
            }
            /*var computingTime = $("input[name='computingtime']"); //进入退出设备时间0
            if(computingTime.is(":checked")){
                computingTime.attr("value","1");
            }else{
                computingTime.attr("value","0");
            }
*/
            var consuming = $("input[name='consuming']");
            if(consuming.is(":checked")){
                consuming.attr("value","1");
            }else {
                consuming.attr("value","0");
            }

            var task=JSON.stringify({areas:tempAreaData});
            $("#taskContentInput").val(task);
            $.ajax({
                url:"addExportInfo",
                type:"post",
                data:$('#exportInfo').serialize(),
                async: false,
                success: function(data) {
//                    if(data.success){
                    if(data >0){
                        alert("添加成功");
                        getPageExportInfo();
                    }else{
                        alert("添加失败");
                        return false;
                    }
                }
            })
        });

        //判断是否选中计算耗时
        $("input[name='consuming']").click(function () {
            var consuming = $("input[name='consuming']");
            consumptionClick(consuming.is(":checked"));
            if(consuming.is(":checked")){
                $("input[type='checkbox']:gt(0)").attr("disabled","false");
            }else {
                $("input[type='checkbox']:gt(0)").removeAttr("disabled");
            }
        });
    });

    function  getPageExportInfo() {

//        window.history.back();
        window.location = "${baseUrl}/getPageExportInfo?page=1";
    }
</script>
</body>
</html>
