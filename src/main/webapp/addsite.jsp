<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#username").blur(function () {
                var customid = $("#username").val();
                $.ajax({
                    url:"isSiteExist",
                    type:"post",
                    data:{
                        customName:customid,
                        id:null
                    },
                    dataType:"json",
                    success:function(data){
                        if(data == "1"){
                            $("#existuser").css("display","block");
                            $("input[name='addSite']").attr("disabled",true);
                        }else{
                            $("#existuser").css("display","none");
                            $("input[name='addSite']").attr("disabled",false);
                        }
                    }
                })
                // $("input[name='addSite']").setAttribute('disabled',true);

            });
        });

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
                                <i class="glyphicon glyphicon-globe"></i> 厂区--添加
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="addsite" method="post">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="username">厂区名称:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="username" required name="customid">
                                            <span style="display: none;color: red;" id="existuser">该厂区已存在！！！</span>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd">厂区描述:</label>
                                            <input type="text" class="form-control" style="width: 300px;" id="pwd"name="desccontent">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="pwd2">备注:</label>
                                            <textarea class="form-control" rows="5" cols="50" id="pwd2"name="remark"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" name="addSite" class="btn btn-primary" value="保存">
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
