// 获取session中的uid
var uid

$(document).ready(function () {
    getUser()
    console.log(uid)
})

// 点击事件-搜索
$("#search-btn").click(function () {
    const content = $("#search-content").val()
    sessionStorage.setItem("content", content)
    window.location.href = "/zhifou/search"
})

// 点击事件-个人头像
$("#img-user-head").click(function () {
    // a标签方式跳转
    // $(this).parent().attr("href", host.concat("/zhifou/people/").concat(uid))
    // window.location.href = host.concat("/zhifou/people/").concat(uid)
    window.location.href = "/zhifou/people/".concat(uid)
})

function getUser() {
    $.ajax({
        url: "/getUser",
        type: "post",
        async: false,
        dataType: "json",
        success: function (user) {
            console.log("1当前登陆用户", user)
            uid = user.uid
        }
    })
}


