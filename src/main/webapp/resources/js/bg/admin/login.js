function formValidate() {
    var adminName = $("#adminName").val();
    if (adminName == undefined || adminName == null ||
        $.trim(adminName) == "") {
        bootbox.alert("需要选择一个账户！")
        return false;
    }
    var password = $("input[name='password']").val();
    if (password == undefined || password == null ||
        $.trim(password) == "") {
        bootbox.alert("请输入密码！")
        return false;
    }
    var input = $("<input type='hidden' name='adminName'>").val(adminName);
    $("form").append(input);
    return true;
}