//*******************************************
//2017年2月24日 0:05 由 向立凯 结束编写
//初步功能实现，仅做参考使用
//生产中使用需要继续完善，无js注入防御
//*******************************************


var IMG_LENGTH = 1;//图片最大1MB
var IMG_MAXCOUNT = 5;//最多选中图片张数

var map = {};
var UP_IMGCOUNT = 0;//上传图片张数记录
var pic;
if($("#attachment").val()!=""){
    pic=JSON.parse($("#attachment").val());
}else{
    pic=new Array();
}
//打开文件选择对话框
$("#addfile").click(function () {
    if ($(".imgfile").length >= IMG_MAXCOUNT) {
        alert("一次最多上传" + IMG_MAXCOUNT + "张图片");
        return;
    }
    var _CRE_FILE = document.createElement("input");
    if ($(".imgfile").length <= 5) {//个数不足则新创建对象
        _CRE_FILE.setAttribute("type", "file");
        _CRE_FILE.setAttribute("class", "imgfile");
        _CRE_FILE.setAttribute("style", "display:none");
        _CRE_FILE.setAttribute("id", "addfile"+UP_IMGCOUNT);
        _CRE_FILE.setAttribute("name", "photo"+UP_IMGCOUNT);
        _CRE_FILE.setAttribute("accept", ".png,.jpg,.jpeg,.pdf,.docx");
        _CRE_FILE.setAttribute("num", UP_IMGCOUNT);//记录此对象对应的编号
        $("#addfile").after(_CRE_FILE);
    }
    else { //否则获取最后未使用对象
        _CRE_FILE = $(".imgfile").eq(0).get(0);
    }
    return $(_CRE_FILE).click();//打开对象选择框
});
$(".imgfile").on("change", function () {
    if ($(this).val().length > 0) {//判断是否有选中图片
        //判断图片格式是否正确
        var FORMAT = $(this).val().substr($(this).val().length - 3, 3);
        if (FORMAT != "png" && FORMAT != "jpg" && FORMAT != "peg" && FORMAT != "pdf"&& FORMAT != "docx") {
            alert("文件格式不正确！！！");
            return;
        }
        //判断图片是否过大，当前设置1MB
        var file = this.files[0];//获取file文件对象
        if (file.size > (IMG_LENGTH * 1024 * 1024)) {
            alert("图片大小不能超过" + IMG_LENGTH + "MB");
            $(this).val("");
            return;
        }
        var imgurl;
        var formData = new FormData();
        formData.append("img",file);
        // formData.append(file.name,file.name);
        $.ajax({
            url:"imgupload",
            type: "POST",
            data:formData,
            async: false,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                // pic.push(data.data);
                imgurl = data.data;
            }
        });
    }
});


