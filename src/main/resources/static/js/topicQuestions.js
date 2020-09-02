function getSearchContent() {
    const pathName = window.location.pathname
    $.get(pathName.concat("/info"), function (questionsList) {
        var html = ""
        $(".question-area").empty()
        for (i=0; i<questionsList.length; i++) {
            html = "<div class=\"card question-card shadow-sm\">\n" +
                "              <a href=\""+ "/zhifou/question/".concat(questionsList[i].id) +"\">\n" +
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