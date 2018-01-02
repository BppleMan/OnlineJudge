$(function () {
    $("#sidebar").find("li:not(.brand)").find("a").each(function () {
        var href = this.href.split("?")[0];
        var locationHref = location.href.split("?")[0];
        if (href == locationHref) {
            $(this).parent().addClass("active");
        }
    })
})