// 保存当前页面的问题id
var qid

// 保存当前登陆用户的id
var uid

// 保存当前要评论的回答者id
var towho

//打印问题
function showQuestion(question) {
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
    // 隐藏的问题的评论表名
    $("#hidden-commentValue").val(question.comment)
}

// 回答页
function getAnswer() {
    const pathName = window.location.pathname
    $.get(pathName, function (resultMap) {
        const question = resultMap.question
        const answers = resultMap.List
        qid = question.id
        showQuestion(question)

    })
}


// 问题页
function getQuestion() {
    /* /zhifou/question/问题id/info */
    const pathName = window.location.pathname
    $.get(pathName.concat("/info"), function (question) {
        qid = question.id
        /* todo 在各组件中打印问题信息 */
        // 问题和标签
        showQuestion(question)

        /* todo 回答列表 */
        $("answer-area").empty()
        $.get("zhifou/answer/get/".concat(question.answer), function (answer) {
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
                            "                                <input type=\"hidden\" value=\""+ answer[i].id +"\">\n" +
                            "                                <button type=\"button\" class=\"btn btn-outline-primary agree2\" id=\"btn-agree-answer\"'><img src=\"../../img/icons8-smiling-face-with-heart-17.png\" alt=\"\">赞同 <span>"+ answer[i].agree +"</span></button>\n" +
                            "                                <input type=\"hidden\" value=\""+ answer[i].comment +"\">\n" +
                            "                                <div class=\"footer-comment2\"><img src=\"../../img/icons8-topic-30.png\" alt=\"\"><span>"+ commentNumber +" </span>&nbsp;条评论</div>\n" +
                            "                                <input type=\"hiden\" value=\""+ user.uid +"\">\n" +
                            "                                <div class=\"footer-star2\"><img src=\"../../img/icons8-star-25.png\" alt=\"\" class=\"footer-icon2\"><span>"+ answer[i].collection +"</span>&nbsp;收藏</div>\n" +
                            "                            </div>\n" +
                            "                        </div>")
                    })
                })
            }
        })
    })
}

// 根据 评论表名 和 打印区域 打印评论
function showComment(commentValue, allComment) {
    const comment = { "comment": commentValue }
    $.post("/zhifou/comment", comment, function (commentList) {
        $(".all-comment").empty()
        var html = ""
        for (i=0; i<commentList.length; i++) {
            $.get("/zhifou/people/".concat(commentList[i].uid).concat("/info"), function (user) {
                html = "<ul>\n" +
                    "                            <li><div class=\"comment-item\">\n" +
                    "                                <div class=\"comment-area\">\n" +
                    "                                    <!-- 评论者头像、账户、时间 -->\n" +
                    "                                    <div class=\"comment-info\">\n" +
                    "                                        <div class=\"comment-head\">\n" +
                    "                                            <img src=\"../../img/icons8-technical-support-38.png\" width=\"25\" height=\"25\">\n" +
                    "                                        </div>\n" +
                    "                                        <div class=\"comment-name\">"+ user.name +"</div>\n" +
                    "                                        <div class=\"comment-time\">"+ commentList[i].date +"</div>\n" +
                    "                                    </div>\n" +
                    "                                    <!-- 评论的内容 -->\n" +
                    "                                    <div class=\"comment-container\">\n" +
                    "                                        <div class=\"comment-text\">"+ commentList[i].content +"</div>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div></li>\n" +
                    "                        </ul>"
                allComment.append(html)
            })
        }
    })
}

/* 点击事件-获取回答的评论内容 */
$(".footer-comment2").click(function () {
    towho = $(this).next().val()
    // 获取评论表名
    const commentValue = $(this).prev().val()
    showComment(commentValue, $(".all-comment"))
})

// 点击事件-获取问题的评论内容
$("#question-comment").click(function () {
    // towho = $(this).next().val()
    const commentValue = $(this).prev().val()
    showComment(commentValue, $(".all-comment2"))
})

// /* 获取对应评论内容 */
// function getAnswerComment() {
//
// }

// 点击事件-发表对回答的评论
$("#btn-confirm-comment").click(function () {
    const content = $("#answer-comment-content").val()
    const comment = {
        "uid": uid,
        "content": content
    }
    $.post("/zhifou/comment/question/".concat(qid).concat("/").concat(towho).concat("/add"), comment, function (result) {
        console.log("发起了对回答的评论：", result)
    })
})

// 点击事件-发表对问题的评论
$("#btn-confirm-comment2").click(function () {
    const content = $("#answer-comment-content2").val()
    const comment = {
        "uid": uid,
        "content": content
    }
    $.post("/zhifou/comment/question/".concat(qid).concat("/add"), comment, function (result) {
        console.log("发起了对问题的评论：", result)
    })
})

// 点击事件-发表回答
$("#btn-confirm-answer").click(function () {
    const content = $("#answer-textarea").val()
    const answer = {
        "uid": uid,
        "qid": qid,
        "content": content
    }
    $.post("/zhifou/answer/put", answer, function (result) {
        console.log("发表回答请求", result)
    })
})

// 给问题点赞
function agreeQuestion() {
    const agree = {
        "kind": "question",
        "uid": uid,
        "qid": qid
    }
    $.post("/zhifou/agree", agree, function (result) {
        console.log("给问题点赞：", result)
    })
}

// 点击事件-好问题
$("#btn-agree-question").click(function () {
    agreeQuestion()
})

// 点击事件-给回答点赞
$("#btn-agree-answer").click(function () {
    const aid = $(this).prev().val()
    const agree = {
        "kind": "answer",
        "uid": uid,
        "qid": qid,
        "aid": aid
    }
    $.post("/zhifou/agree", agree, function (result) {
        console.log("点赞了评论：", result)
    })
})

/* 页面dom加载完成后执行 */
$(document).ready(function () {
    getQuestion()
    // localStorage.setItem("u", "nihao")
    // localStorage.setItem("u", "hello")
    // uid = $(".session-user").val()
})
