function getQuestion() {
    /* zhifou/question/问题id/info */
    const pathName = window.location.pathname.concat("/").concat("info")
    $.get(pathName, function (question) {
        /* todo 在各组件中打印问题信息 */
        // 标签
        for (i=0; i<question.tag.length; i++) {
            $("tag").html("<button type=\"button\" class=\"btn btn-outline-success btn-label\">" + question.tag[i] + "</button>")
        }
        // 问题名
        $("content").text(question.content)
        // 问题描述
        $("introduction").text(question.introduction)
        // 点赞数
        $("agree_count").text(question.agree_count)
        // 浏览数（回答数）
        $("viewCount").text(question.viewCount)
        // 收藏数
        $("collectCount").text(question.collectCount)
    })
}


/* 页面dom加载完成后执行 */
$(document).ready(function () {

})