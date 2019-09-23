<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sdf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript" src="js/formatdate.js"></script>
    <script type="text/javascript">
        $(function () {
            tasknum();
        })
        function deltaskreport(tempid,id){
           if(id==undefined || id==''|| id==null){
               id=0;
           }
            var isDel=confirm('确定删除吗？');
            if (isDel!=true) {
                return false;
            }else {
                $.ajax({
                    url: "deltaskreport?id=" + id+"&tempid="+tempid,
                    type: "post",
                    datatype: "json",
                    success: function (data) {
                        if (data > 0) {
                            alert("删除成功");
                            showtask();
                        } else {
                            alert("删除失败");
                            return false;
                        }
                    }
                });
            }
        }
        function showtask(){
            var tasktype=$("#tasktype").val();
            window.location="/firstPage?page="+$("#page").val();
        }
        function showstop(tempid){
            $("#showstop").empty();
            $.ajax({
                url: "queryByTempid?tempid=" + tempid,
                type: "post",
                datatype: "json",
                success: function (data) {
                    if (data !=null) {
                        $("#showstop").append("<p>终止原因："+data.reason+"</p>" +
                                "<p>终止内容："+data.content+"</p><p>终止时间："+ data.stoptime2+"</p>" +
                                "<p>班组名称："+data.classname+"</p><p>负责人名称："+data.directorname+"</p>");
                        $("#myModal").modal("show");
                    } else {
                        alert("暂时没有其他信息");
                        return false;
                    }
                }
            })
        }
        function tasknum(){
            var type = $("#status option:selected")[0].value;
            var siteid = $("#name2 option:selected")[0].value;
            var taskCcode=$("#taskCcodeaa").val();
            $("#taskno").empty();
            if($("#roleid").val()==null){
                $("#taskno").append("<option  value='' selected>所有任务</option>");
            }
            if(""==type){
                $.ajax({
                    type : "GET",
                    url : "taskbysiteandtype?searchtype="+type+"&siteid="+siteid,
                    success : function(task){
                        for(var i=0;i<task.length;i++){
                            if(task[i].identifyingid==taskCcode){
                                $("#taskno").append("<option  value='"+task[i].identifyingid+"' selected>"+task[i].identifyingid+"</option>");
                            }else{
                                $("#taskno").append("<option  value='"+task[i].identifyingid+"' >"+task[i].identifyingid+"</option>");
                            }
                        }
                    }
                })
            }else {
                $.ajax({
                    type : "GET",
                    url : "taskbysiteandtype?type="+type+"&siteid="+siteid,
                    success : function(task){
                        for(var i=0;i<task.length;i++){
//                            $("#taskno").append("<option  value='"+task[i].identifyingid+"'>"+task[i].identifyingid+"</option>");
                            if(task[i].identifyingid==taskCcode){
                                $("#taskno").append("<option  value='"+task[i].identifyingid+"' selected>"+task[i].identifyingid+"</option>");
                            }else{
                                $("#taskno").append("<option  value='"+task[i].identifyingid+"' >"+task[i].identifyingid+"</option>");
                            }
                        }
                    }
                })
            }
        }
//        function testrefurbish(){
//            var tasktype = $("#tasktype").val();
//            var taskCcode = $('#taskno option:selected').val();
//            var time1 = $("#time1").val();
//            var time2 = $("#time1").val();
//            var operationstate = $("#operationstate option:selected").val();
//            var siteid = $("#name2 option:selected").val();
//            var searchtype = $("#status option:selected").val();
//            window.location="/showallreport1?page=1&tasktype="+tasktype+"&taskCcode="+taskCcode+"&time1="+time1+"&time2="+time2+"&operationstate="+operationstate+"&siteid="+siteid+"&searchtype="+searchtype;
//        }
    </script>
</head>
<body>
<input type="hidden" id="page" value="${pageBean.currentPage}">
<div class="ch-container">
    <div class="row">

        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <input type="hidden" value="${type}" id="tasktype">
                    <input type="hidden" value="${taskCcode}" id="taskCcodeaa">
                    <input type="hidden" value="${roleid}" id="roleid">
                    <div>
                        <ul id="myTab" class="nav nav-tabs">
                            <li>
                                <a href="showtaskplan?page=1&type=${type}">任务列表</a>
                            </li>
                           <%-- <li><a href="showstoptask?page=1&state=3&type=${type}">终止任务</a></li>--%>
                            <li class="active"><a href="#report" data-toggle="tab" style="background: #35A7E7;color: white;">任务报告</a></li>
                            <c:if test="${type==2}">
                                <li>
                                    <shiro:hasPermission name="item:reportquick">
                                        <a href="showQuickReport1?page=1&type=${type}&qtype=1">立即上报</a></li>
                                    </shiro:hasPermission>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="report">
                            <div class="box-inner">
                                <div class="box-header well" data-original-title="">
                                    <h2>
                                        <i class="glyphicon glyphicon-globe"></i>
                                        <c:if test="${type==0}">日常巡检任务报告 - 列表</c:if>
                                        <c:if test="${type==1}">计划巡检任务报告--列表</c:if>
                                        <c:if test="${type==2}">HSE隐患排查报告--列表</c:if>
                                        <c:if test="${type==3}">视频巡检任务报告--列表</c:if>
                                    </h2>
                                </div>
                                <div class="box-content">
                                    <div class="form-inline" style="margin-bottom: 20px;">
                                        <form action="showallreport1?page=1&tasktype=${type}" method="post">
                                            <%--<label class="control-label" for="name2">任务报告编号：</label>--%>
                                            <%--<input type="text" style="width: 200px;" id="name2" name="taskCcode" value="${param.taskCcode}">--%>

                                            <label class="control-label" for="name2">厂区：</label>
                                            <select id="name2" class="form-control" name="siteid" onchange="tasknum()">
                                                <c:if test="${roleid==null}">
                                                    <option value="">所有厂区</option>
                                                </c:if>
                                                <c:forEach items="${sites}" var="site">
                                                    <option  value="${site.id}" ${siteid eq site.id ?'selected':''}>${site.customid}</option>
                                                </c:forEach>
                                            </select>
                                            <label class="control-label" for="status">任务类型：</label>
                                            <select class="form-control" id="status" name="searchtype"  onchange="tasknum()">
                                                <c:if test="${roleid==null}">
                                                    <option ${searchtype eq ''?'selected':''} value="">所有任务</option>
                                                </c:if>
                                                <option ${searchtype eq '0'?'selected':''} ${type eq '0'?'selected':''} value="0">日常巡检</option>
                                                <option ${searchtype eq '1'?'selected':''} ${type eq '1'?'selected':''} value="1">计划巡检</option>
                                                <option ${searchtype eq '2'?'selected':''} ${type eq '2'?'selected':''} value="2">隐患排查</option>
                                                <option ${searchtype eq '3'?'selected':''} ${type eq '3'?'selected':''} value="3">视频巡检</option>
                                                <%--<option ${searchtype eq '4'?'selected':''} ${type eq '4'?'selected':''} value="4">临时任务</option>--%>
                                            </select>
                                            <label class="control-label" for="taskno">任务号：</label>
                                            <select class="form-control" id="taskno" name="taskCcode" >
                                                <%--<c:if test="${taskCcode==null or taskCcode==''}">--%>
                                                    <%--<option value="" selected>所有任务</option>--%>
                                                <%--</c:if>--%>
                                                <%--<c:if test="${taskCcode!=null or taskCcode!=''}">--%>
                                                    <%--<option value="${taskCcode}" selected>${taskCcode}</option>--%>
                                                <%--</c:if>--%>
                                                <c:choose>
                                                    <c:when test="${taskCcode==null and taskCcode==''}"><option value="" selected>所有任务</option></c:when>
                                                    <c:otherwise>
                                                        <option value="${taskCcode}" selected>${taskCcode}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>

                                            <label class="control-label" for="operationstate">执行状态：</label>
                                            <select class="form-control" id="operationstate" name="operationstate">
                                                <option ${operationstate eq '' ? 'selected' : ''} value="">所有</option>
                                                <option ${operationstate eq '1' ? 'selected' : ''} value="1">漏检</option>
                                                <option ${operationstate eq '2' ? 'selected' : ''} value="2">跳检</option>
                                                <option ${operationstate eq '3' ? 'selected' : ''} value="3">正常</option>
                                                <option ${operationstate eq '4' ? 'selected' : ''} value="4">超时</option>
                                                <option ${operationstate eq '5' ? 'selected' : ''} value="5">主动终止</option>
                                            </select><br>
                                            <label class="control-label" style="margin-top: 10px">报告完成时间：</label>
                                            <input type="text" name="time1" onClick="WdatePicker()" readonly value="${param.time1}"style="margin-top: 10px" id="time1">--<input type="text" name="time2" onClick="WdatePicker()" readonly value="${param.time2}"style="margin-top: 10px" id="time2">
                                            <input type="submit" class="btn btn-primary" value="搜索" style="margin-left: 30px;margin-top: 10px">
                                            <%--<button  onclick="testrefurbish()" class="btn btn-primary" style="margin-left: 50px;margin-top: 10px">刷新</button>--%>
                                                <script language="JavaScript">
                                                    Date.prototype.format = function (format) {
                                                        var args = {
                                                            "M+": this.getMonth() + 1,
                                                            "d+": this.getDate(),
                                                            "h+": this.getHours(),
                                                            "m+": this.getMinutes(),
                                                            "s+": this.getSeconds(),
                                                            "q+": Math.floor((this.getMonth() + 3) / 3), //quarter

                                                            "S": this.getMilliseconds()
                                                        };
                                                        if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                                                        for (var i in args) {
                                                            var n = args[i];
                                                            if (new RegExp("(" + i + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
                                                        }
                                                        return format;
                                                    };
                                                    $(function () {
                                                        if($("#time1").val() == ""){
                                                            $("#time1").val(new Date().format("yyyy-MM-dd"));
                                                        }
                                                        if($("#time2").val() == ""){
                                                            $("#time2").val(new Date().format("yyyy-MM-dd"));
                                                        }
                                                    });
                                                </script>

                                        </form>
                                    </div>
                                    <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                        <tr>
                                            <th>操作</th>
                                            <th>任务号</th>
                                            <th>报告编号</th>
                                            <th>执行状态</th>
                                           <%-- <th>子任务状态</th>--%>
                                            <th>报告完成时间</th>
                                            <%--<th>子任务更新时间</th>--%>
                                            <th>工人</th>
                                            <th>终端</th>
                                            <%--<th>操作状态</th>--%>
                                            <th>有无异常项</th>
                                            <th>责任人</th>
                                            <th>数据提交时间</th>
                                            <th>复核状态</th>
                                        </tr>
                                        <c:forEach items="${pageBean.list}" var="report">
                                            <tr>
                                                <td>
                                                    <shiro:hasPermission name="del:taskreport">
                                                        <%--<c:if test="${report.id==null}">--%>
                                                            <a href="javascript:;" onclick="deltaskreport('${report.temp.id}','${report.id==null?'':report.id}')">
                                                            <i class="glyphicon glyphicon-trash"></i></a>
                                                        <%--</c:if>--%>
                                                        <%--<c:if test="${report.id!=null}">--%>
                                                            <%--<a href="javascript:;" onclick="deltaskreport('${report.temp.id}',${report.id})">--%>
                                                                <%--<i class="glyphicon glyphicon-trash"></i></a>--%>
                                                        <%--</c:if>--%>

                                                    </shiro:hasPermission>
                                                    <c:if test="${report.id==null}"></c:if>
                                                    <c:if test="${report.id!=null}">
                                                        <a href="querytaskreportdetail?page=${pageBean.currentPage}&id=${report.id}&type=${type}&type2=1">
                                                            <i class="glyphicon glyphicon-info-sign blue "></i></a>
                                                    </c:if>

                                                </td>
                                                <td>
                                                    <c:if test="${report.id==null}">${fn:substring(report.temp.taskcode,0,report.temp.taskcode.indexOf('-'))}</c:if>
                                                    <c:if test="${report.id!=null}">
                                                        <a href="reportcount2?taskid=${report.taskid}&page=${pageBean.currentPage}&donetime=<sdf:formatDate value="${report.temp.executetime}" pattern="yyyy-MM-dd"></sdf:formatDate>&type=${type}&taskname=${fn:substring(report.taskcode,0,report.taskcode.indexOf('-'))}">
                                                                ${fn:substring(report.temp.taskcode,0,report.temp.taskcode.indexOf('-'))}
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${report.id==null}">${report.temp.taskcode}</c:if>
                                                    <c:if test="${report.id!=null}">
                                                        <a href="querytaskreportdetail?id=${report.id}&page=${pageBean.currentPage}&type=${type}&type2=1">${report.temp.taskcode}</a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${report.temp.state ==0}">未执行
                                                        <%--<c:if test="${report.temp.operationstate==0}">未执行</c:if>--%>
                                                        <%--<c:if test="${report.temp.operationstate==1}">未执行(已漏检)</c:if>--%>
                                                        <%--<c:if test="${report.temp.operationstate==2}">未执行(已跳检)</c:if>--%>
                                                        <%--<c:if test="${report.temp.operationstate==3}">未执行(已完成)</c:if>--%>
                                                    </c:if>
                                                    <c:if test="${report.temp.state ==1}">进行中 </c:if>
                                                    <c:if test="${report.temp.state ==2}">
                                                        <c:if test="${report.temp.operationstate==1}">已漏检</c:if>
                                                        <c:if test="${report.temp.operationstate==2}">已跳检</c:if>
                                                        <c:if test="${report.temp.operationstate==3}">已完成</c:if>
                                                    </c:if>
                                                    <c:if test="${report.temp.state ==3}">
                                                        <c:if test="${report.temp.stopstate==2}">已超时</c:if>
                                                        <c:if test="${report.temp.stopstate==1}">
                                                            <a href="javascript:void(0)" onclick="showstop(${report.temp.id})">已终止</a>
                                                        </c:if>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${report.temp.state ==2}">
                                                        <c:if test="${report.temp.operationstate==1}"><sdf:formatDate value="${report.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></c:if>
                                                        <c:if test="${report.temp.operationstate==2}"><sdf:formatDate value="${report.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></c:if>
                                                        <c:if test="${report.temp.operationstate==3}"><sdf:formatDate value="${report.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></c:if>
                                                    </c:if>
                                                    <c:if test="${report.temp.state ==3}">
                                                        <c:if test="${report.temp.stopstate==2}"><sdf:formatDate value="${report.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></c:if>
                                                        <c:if test="${report.temp.stopstate==1}">
                                                            <sdf:formatDate value="${report.endtime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate>
                                                        </c:if>
                                                    </c:if>
                                                </td>
                                                <%--<td><sdf:formatDate value="${report.temp.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>--%>
                                                <td>${report.worker}</td>

                                                <td>${report.ter.customid}</td>

                                                <td>
                                                    <c:if test="${report.reportstate ==0}">无</c:if>
                                                    <c:if test="${report.reportstate ==1}">有</c:if>
                                                </td>
                                                <td>${report.temp.user.realname}</td>
                                                <td><sdf:formatDate value="${report.temp.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"></sdf:formatDate></td>
                                                <td>
                                                    <c:if test="${report.examstate ==0}">待复核</c:if>
                                                    <c:if test="${report.examstate ==1}">${report.examuser}</c:if>
                                                    <c:if test="${report.examstate ==2}">自动复核</c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <div style="height: 50px;width: 500px;text-align: center;margin-left: 300px;">
                                        <a href="showallreport1?page=1&tasktype=${type}&taskCcode=${taskCcode}&reportSstate=${reportSstate}&time1=${time1}&time2=${time2}&operationstate=${operationstate}&siteid=${siteid}&searchtype=${searchtype}">第一页</a>
                                        <c:if test="${pageBean.currentPage>1}">
                                            <a href="showallreport1?page=${pageBean.currentPage-1}&tasktype=${type}&taskCcode=${taskCcode}&reportSstate=${reportSstate}&time1=${time1}&time2=${time2}&operationstate=${operationstate}&siteid=${siteid}&searchtype=${searchtype}">上一页</a>
                                        </c:if>

                                        <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                                            <a href="showallreport1?page=${pageBean.currentPage+1}&tasktype=${type}&taskCcode=${taskCcode}&reportSstate=${reportSstate}&time1=${time1}&time2=${time2}&operationstate=${operationstate}&siteid=${siteid}&searchtype=${searchtype}">下一页</a>
                                        </c:if>

                                        <a href="showallreport1?page=${pageBean.totalPage}&tasktype=${type}&taskCcode=${taskCcode}&reportSstate=${reportSstate}&time1=${time1}&time2=${time2}&operationstate=${operationstate}&siteid=${siteid}&searchtype=${searchtype}">最后一页</a>

                                        第${pageBean.currentPage}页/共${pageBean.totalPage}页
                                    </div>
                                </div>
                            </div>
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
                    <h3>显示异常</h3>
                </div>
                <div class="modal-body">
                    <div id="showstop"style="height: 30%;">

                    </div>
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
