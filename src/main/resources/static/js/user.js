/* session中存储的uid */
// const uid = sessionStorage.getItem("user");

/* 当前url 即/zhifou/people/uid */
const pathName = window.location.pathname
// 字符串分隔获取uid
const uid = pathName.substring(15)

/* 个人信息请求，加载页面 */
function getUser() {
    $.get(pathName.concat("/info"), function (user) {
        console.log(user);
        console.log("向", pathName.concat("/info"), "发起了get请求")
        /* todo 访问主页，打印从后台获取的用户信息 */
        $("#name").text(user.name)
        $("#gender").text(user.gender)
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
        var html = ""
        for (i=0; i<followingList.length; i++) {

        }
        html = ""
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
    })
}
/* 点击事件-粉丝 */
$("#btn6").click(function () {
    if ($("#btn6").attr("aria-expanded")==="false") {
        getFollowers()
    }
})

$(document).ready(function () {
    getUser()
});
