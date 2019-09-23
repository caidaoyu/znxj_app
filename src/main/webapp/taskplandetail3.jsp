<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>

</head>
<body>
<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>
<style>
    .selected{background: #35A7E7;
        color: white;}
</style>
<%--<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>--%>
<script type="text/javascript"->
    function change(){
        var classid = $("#classid option:selected")[0].value;
        $.ajax({
            url:"querydirectorid",
            type:"post",
            data:{
                classid:classid,
            },
            dataType:"json",
            success:function(data){
                if(data>0){
                    directorname(data);
                    return true;
                }else{
                    alert("该区域暂时没有负责人");
                    return false;
                }
            }
        });
    }
    function directorname(id){
        $.ajax({
            url:"querydirectorname",
            type:"post",
            data:{
                uid:id,
            },
            dataType:"json",
            success:function(data){
                if(data!=null){
                    var parent =  document.getElementById("directorid");
                    parent.innerHTML = "";
                    var op = document.createElement("option");
                    op.innerHTML = data.realname;
                    op.value = data.id;
                    parent.appendChild(op);
                    return true;
                }else{
                    alert("出错了");
                    return false;
                }
            }
        });
    }
    window.onload=change;

    function first(){
        $("#first").css('display','block');
        $("#second").css('display','none');
        $("#third").css('display','none');
    }
    function third(){
        changeradio();
        $("#first").css('display','none');
        $("#second").css('display','none');
        $("#third").css('display','block');


    }
    function changeradio(){
        var listtime=new Array();
        listtime= JSON.parse($("#listtime").val());
        if(document.getElementById("radion1").checked){
            $("#name1").attr("disabled",true);
            $("#name3").attr("disabled",true);
            $("#name4").attr("disabled",true);

            $("#addtimee").attr("disabled",true);
            $("#deltime").attr("disabled",true);

            $("#name2").attr("disabled",false);
            $("#name2").val(listtime);
        }else if(document.getElementById("radion2").checked){
            $("#name2").attr("disabled",true);
            $("#name1").attr("disabled",false);
            $("#name3").attr("disabled",false);
            $("#name4").attr("disabled",false);
            $("#addtimee").attr("disabled",false);
            $("#deltime").attr("disabled",false);
            for(var i=0;i<listtime.length;i++){
                $("#name4").append("<p onclick='selectp(this)'>"+listtime[i]+"</p>");
            }
        }
    }

    var areasdata; //区域数据源
    var equipmentdata; //设备数据源
    var checkitemdata;

    var res ;

    var selectedareaindex;
    var selectedequipmentindex;
    var selecteditemindex;

    function second(){
        res = JSON.parse($("#alldata").val());
        refresharea();
        $("#first").css('display','none');
        $("#second").css('display','block');
        $("#third").css('display','none');
    }
    /*显示所有的区域*/
    function model(){
        $("#myModal").modal("show");
        $("#equipmentlist")[0].style.display="none";
        $("#checklist")[0].style.display="none";
        $("#dotime")[0].style.display="none";
        $("#arealist")[0].style.display="block";

        $.ajax({
            url:"showarea",
            type:"post",
            dataType:"json",
            success:function(data) {
                areasdata = data;
                var first = $("#area tr:first");
                $("#area").empty();
                $("#area").append(first);
                for (var i = 0; i < data.length; i++) {
                    $("#area").append("<tr><td><input type='checkbox' name='data["+i+"].id'></td><td>" + data[i].id + "</td><td>" + data[i].customid + "</td><td>"+data[i].desccontent+"</td></tr>");
                }
            }
        });
    }
    /*根据区域编号显示所有的设备*/
    function showequip(){
        var $li = $("#areaul .selected");
        var areaid = $li.attr("areaid");
        if (areaid==undefined) {
            alert("请选择设备所属区域");
            return false;
        }else{
            $.ajax({
                url:"showequipment?areaid="+areaid,
                type:"post",
                dataType:"json",
                success:function(data) {
                    if(data==""){
                        alert("该区域暂时没有设备");
                        return false;
                    }else{
                        equipmentdata = data ;
                        var first = $("#equipment tr:first");
                        $("#equipment").empty();
                        $("#equipment").append(first);

                        $("#myModal").modal("show");
                        $("#equipmentlist")[0].style.display="block";
                        $("#arealist")[0].style.display="none";
                        $("#checklist")[0].style.display="none";
                        $("#dotime")[0].style.display="none";

                        for (var i = 0; i < data.length; i++) {
                            $("#equipment").append("<tr><td><input type='checkbox' name='data["+i+"].id'></td><td>" + data[i].customid + "</td><td>" + data[i].name + "</td><td>"+data[i].desccontent+"</td></tr>");
                        }
                    }
                }
            });
        }
    }
    /*根据设备编号显示所有的巡检项*/
    function showcheck(){
        var $li = $("#equipul .selected");
        var equipid = $li.attr("equipid");
        if (equipid==undefined) {
            alert("请选择巡检项所属设备");
            return false;
        }else{
            $("#myModal").modal("show");
            $("#equipmentlist")[0].style.display = "none";
            $("#arealist")[0].style.display = "none";
            $("#checklist")[0].style.display = "block";
            $("#dotime")[0].style.display="none";
            $.ajax({
                url: "showcheck",
                type: "post",
                dataType: "json",
                success: function (data) {
                    checkitemdata = data;
                    var first = $("#check tr:first");
                    $("#check").empty();
                    $("#check").append(first);
                    for (var i = 0; i < data.length; i++) {
                        $("#check").append("<tr><td><input type='checkbox' name='data[" + i + "].id'></td>" +
                                "<td>" + data[i].customid + "</td><td>" + data[i].itemname + "</td><td>" + data[i].keyword + "</td><td style=''>" + data[i].recordid + "</td></tr>");
                    }
                }
            });
        }
    }
    /*将选择好的区域追加到指定位置*/
    function addarea(){
        var area= $("#area").find("input[type='checkbox']:checked").parent().parent();
        for(var i=0;i<area.length;i++){
            res.areas.push({area:areasdata[area[i].rowIndex-1], equipments:new Array()});
            $('#myModal').modal('hide');
        }
        refresharea();
    }
    /*填充区域*/
    function refresharea(){
        var data = res.areas;
        var changearea=$("#areaul");
        changearea.empty();
        for(var i=0;i<data.length;i++){
            changearea.append("<li class='list-group-item' " + "areaindex="+i+ " areaid="+data[i].area.id+" onclick='selectarea(this)'>"+data[i].area.customid+"</li>");
            $('#myModal').modal('hide');
        }
    }

    /*将选择好的设备追加到指定位置*/
    function addequip(){
        var equipment= $("#equipment").find("input[type='checkbox']:checked").parent().parent();
        /* equipul.empty();*/
        for(var i=0;i<equipment.length;i++){
            //添加设备
            res.areas[selectedareaindex].equipments.push({equipment:equipmentdata[equipment[i].rowIndex-1],checkItems:new Array()});
            $('#myModal').modal('hide');
        }
        refreshEq();
    }

    /*填充设备*/
    function refreshEq(){
        var data = res.areas[selectedareaindex].equipments;
        var equipul=$("#equipul");
        equipul.empty();
        for(var i=0;i<data.length;i++){
            equipul.append("<li class='list-group-item' " + "equipindex="+i+" equipid="+data[i].equipment.id+" onclick='selectequipment(this)'>"+data[i].equipment.name+"</li>");
            $('#myModal').modal('hide');
        }
    }

    /*将选择好的巡检项追加到指定位置*/
    function addcheck(){
        var check= $("#check").find("input[type='checkbox']:checked").parent().parent();
        for(var i=0;i<check.length;i++){
            //添加巡检项
            res.areas[selectedareaindex].equipments[selectedequipmentindex].checkItems.push({item:checkitemdata[check[i].rowIndex-1]});
            $('#myModal').modal('hide');
        }
        console.log(JSON.stringify(res));
        refreshCheck();
    }

    /*将选择好的巡检项填充*/
    function refreshCheck(){
        data = res.areas[selectedareaindex].equipments[selectedequipmentindex].checkItems;
        var checkul=$("#checkul");
        checkul.empty();
        for(var i=0;i<data.length;i++){
            checkul.append("<li class='list-group-item' "+"checkitemindex ="+ i +" recordid= "+data[i].item.recordid+" onclick='showdata(this)'>"+data[i].item.itemname+"</li>");
            $('#myModal').modal('hide');
        }
    }
    /*控制选定area的li的样式*/
    function selectarea(obj){
        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('li').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);

        var $li = $("#areaul .selected");
        selectedareaindex =$li.attr("areaindex");

        //关联设备列表切换
        refreshEq();

        $("#checkul").empty();
        $("#datarecord").empty();
    }

    /*控制选定equipment的li的样式*/
    function selectequipment(obj){
        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('li').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);
        var $li = $("#equipul .selected");
        selectedequipmentindex =$li.attr("equipindex");
        //关联巡检项列表切换
        refreshCheck();
    }

    /*对选中区域项进行删除*/
    function delarea(id){
        var $li = $(id).find("li:selected");
        if ($li.length <= 0) {
            alert("请选择要删除的选项");
            return false;
        }
        res.areas.splice(selectedareaindex,1);
        refresharea();
        var equipul=$("#equipul");
        equipul.empty();
        var checkul=$("#checkul");
        checkul.empty();
    }

    /*对选中设备项进行删除*/
    function delequip(id){
        var $li = $(id).find("li:selected");
        if ($li.length <= 0) {
            alert("请选择要删除的选项");
            return false;
        }
        res.areas[selectedareaindex].equipments.splice(selectedequipmentindex,1);
        refreshEq();
        var checkul=$("#checkul");
        checkul.empty();

    }

    /*对选中巡检想项进行删除*/
    function delitem(id){
        var $li = $(id).find("li:selected");
        if ($li.length <= 0) {
            alert("请选择要删除的选项");
            return false;
        }
        res.areas[selectedareaindex].equipments[selectedequipmentindex].checkItems.splice(selecteditemindex,1);
        refreshCheck();
    }

    function  showdata(obj) {
        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('li').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);

        var $li = $("#checkul .selected");
        var recordid = $li.attr("recordid");

        selecteditemindex = $li.attr("checkitemindex");
        if (recordid==undefined) {
            alert("请选择巡检项");
            return false;
        }else{
            $.ajax({
                url:"showdatarecord?recordid="+recordid,
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data==""){
                        alert("该巡检项没有可读数据");
                        return false;
                    }else{
                        var datarecord=$("#datarecord");
                        datarecord.empty();
                        datarecord.append("<p>类型："+data.type+"</p>" +
                                "<p>"+data.daterecord.name+"："+data.normalmin+"-"+data.normalmax+"</p>" +
                                "<p>单位："+data.daterecord.unitname+"</p>");
                    }
                }
            });
        }
    }
</script>
<script type="text/javascript">
    $(function(){
        var hour=$("#hour");
        var minute=$("#minute");
        for(var i=0;i<24;i++){
            if(i<=9){
                hour.append("<option>0"+i+"</option>");
            }else{
                hour.append("<option>"+i+"</option>");
            }
        }
        for(var j=0;j<60;j++){
            if(j<=9){
                minute.append("<option>0"+j+"</option>");
            }else{
                minute.append("<option>"+j+"</option>");
            }
        }

        var updweek=new Array();
        updweek=JSON.parse($("#weeklytime").val());
        for(var i=0;i<updweek.length;i++){
            switch (updweek[i]){
                case "周一" :
                    $("#c1")[0].checked = true;
                    break;
                case "周二" :
                    $("#c2")[0].checked = true;
                    break;
                case "周三" :
                    $("#c3")[0].checked = true;
                    break;
                case "周四" :
                    $("#c4")[0].checked = true;
                    break;
                case "周五" :
                    $("#c5")[0].checked = true;
                    break;
                case "周六" :
                    $("#c6")[0].css("checked",true);
                    break;
                case "周日" :
                    $("#c7")[0].checked = true;
                    break;
                default:break;
            }
        }

    })
    function addtime(){
        $("#myModal").modal("show");
        $("#equipmentlist")[0].style.display = "none";
        $("#arealist")[0].style.display = "none";
        $("#checklist")[0].style.display = "none";
        $("#dotime")[0].style.display="block";
    }
    function addtime2(){
        var selecthour=$("#hour").find("option:selected").text();
        var selectminute=$("#minute").find("option:selected").text();
        $("#name4").append("<p onclick='selectp(this)'>"+selecthour+":"+selectminute+"</p>");
        $("#myModal").modal("hide");
    }
    function selectp(obj){
        $(obj).siblings('p').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('p').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);
    }
    function deltime(){
        var $li = $("#name4").find("p:selected");
        if ($li.length <= 0) {
            alert("请选择要删除的选项");
            return false;
        }
        $($li).remove();
    }
</script>
<script type="text/javascript">
    var  res = {areas:new Array()};
    function submitarea(){
        /*将日期转换成jsonarray格式*/
        var timeslist= new Array();
        var aa=$("#name2").val();
        if(aa!=""){
            timeslist.push(aa);
        }else{
            var list=$("#name4").find("p");
            for(var i=0;i<list.length;i++){
                timeslist.push($(list[i]).text());
            }
        }
        $("#listtime").val(JSON.stringify(timeslist));
        /*将周循环转换成jsonarray格式*/
        var chk_value=new Array();
        $('input[name="week1"]:checked').each(function(){
            chk_value.push($(this).val());
        })
        $("#weeklytime").val(JSON.stringify(chk_value));
        var taskcontent=JSON.stringify(res);
        $("#taskcontent").val(taskcontent);
        /*表单提交*/
        $.ajax({
            url:"updtaskplan",
            type:"post",
            data:$('#tasksubmit').serialize(),
            async: false,
            success: function(data) {
                if(data>0){
                    alert("修改成功");
                    showtask();
                }else{
                    alert("修改失败");
                    return false;
                }
            }
        })
    }
    function showtask(){
        window.location="showtaskplan?page=1&type=0";
    }
</script>

<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
        <div id="content" class="col-lg-10 col-sm-10">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 视频巡检任务--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="" method="post" id="tasksubmit">
                                <div id="first">
                                    <input type="hidden" name="type" value="3">
                                    <input type="hidden" name="state" value="1">
                                    <input type="hidden" name="auditstatus" value="0">
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr>
                                            <td class="form-inline">
                                                <label class="control-label" for="idtype">任务号:</label>
                                                <input type="text" class="form-control" id="idtype" name="identifyingid" readonly value="${taskplaninfo.identifyingid}">
                                            </td>
                                            <td class="form-inline">
                                                <label class="control-label" for="taskname">任务名:</label>
                                                <input type="text" name="customid" class="form-control" id="taskname" value="${taskplaninfo.customid}"readonly>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="form-inline" colspan="2">
                                                <label class="control-label" for="username">任务描述:</label>
                                                <textarea  class="form-control" id="username" name="taskdesc" rows="3" cols="150" readonly>${taskplaninfo.identifyingid}</textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd2">创建人:</label>
                                                <input type="text" class="form-control" style="width: 300px;" id="pwd2" name="createuser" readonly value="${taskplaninfo.createuser}">
                                            </td>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd3">创建时间:</label>
                                                <input type="text"  id="pwd3" name="createtime" readonly class="form-control" onClick="WdatePicker()"
                                                       value="<sdf:formatDate value='${taskplaninfo.createtime}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                            </td>
                                        </tr>

                                        <tr>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd4">审核人:</label>
                                                <input type="text" class="form-control" style="width: 300px;" id="pwd4" name="revieweduser" readonly value="${taskplaninfo.revieweduser}">
                                            </td>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd7">审核时间:</label>
                                                <input type="text" class="form-control" id="pwd7"  onClick="WdatePicker()" name="reviewedtime" readonly
                                                       value="<sdf:formatDate value='${taskplaninfo.reviewedtime}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd5">批准人:</label>
                                                <input type="text" class="form-control" style="width: 300px;" id="pwd5" name="approveuser" readonly value="${taskplaninfo.approveuser}">
                                            </td>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd6">批准时间:</label>
                                                <input type="text" class="form-control" id="pwd6"  onClick="WdatePicker()" name="approvetime" readonly
                                                       value="<sdf:formatDate value='${taskplaninfo.approvetime}' pattern='yyyy-MM-dd'></sdf:formatDate>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="form-inline">
                                                <label class="control-label" for="classid">班组:</label>
                                                <select class="form-control" id="classid" name="classid" onchange="change()">
                                                    <c:forEach items="${classinfos}" var="classinfos">
                                                        <option ${taskplaninfo.classid eq classinfos.id ? 'selected' : ''} value="${classinfos.id}">${classinfos.customid}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td class="form-inline">
                                                <label class="control-label" for="directorid">负责人:</label>
                                                <select class="form-control" id="directorid" name="directorid">

                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="form-inline">
                                                <label class="control-label" for="banben">任务版本号:</label>
                                                <input type="text" class="form-control" style="width: 300px;" id="banben" name="taskversion" readonly value="${taskplaninfo.taskversion}">
                                            </td>
                                            <td class="form-inline">
                                                <label class="control-label" for="pwd8">最长执行时间:</label>
                                                <input type="text" class="form-control" style="width: 300px;" id="pwd8" name="maxduration" readonly value="${taskplaninfo.maxduration}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="form-inline" colspan="2">
                                                <label class="control-label" for="zhixing">顺序执行:</label>
                                                <select name="issequentially" class="form-control"  id="zhixing">
                                                    <option ${taskplaninfo.issequentially eq 0 ? 'selected' : ''} value="0">否</option>
                                                    <option ${taskplaninfo.issequentially eq 1 ? 'selected' : ''} value="1">是</option>
                                                </select>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td colspan="2"><input type="button" class="btn btn-primary" value="下一步 区域设置" onclick="second()">
                                                <input type="button" class="btn btn-primary white" value="取消" onclick="javascript:history.go(-1);"></td>
                                        </tr>
                                    </table>
                                    <input type="hidden" id="alldata" value='${taskplaninfo.taskcontent}'>
                                </div>
                                <div id="second" style="display: none">
                                    <input type="hidden" name="taskcontent" id="taskcontent">
                                    <div style="width: 230px;height: 420px;position:relative;float: left;">
                                        <h4>区域列表</h4>
                                        <div style="width: 200px;height: 300px;border: 1px solid gray;overflow:auto;margin-bottom: 20px;">
                                            <ul class="list-group" id="areaul">
                                                <%-- <li class="list-group-item">区域一</li>
                                                  <li class="list-group-item">区域二</li>
                                                  <li class="list-group-item">区域三</li>--%>
                                            </ul>
                                        </div>
                                        <a href="javascript:void(0)" >
                                            <input type="button" class="btn btn-inverse btn-default btn-lg" value="添加区域">
                                        </a>
                                        <a href="javascript:void(0)" >
                                            <input type="button" class="btn btn-inverse btn-default btn-lg" value="删除区域">
                                        </a>
                                    </div>
                                    <div style="width: 30px;height: 400px;position:relative;float: left;padding-top: 130px;padding-left: 3px;">
                                        <a href="#"><i class="glyphicon glyphicon-arrow-up black" style="font-size: 30px;"></i></a><br/><br/><br/><br/>
                                        <a href="#"><i class="glyphicon glyphicon-arrow-down black" style="font-size: 30px;"></i></a>
                                    </div>
                                    <div style="width: 230px;height: 420px;position:relative;float: left;margin-left:30px;">
                                        <h4>设备列表</h4>
                                        <div style="width: 200px;height: 300px;border: 1px solid gray;overflow:auto;margin-bottom: 20px;">
                                            <ul class="list-group" id="equipul">
                                                <%--<li class="list-group-item">设备一</li>
                                                <li class="list-group-item">设备二</li>
                                                <li class="list-group-item">设备三</li>--%>
                                            </ul>
                                        </div>
                                        <input type="button" class="btn btn-inverse btn-default btn-lg" value="添加设备" >
                                        <input type="button" class="btn btn-inverse btn-default btn-lg" value="删除设备" >
                                    </div>
                                    <div style="width: 30px;height: 400px;position:relative;float: left;padding-top: 130px;padding-left: 3px;">
                                        <a href="#"><i class="glyphicon glyphicon-arrow-up black" style="font-size: 30px;"></i></a><br/><br/><br/><br/>
                                        <a href="#"><i class="glyphicon glyphicon-arrow-down black" style="font-size: 30px;"></i></a>
                                    </div>
                                    <div style="width: 270px;height: 420px;position:relative;float: left;margin-left:30px;">
                                        <h4>巡检项列表</h4>
                                        <div style="width: 220px;height: 300px;border: 1px solid gray;overflow:auto;margin-bottom: 20px;">
                                            <ul class="list-group" id="checkul">
                                                <%--<li class="list-group-item">巡检项一</li>
                                                <li class="list-group-item">巡检项二</li>
                                                <li class="list-group-item">巡检项三</li>--%>
                                            </ul>
                                        </div>
                                        <input type="button" class="btn btn-inverse btn-default btn-lg" value="添加巡检项">
                                        <input type="button" class="btn btn-inverse btn-default btn-lg" value="删除巡检项">
                                    </div>
                                    <div style="width: 30px;height: 400px;position:relative;float: left;padding-top: 130px;margin-left: -3px;">
                                        <a href="#"><i class="glyphicon glyphicon-arrow-up black" style="font-size: 30px;"></i></a><br/><br/><br/><br/>
                                        <a href="#"><i class="glyphicon glyphicon-arrow-down black" style="font-size: 30px;"></i></a>
                                    </div>
                                    <div style="width: 200px;height: 420px;position:relative;float: left;margin-left: 30px;">
                                        <h4>巡检项内容</h4>
                                        <div style="width: 200px;height: 300px;border: 1px solid gray;overflow:auto;margin-bottom: 20px;" id="datarecord">
                                            <%--类型：记录项
                                            压力：0.9 ~2.5
                                            单位：MPa--%>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div style="margin-top: 35px;">
                                        <a href="#"><input type="button" class="btn btn-inverse btn-default btn-lg" value="上一步 基本设置" onclick="first()"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="#"><input type="button" class="btn btn-inverse btn-default btn-lg" value="下一步 循环执行设置" onclick="third()"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="showtaskplan3?page=1&type=3"><input type="button" class="btn btn-inverse btn-default btn-lg" value="取消"></a>
                                    </div>
                                </div>
                                <div id="third" style="display: none">
                                    <div style="border: 1px solid gray;width: 800px;" id="checkedradio">
                                        <input type="hidden"  id="listtime" name="implementtime" value='${taskplaninfo.implementtime}'>
                                        <div class="form-inline" >
                                            <input id="radion1" type="radio" name="issingletime" value="1" ${taskplaninfo.issingletime eq '1' ? 'checked' : ''} style="margin-top:15px;margin-left: 15px;margin-bottom: 25px;">单日单次执行<br/>

                                            <label class="control-label" for="name2" style="margin-left: 25px;">执行时间:</label>
                                            <input type="text" class="form-control" id="name2">

                                        </div>
                                        <div class="form-inline"style="margin-top: 25px;margin-bottom: 25px;">
                                            <input id="radion2" type="radio" name="issingletime" value="0" ${taskplaninfo.issingletime eq '0' ? 'checked' : ''}  style="margin-top:15px;margin-left: 15px;margin-bottom: 25px;">单日多次执行<br/>

                                            <label class="control-label" for="name1"style="margin-left: 25px;" >起始时间：</label>
                                            <input type="text" name="starttime" id="name1" disabled class="form-control" value="${taskplaninfo.starttime}" readonly>

                                            <label class="control-label" for="name3">终止时间:</label>
                                            <input type="text" id="name3" name="endtime" class="form-control"id="endtime" value="${taskplaninfo.endtime}" readonly><br>


                                            <div style="margin-left: 25px;font-size:15px;position:relative;float: left;margin-top: 60px;">执行列表：</div>
                                            <div id="name4" style="position:relative;float: left;margin-top: 25px;width:350px;height:150px;border: 1px solid gray;overflow: auto"></div>

                                            <div style="position:relative;float: left;margin-top: 40px;margin-left: 25px;">
                                                <input type="button" id="addtimee" value="添加" class="btn btn-inverse btn-default btn-lg"><br>
                                                <input type="button" id="deltime" value="删除" class="btn btn-inverse btn-default btn-lg" style="margin-top:15px;">
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                    <div style="margin-top:35px;margin-left: 15px;">
                                        周循环：
                                        <input type="checkbox" id="c1" name="week1" value="周一"  disabled="disabled">周一
                                        <input type="checkbox" id="c2" name="week1" value="周二"  disabled="disabled">周二
                                        <input type="checkbox" id="c3"name="week1" value="周三"  disabled="disabled">周三
                                        <input type="checkbox" id="c4" name="week1" value="周四"  disabled="disabled">周四
                                        <input type="checkbox" id="c5"name="week1" value="周五"  disabled="disabled">周五
                                        <input type="checkbox" id="c6"name="week1" value="周六"  disabled="disabled">周六
                                        <input type="checkbox" id="c7"name="week1" value="周日"  disabled="disabled">周日

                                        <input type="hidden" name="weeklytime" value='${taskplaninfo.weeklytime}' id="weeklytime">
                                    </div>
                                    <div style="margin-top: 25px;">
                                        <a href="#" style="margin-left: 35px;">
                                            <input type="button" class="btn btn-inverse btn-default btn-lg" value="上一步 区域设置" onclick="second()">
                                        </a>
                                        <a href="showtaskplan3?page=1&type=3" style="margin-left: 35px;">
                                            <input type="button" class="btn btn-inverse btn-default btn-lg" value="取消" >
                                        </a>
                                    </div>
                                </div>
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
        <div class="modal-content" style="overflow: auto;">
            <div id="arealist">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>区域列表</h3>
                </div>
                <div class="modal-body">
                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable" id="area">
                        <tr>
                            <th></th>
                            <th>区域编号</th>
                            <th>区域名称</th>
                            <th>区域描述</th>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <input type="button" id="btn" value="添加" class="btn btn-primary" onclick="addarea()">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
            <div id="equipmentlist">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>设备列表</h3>
                </div>
                <div class="modal-body">
                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable" id="equipment">
                        <tr>
                            <th></th>
                            <th>设备编号</th>
                            <th>设备名称</th>
                            <th>设备描述</th>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <input type="button" value="添加" class="btn btn-primary" onclick="addequip()">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
            <div id="checklist">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>巡检项列表</h3>
                </div>
                <div class="modal-body">
                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable" id="check">
                        <tr>
                            <th></th>
                            <th>巡检项编号</th>
                            <th>巡检项名称</th>
                            <th>巡检项描述</th>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <input type="button" value="添加" class="btn btn-primary" onclick="addcheck()">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
            <div id="dotime">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button">×</button>
                    <h3>执行列表</h3>
                </div>
                <div class="modal-body">
                    选择执行时间：<select id="hour"></select>:<select id="minute"></select>
                </div>
                <div class="modal-footer">
                    <a data-dismiss="modal" class="btn btn-default" href="#">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
