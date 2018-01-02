$(function () {
    $("ul.pagination").find("a").each(function () {
        if (this.text != "Prev" && this.text != "Next") {
            if (this.href == document.location.href) {
                $(this).parent().addClass("active");
            }
        }
    })
})