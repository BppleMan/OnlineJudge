/**
 * Created by BppleMan on 2017/10/31.
 */
function login(contextPath) {
    var remember;
    var href = document.location.href;
    if ($("#remember_me").is(":checked"))
        remember = true;
    else remember = false;
    var param = new Object();
    param.username = $("#username").val();
    param.password = $("#password").val();
    param.remember = remember;
    param.href = href;
    $.ajax({
        url : contextPath+"/user/login",
        type : "POST",
        data : param
    }).done(function (loginInfo) {
        if (loginInfo == "login_password_error") {
            bootbox.alert("密码错误");
        } else if (loginInfo == "login_username_un_exist") {
            bootbox.alert("用户名不存在");
        } else {
            location.reload();
        }
    }).fail(function (data) {
        console.log(data);
    });
    return false;
}