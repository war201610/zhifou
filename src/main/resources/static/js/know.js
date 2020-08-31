// 获取session中的uid
var uid

// 获取提问标签
function getTag() {
    const allTag = document.getElementsByName("gridCheck1")
    var i = 0;
    var tagArray
    for (var j=0; j<allTag.length; j++) {
        if(allTag[j].checked) {
            tagArray[i++] = allTag[j].value
        }
    }
    return tagArray
}

// 点击事件-提交问题
$("#btn-confirm-put").click(function () {
    const content = $("#exampleFormControlTextarea1").val()
    const introduction = $("#exampleFormControlTextarea2").val()
    var tagArray = getTag()
    const ask = {
        "uid": uid,
        "content": content,
        "introduction": introduction,
        "tag": tagArray
    }
    $.post("/zhifou/ask", ask, function (result) {
        console.log("发出提问请求：", result)
    })
})


$(document).ready(function () {
    console.log($(".session-user").val())
    uid = $(".session-user").val()
    console.log("给uid赋值know:", $(".session-user").val())
    // console.log(localStorage.getItem("u"));
})