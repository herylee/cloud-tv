var socket;


    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }
    if (window.WebSocket) {
        var wsUri = $("#wsUri").text();
        socket = new WebSocket(wsUri);
        socket.onmessage = function(event) {
            var ta = $("#chatBoard");
            ta.append("<div class='fs4 pt2 pb2 pl3 pr3 c-666'>" + event.data + "</div>" );
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