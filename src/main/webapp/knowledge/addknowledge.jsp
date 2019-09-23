<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <style>
        .fileinput-button {
            position: relative;
            display: inline-block;
            overflow: hidden;
        }

        .fileinput-button input{
            position:absolute;
            right: 0px;
            top: 0px;
            opacity: 0;
            -ms-filter: 'alpha(opacity=0)';
            font-size: 200px;
        }
        .selected{background: #35A7E7;
            color: white;}
    </style>
    <script type="text/javascript">
        function sitechange(){
            var siteid = $("#siteid option:selected")[0].value;
            $("#areaid").empty();
            $("#areaid").append("<option value=''></option>")
            $.ajax({
                url:"queryareabysiteid",
                type:"post",
                data:{
                    siteid:siteid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
//                        var areaselect =  document.getElementById("areaid");
//                        areaselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
//                            var areaop = document.createElement("option");
//                            areaop.innerHTML = data[i].customid;
//                            areaop.value = data[i].id;
//                            areaselect.appendChild(areaop);
                            $("#areaid").append("<option value='"+data[i].id+"'>"+data[i].customid+"</option>");
                        }
                        return true;
                    }else{
                        alert("该厂区暂时没有区域");
                        return false;
                    }
                }
            });
        }
        function change(){
            var areaid = $("#areaid option:selected")[0].value;
            $("#equipid").empty();
            $("#equipid").append("<option value=''></option>")
            $.ajax({
                url:"queryequipment",
                type:"post",
                data:{
                    areaid:areaid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
//                        var equipidselect =  document.getElementById("equipid");
//                        equipidselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
//                            var equipidop = document.createElement("option");
//                            equipidop.innerHTML = data[i].name;
//                            equipidop.value = data[i].id;
//                            equipidselect.appendChild(equipidop);
                            $("#equipid").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                        }
                        return true;
                    }else{
                        alert("该区域暂时没有设备");
                        return false;
                    }
                }
            });
        }
        window.onload=sitechange;

//        var filesFormData = new FormData();
        function addattachment(){
            var form = document.getElementById("form");
            var formData = new FormData(form);
            var files = document.getElementById("addfile").files;
            var arr=new Array();
            for(var i=0; i< files.length; i++){
                getFileName(files[i].name);
                $("#file").append("<li class='list-group-item' onclick='selectfile(this)'>"+getFileName(files[i].name)+"</li>");
            }
        }
        function upload(){
            var title=$("#title").val();
            if(title==""){
                alert("知识标题不能为空");
                return false;
            }
            var equipid=$("#equipid option:selected")[0].value;
            if(equipid==""){
                alert("设备不能为空");
                return false;
            }
            var form = document.getElementById("form");
            var formData = new FormData(form);
            var files = document.getElementById("addfile").files;
            var arr=new Array();
            var licount=document.getElementById("file").getElementsByTagName("li").length;
            if(licount>1){
                for(var i=0; i< files.length; i++){
                    //getFileName(files[i].name);
                    //$("#file").append("<li class='list-group-item' onclick='selectfile(this)'>"+getFileName(files[i].name)+"</li>");
                    formData.append(getFileName(files[i].name),files[i]);
                }
            }
//            var form = document.getElementById("form");
//            var formData = new FormData(form);
            //删除formdata input file
            /*formData.delete("attachment[]");*/
           /* for(var i of filesFormData.entries()){
                formData.append(i[0],i[1]);
            }*/
                $.ajax({
                    url:"addknowledges",
                    type:"post",
                    data:formData,
                    async: false,
                    processData: false,
                    contentType: false,
//                    beforeSend: showLoading,
                    success: function(data){
                        console.log(data);
                        if(data.success){
                            alert("添加成功");
                            showknowledge();
                        }else{
                            alert(data.message);
                            return false;
                        }
                    },
//                    error:function(data){
//                        console.log(data);
//                    }
                });

        }
        function showLoading(){
            $("#myModal").modal("show");
        }
//        function hideLoading(data){
//            alert(data.message);
//            $('#myModal').modal('hide');
//        }
        function showknowledge(){
            window.location="showknowledge?page=1";
        }

        function getFileName(o){
            var pos=o.lastIndexOf("\\");
            return o.substring(pos+1);
        }
        function delfile(){
            var $li = $("#file").find("li:selected");
            if ($li.length <= 0) {
                alert("请选择要删除的选项");
                return false;
            }
//            filesFormData.delete($li.text());
            $($li).remove();
        }
        function selectfile(obj){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
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
                                <i class="glyphicon glyphicon-globe"></i> 知识--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="" enctype="multipart/form-data" method="post" id="form">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">知识类别:</label>
                                            <select id="username" name="typeid" class="form-control">
                                                <c:forEach items="${knowledgetypes}" var="types">
                                                    <option value="${types.typeid}">${types.typename}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="title">知识标题:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="title" name="title" placeholder="请输入知识标题" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd1">详细描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd1"name="descontent" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="form-inline">关联设备：&nbsp;
                                            <label class="control-label" for="siteid">厂区:</label>
                                            <select class="form-control" id="siteid" name="siteid" onchange="sitechange()">
                                                <option value=""></option>
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="control-label" for="areaid" style="margin-left: 50px;">区域:</label>
                                            <select class="form-control" id="areaid"  onchange="change()">
                                                <option value=""></option>
                                                <c:forEach items="${areainfos}" var="area">
                                                    <option value="${area.id}">${area.customid}</option>
                                                </c:forEach>
                                            </select>

                                            <label class="control-label" for="equipid" style="margin-left: 50px;">设备:</label>
                                            <select class="form-control" id="equipid" name="equipid" required>
                                                <option value=""></option>
                                                <c:forEach items="${equipmentinfos}" var="equips">
                                                    <option value="${equips.id}">${equips.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <span>附件文档:<span style="color: red;">(只支持 图片,word,pdf的文件)</span><span style="color: red;">(只支持 图片,word,pdf的文件)</span></span>
                                            <div id="attachment">
                                                <%--上传文件：<input type="file" name="file1" multiple="multiple"><br/>--%>
                                                <div style="width: 400px;height:150px;border: 1px solid darkgray;background:white;position:relative;float:left;overflow: auto">
                                                     <ul class="list-group" id="file">

                                                     </ul>
                                                 </div>
                                                <div>
                                                     <span class="btn btn-inverse btn-default btn-lg fileinput-button" style="margin-left: 20px;margin-top: 33px;">
                                                         <span>添加</span>
                                                         <input type="file" onchange="addattachment()" id="addfile" multiple="true" name="attachment[]" onchange ="uploadFile(this)">
                                                     </span>

                                                     <input type="button" class="btn btn-inverse btn-default btn-lg" value="删除" onclick="delfile()" style="margin-top: 33px;">
                                                </div>
                                                 <div class="clearfix"></div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="button" class="btn btn-primary" value="保存" onclick="upload()">
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
<%--<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="z-index: 0">
    <div class="modal-dialog" style="z-index: 9999;">
        <div class="modal-content" >
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>提示</h3>
            </div>
            <div class="modal-body">
                    请稍后。。。。。
            </div>
        </div>
    </div>
</div>--%>
<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade in" style="z-index: 0">
    <div class="modal-dialog" style="z-index: 9999">
        <div class="modal-content" style="overflow: auto;">
            <div class="modal-header">
                <h3>提示</h3>
            </div>
            <div class="modal-body">
                请稍后。。。。。
            </div>
        </div>
    </div>
</div>
</body>
</html>
