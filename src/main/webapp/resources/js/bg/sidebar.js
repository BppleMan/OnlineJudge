$(function () {
    $("ul.list-group").find("a").each(function () {
        var href1 = this.href.split("?")[0]
        var href2 = location.href.split("?")[0]
        if (href1 === href2) {
            $(this).parent().addClass("active")
        }
    });
})