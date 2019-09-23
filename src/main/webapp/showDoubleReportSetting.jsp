<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
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
                                <i class="glyphicon glyphicon-globe"></i>系统设置
                            </h2>
                        </div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li>
                                <shiro:hasPermission name="item:system">
                                    <a href="showsystem?type=1">终端显示和报告缓存设置</a>
                                </shiro:hasPermission></li>
                            <li>
                                <a href="showsystem?type=2">一般性设置</a>
                            </li>
                            <li><a href="showsystem?type=4">图片|视频|音频服务器设置</a></li>
                            <li><a href="showsystem?type=3">报告波动值设置</a></li>
                            <li><shiro:hasPermission name="item:data"><a href="showdaterecord?page=1">可读数据管理</a></shiro:hasPermission></li>
                            <li><shiro:hasPermission name="item:apk"><a href="showappversion?page=1">Android APK更新</a></shiro:hasPermission></li>
                            <li><shiro:hasPermission name="item:warntype"><a href="showwarntype?page=1">隐患类型和终止原因设置</a></shiro:hasPermission></li>
                            <li><a href="showReportSetting">显示单次任务报告设置</a></li>
                            <li class="active" ><a href="showDoubleReportSetting">显示任务报告汇总设置</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <div style="margin-bottom: 20px;margin-left: 25px;">
                                    <h3>显示任务报告汇总设置</h3><hr>
                                    <form action="updsystem" method="post" id="form">
                                        <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                            <tr>
                                                <td class="form-inline">
                                                    <label class="control-label" for="rol">显示区域:</label>
                                                    <select name="areaname" id="rol" class="form-control">
                                                        <option ${areaname eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${areaname eq '0' ? 'selected':''} value="0">否</option>
                                                    </select>
                                                </td>
                                                <td class="form-inline">
                                                    <label class="control-label" for="app">显示设备:</label>
                                                    <select name="equipname" id="app" class="form-control">
                                                        <option ${equipname eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${equipname eq '0' ? 'selected':''} value="0">否</option>
                                                    </select>
                                                </td>
                                                <td class="form-inline">
                                                    <label class="control-label" for="name">显示巡检项:</label>
                                                    <select name="checkname" id="name" class="form-control">
                                                        <option ${checkname eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${checkname eq '0' ? 'selected':''} value="0">否</option>
                                                    </select></td>
                                            </tr>

                                            <tr>
                                                <td class="form-inline">
                                                    <label class="control-label" for="normalmin">显示低值:</label>
                                                    <select name="normalmin" id="normalmin" class="form-control">
                                                        <option ${normalmin eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${normalmin eq '0' ? 'selected':''} value="0">否</option>
                                                    </select>
                                                </td>
                                                <td class="form-inline">
                                                    <label class="control-label" for="normalmax">显示高值:</label>
                                                    <select name="normalmax" id="normalmax" class="form-control">
                                                        <option ${normalmax eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${normalmax eq '0' ? 'selected':''} value="0">否</option>
                                                    </select></td>
                                                <td class="form-inline">
                                                    <label class="control-label" for="normalmin">显示告警下限:</label>
                                                    <select name="lowerwarning" id="lowerwarning" class="form-control">
                                                        <option ${lowerwarning eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${lowerwarning eq '0' ? 'selected':''} value="0">否</option>
                                                    </select>
                                                </td>
                                                </tr>
                                            <tr>
                                                <td class="form-inline" colspan="3">
                                                    <label class="control-label" for="upperwarning">显示告警上限:</label>
                                                    <select name="upperwarning" id="upperwarning" class="form-control">
                                                        <option ${upperwarning eq '1' ? 'selected':''} value="1">是</option>
                                                        <option ${upperwarning eq '0' ? 'selected':''} value="0">否</option>
                                                    </select></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <input type="button" class="btn btn-primary" value="保存" onclick="updDoubleReportSetting()" style="margin-left: 25px;margin-bottom: 20px;">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function updDoubleReportSetting(){
        $.ajax({
            url:"updDoubleReportSetting",
            type:"post",
            data:$('#form').serialize(),
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
        window.location="showDoubleReportSetting";
    }
</script>
</body>
</html>
