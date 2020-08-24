// <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
/* session中存储的uid */
const uid = sessionStorage.getItem("uid");

/* 当前url 即zhifou/people/uid */
// const pathName = window.location.pathname;
const pathName = window.location.pathname.concat("/").concat(uid);

/* 个人信息请求，加载页面 */
function getUser() {
    $.get(pathName, function (user) {
        console.log(user);
        /* todo 访问主页，打印从后台获取的用户信息 */
        $("#gender").text(user.gender)
        $("#introduction").text(user.introduction)
        $("#career").text(user.career)
        $("#industry").text(user.industry)
        $("#email").text(user.email)
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
    const user = {
        "uid": uid,
        "gender": $("#exampleFormControlSelect1").val(),
        "introduction": $("#exampleFormControlTextarea1").val(),
        "career": $("#exampleFormControlInput1").val(),
        "industry": $("#exampleFormControlSelect2").val(),
        "email": $("#exampleFormControlInput3").val()
    };
    $.post(pathName, user, function (result) {
        /* todo 重新获取个人信息，加载页面 */
        getUser();
        console.log(result)
    })
}

/* 点击事件-保存 */
$("#btn-confirm-edit").click(function () {
    editUser()
});

/**/

$(document).ready(function () {

});
