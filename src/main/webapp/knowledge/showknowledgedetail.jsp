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
        a{
            text-decoration:none;
            color:black;
        }
        a:hover{
            text-decoration: none;
        }
    </style>
    <script type="text/javascript">
        function selectfile(obj){
            $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
            $(obj).siblings('li').prop("selected", false);
            $(obj).addClass('selected');
            $(obj).prop("selected", true);
        }

        // function downLoadFile(obj,i) {
        //     var fileName = $('#fileName'+i).val();
        //     alert(fileName);
        //     $.ajax({
        //         url: "/downLoadFile?filename=" + fileName,
        //         type: "post",
        //         datatype: "json",
        //         success: function (data) {
        //             if (data > 0) {
        //                 alert("下载成功");
        //                 // showtask();
        //                 return;
        //             } else {
        //                 alert("下载失败");
        //                 return false;
        //             }
        //         }
        //     });
        //
        //
        //     return;
        //
        // }
        $(function(){
            <%--var  ipAddress = ${ip};--%>
            var attach=JSON.parse($("#attach").val());
            for(var i=0;i<attach.length;i++){
                $("#file").append("<a href='"+ "/report" +attach[i]+"' download=''>" +
                    "<li class='list-group-item' onclick='selectfile(this)'>"+attach[i].substring(attach[i].lastIndexOf("_")+1)+"</li></a>");
                // $("#file").append("<li class='list-group-item'><input hidden value='"+attach[i]+"' id='fileName"+i+"'/><span >"+attach[i].substring(attach[i].lastIndexOf("/")+1)+"</span>&nbsp;&nbsp;&nbsp;<input class='btn btn-primary' type='button' onclick='downLoadFile(this,"+i+");' value='下载'/></li>");
            }
        })
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
                                <i class="glyphicon glyphicon-globe"></i> 知识--详情
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addknowledges" enctype="multipart/form-data" method="post" id="form">
                                <input type="hidden" name="id" value="${knowledge.id}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">知识类别:</label>
                                            <input id="username" name="typeid" class="form-control" value="${knowledge.ktype.typename}" readonly>
                                            <%--<select id="username" name="typeid">
                                                <c:forEach items="${knowledgetypes}" var="types">
                                                    <option ${knowledge.typeid eq types.typeid?'selected':''} value="${types.typeid}">${types.typename}</option>
                                                </c:forEach>
                                            </select>--%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">知识标题:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd" name="title" readonly value="${knowledge.title}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd1">详细描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd1"name="descontent" readonly value="${knowledge.descontent}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="equipid">关联设备名称：</label>
                                            <input type="input" name="equipid" class="form-control"  id="equipid" readonly value="${knowledge.equip.name}">
                                            <%--<select class="form-control" id="equipid" name="equipid">
                                                <c:forEach items="${equipmentinfos}" var="equips">
                                                    <option ${knowledge.equipid eq equips.id ? 'selected':''} value="${equips.id}">${equips.name}</option>
                                                </c:forEach>
                                            </select>--%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="attachment">附件文档:</label>
                                            <input type="hidden" id="attach" value='${knowledge.attachment}'>
                                            <div id="attachment" >
                                                <ul class="list-group" id="file">

                                                </ul>
                                                <%--<div>
                                                     <span class="btn btn-inverse btn-default btn-lg fileinput-button">
                                                         <span>添加</span>
                                                         <input type="file" onchange="addattachment()" id="addfile" multiple="true" name="attachment[]">
                                                     </span>
                                                    <input type="button" class="btn btn-inverse btn-default btn-lg" value="删除" onclick="delfile()">
                                                </div>
                                                <div class="clearfix"></div>--%>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
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
