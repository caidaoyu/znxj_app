<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!-- 日历控件 -->
<script 
src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script
	src="${pageContext.request.contextPath}/charisma/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script
	src='${pageContext.request.contextPath}/charisma/bower_components/moment/min/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath}/charisma/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script
	src='${pageContext.request.contextPath}/charisma/js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script
	src="${pageContext.request.contextPath}/charisma/bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script
	src="${pageContext.request.contextPath}/charisma/bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script
	src="${pageContext.request.contextPath}/charisma/bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script
	src="${pageContext.request.contextPath}/charisma/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script
	src="${pageContext.request.contextPath}/charisma/js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="${pageContext.request.contextPath}/charisma/js/charisma.js"></script>

<!-- 自己写的日期格式js -->
<script src="${pageContext.request.contextPath}/charisma/js/formatdate.js"></script>


<%-- 服务器校验错误信息开始,这里不能使用$(function(){}); --%>
<script>
    window.onload = function(){
    	<s:if test="hasFieldErrors()">      
         	<s:iterator value="fieldErrors">      
             	<s:iterator value="value" status="statu">        
                 	<s:set name="index" value="#statu.index"/>  
                 	<s:set name="msg" value="(#msg==null?'':#msg)+value.get(#request.index).toString()+'\\\n'"/>    	
              </s:iterator>      
         	</s:iterator>      
        </s:if>
           
        <s:if test="#msg.length()>0">       	 
             	alert("<s:property escape="false" value="#msg"/>")                   
		</s:if>
    }
    
   
</script>
