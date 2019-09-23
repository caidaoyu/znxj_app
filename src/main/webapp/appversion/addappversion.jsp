<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function addappversion(){
            var form = document.getElementById("form");
            var formData = new FormData(form);
            $.ajax({
                url:"/addappversion",
                type:"post",
                data:formData,
                async: false,
                processData: false,
                contentType: false,
                success: function(data) {
                    if(data>0){
                        alert("添加成功");
                        showappversion();
                    }else{
                        alert("添加失败");
                        return false;
                    }
                },
            });

        }
        function showappversion(){
            window.location="/showappversion?page=1";
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
                                <i class="glyphicon glyphicon-globe"></i> 添加Android APK
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="" enctype="multipart/form-data" id="form">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">版本名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" name="versionname" required >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">版本号:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd"name="versioncode" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd1">版本更新信息:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd1"name="versiondesc" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">上传Apk:</label>
                                            <input type="file" class="form-control" id="pwd2" name="apk">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="button" class="btn btn-primary" value="保存" onclick="addappversion()">
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
