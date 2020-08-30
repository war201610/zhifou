// 获取session中的uid
var uid

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

$(document).ready(function () {
    uid = $(".session-user").val()
})