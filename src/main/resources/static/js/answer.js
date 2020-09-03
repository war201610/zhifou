uid = parseInt(sessionStorage.getItem("uuid"))

// 保存当前页面的问题id
var qid

// 当前问题页提问者id
var suid

// 回答id，非回答者id
var towho

// 问题的评论表名
var answers

// 问题的评论表名
var commentValue

// 关注后显示已关注
function toggleCare(element, wether) {
    element.text(wether)
}

// 打印 问题 和 提问者 信息
function showQuestion(questionMap) {
    // console.log(questionMap)
    const question = questionMap.question
    // var d = question.uid
    // sessionStorage.setItem("suid", d)
    var tagArr = question.tag.split("#")
    $("#tag").empty()
    for (i=1; i<tagArr.length; i++) {
        $("#tag").append("<button type=\"button\" class=\"btn btn-outline-success btn-label\">" + tagArr[i] + "</button>")
    }
    // 问题名
    $("#content").text(question.content)
    // 问题描述
    $("#introduction").text(question.introduction)
    // 点赞数
    $("#agree_count").text(question.agreeCount)
    // 浏览数（回答数）
    $("#viewCount").text(questionMap.answerCount)
    // 收藏数
    $("#collectCount").text(question.collectCount)
    // 隐藏的问题的评论表名
    $("#hidden-commentValue").val(question.comment)

    // 提问者
    $.ajax({
        url: "/zhifou/people/".concat(question.uid).concat("/info"),
        async: false,
        success: function (user) {
            $(".author-name").text(user.nickname)
            $("#hide-qid").val(user.uid)
            $("#introduction-id").text(user.introduction)
            $("#career-id").text(user.career)
            $("#author-href").attr("href", "/zhifou/people/".concat(user.uid))
            // $(".card-author").empty()
            // var html = "<div class=\"author-area\">\n" +
            //     "                                <div class=\"author-head\"><a href=\""+ "/zhifou/people/".concat(question.uid) +"\"><img src=\"/img/icons8-online-support-38.png\" alt=\"\"></a></div>\n" +
            //     "                                <div class=\"author-title\"><h6 class=\"author-name\">"+ user.nickname +"</h6></div>\n" +
            //     "                                <div class=\"author-follow\" id=\"author-follow-id\">\n" +
            //     "                                    <input type=\"hidden\" value=\""+ question.uid +"\">"+
            //     "                                    <button type=\"button\" class=\"btn btn-outline-info btn-sm\" id=\"follow-author\">关注Ta</button>\n" +
            //     "                                </div>\n" +
            //     "                            </div>\n" +
            //     "                            <div class=\"author-info\">\n" +
            //     "                                <div class=\"author-introduction\">介绍：<span>"+ user.introduction +"</span></div>\n" +
            //     "                                <div class=\"industry\">职业：<span>"+ user.career +"</span></div>\n" +
            //     "                            </div>"
            // $(".card-author").append(html)        }
    }})
}

// 回答页
function getAnswer() {
    const pathName = window.location.pathname
    $.ajax({
        url: pathName.concat("/info"),
        async: false,
        success: function (resultMap) {
            const question = resultMap.question
            const answer = resultMap.list
            qid = question.id
<<<<<<< HEAD
            // sessionStorage.setItem("suid", d)
            console.log(resultMap)
            showQuestion(resultMap)
=======

            console.log(resultMap)
            showQuestion(resultMap)

>>>>>>> zsw2
            showAnswer(answer)
        }
    })
}

// 显示回答列表
function showAnswer(answer) {
    var html = ""
    for (i = 0; i < answer.length; i++) {
        // 请求用户头像、名称等信息
        // 此处前一个请求的响应数据无法在下一个请求的回调函数中使用
        answers = answer[i]
        $.ajax({
            url: "/zhifou/people/".concat(answer[i].uid).concat("/info"),
            async: false,
            success: function (user) {
                $.ajax({
                    url: "/zhifou/comment/".concat(answers.comment).concat("/number"),
                    async: false,
                    success: function (commentNumber) {
                        html = "<div class=\"card shadow-sm\">\n" +
                            "                           <div class='answer card-body'>\n" +
                            "                            <!-- 回答者头像和信息区域 -->\n" +
                            "                            <div class=\"user\">\n" +
                            "                                <!-- 头像 -->\n" +
                            "                                <div class=\"user-head\"><img src=\"/img/icons8-technical-support-38.png\" alt=\"\"></div>\n" +
                            "                                <!-- 个人id和简介 -->\n" +
                            "                                <div class=\"user-info\">" + user.name + "<span class=\"user-intro\">" + user.introduction + "</span></div>\n" +
                            "                            </div>\n" +
                            "                            <!-- 回答内容信息 -->\n" +
                            "                            <div class=\"answer-info\">\n" +
                            "                                <pre>" + answers.content +
                            "                                </pre>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"container-footer2\" id=\"container-footer3\">\n" +
                            "                                <input type='hidden' value=\"" + answers.id + "\">\n" +
                            "                                <button type=\"button\" class=\"btn btn-outline-primary agree2\" id=\"btn-agree-answer\"'><img src=\"/img/icons8-smiling-face-with-heart-17.png\" alt=\"\">赞同 <span id=\"agree-answer\">" + answer[i].agree + "</span></button>\n" +
                            "                                <input type='hidden' value=\"" + answers.comment + "\">\n" +
                            "                                <div class='footer-comment2' id='footer-comment3' data-toggle=\"modal\" data-target=\"#comment-modal\"><img src=\"/img/icons8-topic-30.png\" alt=\"\"><span>" + commentNumber + " </span>&nbsp;条评论</div>\n" +
                            "                                <input type='hidden' value=\"" + user.uid + "\">\n" +
                            "                                <div class=\"footer-star2\" id=\"footer-star3\"><img src=\"/img/icons8-star-25.png\" alt=\"\" class=\"footer-icon2\"><span id=\"collect-id\">" + answers.collection + "</span>&nbsp;收藏</div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                       </div>"
                        $("#answer-area").append(html)
                    }
                })
            }
        })
    }
}



// 问题页
// function getQuestion() {
//     /* /zhifou/question/问题id/info */
//     const pathName = window.location.pathname
//     $.get(pathName.concat("/info"), function (question) {
//         qid = question.id
//         /* todo 在各组件中打印问题信息 */
//         // 问题和标签
//         showQuestion(question)
//
//         /* todo 回答列表 */
//         $("answer-area").empty()
//
//         $.get("/zhifou/answer/get/".concat(question.answer), function (answer) {
//             // $("#answer-content").text(answer.content)
//             for (i=0; i<answer.length; i++) {
//                 // 请求用户头像、名称等信息
//                 // 此处前一个请求的响应数据无法在下一个请求的回调函数中使用
//                 answers = answer[i]
//                 console.log(answers.comment);
//                 $.get("/zhifou/people/".concat(answer[i].uid).concat("/info"), function (user) {
//                     // var qid = answer[i].qid
//                     // var uid = answer[i].uid
//                     // var comment = {"id": qid, "uid": uid}
//                     // 请求评论数量
//                     console.log(answers.comment);
//                     $.get("/zhifou/comment/".concat(answers.comment).concat("/number"), function (commentNumber) {
//                         $("answer-area").append("<div class=\"answer card-body\">\n" +
//                             "                            <!-- 回答者头像和信息区域 -->\n" +
//                             "                            <div class=\"user\">\n" +
//                             "                                <!-- 头像 -->\n" +
//                             "                                <div class=\"user-head\"><img src=\"/img/icons8-technical-support-38.png\" alt=\"\"></div>\n" +
//                             "                                <!-- 个人id和简介 -->\n" +
//                             "                                <div class=\"user-info\">"+ user.name + "<span class=\"user-intro\">"+ user.introduction +"</span></div>\n" +
//                             "                            </div>\n" +
//                             "                            <!-- 回答内容信息 -->\n" +
//                             "                            <div class=\"answer-info\">\n" +
//                             "                                <pre>" + answers.content +
//                             "                                </pre>\n" +
//                             "                            </div>\n" +
//                             "                            <div class=\"container-footer2\">\n" +
//                             "                                <input type=\"hidden\" value=\""+ answers.id +"\">\n" +
//                             "                                <button type=\"button\" class=\"btn btn-outline-primary agree2\" id=\"btn-agree-answer\"'><img src=\"/img/icons8-smiling-face-with-heart-17.png\" alt=\"\">赞同 <span>"+ answer[i].agree +"</span></button>\n" +
//                             "                                <input type=\"hidden\" value=\""+ answers.comment +"\">\n" +
//                             "                                <div class=\"footer-comment2\"><img src=\"/img/icons8-topic-30.png\" alt=\"\"><span>"+ commentNumber +" </span>&nbsp;条评论</div>\n" +
//                             "                                <input type=\"hiden\" value=\""+ user.uid +"\">\n" +
//                             "                                <div class=\"footer-star2\"><img src=\"/img/icons8-star-25.png\" alt=\"\" class=\"footer-icon2\"><span>"+ answers.collection +"</span>&nbsp;收藏</div>\n" +
//                             "                            </div>\n" +
//                             "                        </div>")
//                     })
//                 })
//             }
//         })
//     })
// }


function getQuestion() {
    /* /zhifou/question/问题id/info */
    const pathName = window.location.pathname
    $.ajax({
        url: pathName.concat("/info"),
        async: false,
        success: function (questionMap) {
            const question = questionMap.question
            console.log("问题信息获取请求:", question);
            qid = question.id
            /* todo 在各组件中打印问题信息 */
            // 问题和标签
            showQuestion(questionMap)

            /* todo 回答列表 */
            $("#answer-area").empty()
            $.ajax({
                url: "/zhifou/answer/get/".concat(question.answer),
                async: false,
                success: function (answer) {
                    showAnswer(answer)
                    // var html = ""
                    // for (i = 0; i < answer.length; i++) {
                    //     // 请求用户头像、名称等信息
                    //     // 此处前一个请求的响应数据无法在下一个请求的回调函数中使用
                    //     answers = answer[i]
                    //     $.ajax({
                    //         url: "/zhifou/people/".concat(answer[i].uid).concat("/info"),
                    //         async: false,
                    //         success: function (user) {
                    //             $.ajax({
                    //                 url: "/zhifou/comment/".concat(answers.comment).concat("/number"),
                    //                 async: false,
                    //                 success: function (commentNumber) {
                    //                     html = "<div class=\"card shadow-sm\">\n" +
                    //                         "                           <div class='answer card-body'>\n" +
                    //                         "                            <!-- 回答者头像和信息区域 -->\n" +
                    //                         "                            <div class=\"user\">\n" +
                    //                         "                                <!-- 头像 -->\n" +
                    //                         "                                <div class=\"user-head\"><img src=\"/img/icons8-technical-support-38.png\" alt=\"\"></div>\n" +
                    //                         "                                <!-- 个人id和简介 -->\n" +
                    //                         "                                <div class=\"user-info\">" + user.name + "<span class=\"user-intro\">" + user.introduction + "</span></div>\n" +
                    //                         "                            </div>\n" +
                    //                         "                            <!-- 回答内容信息 -->\n" +
                    //                         "                            <div class=\"answer-info\">\n" +
                    //                         "                                <pre>" + answers.content +
                    //                         "                                </pre>\n" +
                    //                         "                            </div>\n" +
                    //                         "                            <div class=\"container-footer2\">\n" +
                    //                         "                                <input type='hidden' value=\"" + answers.id + "\">\n" +
                    //                         "                                <button type=\"button\" class=\"btn btn-outline-primary agree2\" id=\"btn-agree-answer\"'><img src=\"/img/icons8-smiling-face-with-heart-17.png\" alt=\"\">赞同 <span>" + answer[i].agree + "</span></button>\n" +
                    //                         "                                <input type='hidden' value=\"" + answers.comment + "\">\n" +
                    //                         "                                <div class='footer-comment2' id='footer-comment3' data-toggle=\"modal\" data-target=\"#comment-modal\"><img src=\"/img/icons8-topic-30.png\" alt=\"\"><span>" + commentNumber + " </span>&nbsp;条评论</div>\n" +
                    //                         "                                <input type='hidden' value=\"" + user.uid + "\">\n" +
                    //                         "                                <div class=\"footer-star2\"><img src=\"/img/icons8-star-25.png\" alt=\"\" class=\"footer-icon2\"><span>" + answers.collection + "</span>&nbsp;收藏</div>\n" +
                    //                         "                            </div>\n" +
                    //                         "                        </div>\n" +
                    //                         "                       </div>"
                    //                     $("#answer-area").append(html)
                    //                 }
                    //             })
                    //         }
                    //     })
                    // }
                }
            })
        }
    })
}

// 根据 评论表名 和 打印区域 打印评论
function showComment(commentValue, allComment) {
    const comment = {"comment": commentValue}
    $.ajax({
        type: "post",
        url: "/zhifou/comment",
        data: comment,
        async: false,
        success: function (commentList) {
            $(".all-comment").empty()
            var html = ""
            for (i=0; i<commentList.length; i++) {
                $.ajax({
                    url: "/zhifou/people/".concat(commentList[i].uid).concat("/info"),
                    async: false,
                    success: function (user) {
                        console.log("commentList:", commentList[i])
                        html = "<ul>\n" +
                            "                            <li><div class=\"comment-item\">\n" +
                            "                                <div class=\"comment-area\">\n" +
                            "                                    <!-- 评论者头像、账户、时间 -->\n" +
                            "                                    <div class=\"comment-info\">\n" +
                            "                                        <div class=\"comment-head\">\n" +
                            "                                            <img src=\"/img/icons8-technical-support-38.png\" width=\"25\" height=\"25\">\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"comment-name\">"+ user.name +"</div>\n" +
                            "                                        <div class=\"comment-time\">"+ commentList[i].createDate +"</div>\n" +
                            "                                    </div>\n" +
                            "                                    <!-- 评论的内容 -->\n" +
                            "                                    <div class=\"comment-container\">\n" +
                            "                                        <div class=\"comment-text\">"+ commentList[i].content +"</div>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div></li>\n" +
                            "                        </ul>"
                        allComment.append(html)
                    }
                })
            }
        }
    })
}

// function showComment(commentValue, allComment) {
//     const comment = { "comment": commentValue }
//     $.post("/zhifou/comment", comment, function (commentList) {
//         $(".all-comment").empty()
//         var html = ""
//         for (i=0; i<commentList.length; i++) {
//             $.get("/zhifou/people/".concat(commentList[i].uid).concat("/info"), function (user) {
//                 html = "<ul>\n" +
//                     "                            <li><div class=\"comment-item\">\n" +
//                     "                                <div class=\"comment-area\">\n" +
//                     "                                    <!-- 评论者头像、账户、时间 -->\n" +
//                     "                                    <div class=\"comment-info\">\n" +
//                     "                                        <div class=\"comment-head\">\n" +
//                     "                                            <img src=\"/img/icons8-technical-support-38.png\" width=\"25\" height=\"25\">\n" +
//                     "                                        </div>\n" +
//                     "                                        <div class=\"comment-name\">"+ user.name +"</div>\n" +
//                     "                                        <div class=\"comment-time\">"+ commentList[i].createDate +"</div>\n" +
//                     "                                    </div>\n" +
//                     "                                    <!-- 评论的内容 -->\n" +
//                     "                                    <div class=\"comment-container\">\n" +
//                     "                                        <div class=\"comment-text\">"+ commentList[i].content +"</div>\n" +
//                     "                                    </div>\n" +
//                     "                                </div>\n" +
//                     "                            </div></li>\n" +
//                     "                        </ul>"
//                 allComment.append(html)
//             })
//         }
//     })
// }

/* 点击事件-获取回答的评论内容 */
// $("#footer-comment3").click(function () {
//     towho = $(this).next().val()
//     console.log("towho:", towho);
//     // 获取评论表名
//     const commentValue = $(this).prev().val()
//     showComment(commentValue, $(".all-comment"))
// })

// 点击事件-对回答的评论 为未来的元素设置点击事件，selector必须为已有。
$("#answer-area").delegate("#footer-comment3", "click", function () {
    // 回答id
    towho = $(this).prev().prev().prev().val()
    // 获取评论表名
    commentValue = $(this).prev().val()
    showComment(commentValue, $(".all-comment"))
})

// 点击关注作者
$(".author-follow").delegate("#follow-author", "click", function () {
    const suid = $(this).prev().val()
    if($("#follow-author").text()==="关注Ta") {
        $.get("/zhifou/people/".concat(uid).concat("/followers/").concat(suid), function (result) {
            console.log("发起了关注Ta：", result)
            toggleCare($("#follow-author"), "已关注")
        })
    }else {
        $.ajax({
            url: "/zhifou/people/".concat(uid).concat("/following/").concat(suid),
            async: false,
            success: function (result) {
                console.log("发起了取消关注：", result)
                toggleCare($("#follow-author"), "关注Ta")
            }
        })
    }
})

// 进入问题页判断是否关注过作者
function wetherFollowAuthor(suid) {
    if (suid !== uid) {
        console.log("suid!==uid")
        // 去除编辑按钮
        // 判断是否已关注
        $.ajax({
            url: "/zhifou/follow/wether",
            async: false,
            type: "post",
            data: { "uid": uid,
                "suid": suid },
            success: function (result) {
                if (result===true) {
                    toggleCare($("#follow-author"), "已关注")
                }
            }
        })
    }
}

// 点击事件-获取问题的评论内容
$("#question-comment").click(function () {
    // towho = $(this).next().val()
    commentValue = $(this).prev().val()
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
    $.post("/zhifou/comment/answer/".concat(qid).concat("/").concat(towho).concat("/add"), comment, function (result) {
        console.log("发起了对回答的评论：", result)
        getQuestion()
        showComment(commentValue, $(".all-comment"))
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
        getQuestion()
        showComment(commentValue, $(".all-comment2"))
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
        getQuestion()
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
        var agree = $("#agree_count").text()
        $("#agree_count").text(parseInt(agree)+1)
        // getQuestion()
    })
}

// 点击事件-好问题
$("#btn-agree-question").click(function () {
    agreeQuestion()
})

// 点击事件-给回答点赞
$("#answer-area").delegate("#btn-agree-answer", "click", function () {
    const aid = $(this).prev().val()
    const agree = {
        "kind": "answer",
        "uid": uid,
        "qid": qid,
        "aid": aid
    }
    var commentThis = $(this).children("span#agree-answer")
    $.post("/zhifou/agree", agree, function (result) {
        console.log("点赞了回答：", result)
        var agree = $(commentThis).text()
        $(commentThis).text(parseInt(agree)+1)
        console.log("this", commentThis)
        // getQuestion()
    })
})

// 判断回答页或问题页
function jump() {
    var pathName1 = window.location.pathname
    var pathName2 = pathName1.split("/")
    if (pathName2.length===4) {
        return true
    }else {
        return false
    }
}


// 点击事件-收藏
$("#answer-area").delegate("#footer-star3", "click", function () {
    const id = $(this).prev().prev().prev().prev().prev().val()
    // var result = putCollection(id, qid, uid)
    // console.log(result);
    var thisc = $(this).children("#collect-id")
    $.ajax({
        url: "/zhifou/collection/answer/put",
        async: false,
        type: "post",
        data: { "id": id,
            "qid": qid,
            "uid": uid },
        success: function (result) {
            console.log("发起了收藏请求：", result)
            // console.log(thisc)
            if (result===true) {
                var collectId = thisc.text()
                thisc.text(parseInt(collectId)+1)
            }
        }
    })

})

// // 收藏回答请求
// function putCollection(id, qid, uid) {
//     $.ajax({
//         url: "/zhifou/collection/answer/put",
//         async: false,
//         type: "post",
//         data: { "id": id,
//             "qid": qid,
//             "uid": uid },
//         success: function (result) {
//             console.log("发起了收藏请求：", result)
//
//         }
//     })
// }


/* 页面dom加载完成后执行 */
$(document).ready(function () {
    suid = parseInt(sessionStorage.getItem("suid"))
    if (jump()) {
        console.log("getQuestion")
        getQuestion()
    }else {
        console.log("getAnswer")
        getAnswer()
    }
    // getQuestion()
    wetherFollowAuthor(suid)
})
