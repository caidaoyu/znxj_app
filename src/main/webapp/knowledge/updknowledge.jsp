<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <meta http-equiv=“X-UA-Compatible” content=“IE=5″>
    <meta http-equiv=“X-UA-Compatible” content=“IE=6″>
    <meta http-equiv=“X-UA-Compatible” content=“IE=7″>
    <meta http-equiv=“X-UA-Compatible” content=“IE=8″>
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
        var map = {};
        var pic;
        var UP_IMGCOUNT = 0;

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
                        var areaselect =  document.getElementById("areaid");
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
        function change(){
            var areaid = $("#areaid option:selected")[0].value;
            $.ajax({
                url:"queryequipment",
                type:"post",
                data:{
                    areaid:areaid,
                },
                dataType:"json",
                success:function(data){
                    if(data!=null){
                        var equipidselect =  document.getElementById("equipid");
                        equipidselect.innerHTML = "";
                        for(var i=0;i<data.length;i++){
                            var equipidop = document.createElement("option");
                            equipidop.innerHTML = data[i].name;
                            equipidop.value = data[i].id;
                            equipidselect.appendChild(equipidop);
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
//            var form = document.getElementById("form");
            // 用表单来初始化
//            var formData = new FormData(form);
//            var files = document.getElementById("addfile").files;
//            var arr=new Array();

            var form = document.getElementById("form");
            var formData = new FormData(form);
            var files = document.getElementById("addfile").files;
            var arr=new Array();
            for(var i=0; i< files.length; i++){
                UP_IMGCOUNT++;
                $("#file").append("<li class='list-group-item' onclick='selectfile(this)' num='"+UP_IMGCOUNT+"'>"+getFileName(files[i].name)+"</li>");
//                filesFormData.append(getFileName(files[i].name),files[i]);
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
//            var licount=document.getElementById("file").getElementsByTagName("li").length;
//            var files = document.getElementById("addfile").files;

            pic.splice(0,pic.length);
            for(key in map){
//                alert(key + map[key]);
                pic.push(map[key]);
            }
            formData.append(pic);
//            if(licount>1){
//                for(var i=0; i< files.length; i++){
//                    UP_IMGCOUNT++;
//                    //getFileName(files[i].name);
//                    //$("#file").append("<li class='list-group-item' onclick='selectfile(this)'>"+getFileName(files[i].name)+"</li>");
//                    formData.append(getFileName(files[i].name),files[i]);
//                }
//            }
            //删除formdata input file
//            formData.delete("attachment[]");
            //将文件formdata 赋值给 dormdata
//            for(var i of filesFormData.entries()){
//                formData.append(i[0],i[1]);
//            }
            $.ajax({
                url:"updknowledge",
                type:"post",
                data:formData,
                async: false,
                processData: false,
                contentType: false,
                success: function(data) {
                    if(data.success){
                        alert("修改成功");
                        showknowledge();
                    }else{
                        alert("修改失败");
                        return false;
                    }
                }
            })
        }
        function showknowledge(){
            window.location="showknowledge?page=1";
        }
        function getFileName(o){
            var pos=o.lastIndexOf("\\");
            return o.substring(pos+1);
        }
        function delfile(obj){
            var $li = $("#file").find("li:selected");
            delete map[$(obj).attr("num")];
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
        $(function(){
            var attach=JSON.parse($("#attach").val());
            UP_IMGCOUNT = attach.length;
            for(var i=0;i<attach.length;i++){
                map[$(this).attr(i)] = attach[i].substring(attach[i].lastIndexOf("/")+1);
                $("#file").append("<li class='list-group-item' onclick='selectfile(this)' num='"+i+"'>"+attach[i].substring(attach[i].lastIndexOf("/")+1)+"</li>");
            }
            if($("#photo").val()!=""){
                pic=JSON.parse($("#photo").val());
            }else{
                pic=new Array();
            }
            change();
        })
    </script>
</head>
<body>
<input type="text" id="photo" value='${knowledge.attachment}'>
<%--<%@ include file="/WEB-INF/pages/common/navigation.jsp"%>--%>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <%--
                <%@ include file="/WEB-INF/pages/common/menu.jsp"%>
        --%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 知识--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="" enctype="multipart/form-data" method="post" id="form">
                                <input type="hidden" name="id" value="${knowledge.id}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">知识类别:</label>
                                            <select id="username" name="typeid" class="form-control">
                                                <c:forEach items="${knowledgetypes}" var="types">
                                                    <option ${knowledge.typeid eq types.typeid?'selected':''} value="${types.typeid}">${types.typename}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">知识标题:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="title" required value="${knowledge.title}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd1">详细描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd1"name="descontent" required value="${knowledge.descontent}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">关联设备：&nbsp;
                                            <label class="control-label" for="siteid">厂区:</label>
                                            <select class="form-control" id="siteid" name="siteid" onchange="sitechange()">
                                                <c:forEach items="${siteareainfos}" var="site">
                                                    <option value="${site.id}">${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="control-label" for="areaid" style="margin-left: 50px;">区域:</label>
                                            <select class="form-control" id="areaid"  onchange="change()">
                                                <c:forEach items="${areainfos}" var="area">
                                                    <option value="${area.id}">${area.customid}</option>
                                                </c:forEach>
                                            </select>

                                            <label class="control-label" for="equipid" style="margin-left: 50px;">设备:</label>
                                            <select class="form-control" id="equipid" name="equipid">
                                                <option value=""></option>
                                                <c:forEach items="${equipmentinfos}" var="equips">
                                                    <option ${knowledge.equipid eq equips.id ? 'selected':''} value="${equips.id}">${equips.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="attachment">附件文档:<span style="color: red;">(只支持 图片,word,pdf的文件)</span></label>
                                            <input type="hidden" id="attach" value='${knowledge.attachment}'>
                                            <div id="attachment" >
                                                <div style="width: 400px;height:150px;border: 1px solid black;position:relative;float:left;overflow: auto">
                                                    <ul class="list-group" id="file">

                                                    </ul>
                                                </div>
                                                <div>
                                                     <span class="btn btn-inverse btn-default btn-lg fileinput-button">
                                                         <span>添加</span>
                                                         <input type="file" onchange="addattachment()" id="addfile" multiple="true" name="attachment[]">
                                                     </span>
                                                    <input type="button" class="btn btn-inverse btn-default btn-lg" value="删除" onclick="delfile()">
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


    <hr>


</div>

</body>
</html>
