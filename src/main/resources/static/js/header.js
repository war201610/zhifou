// 获取session中的uid
var uid = parseInt(sessionStorage.getItem("uuid"))

var qid
// getUser()
$(document).ready(function () {
    // 发起请求较晚，user页无法获取uid
    // getUser()
})

// 点击事件-搜索
$(".search-btn").click(function () {
    const content = $("#search-content").val()
    sessionStorage.setItem("content", content)
    console.log("点击了搜索")
    window.location.href = "/zhifou/search"
})

// 点击事件-个人头像
$("#img-user-head").click(function () {
    // a标签方式跳转
    // $(this).parent().attr("href", host.concat("/zhifou/people/").concat(uid))
    // window.location.href = host.concat("/zhifou/people/").concat(uid)
    window.location.href = "/zhifou/people/".concat(uid)
})


// 获取提问标签
function getTag() {
    const allTag = document.getElementsByName("gridCheck1")
    var i = 0;
    var tagArray = []
    var tagStr = ""
    for (var j=0; j<allTag.length; j++) {
        if(allTag[j].checked) {
            tagArray[i++] = allTag[j].value
        }
    }
    tagStr = tagArray.join(",")
    return tagStr
}

// 点击事件-提交问题
$("#btn-confirm-put").click(function () {
    const content = $("#exampleFormControlTextarea1").val()
    const introduction = $("#exampleFormControlTextarea2").val()
    var tagStr = getTag()
    const ask = {
        "uid": uid,
        "content": content,
        "introduction": introduction,
        "tag": tagStr
    }
    $.post("/zhifou/ask", ask, function (result) {
        console.log("发出提问请求：", result)
    })
})


