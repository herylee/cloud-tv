// 登录box
$(document).ready(function() {
    $("#loginButton").fancybox({});
    $("#modifyHead").fancybox({});
});


// 日期选择器
$(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
    $("#datepicker").datepicker( "option", "dateFormat", "yy-mm-dd");
});

// 登录
function login() {
    $.ajax({
        type: 'post',
        url: '/auth/login',
        dataType:'json',
        data: $('#loginForm').serialize(),
        success: function(data) {
            if(data.retCode==200) {
                location.reload(true);
            } else {
                alert(data.message);
            }
        }
    });
};

// 修改用户信息按钮
function modifyUser() {
	$("#userInfoMap").addClass("hide");
	$("#userInfoForm").removeClass("hide");
	$("#userModifyButton").addClass("hide");
}

// 提交用户更新信息
function submitUserInfo() {
	    $.ajax({
        type: 'post',
        url: window.location.href,
        dataType:'json',
        data: $('#userInfoForm').serialize(),
        success: function(data) {
            if(data.retCode==200) {
                location.reload();
            } else {
                alert(data.message);
            }
        }
    });
}

// 取消修改用户信息
function cancelModifyUser() {
	$("#userInfoMap").removeClass("hide");
	$("#userInfoForm").addClass("hide");
	$("#userModifyButton").removeClass("hide");
}

// 修改头像
function uploadImgFunc() {   
    //判断上传控件中是否选择了图片
    var image = $("#uploadImg").val();
    if ($.trim(image) == "") {
        alert("请选择图片！");
        return;
    }
    //提交请求处理的url
    var actionUrl = "/upload/img";
    //开始ajax操作
    $("#uploadImgForm").ajaxSubmit({
      
            type: "post",//提交类型
            dataType: "json",//返回结果格式
            url: actionUrl,//请求地址
            success: function (data) {//请求成功后的函数         
                if (data.retCode == 200) {//成功
                    $("#uploadImgForm").append("<img class='circle mb3' style='max-width: 130px; border: solid gainsboro;' src='" + data.urlPath + "' />");
                    $("#userImgUrlPath").attr("value", data.urlPath);
                } else {
                    alert(data.meessage);
                }
            },
            error: function (data) { alert(data.message); },//请求失败的函数
            async: true
     
    });
}

// 提交头像到用户实体
function submitUserImg() {
	    $.ajax({
        type: 'post',
        url: window.location.href,
        dataType:'json',
        data: $('#userImgForm').serialize(),
        success: function(data) {
            if(data.retCode==200) {
                location.reload();
            } else {
                alert(data.message);
            }
        }
    });
}

// 上传证件照片
function uploadProofFunc() {   
    //判断上传控件中是否选择了图片
    var image = $("#uploadProof").val();
    if ($.trim(image) == "") {
        alert("请选择图片！");
        return;
    }
    //提交请求处理的url
    var actionUrl = "/upload/img";
    //开始ajax操作
    $("#uploadProofForm").ajaxSubmit({
      
            type: "post",//提交类型
            dataType: "json",//返回结果格式
            url: actionUrl,//请求地址
            success: function (data) {//请求成功后的函数         
                if (data.retCode == 200) {//成功
                    $("#uploadProofForm").append("<img class='mb3' style='max-width: 130px; border: solid gainsboro;' src='" + data.urlPath + "' />");
                    $("#proofUrlPath").attr("value", data.urlPath);
                } else {
                    alert(data.meessage);
                }
            },
            error: function (data) { alert(data.message); },//请求失败的函数
            async: true
     
    });
}

// 提交头像到用户实体
function submitApply() {
	    $.ajax({
        type: 'post',
        url: '/auth/apply',
        dataType:'json',
        data: $('#applyForm').serialize(),
        success: function(data) {
            if(data.retCode==200) {
                location.reload();
            } else {
                alert(data.message);
            }
        }
    });
}

// 提交直播令牌申请
function submitApplyAccessToken() {
	    $.ajax({
        type: 'post',
        url: '/auth/accessToken',
        dataType:'json',
        data: $('#accessTokenForm').serialize(),
        success: function(data) {
            if(data.retCode==200) {
                $('#publishUrl').attr("value", data.publishUrl);
                $('#publishAccessToken').attr("value", data.accessToken);
            } else {
                alert(data.message);
            }
        }
    });
}

var editor; 
$(document).ready(function() {
	editor = new wangEditor('textarea1');
	 // 上传图片
    editor.config.uploadImgUrl = '/upload/roomImg';
    // 配置自定义参数
    editor.config.uploadParams = {};
    // 设置 headers
    editor.config.uploadHeaders = {
        'Accept' : 'text/x-json'
    };
    editor.create();
});

// 调出房间信息编辑
function editRoomMeta() {
    var roomName = $('#roomName').text();
    var roomDescription = $('#roomDescription').html();

    $('#roomNameEdit').attr('value', roomName);
    editor.$txt.html(roomDescription);

    $('#roomMeta').addClass('hide');
    $('#roomMetaForm').removeClass('hide');
}

// 提交房间描述
function submitRoomMeta() {
	var html = editor.$txt.html();
	$('#roomDescriptionEdit').attr("value", html);
	$.ajax({
        type: 'post',
        url: window.location.href,
        dataType:'json',
        data: $('#roomMetaForm').serialize(),
        success: function(data) {
            if(data.retCode==200) {
                window.location.reload(true);
            } else {
                alert(data.message);
            }
        }
    });
}
