// 获取主机地址和端口号 如localhost:8080
const host = window.location.host

function getSearchContent() {
    var content = sessionStorage.getItem("content")
    $.post("zhifou/question/search", {"content": content}, function (questionsList) {
        var html = ""
        $(".question-area").empty()
        for (i=0; i<questionsList.length; i++) {
            html = "<div class=\"card question-card shadow-sm\">\n" +
                "              <a href=\""+ host.concat("/zhifou/question/").concat(questionsList[i].id) +"\">\n" +
                "                <div class=\"card-body question-card-body\">\n" +
                "                    <h5 class=\"card-title question-title\">"+ questionsList[i].content +"</h5>\n" +
                "                    <p class=\"question-content\">"+ questionsList[i].introduction +"</p>\n" +
                "                </div>\n" +
                "              </a>\n" +
                "            </div>"
            $(".question-area").append(html)
        }
    })
}

$(document).ready(function () {
    getSearchContent()
})