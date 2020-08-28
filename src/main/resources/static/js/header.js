$("#search-btn").click(function () {
    const content = $("#search-content").val()
    sessionStorage.setItem("content", content)
    window.location.href = "/zhifou/search"
})