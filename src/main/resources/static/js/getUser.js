var uuid
function getUser() {
    $.ajax({
        url: "/getUser",
        type: "post",
        async: false,
        dataType: "json",
        success: function (user) {
            uuid = user.uid
        }
    })
}
getUser()
console.log(uuid)
sessionStorage.setItem("uuid", uuid)