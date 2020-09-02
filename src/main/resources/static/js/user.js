/* session中存储的uid */
// const uid = sessionStorage.getItem("user");

// 获取主机地址和端口号 如localhost:8080
// const host = window.location.host

/* 当前url 即/zhifou/people/uid */
const pathName = window.location.pathname

// 字符串分隔获取uid
// const uid = pathName.substring(15)
uid = parseInt(sessionStorage.getItem("uuid"))
// 从url中获取登陆用户的uid
var suid = parseInt(pathName.substring(15))

/* 个人信息请求，加载页面 */
function getUserInfo() {
    $.get(pathName.concat("/info"), function (user) {
        console.log(user);
        console.log("向", pathName.concat("/info"), "发起了get请求")
        /* todo 访问主页，打印从后台获取的用户信息 */
        $("#name").text(user.name)
        var gender;
        if (user.gender===0) {
            gender = "女"
        }else {
            gender = "男"
        }
        $("#gender").text(gender)
        $("#introduction").text(user.introduction)
        $("#career").text(user.career)
        $("#industry").text(user.industry)
        $("#email").text(user.email)
        $("#registerDate").text(user.registerDate)
    })
}

/* 获取现有的个人信息到修改板块 */
function getEditUser() {
    $("#exampleFormControlSelect1").val($("#gender").text());
    $("#exampleFormControlTextarea1").val($("#introduction").text());
    $("#exampleFormControlInput1").val($("#career").text());
    $("#exampleFormControlSelect2").val($("#industry").text());
    $("#exampleFormControlInput3").val($("#email").text());
}

/* 点击事件-修改 */
$("#btn-edit-user").click(function () {
    getEditUser()
});

/* 个人信息修改请求 */
function editUser(){
    // console.log($("#exampleFormControlSelect1").val()); // 测试select dom val
    var gender;
    if ($("#exampleFormControlSelect1").val()==="男") {
        gender = 1
    }else {
        gender = 0
    }
    const user = {
        "uid": uid,
        "gender": gender,
        "introduction": $("#exampleFormControlTextarea1").val(),
        "career": $("#exampleFormControlInput1").val(),
        "industry": $("#exampleFormControlSelect2").val(),
        "email": $("#exampleFormControlInput3").val()
    };
    $.post(pathName.concat("/edit"), user, function (result) {
        /* todo 重新获取个人信息，加载页面 */
        getUser();
        // console.log(result)
        console.log("向", pathName.concat("/edit"), "发起了post请求")
    })
}

/* 点击事件-保存 */
$("#btn-confirm-edit").click(function () {
    editUser()
});

/* 获取收藏数据 */
function getCollections() {
    $.get(pathName.concat("/collections"), function (collectionList) {
        console.log(collectionList)
        $(".q1").empty()
        var html = ""
        // 收藏的回答
        const answers = collectionList.answers
        for (i=0; i<answers.length; i++) {
            html = printAnswerList(answers[i].questionId, answers[i].id, answers[i].content)
            $(".q1").append(html)
        }
        // 收藏的问题
        // 收藏的文章
    })
}

/* 点击事件-收藏 */
$("#btn1").click(function () {
    if ($("#btn1").attr("aria-expanded")==="false") {
        getCollections()
    }
})

/* 获取提出的问题数据 */
function getAsks() {
    $.get(pathName.concat("/asks"), function (asksList) {
        console.log(asksList);
        $(".q2").empty()
        var html = ""
        for (i=0; i<asksList.length; i++) {
            html = printQuestionsList(asksList[i].id, asksList[i].content)
            $(".q2").append(html)
        }
    })
}

/* 点击事件-提出的问题 */
$("#btn2").click(function () {
    if ($("#btn2").attr("aria-expanded")==="false") {
        getAsks()
    }
})

/* 获取回答的问题数据 */
function getAnswers() {
    $.get(pathName.concat("/answers"), function (answersList) {
        console.log(answersList);
        $(".q3").empty()
        var html = ""
        for (i=0; i<answersList.length; i++) {
            html = printQuestionsList(answersList[i].id, answersList[i].content)
            $(".q3").append(html)
        }
    })
}

/* 点击事件-回答的问题 */
$("#btn3").click(function () {
    if ($("#btn3").attr("aria-expanded")==="false") {
        getAnswers()
    }
})

/* 获取关注的问题数据 */
function getQuestions() {
    $.get(pathName.concat("/questions"), function (questionsList) {
        console.log(questionsList);
        $(".q4").empty()
        var html = ""
        for (i=0; i<questionsList.length; i++) {
            html = printQuestionsList(questionsList[i].id, questionsList[i].content)
            $(".q4").append(html)
        }
    })
}

/* 点击事件-关注的问题 */
$("#btn4").click(function () {
    if ($("#btn4").attr("aria-expanded")==="false") {
        getQuestions()
    }
})

/* 获取关注的用户数据 */
function getFollowing() {
    $.get(pathName.concat("/following"), function (followingList) {
        console.log(followingList);
        $("#follow-card").empty()
        var html = ""
        for (i=0; i<followingList.length; i++) {
            // <input type=\"text\" value=\""+ uid +"\">
            html = "<div class=\"follow-container\">\n" +
                "                                        <ul><li>\n" +
                "                                            <div class=\"fiveBox\">\n" +
                "                                                <div class=\"follow-head\"><img src=\"../../img/icons8-online-support-38.png\" alt=\"\"></div>\n" +
                "                                                <a href=\""+ "/zhifou/people/".concat(followingList[i].uid) +"\">\n" +
                "                                                <div class=\"follow-info-area\">\n" +
                "                                                    <div class=\"follow-info\">\n" +
                "                                                        <div class=\"follow-name\">"+ followingList[i].name +"</div>\n" +
                "                                                        <div class=\"follow-intro\">"+ followingList[i].introduction +"</div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                                </a>\n" +
                "                                                <input type='hidden' value=\""+ followingList[i].uid +"\">\n" +
                "                                                <div class=\"follow-btn\"><button type=\"button\" class=\"btn btn-info cancel-btn\">取消关注</button></div>\n" +
                "                                            </div>\n" +
                "                                        </li></ul>\n" +
                "                                    </div>"
            $("#follow-card").append(html)
        }
    })
}
/* 点击事件-关注的用户 */
$("#btn5").click(function () {
    // console.log($("#btn5").attr("aria-expanded"));
    if ($("#btn5").attr("aria-expanded")==="false") {
        getFollowing()
    }
})
/* 获取粉丝数据 */
function getFollowers() {
    $.get(pathName.concat("/followers"), function (followersList) {
        console.log(followersList);
        $(".followed-card").empty()
        var html = ""
        for (i=0; i<followersList.length; i++) {
            html = "<div class=\"follow-container\">\n" +
                "                                        <ul><li>\n" +
                "                                            <div class=\"fiveBox\">\n" +
                "                                                <div class=\"follow-head\"><img src=\"../../img/icons8-online-support-38.png\" alt=\"\"></div>\n" +
                "                                                <a href=\""+ "/zhifou/people/".concat(followersList[i].uid) +"\">\n" +
                "                                                    <div class=\"follow-info-area\">\n" +
                "                                                        <div class=\"follow-info\">\n" +
                "                                                            <div class=\"follow-name\">"+ followersList[i].name +"</div>\n" +
                "                                                            <div class=\"follow-intro\">"+ followersList[i].introduction +"</div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </a>\n" +
                "                                                <input type=\"hidden\" value=\""+ followersList[i].uid +"\">\n" +
                "                                                <div class=\"follow-btn\"><button type=\"button\" class=\"btn btn-info care-btn\">关注Ta</button></div>\n" +
                "                                            </div>\n" +
                "                                        </li></ul>\n" +
                "                                    </div>"
            $(".followed-card").append(html)
        }
    })
}
/* 点击事件-粉丝 */
$("#btn6").click(function () {
    if ($("#btn6").attr("aria-expanded")==="false") {
        getFollowers()
    }
})

/* 点击事件-取消关注 */
$(".cancel-btn").click(function () {
    const fid = $(this).parent().prev().val()
    $.get(pathName.concat("/following/").concat(fid), function (result) {
        getFollowing()
        console.log("发起了取消关注:", result)
    })
})

/* 点击事件-关注Ta*/
$(".care-btn").click(function () {
    const fid = $(this).parent().prev().val()
    $.get(pathName.concat("/followers/").concat(fid), function (result) {
        getFollowers()
        console.log("发起了关注Ta：", result)
    })

})

/* 打印（收藏、提出的问题、回答的问题、关注的问题）列表*/
function printQuestionsList(qid, qContent) {
    var html = ""
    html = "<a href=\""+ "/zhifou/question/".concat(qid) +"\"><p class=\"content-p\">"+ qContent +"</p></a>"
    return html
}

// 打印（回答）
function printAnswerList(qid, aid, aContent) {
    var html = ""
    html = "<a href=\""+ "/zhifou/question/".concat(qid).concat("/answer/").concat(aid) +"\"><p class=\"content-p\">"+ aContent +"</p></a>"
    return html
}

/* 访问他人主页时，去除修改按钮 */
function visitOther() {
    if (suid !== uid) {
        console.log("suid!==uid")
        // 去除编辑按钮
        $("#btn-edit-user").hide()
        // 判断是否已关注
        $.ajax({
            url: "/zhifou/follow/wether",
            async: false,
            type: "post",
            data: { "uid": uid,
                    "suid": suid },
            success: function (result) {
                if (result===true) {
                    toggleCare($("#btn-follow-user"), "已关注")
                }
            }
        })
    }else {
        console.log("suid===uid")
        $("#btn-follow-user").hide()
    }
}

// 点击事件-进入他人主页关注Ta
$("#btn-follow-user").click(function () {
    if($("#btn-follow-user").val()==="关注Ta") {
        $.get("/zhifou/people/".concat(uid).concat("/followers/").concat(suid), function (result) {
            console.log("发起了关注Ta：", result)
            toggleCare($("#btn-follow-user"), "已关注")
        })
    }else {
        $.ajax({
            url: "/zhifou/people/".concat(uid).concat("following").concat(suid),
            async: false,
            success: function (result) {
                console.log("发起了取消关注：", result)
                toggleCare($("#btn-follow-user"), "关注Ta")
            }
        })
    }
})

// 关注后显示已关注
function toggleCare(element, wether) {
    element.val(wether)
}

// visitOther()
$(document).ready(function () {
    // 获取个人主页数据
    getUserInfo()
    // 若非自己的主页，去除修改按钮
    visitOther()
});
