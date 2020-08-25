function getQuestion() {
    /* zhifou/question/问题id/info */
    const pathName = window.location.pathname.concat("/info")
    $.get(pathName, function (question) {
        /* todo 在各组件中打印问题信息 */
        // 标签
        for (i=0; i<question.tag.length; i++) {
            $("tag").html("<button type=\"button\" class=\"btn btn-outline-success btn-label\">" + question.tag[i] + "</button>")
        }
        // 问题名
        $("#content").text(question.content)
        // 问题描述
        $("#introduction").text(question.introduction)
        // 点赞数
        $("#agree_count").text(question.agree_count)
        // 浏览数（回答数）
        $("#viewCount").text(question.viewCount)
        // 收藏数
        $("#collectCount").text(question.collectCount)

        /* todo 回答列表 */
        $.get("zhifou/answer/".concat(question.answer), function (answer) {
            // $("#answer-content").text(answer.content)
            for (i=0; i<answer.length; i++) {
                // 请求用户头像、名称等信息
                $.get("zhifou/people/".concat(answer[i].uid).concat("/info"), function (user) {
                    // var qid = answer[i].qid
                    // var uid = answer[i].uid
                    // var comment = {"id": qid, "uid": uid}
                    // 请求评论数量
                    $.get("zhifou/comment/".concat(answer[i].comment).concat("/number"), function (commentNumber) {
                        $("answer-area").append("<div class=\"answer card-body\">\n" +
                            "                            <!-- 回答者头像和信息区域 -->\n" +
                            "                            <div class=\"user\">\n" +
                            "                                <!-- 头像 -->\n" +
                            "                                <div class=\"user-head\"><img src=\"../../img/icons8-technical-support-38.png\" alt=\"\"></div>\n" +
                            "                                <!-- 个人id和简介 -->\n" +
                            "                                <div class=\"user-info\">"+ user.name + "<span class=\"user-intro\">"+ user.introduction +"</span></div>\n" +
                            "                            </div>\n" +
                            "                            <!-- 回答内容信息 -->\n" +
                            "                            <div class=\"answer-info\">\n" +
                            "                                <pre>" + answer[i].content +
                            "                                </pre>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"container-footer2\">\n" +
                            "                                <button type=\"button\" class=\"btn btn-outline-primary agree2\"><img src=\"../../img/icons8-smiling-face-with-heart-17.png\" alt=\"\">赞同 <span>"+ answer[i].agree +"</span></button>\n" +
                            "                                <div class=\"footer-comment2\"><img src=\"../../img/icons8-topic-30.png\" alt=\"\"><span>"+ commentNumber +" </span>&nbsp;条评论</div>\n" +
                            "                                <div class=\"footer-star2\"><img src=\"../../img/icons8-star-25.png\" alt=\"\" class=\"footer-icon2\"><span>"+ answer[i].collection +"</span>&nbsp;收藏</div>\n" +
                            "                            </div>\n" +
                            "                        </div>")
                    })
                })
            }
        })
    })
}


/* 页面dom加载完成后执行 */
$(document).ready(function () {

})