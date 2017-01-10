/**
 * jquery.danmu.js (//github.com/chiruom/danmu/) - Licensed under the MIT license
 */


$("#danmu").danmu({
        left:0,
        top:0,
        height:470,
        width:913,
        speed:30000,
        opacity:1,
        font_size_small:16,
        font_size_big:24,
        top_botton_danmu_time:6000
    }
);

$('#danmu').danmu('danmu_start');


function send1(){
    var text = document.getElementById('text11111').value;
    var color = "white";
    var position = "0";
    var time = $('#danmu').data("nowtime")+5;
    var size ="1";
    var text_obj='{ "text":"'+text+'","color":"'+color+'","size":"'+size+'","position":"'+position+'","time":'+time+'}';
    alert(text_obj);
    var new_obj=eval('('+text_obj+')');
    $('#danmu').danmu("add_danmu",new_obj);
    document.getElementById('text11111').value='';
}

var socket;

// websocket
if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket;
}
if (window.WebSocket) {
    var wsUri = $("#wsUri").text();
    socket = new WebSocket(wsUri);
    socket.onmessage = function(event) {
        var ta = $("#chatBoard");
        ta.append("<div class='fs4 pt2 pb2 pl3 pr3 c-666'>" + event.data + "</div>" );

        // 弹幕
        var text = event.data;
        var color = "white";
        var position = "0";
        var time = $('#danmu').data("nowtime")+5;
        var size ="1";
        var text_obj='{ "text":"'+text+'","color":"'+color+'","size":"'+size+'","position":"'+position+'","time":'+time+'}';
        var new_obj=eval('('+text_obj+')');
        $('#danmu').danmu("add_danmu",new_obj);
        $("#chatBoard").scrollTop( $('#chatBoard')[0].scrollHeight );
    };
    socket.onopen = function(event) {
        var ta = $("#chatBoard");
        ta.append("<div class='fs4 pt2 pb2 pl3 pr3 c-666'>连接成功</div>" );
    };
    socket.onclose = function(event) {
        var ta = $("#chatBoard");
        ta.append("<div class='fs4 pt2 pb2 pl3 pr3 c-666'>连接被关闭</div>" );
    };
} else {
    alert("你的浏览器不支持 WebSocket！");
}

function send(message) {
    if (!window.WebSocket) {
        return;
    }
    if (socket.readyState == WebSocket.OPEN) {
        socket.send(message);
    } else {
        alert("连接没有开启.");
    }
    $("#chatBoard").scrollTop( $('#chatBoard')[0].scrollHeight );
}

