function stopStream(data) {
    $.get(data, function(result) {
        if(result=="1")
            alert("停止成功！");
        else
            alert("停止失败或并没有推流！");
    });
}