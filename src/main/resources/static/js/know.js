// 获取session中的uid
// var uid



// 获取推荐（首页）列表
function getKnowList() {
    $.ajax({
        url: "/zhifou/know/info",
        async: false,
        success: function (resultMap) {
            const questionList = resultMap.questionList
            const answerCountList = resultMap.answerCountList
            console.log("推荐页数据：", resultMap)
            var html = ""
            $(".question-area").empty()
            for (i=0; i<questionList.length; i++) {
                $.ajax({
                    url: "/zhifou/comment/".concat(questionList[i].comment).concat("/number"),
                    async: false,
                    success: function (commentCount) {
                        html = "<div class=\"card shadow-sm\">\n" +
                            "                            <!-- 卡片导航头 -->\n" +
                            "                            <!-- 第一个模块内容区 -->\n" +
                            "                            <div class=\"card-body\">\n" +
                            "                                <!-- 标题和内容 -->\n" +
                            "                                <div>\n" +
                            "                                    <h5 class=\"card-title\">"+ questionList[i].content +"</h5>\n" +
                            "                                    <p class=\"card-text\">\n" +
                            "                                        "+ questionList[i].introduction +"\n" +
                            "                                    </p>\n" +
                            "                                </div>\n" +
                            "                                <!-- 赞同评论收藏 -->\n" +
                            "                                <div class=\"container-footer\">\n" +
                            "                                    <button type=\"button\" class=\"btn btn-outline-primary agree\"><img src=\"../../img/icons8-smiling-face-with-heart-17.png\" alt=\"\">好问题 <span>"+ questionList[i].agreeCount +"</span></button>\n" +
                            "                                    <input type=\"hidden\" value=\""+ questionList[i].id +"\">\n" +
                            "                                    <div class=\"footer-comment\"><img src=\"../../img/icons8-topic-30.png\" alt=\"\"><span>"+ answerCountList[i] +" </span>&nbsp;条评论</div>\n" +
                            "                                    <div class=\"footer-star\"><img src=\"../../img/icons8-star-25.png\" alt=\"\" class=\"footer-icon\"><span>"+ questionList[i].collectCount +"&nbsp;</span>收藏</div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>"
                        $(".question-area").append(html)
                    }
                })
            }
        }
    })
}


$(document).ready(function () {
    // console.log("know:", uid)
    getKnowList()
})