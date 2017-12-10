function register(contextPath) {
    var form = $("form");
    $.ajax({
       url : contextPath + "/user/register_submit",
       type : "POST",
       data : form.serialize(),
       success : function (data) {
           if (data == "register_success") {
                location.href = contextPath + "/user/register_success";
           } else if (data == "register_exist") {
               bootbox.alert("用户已存在，请直接登录！");
           } else {
               bootbox.alert("系统错误，请稍后重试！");
           }
       },
       error : function (data) {
           bootbox.alert("error : " + data);
       }
    });
}