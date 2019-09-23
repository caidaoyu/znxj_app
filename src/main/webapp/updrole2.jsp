<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智能巡检系统</title>
    <%@ include file="/WEB-INF/pages/common/header.jsp"%>
    <script type="text/javascript">
        function selectallrole(obj,checkboxname){
            if($(obj).prop("checked")){
                $("input:checkbox[name='"+checkboxname+"']").each(function() {
                    $(this).prop("checked",true);
                });
            }else{
                $("input:checkbox[name='"+checkboxname+"']").each(function() {
                    $(this).prop("checked",false);
                });
            }
        }
        function isOn(id, cl) {
            if (cl == "root_menu") {
                if (document.getElementById(id).checked) {
                    $("." + id).prop("checked", true);
                } else {
                    $("." + id).removeAttr("checked");
                }
            } else {
                if (document.getElementById(id).checked) {
                    $("#" + cl).prop("checked", true);
                }
            }
        }
    </script>
    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <link href="/js/jquery-easyui-1.4/themes/default/easyui.css" rel="stylesheet" />
    <link href="/js/jquery-easyui-1.4/themes/icon.css" rel="stylesheet" />
    <script src="/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
</head>
<body>

<div class="ch-container">
    <div class="row">

        <%--<%@ include file="/WEB-INF/pages/common/menu.jsp"%>--%>
        <div id="content" class="col-lg-12 col-sm-12">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well" data-original-title="">
                            <h2>
                                <i class="glyphicon glyphicon-globe"></i> 角色--编辑
                            </h2>
                        </div>
                        <div class="box-content">
                            <form action="updroles" method="post">
                                <input type="hidden" name="page" value="${page}">
                                <input type="hidden" class="form-control" style="width: 300px;" name="roleid" required="required" value="${roles.roleid}">
                                <table class="table table-striped table-bordered table-hover bootstrap-datatable datatable responsive dataTable">
                                    <tr>
                                        <td class="form-inline">
                                            <label class="control-label" for="rolename">角色名称:</label>
                                            <input type="text" name="rolename" class="form-control" id="rolename" value="${roles.rolename}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="form-inline">
                                            <span>角色状态:</span>
                                            <input type="radio" name="rolestate" ${roles.rolestate eq "0"?'checked':''} value="0" >可用
                                            <input type="radio" name="rolestate" ${roles.rolestate eq "1"?'checked':''} value="1" >不可用
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>选择权限:<input type="checkbox" name="role" id="role" onclick="selectallrole(this,'selectedpowers')" style="width: 16px;height: 16px;"> 全选/全不选</h4><br/>
                                            <c:if test="${not empty(userPermission)}">
                                                <ul id="tt" class="easyui-tree">
                                                    <c:forEach var="powers" items="${userPermission}">
                                                        <div class="easyui-tree">
                                                            <c:if test="${powers.power.parentid eq 'root_menu'}">
                                                                <li >
                                                                    <span>${powers.power.permissionname }
                                                                        <%--<input id="${powers.persionid}" class="${powers.parentid}"--%>
                                                                               <%--name="selectedpowers" type="checkbox" onclick="isOn(this.id,'${powers.parentid}')" value="${powers.persionid }">--%>
                                                                    </span>
                                                                    <ul>

                                                                        <c:forEach var="power2" items ="${userPermission}">
                                                                            <c:if test="${powers.power.persionid eq power2.power.parentid }">
                                                                                <li style="margin-top: 15px;">
                                                                                    <c:forTokens items="${power2.power.persionid}" delims="0" var="i">
                                                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                                                    </c:forTokens>
                                                                                    <span>${power2.power.permissionname}
                                                                                        <input id="${power2.power.persionid}" class="${power2.power.parentid}"
                                                                                               name="selectedpowers" type="checkbox" style="width: 17px;height: 17px;"
                                                                                               onclick="isOn(this.id,'${power2.power.parentid}')"
                                                                                               value="${power2.power.persionid }"
                                                                                        <c:if test="${power2.hasPower}">checked</c:if>/></span>
                                                                                </li>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </ul>
                                                                </li>
                                                            </c:if>
                                                        </div>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2"><input type="submit" class="btn btn-primary" value="保存">
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
<ul id="aa" class="easyui-tree">
    <c:forEach var="powers" items="${allpermission}">
        <div class="easyui-tree">
            <c:forTokens items="${powers.persionid}" delims="0" var="i">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forTokens>
                ${powers.permissionname }
            <input id="${powers.persionid}" class="${powers.parentid}" name="selectedpowers"
                   type="checkbox" onclick="isOn(this.id,'${powers.parentid}')"
                   value="${powers.persionid }"
                    <c:forEach var="power" items="${permissionlist }">
                        <c:if test="${power.persionid eq powers.persionid }">checked="checked"
                        </c:if>
                    </c:forEach>/>
            <br />
        </div>
</c:forEach>
</ul>
</body>
</html>
