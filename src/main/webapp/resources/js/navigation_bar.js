/**
 * Created by BppleMan on 2017/10/29.
 */
$(document).ready(function () {
    $('.nav.navbar-nav').find('a').each(function () {
        if (this.href == document.location.href) {
            $(this).parent().addClass('active');
        }
    });
});

function createContest(contextPath) {
    $.ajax({
        url : contextPath + "/contest/can_create_contest",
        success : function (data) {
            if (data == "CanNotCreate") {
                alert("非教师账号无法创建教学实验！")
            } else if (data == "CanCreate") {
                document.location.href = contextPath + "/contest/create_contest";
            } else {
                alert("请先登陆");
            }
        }
    });
}

function logout(contextPath) {
    $.ajax({
        url : contextPath + "/user/logout",
        success : function (data) {
            location.reload();
        }
    });
}