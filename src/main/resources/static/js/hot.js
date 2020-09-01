function getHot(url) {
    $.ajax({
        url: url,
        async: false,
        success: function (questionList) {
            var html = ""
            for (i=0; i<questionList.length; i++) {
                html = "<div class=\"hot-card card shadow-sm\">\n" +
                    "                            <!-- 卡片导航头 -->\n" +
                    "                            <!-- 第一个模块内容区 -->\n" +
                    "                            <div class=\"hot-card-body card-body\">\n" +
                    "                                <!-- 数字、标题和内容 -->\n" +
                    "                                <div class=\"hot-section\">\n" +
                    "                                    <!-- 左 -->\n" +
                    "                                    <div class=\"order-left\">\n" +
                    "                                        <!-- 热榜排序数字 -->\n" +
                    "                                        <div class=\"order-text\">1</div>\n" +
                    "                                    </div>\n" +
                    "                                    <!-- 右 -->\n" +
                    "                                    <div class=\"container-right\">\n" +
                    "                                        <a href=\"#\">\n" +
                    "                                            <!-- 标题 -->\n" +
                    "                                            <h2 class=\"container-tittle-style\">"+ questionList[i].content +"</h2>\n" +
                    "                                            <!-- 热榜话题描述 -->\n" +
                    "                                            <p class=\"container-text\">"+ questionList[i].introduction +"</p>\n" +
                    "                                        </a>\n" +
                    "                                    </div>\n" +
                    "                                    <!-- 图片 -->\n" +
                    "                                    <div class=\"hot-img\">\n" +
                    "                                        <img src=\"../../img/zhoumou.jpg\" alt=\"\">\n" +
                    "                                    </div>\n" +
                    "                                    <!-- 热度 -->\n" +
                    "                                    <div class=\"hot-footer\">\n" +
                    "                                        <!-- 火星图片 -->\n" +
                    "                                        <div class=\"fire-img\"></div>\n" +
                    "                                        <div class=\"hot-value\"><span>"+ questionList[i].viewCount +"</span> 热度</div>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>"
            }
        }
    })
}

// 主站
function showMain() {
    getHot("/zhifou/hot")
}

// 视频
function showVideo() {
    getHot("/zhifou/hot?list=video")
}

// 科学
function showScience() {
    getHot("/zhifou/hot?list=science")
}

// 数码
function showDifital() {
    getHot("/zhifou/hot?list=difital")
}

// 体育
function showSport() {
    getHot("/zhifou/hot?list=sport")
}

// 时尚
function showFashion() {
    getHot("/zhifou/hot?list=fashion")
}

// 影视
function showFile() {
    getHot("/zhifou/hot?list=file")
}

$(document).ready(function () {
    showMain()
})