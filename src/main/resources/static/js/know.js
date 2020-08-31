// 获取session中的uid
// var uid



// 获取推荐（首页）列表
function getKnowList() {
    $.get("/zhifou/know/info", function (resultList) {
        // console.log("推荐页数据：", resultList)
    })
}


$(document).ready(function () {
    // console.log("know:", uid)
    getKnowList()
})