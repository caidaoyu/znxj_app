<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/6/7
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看异常详情</title>
    <style>
        body {
            text-align: center;
        }
        .selected {
            background: #35A7E7 !important;
            color: white !important;
        }
        #div {
            width: 80%;
        }

        #showerror {
            border: 1px solid #F3F3F3;
            padding: 0 200px;
        }

        .main {
            text-align: center;
            background-color: #ffffff;
            border-radius: 20px;
            /*width: 300px;*/
            /*height: 350px;*/
            position: absolute;
            left: 50%;
            top: 20%;
            transform: translate(-50%, -20%);
        }

    </style>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<script>
    $(function () {
        <%--var  cont = ${};--%>
        findbystate(${img}, ${audio}, ${video})
    });
    function findbystate(val2, val4, val5) {
        if (val2 == "[]" && val4 == "[]" && val5 == "[]") {
            alert("暂无其他异常信息");
        } else {
//            var img = new Array();
//            img = JSON.parse(val2);
//            var audio = new Array();
//            audio = JSON.parse(val4);
//            var video = new Array();
//            video = JSON.parse(val5);

            var errorul = $("#errorul");
            errorul.empty();
            if (val2 != null && val2 != undefined ) {
                for (var i = 0; i < val2.length; i++) {
                    var j = parseInt(i) + 1;
                    errorul.append("<li class='list-group-item' id='imgException"+i+"' style='float: left;width: 100px;'  onclick=showimg(this,'" + val2[i] + "')>图片" + j + "</li>");
                }
                var firstImgLi = document.getElementById("imgException0");
                showimg(firstImgLi, val2[0]);

            }
            if (val4 != null && val4 != undefined ) {
                for (var m = 0; m < val4.length; m++) {
                    var n = parseInt(m) + 1;
                    errorul.append("<li class='list-group-item' id='audioException"+m+"'   style='float: left;width: 100px;' onclick=showaudio(this,'" + val4[m] + "')>音频" + n + "</li>");
                }
                if(val2 ==null && val2 == undefined){
                    var firstAudioLi = document.getElementById("audioException0");
                    showaudio(firstAudioLi, val4[0]);
                }
            }
//            if (val5 != null && val5 != undefined && video != null) {
//                for (var a = 0; a < video.length; a++) {
//                    var b = parseInt(a) + 1;
//                    errorul.append("<li class='list-group-item'  onclick=showvideo(this,'" + video[a] + "')>视频" + b + "</li>");
//                }
//            }
            if (val5 != null && val5 != undefined ) {
                for (var a = 0; a < val5.length; a++) {
                    var b = parseInt(a) + 1;
                    errorul.append("<li class='list-group-item'  id='videoException"+a+"'  style='float: left;width: 100px;'  onclick=showvideo(this,'" + val5[a] + "')>视频" + b + "</li>");
                }
                if(val2 ==null && val2 == undefined && val4 ==null && val4 == undefined){
                    var firstVideoLi = document.getElementById("videoException0");
                    showvideo(firstVideoLi, val5[0]);
                }

            }
            $("#myModal1").modal("show");
        }

    }
    /*显示图片*/
    function showimg(obj, imgs) {
        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('li').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);

        $("#pic").empty();
        var pic = document.createElement("img");
// 服务器上路径为 /report/
        pic.src="report"+imgs;
        //本地为 /images/
//        pic.src = imgs;
        pic.width = "800";
        pic.height = "600";
        $("#pic")[0].appendChild(pic);
    }
//    /*显示文字*/
//    function showtext(obj, val) {
//        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
//        $(obj).siblings('li').prop("selected", false);
//        $(obj).addClass('selected');
//        $(obj).prop("selected", true);
//        $("#pic").empty();
//        $("#pic")[0].innerHTML = val;
//    }
    /*显示音频*/
    function showaudio(obj, audioval) {
        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('li').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);
        $("#pic").empty();
        var au = document.createElement('audio');
        au.controls = true;
        au.preload = "auto";
        au.loop = "loop";
        var source = document.createElement('source');
        au.appendChild(source);
//        source.src = audioval;
        source.src="report"+audioval;
        source.type = "audio/mpeg";
        $("#pic")[0].appendChild(au);

    }
    function showvideo(obj, videoval) {
        $(obj).siblings('li').removeClass('selected');  // 删除其他兄弟元素的样式
        $(obj).siblings('li').prop("selected", false);
        $(obj).addClass('selected');
        $(obj).prop("selected", true);
        $("#pic").empty();
        var video = document.createElement('video');
//        video.src = videoval;
        video.src = "report"+videoval;
        video.controls = true;
        video.preload = "auto";
//        video.loop = "loop";
        video.width = "800";
        video.height = "600";

        $("#pic")[0].appendChild(video);
    }
</script>
<div id="div" class="main">
    <h3 style="text-align: center">异常详情</h3>
    <div id="showerror" style="width: 100%;">
        <div style="width: 100%">
            <ul class="list-group" id="errorul" style="margin-bottom: 5px">

            </ul>
        </div>
        <div id="pic" style="border: 1px solid #fff;width: 500px; height: 650px;">

        </div>
        <%--<div class="clearfix" style="display: none;"></div>--%>
    </div>
</div>


</body>
</html>
